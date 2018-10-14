package com.darkcom.backend.controller;

import com.alibaba.fastjson.JSON;
import com.darkcom.backend.common.Result;
import com.darkcom.backend.common.enums.BizCodeEnum;
import com.darkcom.backend.constants.Urls;
import com.darkcom.backend.dto.request.UserRequest;
import com.darkcom.backend.dto.response.UserResponse;
import com.darkcom.backend.model.User;
import com.darkcom.backend.service.UserService;
import com.darkcom.backend.utils.AssertUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author yjy
 */
@RestController
@RequestMapping(Urls.User.ROOT)
@Api("用户相关")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping(Urls.User.USER)
    public Result<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        logger.info("[addUser],userRequest={}", JSON.toJSONString(userRequest));
        userService.addUser(userRequest);
        return Result.succeed();
    }

    @GetMapping(Urls.User.USER_INFO)
    public Result<UserResponse> getUserInfo(@RequestParam("account") String account) {
        User user = userService.getUserByAccount(account);
        UserResponse userResponse = new UserResponse();
        AssertUtil.isTrueForRpc(!ObjectUtils.isEmpty(user), BizCodeEnum.DATA_NOT_FOUND);
        BeanUtils.copyProperties(user, userResponse);
        return new Result<>(userResponse);
    }


}
