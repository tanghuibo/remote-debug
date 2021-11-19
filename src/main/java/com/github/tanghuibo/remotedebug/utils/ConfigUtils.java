package com.github.tanghuibo.remotedebug.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.tanghuibo.remotedebug.properties.ConfigProperties;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collections;

/**
 * ConfigUtils
 *
 * @author tanghuibo
 * @date 2021/11/19 14:45
 */
public class ConfigUtils {

    static ObjectMapper yamlMapper = new YAMLMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static ConfigProperties read(Project project) {
        File file = new File(project.getBasePath() + "/rdebug.yml");
        if(!file.exists() || !file.isFile()) {
            return emptyResult();
        }
        final VirtualFile virtualFile = VirtualFileManager.getInstance().findFileByNioPath(file.toPath());
        String yml = FileUtils.toText(virtualFile);
        try {
            return yamlMapper.readValue(yml, ConfigProperties.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return emptyResult();
        }
    }

    @NotNull
    private static ConfigProperties emptyResult() {
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setDebug(Collections.emptyList());
        return configProperties;
    }

    public static void write(Project project) {

    }

    public static void write(Project project, String content) {

    }
}
