package com.school.bbs.controller;

import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.input.QueryArticleInput;
import com.school.bbs.controller.input.QueryCommentListInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lu.xin
 * @version 1.0.0
 * @description 评论控制类
 * @createDate 2022/10/10 22:03
 * @since 1.0.0
 */
@ApiResult
@RestController
@RequestMapping("/comment")
@Api("评论相关请求类")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 根据帖子编号分页查询全部评论
     *
     * @param queryCommentListInput QueryCommentListInput 入参
     * @return List<QueryArticleListOutput> 出参
     */
    @ApiOperation("根据帖子编号分页查询全部评论")
    @GetMapping("/commentList")
    public List<QueryArticleListOutput> queryCommentList(QueryCommentListInput queryCommentListInput) {
        return commentService.queryCommentList(queryCommentListInput);

    }

}
