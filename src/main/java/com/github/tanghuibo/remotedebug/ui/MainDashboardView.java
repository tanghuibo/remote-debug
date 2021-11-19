package com.github.tanghuibo.remotedebug.ui;

import com.github.tanghuibo.remotedebug.properties.ConfigProperties;
import com.github.tanghuibo.remotedebug.utils.ConfigUtils;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.panels.Wrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Collections;
import java.util.Optional;

/**
 * MainDashboardView
 *
 * @author tanghuibo
 * @date 2021/11/19 15:30
 */
public class MainDashboardView extends JComponent {

    private final Wrapper wrapper;
    private final Project project;
    private VmListView vmListView;
    private MainToolbarViewBuilder mainToolbarViewBuilder;
    public MainDashboardView(Project project) {
        this.project = project;
        this.wrapper = new Wrapper();
        //设置组件
        addView();
        //自适应
        setAdaptive();
        //设置监听
        setListener();
        //data init
        dataInit();
        add(wrapper);
    }

    private void dataInit() {
        ApplicationManager.getApplication().runReadAction(() -> vmListView.setListData(Optional.ofNullable(ConfigUtils.read(this.project)).map(ConfigProperties::getDebug).orElse(Collections.emptyList())));
    }

    private void setListener() {
        vmListView.addListSelectionListener(e -> mainToolbarViewBuilder.onSelect(Optional.of(vmListView.getSelectedValue()).map(VmListView.VmInfo::getVmInfoDto).orElse(null)));
    }

    private void addView() {
        this.mainToolbarViewBuilder = new MainToolbarViewBuilder(project);
        ActionToolbarImpl actionToolbar = mainToolbarViewBuilder.build();
        wrapper.add(actionToolbar, BorderLayout.WEST);
        this.vmListView = new VmListView(project);
        JBScrollPane jbScrollPane = new JBScrollPane(this.vmListView);
        wrapper.add(jbScrollPane);
    }

    private void setAdaptive() {
        wrapper.setSize(getSize());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                wrapper.setSize(getSize());
            }
        });
    }
}
