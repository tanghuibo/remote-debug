package com.github.tanghuibo.remotedebug.ui.action;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * AbsTaskAction
 *
 * @author tanghuibo
 * @date 2021/11/20 14:35
 */
public abstract class AbsTaskAction extends AnAction {

    private boolean enable;

    private VmInfoDto vmInfoDto;

    public AbsTaskAction(Icon icon, boolean enable) {
        super(icon);
        setEnabled(enable);
    }

    /**
     * 被点击
     * @param e
     * @param vmInfoDto
     */
    public abstract void onClick(@NotNull AnActionEvent e, VmInfoDto vmInfoDto);

    /**
     * 被选中
     * @param vmInfoDto
     */
    public abstract void onSelect(VmInfoDto vmInfoDto);
    /**
     * 被选择
     * @param vmInfoDto
     */
    public void update(VmInfoDto vmInfoDto) {
        this.vmInfoDto = vmInfoDto;
        onSelect(vmInfoDto);

    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(enable);
        super.update(e);
    }

    public void setEnabled(boolean enable) {
        this.enable = enable;
        getTemplatePresentation().setEnabled(enable);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        onClick(e, vmInfoDto);
    }
}
