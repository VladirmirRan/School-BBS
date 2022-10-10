package com.school.bbs.mapper;

import com.school.bbs.controller.output.QueryCommentListOutput;
import com.school.bbs.domain.CommentDomain;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 17423
 * @description 针对表【comment(评论表)】的数据库操作Mapper
 * @createDate 2022-10-10 21:57:49
 * @Entity com.school.bbs.domain.Comment
 */
@Mapper
public interface CommentDomainMapper extends BaseMapper<CommentDomain> {

    /**
     * 查询对应帖子的根评论
     *
     * @param articleId Long 帖子编号
     * @return CommentDomain
     */
    List<CommentDomain> getRootCommentListByArticleId(Long articleId);

}




