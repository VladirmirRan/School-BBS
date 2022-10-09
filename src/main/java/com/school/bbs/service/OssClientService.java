package com.school.bbs.service;

import com.school.bbs.domain.OssClientDomain;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2022-10-09
 */
public interface OssClientService extends IService<OssClientDomain> {

    /**
     * 注册 OSS 服务
     * @param name 客户端名称
     * @return
     */
    OssClientDomain register(String name);



    /**
     * 获取客户端信息
     * @param clientId
     * @return
     */
    OssClientDomain getClient(String clientId);
}
