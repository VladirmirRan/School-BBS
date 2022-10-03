package com.school.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.bbs.domain.UserDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 用户持久层接口
 * @createDate 2022/10/2 12:17
 * @since 1.0.0
 */
@Mapper
public interface UserDomainMapper extends BaseMapper<UserDomain> {
}
