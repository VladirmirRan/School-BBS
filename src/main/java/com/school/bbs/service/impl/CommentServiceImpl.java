package com.school.bbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.bbs.common.exception.YyghException;
import com.school.bbs.controller.input.QueryCommentListInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.controller.output.QueryCommentListOutput;
import com.school.bbs.domain.CommentDomain;
import com.school.bbs.domain.UserDomain;
import com.school.bbs.mapper.UserDomainMapper;
import com.school.bbs.service.CommentService;
import com.school.bbs.mapper.CommentDomainMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.school.bbs.constant.errorCode.UserCodeEnum.UUID_NOT_NULL;

/**
* @author 17423
* @description 针对表【comment(评论表)】的数据库操作Service实现
* @createDate 2022-10-10 21:57:49
*/
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDomainMapper, CommentDomain> implements CommentService{

    @Autowired
    private CommentDomainMapper commentMapper;

    @Autowired
    private UserDomainMapper userDomainMapper;

    /**
     * 根据帖子编号分页查询全部评论
     *
     * @param queryCommentListInput QueryCommentListInput 入参
     * @return List<QueryArticleListOutput> 出参
     */
    @Override
    public List<QueryArticleListOutput> queryCommentList(QueryCommentListInput queryCommentListInput) {
        log.info("queryCommentList begin input params:" + queryCommentListInput);
        // 查询对应帖子的根评论
        List<CommentDomain> rootCommentListByArticleId = commentMapper.getRootCommentListByArticleId(queryCommentListInput.getArticleId());
        if (StringUtils.isEmpty(rootCommentListByArticleId)) {
            throw new RuntimeException("暂无评论");
        }
        List<QueryCommentListOutput> listOutputs = new ArrayList<>();
        for (QueryCommentListOutput listOutput : listOutputs) {
            for (CommentDomain commentDomain : rootCommentListByArticleId) {
                listOutput.setCommentId(commentDomain.getCommentId());
                listOutput.setToCommentId(commentDomain.getToCommentId());
                listOutput.setToCommentUserId(commentDomain.getToCommentUserId());
                listOutput.setContent(commentDomain.getContent());
                listOutput.setArticleId(commentDomain.getArticleId());
                listOutput.setCreateBy(commentDomain.getCreateBy());
                listOutput.setCreateTime(commentDomain.getCreateTime());
                UserDomain userDomain = userDomainMapper.getUserListById(commentDomain.getCreateBy());
                listOutput.setAvatar(userDomain.getAvatar());
                listOutput.setName(userDomain.getName());
//            listOutput.setChildren(); TODO:子评论查询
            }
        }







        /*// 查询对应帖子的根评论
        LambdaQueryWrapper<CommentDomain> queryWrapper = new LambdaQueryWrapper<>();
        // 对articleId进行判断
        queryWrapper.eq(CommentDomain::getArticleId, queryCommentListInput.getArticleId());
        // 根评论 rootId 为-1
        queryWrapper.eq(CommentDomain::getRootId, -1);
        // 分页查询
        Page<CommentDomain> page = new Page(queryCommentListInput.getPageNum(), queryCommentListInput.getPageSize());
        page(page , queryWrapper);
        List<CommentDomain> commentDomains = page.getRecords();
        for (CommentDomain commentDomain : commentDomains) {

        }*/
        return null;
    }
}




