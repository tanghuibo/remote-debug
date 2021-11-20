package com.github.tanghuibo.remotedebug.ui;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.ui.action.AbsTaskAction;
import com.github.tanghuibo.remotedebug.ui.action.impl.BrowserAction;
import com.github.tanghuibo.remotedebug.ui.action.impl.DebugAction;
import com.github.tanghuibo.remotedebug.ui.action.impl.EditAction;
import com.github.tanghuibo.remotedebug.ui.action.impl.RefreshAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.project.Project;

import java.util.Arrays;
import java.util.List;

/**
 * MainToolbarView
 *
 * @author tanghuibo
 * @date 2021/11/19 17:05
 */
public class MainToolbarViewBuilder {

    private VmInfoDto vmInfoDto;

    private List<AbsTaskAction> topActionList;
    private List<AbsTaskAction> bottomActionList;

    public MainToolbarViewBuilder(Project project) {

    }

    public ActionToolbarImpl build() {
        DefaultActionGroup framesGroup = new DefaultActionGroup();
        this.topActionList = buildTopAction();
        for (AnAction anAction : topActionList) {
            framesGroup.add(anAction);
        }
        framesGroup.addSeparator();
        this.bottomActionList = buildBottomAction();
        for (AnAction anAction : bottomActionList) {
            framesGroup.add(anAction);
        }
        return (ActionToolbarImpl) ActionManager.getInstance().createActionToolbar("rd-tool-bar", framesGroup, false);
    }

    private List<AbsTaskAction> buildBottomAction() {
        return Arrays.asList(new RefreshAction(), new EditAction());
    }

    private List<AbsTaskAction> buildTopAction() {
        return Arrays.asList(new DebugAction(), new BrowserAction());
    }

    public void onSelect(VmInfoDto vmInfoDto) {
        this.vmInfoDto = vmInfoDto;
        for (AbsTaskAction absTaskAction : topActionList) {
            absTaskAction.update(vmInfoDto);
        }
        for (AbsTaskAction absTaskAction : bottomActionList) {
            absTaskAction.update(vmInfoDto);
        }

    }
}
