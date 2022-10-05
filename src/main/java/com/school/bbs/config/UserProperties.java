package com.school.bbs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author why 相关用户配置
 * @since 2022/10/05/22:16
 */
@Data
@Component
@ConfigurationProperties(prefix = "user", ignoreUnknownFields = false)
public class UserProperties {
    /**
     * 多次密码错误锁定时间
     */
    private Integer blockedTime;

    /**
     * 密码错误重试次数
     */
    private Integer allowedPasswordErrors;

}
