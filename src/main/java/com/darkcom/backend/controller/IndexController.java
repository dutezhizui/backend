package com.darkcom.backend.controller;

import com.darkcom.backend.common.ResponseVo;
import com.darkcom.backend.constants.Urls;
import com.darkcom.backend.dto.response.GroupQuotaResponse;
import com.darkcom.backend.exception.BizException;
import com.darkcom.backend.exception.ErrorCodeEnum;
import com.darkcom.backend.generate.tables.records.GroupQuotaRecord;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    private DSLContext dslContext;
    com.darkcom.backend.generate.tables.GroupQuota gq = com.darkcom.backend.generate.tables.GroupQuota.T_GROUP_QUOTA.as("gq");

    @PostMapping(value = "/dsl/insert")
    @ResponseBody
    public ResponseVo<List<GroupQuotaResponse>> addUser() {
        List<GroupQuotaRecord> list=new ArrayList<>();
        GroupQuotaRecord groupQuotaRecord=new GroupQuotaRecord();
        groupQuotaRecord.setGroupId("C");
        groupQuotaRecord.setQuotaBalance(22);
        groupQuotaRecord.setCreatedTime(LocalDateTime.now());
        GroupQuotaRecord groupQuotaRecord1=new GroupQuotaRecord();
        groupQuotaRecord1.setGroupId("D");
        groupQuotaRecord1.setQuotaBalance(22);
        groupQuotaRecord1.setCreatedTime(LocalDateTime.now());
        list.add(groupQuotaRecord);
        list.add(groupQuotaRecord1);
        int[] count=dslContext.batchInsert(list).execute();
        List<GroupQuotaResponse> result = dslContext.selectFrom(gq).fetchInto(GroupQuotaResponse.class);
        return new ResponseVo<>(result);
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
    public ResponseVo testexp(){
        throw new BizException(ErrorCodeEnum.DATA_NOT_FOUND);
    }
}
