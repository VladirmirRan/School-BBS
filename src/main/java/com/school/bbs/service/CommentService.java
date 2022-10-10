package com.school.bbs.service;

import com.school.bbs.controller.input.QueryCommentListInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.domain.CommentDomain;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author lu.xin
 * @description 针对表【comment(评论表)】的数据库操作Service
 * @createDate 2022-10-10 21:57:49
 */
public interface CommentService extends IService<CommentDomain> {

    /**
     * 根据帖子编号分页查询全部评论
     *
     * @param queryCommentListInput QueryCommentListInput 入参
     * @return List<QueryArticleListOutput> 出参
     */
    List<QueryArticleListOutput> queryCommentList(QueryCommentListInput queryCommentListInput);

}
