package com.school.bbs.service;

import com.school.bbs.domain.OssMetaDomain;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author why
 * @since 2022/10/08/17:37
 */
public interface OssService {
    /**
     * 上传文件
     * @param clientId
     * @param value
     * @return
     */
    OssMetaDomain upload(String clientId, MultipartFile value);

    /**
     * 下载文件--获取文件信息
     * @param fileId
     * @return
     */
    OssMetaDomain exchange(Long fileId);
    /**
     * 上窜文件
     * @param clientId
     * @param value
     * @return
     */
    //OSSMetaDomain upload(String clientId, MultipartFile value);
}
