package com.darkcom.backend.controller;

import com.darkcom.backend.common.ResponseVo;
import com.darkcom.backend.constants.Urls;
import com.darkcom.backend.dto.response.GroupQuotaResponse;
import com.darkcom.backend.exception.BizException;
import com.darkcom.backend.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页相关
 *
 * @author yaojiaoyi
 */
@Slf4j
@Controller
@RequestMapping(value = Urls.IndexUrls.ROOT)
public class IndexController {
    @PostMapping(value = "/dsl/insert")
    @ResponseBody
    public ResponseVo<List<GroupQuotaResponse>> addUser() {

        return new ResponseVo<>();
    }

    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/test")
    public String indexD() {
        return "index";
    }


    @GetMapping(value = "/testa")
    public String indexDd() {
        return "index";
    }

    @ResponseBody
    @PostMapping("/testExp")
    public ResponseVo testexp() {
        throw new BizException(ErrorCodeEnum.DATA_NOT_FOUND);
    }
}
