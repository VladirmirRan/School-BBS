package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.controller.input.AddArticleInput;
import com.school.bbs.controller.input.QueryArticleInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.converter.ArticleConvert;
import com.school.bbs.domain.ArticleDomain;
import com.school.bbs.mapper.ArticleDomainMapper;
import com.school.bbs.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author why
 * @since 2022-10-04
 */
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleDomainMapper, ArticleDomain> implements ArticleService {

    @Autowired
    private ArticleDomainMapper articleDomainMapper;
    @Override
    public void add(AddArticleInput articleInput, long userId) {
        //添加 博客
        ArticleDomain article = ArticleConvert.toAddArticle(articleInput,userId);
        save(article);
    }

    @Override
    public List<QueryArticleListOutput> queryArticleList(QueryArticleInput input) {
        log.info("queryArticleList begin input params:" + input);
        Page<ArticleDomain> page = new Page<>(input.getPageNum(), input.getPageSize());

        List<ArticleDomain> articleDomains = articleDomainMapper.queryArticleList(input);

        return ArticleConvert.toQueryArticleList(articleDomains);
    }
}
