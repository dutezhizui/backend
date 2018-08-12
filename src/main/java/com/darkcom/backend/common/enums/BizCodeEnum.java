package com.darkcom.backend.common.enums;

/**
 *
 */

public enum BizCodeEnum {

    SUCCESS("200", "成功"),
    SYSTEM_ERROR("500", "系统异常"),
    DATA_NOT_FOUND("10000", "未找到数据");
    // 成员变量
    private String code;
    private String msg;

    BizCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static BizCodeEnum of(String code) {
        for (BizCodeEnum bizCodeEnum : BizCodeEnum.values()) {
            if (bizCodeEnum.getCode().equals(code)) {
                return bizCodeEnum;
            }
        }
        return SYSTEM_ERROR;
    }
}
