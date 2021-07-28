package com.yinwei.restfulapi.common;

import lombok.*;

/**
 * create by: yinwei
 * description: TODO 通用返回对象类
 * create time: 2021/7/23 10:43
 * @Param: null
 * @return
 */
@Data
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;
    private long length;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    protected CommonResult(long code, long length, T data) {
        this.code = code;
        this.data = data;
        this.length=length;
    }
   /**
    * create by: yinwei
    * description: TODO 成功返回结果
    * create time: 2021/7/23 10:44
    * @Param: data 获取的数据
    * @return CommonResult
    */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),data);
    }

     /**
      * create by: yinwei
      * description: TODO 成功返回结果
      * create time: 2021/7/23 10:45
      * @Param: data 返回的数据 message 需要返回的信息
      * @return CommonResult
      */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }
    public static <T> CommonResult<T> success(T data, long length) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), length, data);
    }
    /**
    * create by: yinwei
    * description: TODO 失败返回结果
    * create time: 2021/7/23 10:48
    * @Param: errorcode 错误码
    * @return CommonResult
    */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    /**
     * create by: yinwei
     * description: TODO 失败返回信息
     * create time: 2021/7/23 10:50
      * @Param: message 失败提示信息
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * create by: yinwei
     * description: TODO 失败返回信息
     * create time: 2021/7/23 10:51
      * @Param: null
     * @return CommonResult
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * create by: yinwei
     * description: TODO 参数验证失败返回信息
     * create time: 2021/7/23 10:51
      * @Param: null
     * @return
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * create by: yinwei
     * description: TODO 参数验证失败返回的结果
     * create time: 2021/7/23 10:52
      * @Param: message
     * @return CommonResult
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }
    /**
    * create by: yinwei
    * description: TODO 未登录返回结果
    * create time: 2021/7/23 10:53
    * @Param: data
    * @return CommonResult
    */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }
    /**
     * create by: yinwei
     * description: TODO 未授权返回结果
     * create time: 2021/7/23 10:54
      * @Param: data
     * @return CommonResult
     */

    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

}
