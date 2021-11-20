package com.github.tanghuibo.remotedebug.ui.action.impl;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.ui.action.AbsTaskAction;
import com.github.tanghuibo.remotedebug.utils.DebugUtils;
import com.github.tanghuibo.remotedebug.utils.IconManageUtils;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;


/**
 * AbsTaskActionImpl
 *
 * @author tanghuibo
 * @date 2021/11/20 14:39
 */
public class DebugAction extends AbsTaskAction {

    public DebugAction() {
        super(IconManageUtils.DEBUG_ICON, false);
    }

    @Override
    public void onClick(@NotNull AnActionEvent e, VmInfoDto vmInfoDto) {
        DebugUtils.startDebug(e.getProject(), vmInfoDto);
    }

    @Override
    public void onSelect(VmInfoDto vmInfoDto) {
        setEnabled(vmInfoDto != null);
    }
}
