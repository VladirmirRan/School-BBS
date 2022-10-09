package com.school.bbs.service.impl;

import com.school.bbs.service.StorageService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author why
 * @since 2022-10-09
 */
public abstract class AbstractStorage implements StorageService {

    protected static final String DIR_DELIMITER = "/";

    @Override
    public abstract File obtain(String uri) throws IOException;

    @Override
    public abstract String save(String clientId, InputStream inputStream) throws IOException;

    @Override
    public abstract String save(String clientId, String directory, InputStream inputStream) throws IOException;
}
