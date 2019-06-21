package com.darkcom.backend.exception;

/**
 *
 */

public enum ErrorCodeEnum {

    SUCCESS(200, "成功"),
    SYSTEM_ERROR(500, "系统异常"),
    DATA_NOT_FOUND(40000, "未找到数据"),
    JSCODE_NOT_FOUND(40001, "未找微信Code"),
    WX_USER_CHECK_ERROR(40002, "用户校验失败");
    // 成员变量
    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCodeEnum of(String code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.getCode().equals(code)) {
                return errorCodeEnum;
            }
        }
        return SYSTEM_ERROR;
    }
}
