package com.darkcom.backend.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.darkcom.backend.common.ResponseVo;
import com.darkcom.backend.config.WxMaConfiguration;
import com.darkcom.backend.constants.Urls;
import com.darkcom.backend.exception.BizException;
import com.darkcom.backend.exception.ErrorCodeEnum;
import com.darkcom.backend.generate.Tables;
import com.darkcom.backend.generate.tables.pojos.UserInfo;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.beanutils.BeanUtils;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理
 *
 * @author yjy
 */
@RestController
@Slf4j
@RequestMapping(value = Urls.User.ROOT)
public class UserInfoController {
    @Autowired
    private DSLContext dslContext;

    @PostMapping(value = Urls.User.LOGIN)
    public ResponseVo login(@PathVariable("appid") String appid, @RequestParam("code") String code) {
        if (StringUtils.isEmpty(code)) {
            throw new BizException(ErrorCodeEnum.JSCODE_NOT_FOUND);
        }
        WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult wxMaJscode2SessionResult = wxMaService.getUserService().getSessionInfo(code);
            wxMaJscode2SessionResult.getOpenid();
            UserInfo userInfo = dslContext.selectFrom(Tables.T_USER_INFO).where(Tables.T_USER_INFO.UNION_ID.equal(wxMaJscode2SessionResult.getOpenid())).fetchOneInto(UserInfo.class);
            if (ObjectUtils.isEmpty(userInfo)) {
                UserInfo userInfoForInsert = new UserInfo();
                userInfoForInsert.setSessionKey(wxMaJscode2SessionResult.getSessionKey());
                userInfoForInsert.setOpenId(wxMaJscode2SessionResult.getOpenid());
                userInfoForInsert.setUnionId(wxMaJscode2SessionResult.getUnionid());
                dslContext.insertInto(Tables.T_USER_INFO).values(userInfoForInsert).execute();
            } else {
                userInfo.setSessionKey(wxMaJscode2SessionResult.getSessionKey());
                dslContext.update(Tables.T_USER_INFO).set(Tables.T_USER_INFO.SESSION_KEY, userInfo.getSessionKey()).where(Tables.T_USER_INFO.ID.equal(userInfo.getId())).execute();
            }
            return ResponseVo.succeed();
        } catch (WxErrorException e) {
            log.error("【登录失败，失败信息：{}】", e);
            throw new BizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @PostMapping(Urls.User.USER_INFO)
    public ResponseVo info(@PathVariable("appid") String appid, String sessionKey,
                           String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new BizException(ErrorCodeEnum.WX_USER_CHECK_ERROR);
        }

        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        Map<String, String> map = new HashMap();
        try {
            BeanUtils.describe(userInfo);
        } catch (IllegalAccessException e) {
            log.error("获取用户信息失败，{}", e);
            throw new BizException(ErrorCodeEnum.SYSTEM_ERROR);
        } catch (InvocationTargetException e) {
            log.error("获取用户信息失败，{}", e);
            throw new BizException(ErrorCodeEnum.SYSTEM_ERROR);
        } catch (NoSuchMethodException e) {
            log.error("获取用户信息失败，{}", e);
            throw new BizException(ErrorCodeEnum.SYSTEM_ERROR);
        }
        dslContext.update(Tables.T_USER_INFO).set(map).where(Tables.T_USER_INFO.OPEN_ID.eq(userInfo.getOpenId()));
        return ResponseVo.succeed(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @PostMapping(Urls.User.GET_USER_PHONE)
    public ResponseVo<Object> getUserPhone(@PathVariable("appid") String appid, String sessionKey, String signature,
                                           String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);

        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            throw new BizException(ErrorCodeEnum.WX_USER_CHECK_ERROR);
        }
        //获取用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        dslContext.update(Tables.T_USER_INFO).set(Tables.T_USER_INFO.PHONE, phoneNoInfo.getPhoneNumber()).where(Tables.T_USER_INFO.OPEN_ID.eq(userInfo.getOpenId())).execute();
        return ResponseVo.succeed();
    }
}
