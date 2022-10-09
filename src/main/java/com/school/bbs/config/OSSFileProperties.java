package com.school.bbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author why
 * @since 2022/10/09/15:29
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.file",ignoreUnknownFields = false)
public class OSSFileProperties {
    private Type type;

    private String olddir;

    private String directory;


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