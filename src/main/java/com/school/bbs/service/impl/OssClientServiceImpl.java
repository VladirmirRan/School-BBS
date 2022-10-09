package com.school.bbs.service.impl;

import com.school.bbs.common.exception.YyghException;
import com.school.bbs.constant.errorCode.OSSErrorCodeEnum;
import com.school.bbs.domain.OssClientDomain;
import com.school.bbs.mapper.OssClientDomainMapper;
import com.school.bbs.service.OssClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.utils.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2022-10-09
 */
@Service
@Slf4j
public class OssClientServiceImpl extends ServiceImpl<OssClientDomainMapper, OssClientDomain> implements OssClientService {

    @Autowired
    private OssClientDomainMapper ossClientDomainMapper;

    @Override
    public OssClientDomain register(String name) {
        log.info("register start param:name:{} ",name);
        OssClientDomain record = new OssClientDomain();
        record.setName(name);
        record.setClientId(OssUtils.getUUID());
        this.save(record);
        log.info("register end result:{} ",record);
        return record;
    }

    @Override
    public OssClientDomain getClient(String clientId) {
        log.info("getClient start param:client:{} ",clientId);
        OssClientDomain ossClient = ossClientDomainMapper.selectByClientId(clientId);
        if (null == ossClient) {
            log.error("clientId: {} not found.",clientId);
            throw new YyghException(OSSErrorCodeEnum.CLIENT_ID_NOT_FOUND);
        }
        log.info("getClient end result:{} ",ossClient);
        return ossClient;

    }
}
