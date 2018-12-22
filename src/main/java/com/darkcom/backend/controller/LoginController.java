package com.darkcom.backend.controller;

import com.darkcom.backend.constants.Urls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping(value = Urls.LonginUrls.ROOT)
public class LoginController {
    @GetMapping
    public String loginPage() {
        return "login";
    }
    @PostMapping(value = Urls.LonginUrls.LOGIN_ACTION)
    public String login(){
        return "index";
    }
}
