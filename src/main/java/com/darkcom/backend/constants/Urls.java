package com.darkcom.backend.constants;

/**
 * Url管理
 *
 * @author yjy
 */
public interface Urls {

    interface User {
        String ROOT = "/v1/user/";
        String USER = "add";
        String USER_INFO = "userInfo";
    }
    interface LonginUrls{
        String ROOT="/login";
        String LOGIN_ACTION="/loginAction";
    }
}
