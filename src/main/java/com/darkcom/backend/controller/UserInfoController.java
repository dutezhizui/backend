package com.darkcom.backend.controller;

import com.darkcom.backend.common.ResponseVo;
import com.darkcom.backend.constants.Urls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author yjy
 */
@RestController
@Slf4j
@RequestMapping(value = Urls.User.ROOT)
public class UserInfoController {

    @PostMapping(value = Urls.User.LOGIN)
    public ResponseVo login(){

        return ResponseVo.succeed();
    }
}
