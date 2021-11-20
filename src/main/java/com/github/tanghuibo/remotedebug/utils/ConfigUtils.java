package com.github.tanghuibo.remotedebug.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.tanghuibo.remotedebug.properties.ConfigProperties;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        final VirtualFile virtualFile = getConfigFile(project);
        if(virtualFile == null) {
            return emptyResult();
        }
        String yml = FileUtils.toText(virtualFile);
        try {
            return yamlMapper.readValue(yml, ConfigProperties.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return emptyResult();
        }
    }

    public static VirtualFile getConfigFile(Project project) {
        File file = new File(project.getBasePath() + "/rdebug.yml");
        if(!file.exists() || !file.isFile()) {
            return null;
        }
        return VirtualFileManager.getInstance().findFileByNioPath(file.toPath());
    }

    private static ConfigProperties emptyResult() {
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setDebug(Collections.emptyList());
        return configProperties;
    }

    public static VirtualFile write(Project project) {
        try {
            final String yml = IOUtils.toString(ConfigUtils.class.getResource("/template/rdebug.yml"), StandardCharsets.UTF_8);
            return write(project, yml);
        } catch (IOException e) {
            e.printStackTrace();
            return write(project, "");
        }
    }

    public static VirtualFile write(Project project, String content) {
        VirtualFile configFile = getConfigFile(project);
        if(configFile == null) {
            File file = new File(project.getBasePath());
            try {
                configFile = VirtualFileManager.getInstance().findFileByNioPath(file.toPath()).createChildData(null, "rdebug.yml");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        FileUtils.writeText(configFile, content);
        return configFile;
    }
}
