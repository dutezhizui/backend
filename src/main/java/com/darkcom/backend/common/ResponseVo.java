package com.darkcom.backend.common;


import java.io.Serializable;

/**
 * @param <T>
 * @author yaojy
 */
public class ResponseVo<T> implements Serializable {
    public static final Integer OK = 200;
    public static final Integer ACCEPT = 202;
    public static final Integer BAD_REQUEST = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer FORBIDDEN = 403;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public ResponseVo() {
        this(OK);
    }

    public ResponseVo(Integer code) {
        this(code, (String) null);
    }

    public ResponseVo(Integer code, String message) {
        this(code, message, null);
    }

    public ResponseVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseVo(T payload) {
        this(OK, (String) null, payload);
    }

    public static <T> ResponseVo<T> succeed() {
        return new ResponseVo();
    }

    public static <T> ResponseVo<T> succeed(T payload) {
        return new ResponseVo(payload);
    }

    public static <T> ResponseVo<T> fail(Integer code) {
        return new ResponseVo(code);
    }

    public static <T> ResponseVo<T> fail(Integer code, String message) {
        return new ResponseVo(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVo{code=" + this.code + ", message='" + this.message + '\'' + ", data=" + this.data + '}';
    }
}
