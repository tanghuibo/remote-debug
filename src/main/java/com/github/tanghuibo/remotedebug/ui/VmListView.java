package com.github.tanghuibo.remotedebug.ui;

import com.github.tanghuibo.remotedebug.dto.VmInfoDto;
import com.github.tanghuibo.remotedebug.properties.ConfigDetailProperties;
import com.github.tanghuibo.remotedebug.strategy.ConfigReader;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * VmListView
 *
 * @author tanghuibo
 * @date 2021/11/19 16:55
 */
public class VmListView extends JBList<VmListView.VmInfo>{


    public VmListView(Project project) {
        setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof VmInfo) {
                    Optional.of((VmInfo) value).map(VmInfo::getVmInfoDto).map(VmInfoDto::getIcon).ifPresent(this::setIcon);
                }
                return component;
            }
        });
    }

    public void setListData(List<ConfigDetailProperties> data) {
        setListData(data.stream()
                .map(ConfigReader::convert)
                .filter(Objects::nonNull)
                .map(VmInfo::new)
                .toArray(VmInfo[]::new));
    }

    protected static class VmInfo {
        private final VmInfoDto vmInfoDto;

        public VmInfo(VmInfoDto vmInfoDto) {
            this.vmInfoDto = vmInfoDto;
        }

        public VmInfoDto getVmInfoDto() {
            return vmInfoDto;
        }

        @Override
        public String toString() {
            if(StringUtils.isEmpty(vmInfoDto.getTag())) {
                return vmInfoDto.getName();
            }
            return vmInfoDto.getName() + "(" + vmInfoDto.getTag() + ")";
        }
    }
}
