package com.school.bbs.service.impl;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import com.school.bbs.config.OSSCloudProperties;
import com.school.bbs.config.OSSFileProperties;
import com.school.bbs.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2022-10-09
 */
@Slf4j
public class OSSCloudStorage extends AbstractCloudStorage {

    private OSS client;

    private final OSSFileProperties fileProperties;

    private final OSSCloudProperties cloudProperties;

    public OSSCloudStorage(OSSFileProperties fileProperties, OSSCloudProperties cloudProperties) {
        this.fileProperties = fileProperties;
        this.cloudProperties = cloudProperties;
    }

    @PostConstruct
    public void init() {
        boolean cache = cloudProperties.isCache();
        String endpoint = cloudProperties.getEndpoint();
        String accessKey = cloudProperties.getAccessKeyId();
        String accessSecret = cloudProperties.getAccessSecret();

         client= new OSSClientBuilder().build(endpoint, accessKey, accessSecret);

        // 开启访问日志
        openLog();

        // 从云端缓存文件到本地
        if (cache) {
            cacheFile();
        }
    }

    private void openLog() {
        String srcBucket = cloudProperties.getBucket();
        String logBucket = cloudProperties.getLogBucket();
        String logPrefix = cloudProperties.getLogPrefix();

        SetBucketLoggingRequest logReq = new SetBucketLoggingRequest(srcBucket);
        logReq.setTargetBucket(logBucket);
        logReq.setTargetPrefix(logPrefix);
        client.setBucketLogging(logReq);
    }

    private void cacheFile() {
        String bucket = cloudProperties.getBucket();
        String subdir = cloudProperties.getSubdir();
        String directory = fileProperties.getDirectory();
        ObjectListing listing = client.listObjects(bucket);
        for (OSSObjectSummary summary : listing.getObjectSummaries()) {
            String key = summary.getKey();
            String uri = key.substring(subdir.length());
            String localPath = directory + uri;

            File file = new File(localPath);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                if (!fileParent.mkdirs()) {
                    log.error("oss create forder err");
                }

            }

            GetObjectRequest getObjectReq = new GetObjectRequest(bucket, key);
            client.getObject(getObjectReq, file);
            log.info("oss cache file success from aliyun. key:{}, localpath:{}", key, localPath);
        }
    }

    @PreDestroy
    public void destroy() {
        client.shutdown();
    }

    @Override
    public File obtain(String uri) throws IOException {
        log.info("file download start param:{}",uri);
        // 本地读取未果，尝试从云端拉取
        String path = fileProperties.getDirectory() + uri;
        File file = cacheFromLocal(path);
        if (file.exists()) {
            return file;
        }
        log.info("---------file download to ali yun ---------");
        String bucket = cloudProperties.getBucket();
        String subdir = cloudProperties.getSubdir();
        OSSObject ossObj = client.getObject(bucket, subdir + uri);
        if (ossObj != null) {
            try (InputStream objContent = ossObj.getObjectContent()) {
                FileUtils.copyInputStreamToFile(objContent, file);
            }
        }
        log.info("file download end result:{}",file.hashCode());
        return file;
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
       log.info("file save start param:clientId{}  ,directory{} ,fileName{}",clientId,directory,fileName);
        // 先存储在本地然后上传到云端
        String uri = OssUtils.getUri(clientId, directory, fileName);
        try {
            String path = fileProperties.getDirectory() + uri;
            File file = cacheToLocal(inputStream, path);
            String subdir = cloudProperties.getSubdir();
            String bucket = cloudProperties.getBucket();

            String key = getSaveKey(subdir, clientId, directory, fileName);
            client.putObject(bucket, key, file);
            log.info("oss file upload to aliyun-oss storage success. clientId:{}, fileName:{}, size:{}", clientId, fileName, file.length());
        } catch (OSSException oe) {
            log.error("caught an OSSException, error message : {}, error code : {}, request ID : {}, host ID : {}",
                    oe.getMessage(),
                    oe.getErrorCode(),
                    oe.getRequestId(),
                    oe.getHostId());
        } catch (ClientException ce) {
            log.error("caught an OSSClient Exception which means the client encountered error message : {}", ce.getMessage());
        }

        return uri;
    }

    @Override
    public String repair(String clientId, String uri, String fileName, InputStream inputStream) throws IOException {
        File file = new File(fileProperties.getDirectory() + uri);
        try {
            String subdir = cloudProperties.getSubdir();
            FileUtils.copyInputStreamToFile(inputStream, file);
            StringBuilder keyBuf = new StringBuilder(subdir + DIR_DELIMITER + clientId);
            String key = keyBuf.append(DIR_DELIMITER).append(fileName).toString();
            String bucket = cloudProperties.getBucket();
            client.putObject(bucket, key, file);
        } catch (OSSException oe) {
            log.error("Caught an OSSException, error message : {}, error code : {}, request ID : {}, host ID : {}",
                    oe.getMessage(),
                    oe.getErrorCode(),
                    oe.getRequestId(),
                    oe.getHostId());
        } catch (ClientException ce) {
            log.error("Caught an OSSClient Exception which means the client encountered error message : {}", ce.getMessage());
        }

        return uri;
    }
}
