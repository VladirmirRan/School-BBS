package com.school.bbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author why
 * @since 2022/10/09/14:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.cloud", ignoreUnknownFields = false)
public class OSSCloudProperties {
    private String endpoint;

    private String accessKeyId;

    private String accessSecret;

    private String bucket;

    private String subdir;

    private String logBucket;

    private String logPrefix;

    private boolean cache;

    private long maxSize;

    private long expireTime;
}
