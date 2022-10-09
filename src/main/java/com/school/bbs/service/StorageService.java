package com.school.bbs.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author why
 * @since 2022/10/09/14:13
 */
public interface StorageService {

    /**
     * 获取对象
     * @param uri
     * @return
     */
    File obtain(String uri) throws IOException;

    /**
     * 存储对象
     * @param clientId
     * @param inputStream
     * @return
     * @throws IOException
     */
    String save(String clientId, InputStream inputStream) throws IOException;

    /**
     * 存储对象
     * @param clientId
     * @param directory
     * @param inputStream
     * @return
     */
    String save(String clientId, String directory, InputStream inputStream) throws IOException;

    /**
     * 存储对象
     * @param clientId
     * @param directory
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    String save(String clientId, String directory, String fileName, InputStream inputStream) throws IOException;

    /**
     * 文件修复
     * @param clientId
     * @param uri
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    String repair(String clientId, String uri, String fileName, InputStream inputStream) throws  IOException;
}
