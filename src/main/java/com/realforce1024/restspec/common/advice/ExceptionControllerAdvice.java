package com.realforce1024.restspec.common.advice;

import com.realforce1024.restspec.common.enums.ResultCodeEnum;
import com.realforce1024.restspec.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice<T> {
    @ExceptionHandler
    public ResultVO<T> handleGlobalException(Exception ex) {
        log.error("兜底异常: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.FAIL);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResultVO<T> handleArithmeticException(ArithmeticException ex) {
        log.error("数学异常: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.MATH_ERROR, "数学异常，原因:" + ex.getMessage());
    }
}
