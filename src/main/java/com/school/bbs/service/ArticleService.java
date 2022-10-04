package com.school.bbs.service;

import com.school.bbs.controller.input.AddArticleInput;
import com.school.bbs.controller.input.QueryArticleInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.domain.ArticleDomain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author why
 * @since 2022-10-04
 */
public interface ArticleService extends IService<ArticleDomain> {

    /**
     * 创建帖子
     *
     * @param article
     * @param userId
     */
    void add(AddArticleInput article, long userId);

    /**
     * 查询文件
     * @param  queryArticleInput QueryArticleInput
     * @return List<QueryArticleListOutput>
     */
    List<QueryArticleListOutput> queryArticleList(QueryArticleInput queryArticleInput);
}
