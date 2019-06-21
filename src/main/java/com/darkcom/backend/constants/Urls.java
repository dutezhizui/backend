package com.darkcom.backend.constants;

/**
 * Url管理
 *
 * @author yjy
 */
public interface Urls {

    interface User {
        String ROOT = "/{appid}/v1/user/";
        String LOGIN = "/login";
        String USER = "add";
        String USER_INFO = "/userInfo";
        String GET_USER_PHONE = "/getPhone";
    }

    interface IndexUrls {
        String ROOT = "/index";
    }
}
