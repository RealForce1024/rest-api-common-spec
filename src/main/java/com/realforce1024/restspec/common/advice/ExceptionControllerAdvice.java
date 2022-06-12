package com.realforce1024.restspec.common.advice;

import com.realforce1024.restspec.common.exception.BizException;
import com.realforce1024.restspec.common.enums.ResultCodeEnum;
import com.realforce1024.restspec.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice<T> {
    private static final String REASON = "%s，原因: %s";

    @ExceptionHandler
    public ResultVO<T> handleGlobalException(Exception ex) {
        log.error("兜底异常: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.FAIL,ex.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public ResultVO<T> handleBizException(BizException ex) {
        log.warn("自定义业务异常: {}", ex.getMessage(), ex);
        return ResultVO.fail(ex);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResultVO<T> handleArithmeticException(ArithmeticException ex) {
        log.error("数学异常: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.MATH_ERROR, String.format(REASON, "数学异常", ex.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("请求方法不支持: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.BAD_METHOD, String.format(REASON, "请求方法不支持", ex.getMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultVO<T> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("请求媒体类型不支持: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.BAD_MEDIA, String.format(REASON, "请求媒体不支持", ex.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVO<T> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        log.warn("请求路径不存在: {}", ex.getRequestURL(), ex);
        return ResultVO.fail(ResultCodeEnum.BAD_PATH, String.format(REASON, "请求路径不存在", ex.getRequestURL()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO<T> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("请求body参数不可读: {}", ex.getMessage(), ex);
        return ResultVO.fail(ResultCodeEnum.BAD_BODY, String.format(REASON, "请求body参数不可读", ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVO<T> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("请求参数{}缺失: ", ex.getParameterName(), ex);
        return ResultVO.fail(ResultCodeEnum.BAD_PARAMETER, String.format(REASON, "请求参数缺失", ex.getParameterName()));
    }

}

