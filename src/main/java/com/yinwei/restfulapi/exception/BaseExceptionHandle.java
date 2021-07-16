package com.yinwei.restfulapi.exception;

import com.yinwei.restfulapi.entity.Result;
import com.yinwei.restfulapi.entity.StatusCode;
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
    public Result requestExceptionHandler(Exception e) {
        log.error("-----------error---------");
        return new Result(false,StatusCode.ERROR,e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e ){
        return new Result(false,StatusCode.ERROR,"系统异常");
    }
}
