package com.github.tanghuibo.remotedebug.utils;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.intellij.debugger.DebuggerManager;
import com.intellij.debugger.engine.DebugProcessImpl;
import com.intellij.debugger.impl.DebuggerManagerImpl;
import com.intellij.debugger.impl.DebuggerSession;
import com.intellij.debugger.jdi.VirtualMachineProxyImpl;
import com.intellij.execution.Executor;
import com.intellij.execution.ProgramRunnerUtil;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.impl.RunnerAndConfigurationSettingsImpl;
import com.intellij.execution.remote.RemoteConfiguration;
import com.intellij.execution.remote.RemoteConfigurationType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.VirtualMachine;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * DebugUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 14:53
 */
public class DebugUtils {

    public static void startDebug(Project project, VmInfoDto vmInfoDto) {
        ConfigurationFactory configurationFactory = RemoteConfigurationType.getInstance();
        RunManager runManager = RunManager.getInstance(project);
        RunnerAndConfigurationSettingsImpl configuration = runManager.getConfigurationSettingsList(RemoteConfigurationType.class)
                .stream()
                .filter(item -> StringUtils.equals(item.getName(), vmInfoDto.getName()))
                .filter(item -> item instanceof RunnerAndConfigurationSettingsImpl)
                .map(item -> (RunnerAndConfigurationSettingsImpl) item)
                .findFirst()
                .orElse(null);

        if(configuration != null) {
            setRemoteConfiguration(configuration.getConfiguration(), vmInfoDto, project);
            runManager.setSelectedConfiguration(configuration);
            executeConfiguration(configuration);
            return;
        }

        configuration = ((RunnerAndConfigurationSettingsImpl) runManager.createConfiguration(vmInfoDto.getName(), configurationFactory));
        RemoteConfiguration remoteConfiguration = new RemoteConfiguration(project, configurationFactory);
        setRemoteConfiguration(remoteConfiguration, vmInfoDto, project);
        configuration.setConfiguration(remoteConfiguration);
        configuration.setName(vmInfoDto.getName());
        runManager.addConfiguration(configuration);
        runManager.setSelectedConfiguration(configuration);
        executeConfiguration(configuration);
    }

    public static void redefineClass(Project project, VirtualFile virtualFile) {
        byte[] bytes = FileUtils.toByteArray(virtualFile);
        String className = ClassUtils.getClassName(bytes);

        DebuggerManager debuggerManager = DebuggerManager.getInstance(project);
        if(debuggerManager instanceof DebuggerManagerImpl) {
            Collection<DebuggerSession> sessions = ((DebuggerManagerImpl) debuggerManager).getSessions();
            if(sessions.size() == 0) {
                NotifyUtils.error("缺少 debug 会话，请建立会话后重试");
                return;
            }
            for (DebuggerSession session : sessions) {
                try {
                    redefineClass(bytes, className, session);
                } catch (Exception e) {
                    NotifyUtils.error(session.getSessionName() + ":替换 class 失败", e);
                }
            }
        } else {
            NotifyUtils.error("获取 DebuggerManagerImpl 失败");
        }
    }

    private static void executeConfiguration(RunnerAndConfigurationSettings configuration) {
        Executor executor = DefaultDebugExecutor.getDebugExecutorInstance();
        if(executor == null) {
            executor = new DefaultDebugExecutor();
        }
        ProgramRunnerUtil.executeConfiguration(configuration, executor);

    }

    private static void setRemoteConfiguration(RunConfiguration runConfiguration, VmInfoDto vmInfoDto, Project project) {
        if(!(runConfiguration instanceof RemoteConfiguration)) {
            NotifyUtils.error("配置失败，无法进行远程配置");
            return;
        }
        RemoteConfiguration remoteConfiguration = (RemoteConfiguration) runConfiguration;
        remoteConfiguration.HOST = vmInfoDto.getHost();
        remoteConfiguration.PORT = vmInfoDto.getPort().toString();

        ModuleManager moduleManager = ModuleManager.getInstance(project);
        Module[] modules = moduleManager.getModules();
        if(modules.length > 0) {
            Module module = modules[0];
            remoteConfiguration.setModule(module);
        }
    }

    private static void redefineClass(byte[] bytes, String className, DebuggerSession session) {
        VirtualMachine virtualMachine = Optional.of(session)
                .map(DebuggerSession::getProcess)
                .map(DebugProcessImpl::getVirtualMachineProxy)
                .map(VirtualMachineProxyImpl::getVirtualMachine)
                .orElse(null);

        if(!virtualMachine.canRedefineClasses()) {
            NotifyUtils.error(session.getSessionName() + ":不允许改 class");
            return;
        }

        Map<ReferenceType, byte[]> redefineMap = new HashMap<>(1);
        List<ReferenceType> referenceTypes = virtualMachine.classesByName(className);

        if(referenceTypes == null || referenceTypes.size() == 0) {
            NotifyUtils.error(session.getSessionName() + ":没有 class" + className);
            return;
        }

        for (ReferenceType referenceType : referenceTypes) {
            redefineMap.put(referenceType, bytes);
        }

        virtualMachine.redefineClasses(redefineMap);
        NotifyUtils.info(session.getSessionName() + ":替换 class " + className + " 成功");
    }
}
