package com.darkcom.backend.common;

import com.darkcom.backend.exception.BizException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseVo Handle(Exception e) {
        logger.error("异常抛出：message={}", e.getMessage());
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            return ResponseVo.fail(bizException.getCode(), bizException.getMessage());
        }
        return ResponseVo.fail(403, "系统错误");
    }

}
