package com.yinwei.restfulapi.exception;

import com.yinwei.restfulapi.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by: yinwei
 * description: TODO 异常时返回结果
 * create time: 2021/7/14 9:33
 *
 * @Param: null
 * @return
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandle {
    @ExceptionHandler(CustomsException.class)
    @ResponseBody
    public CommonResult requestExceptionHandler(Exception e) {
        log.error("-----------error---------");
        return CommonResult.failed(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult error(Exception e ){
        return CommonResult.failed("系统异常");
    }
}