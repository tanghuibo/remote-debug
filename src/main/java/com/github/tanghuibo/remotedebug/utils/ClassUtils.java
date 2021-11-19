package com.github.tanghuibo.remotedebug.utils;

import org.objectweb.asm.ClassReader;

/**
 * ClassUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 15:12
 */
public class ClassUtils {

    /**
     * 获取类名
     * @param bytes
     * @return
     */
    public static String getClassName(byte[] bytes) {
        ClassReader classReader = new ClassReader(bytes);
        return classReader.getClassName().replace("/", ".");
    }
}
