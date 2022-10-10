package com.school.bbs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author why
 * @since 2022-10-09
 */
@Slf4j
public abstract class AbstractCloudStorage extends AbstractStorage {

    /**
     * 从云端获取文件时先从本地获取
     *
     * @param path path
     * @return File
     */
    protected File cacheFromLocal(String path) {
        return new File(path);
    }

    /**
     * 文件上传到云端时先保存在本地
     *
     * @param inputStream inputStream
     * @param path        path
     * @return File
     * @throws IOException IOException
     */
    protected File cacheToLocal(InputStream inputStream, String path) throws IOException {
        log.info("cacheToLocal start params:path{}",path);
        File file = new File(path);

        if (file.exists()) {
            log.error("abstract cloud storage cache file to local error, file already exist. path:{}", path);
            return file;
        }

        FileUtils.copyInputStreamToFile(inputStream, file);
        return file;
    }

    /**
     * 获取云端对象保存的key
     *
     * @param parentDir parentDir
     * @param clientId  clientId
     * @param customDir customDir
     * @param fileName  fileName
     * @return String
     */
    protected String getSaveKey(String parentDir, String clientId, String customDir, String fileName) {
        StringBuilder keyBuf = new StringBuilder(parentDir + DIR_DELIMITER + clientId);
        if (!StringUtils.isEmpty(customDir)) {
            keyBuf.append(DIR_DELIMITER).append(customDir);
        }
        return keyBuf.append(DIR_DELIMITER).append(fileName).toString();
    }

    @Override
    public String repair(String clientId, String uri, String fileName, InputStream inputStream) throws IOException {
        return null;
    }
}
