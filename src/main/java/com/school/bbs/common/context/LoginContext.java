package com.school.bbs.common.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author why
 * @date 2022/10/03/10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginContext {
    /**
     * uuid
     */
    private String uuid;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 密钥
     */
    private String privateKey;
}
