package com.school.bbs.controller;


import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.input.AddArticleInput;
import com.school.bbs.controller.input.QueryArticleInput;
import com.school.bbs.controller.output.QueryArticleListOutput;
import com.school.bbs.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author why
 * @since 2022-10-04
 */
@ApiResult
@RestController
@RequestMapping("/article")
@Api("帖子相关请求类")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("新建帖子")
    @PostMapping("/add")
    public void add(@RequestBody AddArticleInput article) {
        articleService.add(article, 1004);
    }

    @ApiOperation("分页查询帖子")
    @GetMapping("/query/articleList")
    public List<QueryArticleListOutput> queryArticleList( QueryArticleInput queryArticleInput) {
        return articleService.queryArticleList(queryArticleInput);

    }

    /*@GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id")Long id){
        ArticleVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }

    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto article){
        articleService.edit(article);
        return ResponseResult.okResult();
    }
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        articleService.removeById(id);
        return ResponseResult.okResult();
    }*/
}
