package com.github.tanghuibo.remotedebug.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * MainToolWindowFactory
 *
 * @author tanghuibo
 * @date 2021/11/19 14:19
 */
public class MainToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        JComponent jComponent = new JButton("test");
        Content content = contentFactory.createContent(jComponent, "Dashboard", false);
        toolWindow.getContentManager().addContent(content);
    }
}
