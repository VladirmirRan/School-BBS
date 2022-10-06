package com.school.bbs.converter;

import com.school.bbs.constant.ArticleCategoryEnum;
import com.school.bbs.controller.input.AddArticleInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.domain.ArticleDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author why
 * @since 2022/10/04/22:46
 */
public class ArticleConvert {
    public static ArticleDomain toAddArticle(AddArticleInput articleInput, long userId) {
        ArticleDomain domain = new ArticleDomain();
        domain.setTitle(articleInput.getTitle());
        domain.setContent(articleInput.getContent());
        domain.setSummary(articleInput.getSummary());
        domain.setCategoryId(articleInput.getCategoryEnum().getCode());
        domain.setThumbnail(articleInput.getThumbnail());
        domain.setCreateBy(userId);
        return domain;
    }

    public static List<QueryArticleListOutput> toQueryArticleList(List<ArticleDomain> articleDomains) {
        List<QueryArticleListOutput> outputs = new ArrayList<>(articleDomains.size());
        for (ArticleDomain articleDomain : articleDomains) {
            QueryArticleListOutput output = new QueryArticleListOutput();
            output.setTitle(articleDomain.getTitle());
            output.setSummary(articleDomain.getSummary());
            output.setCategoryEnum(ArticleCategoryEnum.getByCode(articleDomain.getCategoryId()));
            output.setThumbnail(articleDomain.getThumbnail());
            output.setIsTop(articleDomain.getIsTop());
            output.setViewCount(articleDomain.getViewCount());
            outputs.add(output);
        }
        return outputs;
    }
}
