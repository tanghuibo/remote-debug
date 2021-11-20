package com.github.tanghuibo.remotedebug.ui.action.impl;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.ui.action.AbsTaskAction;
import com.github.tanghuibo.remotedebug.utils.IconManageUtils;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * OpenAction
 *
 * @author tanghuibo
 * @date 2021/11/20 15:31
 */
public class BrowserAction extends AbsTaskAction {
    public BrowserAction() {
        super(IconManageUtils.BROWSER, false);
    }

    @Override
    public void onClick(@NotNull AnActionEvent e, VmInfoDto vmInfoDto) {
        BrowserLauncher.getInstance().open(vmInfoDto.getUrl());
    }

    @Override
    public void onSelect(VmInfoDto vmInfoDto) {
        setEnabled(vmInfoDto != null && StringUtils.isNotEmpty(vmInfoDto.getUrl()));
    }
}
