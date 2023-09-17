package com.qk.chat.common.exception;

import com.qk.chat.common.result.CommonResult;
import com.qk.chat.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * validation参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errorMsg.toString();
        log.info("validation parameters error！The reason is:{}", message);
        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), message.substring(0, message.length() - 1));
    }

    /**
     * validation参数校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public CommonResult bindException(BindException e) {
        StringBuilder errorMsg = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(x -> errorMsg.append(x.getField()).append(x.getDefaultMessage()).append(","));
        String message = errorMsg.toString();
        log.info("validation parameters error！The reason is:{}", message);
        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), message.substring(0, message.length() - 1));
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    public CommonResult exceptionHandler(NullPointerException e) {
        log.error("null point exception！The reason is: ", e);
        return CommonResult.failed(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage());
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult systemExceptionHandler(Exception e) {
        log.error("system exception！The reason is：{}", e.getMessage(), e);
        return CommonResult.failed(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonResult IllegalArgumentException(IllegalArgumentException e) {
        log.error("system exception！The reason is：{}", e.getMessage(), e);
        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(),e.getMessage());
    }

    /**
     * 自定义校验异常（如参数校验等）
     */
    @ExceptionHandler(value = BusinessException.class)
    public CommonResult businessExceptionHandler(BusinessException e) {
        log.info("business exception！The reason is：{}", e.getMessage(), e);
        return CommonResult.failed(e.getCode(), e.getMessage());
    }

    /**
     * http请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult<Void> handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(-1, String.format("不支持'%s'请求", e.getMethod()));
    }

    /**
     * 限流异常
     */
    @ExceptionHandler(value = FrequencyControlException.class)
    public CommonResult frequencyControlExceptionHandler(FrequencyControlException e) {
        log.info("frequencyControl exception！The reason is：{}", e.getMessage(), e);
        return CommonResult.failed(e.getErrorCode(), e.getMessage());
    }
    
//    @ExceptionHandler(value = ValidationException.class)
//    public CommonResult validationExceptionException(ValidationException e){
//        log.info("validation parameters error！The reason is:{}", e.getMessage());
//        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), e.getMessage());
//    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult ConstraintViolationExceptionHandler(ConstraintViolationException e, HttpServletRequest request) {
        log.error("GlobalExceptionHandler ConstraintViolationExceptionHandler exception={}", e.getMessage(), e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessage());
        }
        return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(),msgList.get(0).toString());
    }
}
