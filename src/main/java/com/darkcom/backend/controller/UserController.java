package com.darkcom.backend.controller;

import com.darkcom.backend.constants.Urls;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author yjy
 */
@RestController
@RequestMapping(Urls.User.ROOT)
@Api("用户相关")
public class UserController {

}
