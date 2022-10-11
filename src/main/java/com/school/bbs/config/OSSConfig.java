package com.school.bbs.config;

import com.school.bbs.service.StorageService;
import com.school.bbs.service.impl.OSSCloudStorage;
import com.school.bbs.service.impl.OSSLocalStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author why
 * @since 2022/10/09/16:32
 */
@Configuration
public class OSSConfig {
    @Configuration
    @ConditionalOnMissingBean(EmbeddedMongoProperties.Storage.class)
    @ConditionalOnProperty(prefix = "oss.file", name = "type", havingValue = "CLOUD_OSS")
    public static class OSSCloudConfiguration {

        @Bean
        public StorageService ossCloudStorage(final OSSFileProperties fileProperties,
                                              final OSSCloudProperties cloudProperties) {
            return new OSSCloudStorage(fileProperties, cloudProperties);
        }
    }

    @Configuration
    @ConditionalOnMissingBean(EmbeddedMongoProperties.Storage.class)
    @ConditionalOnProperty(prefix = "oss.file", name = "type", havingValue = "LOCAL")
    public static class OSSLOCALConfiguration {

        @Bean
        public StorageService ossCloudStorage(final OSSFileProperties fileProperties,
                                              final OSSCloudProperties cloudProperties) {
            return new OSSLocalStorage(fileProperties);
        }
    }
}
