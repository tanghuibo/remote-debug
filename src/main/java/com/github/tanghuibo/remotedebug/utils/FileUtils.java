package com.github.tanghuibo.remotedebug.utils;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;

/**
 * FileUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 15:10
 */
public class FileUtils {

    public static byte[] toByteArray(VirtualFile virtualFile) {
        if(virtualFile == null) {
            return new byte[0];
        }
        try {
            return VfsUtil.loadBytes(virtualFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String toText(VirtualFile virtualFile) {
        if(virtualFile == null) {
            return "";
        }
        try {
            return VfsUtil.loadText(virtualFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void writeText(VirtualFile virtualFile, String text) {
        if(virtualFile == null) {
            return;
        }
        try {
            VfsUtil.saveText(virtualFile, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
