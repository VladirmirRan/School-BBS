package com.school.bbs.service.impl;

import com.school.bbs.common.exception.YyghException;
import com.school.bbs.constant.OssConstant;
import com.school.bbs.domain.OssClientDomain;
import com.school.bbs.domain.OssMetaDomain;
import com.school.bbs.mapper.OssMetaDomainMapper;
import com.school.bbs.service.OssClientService;
import com.school.bbs.service.OssService;
import com.school.bbs.service.StorageService;
import com.school.bbs.utils.FileTypeUtil;
import com.school.bbs.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import static com.school.bbs.constant.errorCode.OSSErrorCodeEnum.*;

/**
 * @author why
 * @since 2022/10/08/17:37
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Autowired
    private OssMetaDomainMapper ossMetaDomainMapper;
    @Autowired
    private OssClientService ossClientService;
    @Autowired
    private StorageService storageService;

    private static final Pattern p = Pattern.compile("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
    @Override
    public OssMetaDomain upload(String clientId, MultipartFile multipartFile) {
        OssClientDomain ossClient = ossClientService.getClient(clientId);
        try {
            long size = multipartFile.getSize();
            String mediaType = multipartFile.getContentType();
            String name = multipartFile.getOriginalFilename();
            if (null == mediaType || "null".equals(mediaType) || mediaType.isEmpty()) {
                if (name.endsWith(".html") || name.endsWith(".plain")) {
                    mediaType = "text/" + name.substring(name.lastIndexOf(".") + 1);
                } else if (name.endsWith(".bmp") || name.endsWith(".git") || name.endsWith(".jepg")
                        || name.endsWith(".png") || name.endsWith(".webp") || name.endsWith(".x-icon")) {
                    mediaType = "image/" + name.substring(name.lastIndexOf(".") + 1);
                } else if (name.endsWith(".css")) {
                    mediaType = "text/css";
                } else {
                    mediaType = "application/" + name.substring(name.lastIndexOf(".") + 1);
                }
            }

            String md5 = OssUtils.md5Hex(multipartFile.getBytes());
            // 去除路径名
            if (name.indexOf("\\") >= 0) {
                name = name.substring(name.lastIndexOf("\\") + 1);
            }

            // 文件名长度校验
            if (name.length() > OssConstant.FILE_NAME_MAX_LENGTH) {
                log.error("upload file name is too long. clientId: {}, fileName: {}", clientId, name);
                throw new YyghException(FILE_NAME_TOO_LONG);
            }
            if (name == null || !p.matcher(name).matches()) {
                log.error("文件名称中含有特殊字符，不合法. clientId: {}, fileName: {}", clientId, name);
                throw new YyghException(FILE_NAME_ERROR);
            }
            String fileSuffix = name.substring(name.lastIndexOf(".") + 1);
            log.info("文件信息："+fileSuffix);
            //.git css文件无法判断，暂时忽略
            if (!"git".equalsIgnoreCase(fileSuffix) && !"css".equalsIgnoreCase(fileSuffix)) {
                //根据文件头字节判断文件格式
                byte[] bytes = multipartFile.getBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                boolean flag = FileTypeUtil.fileType(byteArrayInputStream, fileSuffix);
                if (!flag) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    log.error("上传文件格式非法. clientId: {}, fileName: {}", clientId, name);
                    throw new YyghException(FILE_FORMAT_ERROR);
                }
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 1. 上传文件
            String uri = storageService.save(ossClient.getClientId(), multipartFile.getInputStream());

            // 2. 存储Meta
            OssMetaDomain meta = new OssMetaDomain();
            meta.setClientId(clientId);
            meta.setName(name);
            meta.setSize(size);
            meta.setUri(uri);
            meta.setMd5(md5);
            meta.setMediaType(mediaType);

            Date now = new Date();
            meta.setCreateTime(now);
            meta.setUpdateTime(now);

            ossMetaDomainMapper.insert(meta);

            log.info("upload file success. name:{}, size:{}, mediaType:{}", name, size, mediaType);
            return meta;
        } catch (IOException e) {
            log.error("upload file failed. clientId: {}", clientId, e);
            throw new YyghException(UPLOAD_FILE_ERROR);
        }

    }

    @Override
    public OssMetaDomain exchange(Long fileId) {
        return ossMetaDomainMapper.selectById(fileId);
    }
}
