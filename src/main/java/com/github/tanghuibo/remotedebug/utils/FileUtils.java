package com.github.tanghuibo.remotedebug.utils;

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
        try {
            return virtualFile.contentsToByteArray();
        } catch (IOException e) {
            throw new RuntimeException("读取文件失败", e);
        }
    }
}
