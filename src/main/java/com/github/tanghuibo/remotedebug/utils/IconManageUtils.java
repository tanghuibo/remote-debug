package com.github.tanghuibo.remotedebug.utils;

import com.intellij.ui.IconManager;
import com.intellij.ui.IconWrapperWithToolTip;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

/**
 * IconManageUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 14:39
 */
public class IconManageUtils {


    public static final Icon IP_ICON = buildImageIcon("/icon/ip.png");

    public static final Icon DEBUG_ICON  = IconManager.getInstance().getIcon("actions/startDebugger.svg", IconWrapperWithToolTip.class);

    public static final Icon EDIT_ICON  = IconManager.getInstance().getIcon("actions/edit.svg", IconWrapperWithToolTip.class);

    public static final Icon REFRESH_ICON = IconManager.getInstance().getIcon("actions/refresh.svg", IconWrapperWithToolTip.class);


    private static ImageIcon buildImageIcon(String path) {
        try {
            return new ImageIcon(IOUtils.toByteArray(Objects.requireNonNull(IconManageUtils.class.getResourceAsStream(path))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
