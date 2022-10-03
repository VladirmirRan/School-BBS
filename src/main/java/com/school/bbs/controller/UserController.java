package com.school.bbs.controller;


import com.school.bbs.common.context.ContextHolder;
import com.school.bbs.common.result.ApiResult;
import com.school.bbs.controller.input.EditUserInfoInput;
import com.school.bbs.controller.output.UserInfoOutput;
import com.school.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author why
 * @since 2022-10-03
 */
@ApiResult
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查看用户信息
     *
     * @return UserInfoOutput
     */
    @PostMapping("/getUserInfo")
    private UserInfoOutput getUserInfo() {
        return userService.getUserInfo(ContextHolder.getLoginContext().getId());
    }

    @PostMapping("/editUserInfo")
    private void editUserInfo(@RequestBody EditUserInfoInput input) {
        userService.editUserInfo(ContextHolder.getLoginContext().getId(), ContextHolder.getLoginContext().getRole(), input);
    }

}
