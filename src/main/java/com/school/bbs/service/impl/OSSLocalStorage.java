package com.school.bbs.service.impl;

import com.school.bbs.config.OSSFileProperties;
import com.school.bbs.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author why
 * @since 2022-10-09
 */
@Slf4j
public class OSSLocalStorage extends AbstractStorage {


    private final OSSFileProperties properties;


    public OSSLocalStorage(OSSFileProperties properties) {
        this.properties = properties;
    }

    @Override
    public File obtain(String uri) throws IOException {
        return new File(properties.getDirectory() + uri);
    }

    @Override
    public String save(String clientId, InputStream inputStream) throws IOException {
        return save(clientId, null, inputStream);
    }

    @Override
    public String save(String clientId, String directory, InputStream inputStream) throws IOException {
        String fileName = OssUtils.getUUID();
        return save(clientId, directory, fileName, inputStream);
    }

    @Override
    public String save(String clientId, String directory, String fileName, InputStream inputStream) throws IOException {
        String uri = OssUtils.getUri(clientId, directory, fileName);
        String filePath = properties.getDirectory() + uri;
        File file = new File(filePath);
        FileUtils.copyInputStreamToFile(inputStream, file);

        log.info("oss file upload to local storage success. clientId:{}, fileName:{}, size:{}", clientId, fileName, file.length());
        return uri;
    }

    @Override
    public String repair(String clientId, String uri, String fileName, InputStream inputStream) throws IOException {
        String filePath = properties.getDirectory() + uri;
        File file = new File(filePath);
        FileUtils.copyInputStreamToFile(inputStream, file);
        return uri;
    }
}
