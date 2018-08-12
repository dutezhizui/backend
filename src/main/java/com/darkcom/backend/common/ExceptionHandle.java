package com.darkcom.backend.common;

import com.darkcom.backend.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yaojy
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Handle(Exception e) {
        logger.error("异常抛出：message={}", e.getMessage());
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            return Result.fail(businessException.getCode(), businessException.getMessage());
        }
        return Result.fail(403, "系统错误");
    }

}
