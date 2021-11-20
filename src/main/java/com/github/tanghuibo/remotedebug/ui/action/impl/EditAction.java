package com.github.tanghuibo.remotedebug.ui.action.impl;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.ui.action.AbsTaskAction;
import com.github.tanghuibo.remotedebug.utils.ConfigUtils;
import com.github.tanghuibo.remotedebug.utils.IconManageUtils;
import com.intellij.ide.actions.OpenFileAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;


/**
 * EditAction
 *
 * @author tanghuibo
 * @date 2021/11/20 14:59
 */
public class EditAction extends AbsTaskAction {
    public EditAction() {
        super(IconManageUtils.EDIT_ICON, true);
    }

    @Override
    public void onClick(@NotNull AnActionEvent e, VmInfoDto vmInfoDto) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            final Project project = e.getProject();
            VirtualFile virtualFile = ConfigUtils.getConfigFile(project);
            if(virtualFile == null) {
                virtualFile = ConfigUtils.write(project);
            }
            OpenFileAction.openFile(virtualFile, project);
        });

    }

    @Override
    public void onSelect(VmInfoDto vmInfoDto) {

    }
}
