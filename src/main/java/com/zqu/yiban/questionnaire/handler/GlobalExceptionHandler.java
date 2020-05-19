package com.zqu.yiban.questionnaire.handler;

import com.zqu.yiban.questionnaire.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未知异常处理
     * @param exception 异常
     * @return 异常处理响应
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object unknownExceptionHandler(Exception exception) {
        log.debug(exception.getMessage());
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统繁忙，请稍后重试！", null);
    }
}