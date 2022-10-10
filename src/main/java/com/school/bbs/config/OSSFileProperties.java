package com.school.bbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author why
 * @since 2022/10/09/15:29
 */

@Component
@ConfigurationProperties(prefix = "oss.file",ignoreUnknownFields = false)
public class OSSFileProperties {
    private Type type;

    private String olddir;

    private String directory;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getOlddir() {
        return olddir;
    }

    public void setOlddir(String olddir) {
        this.olddir = olddir;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public enum Type {
        /**
         * 本地存储
         */
        LOCAL,

        /**
         * 阿里云OSS存储
         */
        CLOUD_OSS,;

        Type() {

        }
    }

}