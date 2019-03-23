package com.darkcom.backend.controller;

import com.darkcom.backend.constants.Urls;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页相关
 *
 * @author yaojiaoyi
 */
@Slf4j
@Api("首页相关")
@Controller
@RequestMapping(value = Urls.IndexUrls.ROOT)
public class IndexController {

    @GetMapping
    public String index(){
        return "index.html";
    }

    @GetMapping(value = "/test")
    public String indexD(){
        return "index";
    }


    @GetMapping(value = "/testa")
    public String indexDd(){
        return "index";
    }
}
