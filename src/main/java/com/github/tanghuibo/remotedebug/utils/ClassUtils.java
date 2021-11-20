package com.github.tanghuibo.remotedebug.utils;

import org.apache.commons.lang3.StringUtils;
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

    public static String convertSimpleName(String className) {
        if(StringUtils.isEmpty(className)) {
            return "";
        }
        String[] split = className.split("\\.");
        return split[split.length - 1];
    }
}
