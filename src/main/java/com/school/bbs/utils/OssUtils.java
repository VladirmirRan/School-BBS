package com.school.bbs.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.UUID;

/**
 * @author why
 * @since 2022/10/09/10:24
 */
public class OssUtils {

    public static String md5Hex(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getUri(String clientId, String bucket, String fileName) {
        StringBuffer buffer = new StringBuffer(File.separator).append(clientId);
        if (!StringUtils.isEmpty(bucket)) {
            buffer.append(File.separator).append(bucket);
        }

        String uri = buffer.append(File.separator).append(fileName).toString();
        return uri;
    }
}
