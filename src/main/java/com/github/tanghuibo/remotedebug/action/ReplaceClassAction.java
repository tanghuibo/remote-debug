package com.github.tanghuibo.remotedebug.action;

import com.github.tanghuibo.remotedebug.utils.*;
import com.intellij.debugger.engine.DebuggerManagerThreadImpl;
import com.intellij.debugger.impl.PrioritizedTask;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * ReplaceClassAction
 *
 * @author tanghuibo
 * @date 2021/11/20 15:53
 */
public class ReplaceClassAction extends AnAction {

    private final Disposable disposable = Disposer.newDisposable();

    public ReplaceClassAction() {
        super(IconManageUtils.REPLACE_CLASS);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        DebuggerManagerThreadImpl testInstance = DebuggerManagerThreadImpl.createTestInstance(disposable, event.getProject());
        testInstance.invoke(PrioritizedTask.Priority.HIGH, () -> redefineClass(event));

    }

    private void redefineClass(AnActionEvent event) {
        VirtualFile virtualFile = event.getData(CommonDataKeys.VIRTUAL_FILE);
        DebugUtils.redefineClass(event.getProject(), virtualFile);


    }


    @Override
    public void update(AnActionEvent event) {
        event.getPresentation().setVisible(hasClassFile(event));
    }

    @Nullable
    private boolean hasClassFile(AnActionEvent event) {
        String extension = Optional.ofNullable(event.getData(CommonDataKeys.VIRTUAL_FILE)).map(VirtualFile::getExtension).orElse(null);
        return "class".equals(extension);
    }
}
