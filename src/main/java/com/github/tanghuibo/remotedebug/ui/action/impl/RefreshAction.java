package com.github.tanghuibo.remotedebug.ui.action.impl;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.ui.MainDashboardView;
import com.github.tanghuibo.remotedebug.ui.action.AbsTaskAction;
import com.github.tanghuibo.remotedebug.utils.IconManageUtils;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * RefreshAction
 *
 * @author tanghuibo
 * @date 2021/11/20 14:50
 */
public class RefreshAction extends AbsTaskAction {
    public RefreshAction() {
        super(IconManageUtils.REFRESH_ICON, true);
    }

    @Override
    public void onClick(@NotNull AnActionEvent e, VmInfoDto vmInfoDto) {
        Optional.ofNullable(MainDashboardView.getByProject(e.getProject())).ifPresent(MainDashboardView::dataInit);
    }

    @Override
    public void onSelect(VmInfoDto vmInfoDto) {

    }
}
