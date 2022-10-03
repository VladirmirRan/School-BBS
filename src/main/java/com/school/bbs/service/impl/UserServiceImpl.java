package com.school.bbs.service.impl;

import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2022-10-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDomainMapper, UserDomain> implements UserService {

}
