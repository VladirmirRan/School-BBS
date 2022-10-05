package com.school.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.school.bbs.controller.input.QueryArticleInput;
import com.school.bbs.domain.ArticleDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author why
 * @since 2022-10-04
 */
@Mapper
public interface ArticleDomainMapper extends BaseMapper<ArticleDomain> {
    /**
     * 分页查询帖子概览
     * @param input
     * @return
     */
    List<ArticleDomain> queryArticleList(QueryArticleInput input);
}
