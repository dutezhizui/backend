package com.darkcom.backend.exception;


import com.darkcom.backend.common.ResponseVo;

/**
 * @author yaojy
 */
public class BizException extends RuntimeException {
    private Integer code = ResponseVo.ACCEPT;

    public BizException() {
        super();
    }

    public BizException(Integer code) {
        super();
        this.code = code;
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.code = errorCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
