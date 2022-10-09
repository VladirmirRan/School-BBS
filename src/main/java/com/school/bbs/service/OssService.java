package com.school.bbs.service;

import com.school.bbs.domain.OssMetaDomain;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author why
 * @since 2022/10/08/17:37
 */
public interface OssService {
    OssMetaDomain upload(String clientId, MultipartFile value);
    /**
     * 上窜文件
     * @param clientId
     * @param value
     * @return
     */
    //OSSMetaDomain upload(String clientId, MultipartFile value);
}
