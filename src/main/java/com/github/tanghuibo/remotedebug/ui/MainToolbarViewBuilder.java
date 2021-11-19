package com.github.tanghuibo.remotedebug.ui;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.List;

/**
 * MainToolbarView
 *
 * @author tanghuibo
 * @date 2021/11/19 17:05
 */
public class MainToolbarViewBuilder {

    public MainToolbarViewBuilder(Project project) {

    }

    public ActionToolbarImpl build() {
        DefaultActionGroup framesGroup = new DefaultActionGroup();
        List<AnAction> topActionList = new ArrayList<>();
        for (AnAction anAction : topActionList) {
            framesGroup.add(anAction);
        }
        framesGroup.addSeparator();
        List<AnAction> bottomActionList = new ArrayList<>();
        for (AnAction anAction : bottomActionList) {
            framesGroup.add(anAction);
        }
        return (ActionToolbarImpl) ActionManager.getInstance().createActionToolbar("rd-tool-bar", framesGroup, false);
    }

    public void onSelect(VmInfoDto vmInfoDto) {

    }
}
