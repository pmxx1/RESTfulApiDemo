package com.yinwei.restfulapi.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * create by: yinwei
 * description: TODO 定义共用的返回结果Result类
 * create time: 2021/7/14 9:32
 */
@Setter
@Getter

public class Result {
    private Boolean flag;
    private Integer code;
    private String message;
    private Object data;
    public Result(){}
    public Result(Boolean flag,Integer code,String message,Object data){
        this.flag=flag;
        this.code=code;
        this.message=message;
        this.data=data;}

    public Result(boolean flag, Integer code, String message){
        this.flag=flag;
        this.code=code;
        this.message=message;
    }

}
