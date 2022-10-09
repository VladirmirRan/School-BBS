package com.school.bbs.mapper;

import com.school.bbs.domain.OssClientDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author why
 * @since 2022-10-09
 */
public interface OssClientDomainMapper extends BaseMapper<OssClientDomain> {

    /**
     * 按 clientId 查询
     * @param clientId
     * @return
     */
    OssClientDomain selectByClientId(String clientId);

    /**
     * 根据用户名获取client
     * @param userName
     * @return
     */
    String selectByUserName(@Param("userName")String userName);
}
