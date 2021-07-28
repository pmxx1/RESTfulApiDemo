package com.yinwei.restfulapi.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * create by: yinwei
 * description: TODO 定义aop类在访问接口时输出日志
 * create time: 2021/7/23 17:14
 */
@Slf4j
@Aspect
@Component
public class AopWebLogAspect {

    private static long beforetime = 0;

    /**定义的是切点
     * 方法名就是切点名
     * 使用execution表达式，定义切点为：controller包中的所有方法
     * 第一个*：选中的连接点的方法返回值可以是任意类型，
     * 如* 改为void意思为选中的连接点的方法返回值为void的类型
     * 第二个*：选中的连接点的方法所在类可以是任意名字
     * 第三个*：选中的连接点的方法名字可以是任意名字
     * ..:表示任意参数
     */
    @Pointcut("execution(public * com.yinwei.restfulapi.controller.*.*(..))")
    public void webLog(){

    }

    /**
     * 定义增强
     * 在切点的什么位置增加新的功能
     * @Before：方法执行前
     * @After方法执行后
     * @Around方法执行前+方法执行后
     * @AfterThrowing：方法抛出异常后
     * @AfterReturning：方法返回后
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //在controller层以外获取当前正在处理的请求，固定格式
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String ip = request.getRemoteAddr();
        String method = request.getMethod();
        String URL = request.getRequestURI().toString();

        String params = null;
        if ("POST".equals(method) || "PUT".equals(method)) {
            //joinPoint 就是targer
            //获取target方法中的所有参数
            //如果target = sendCode 那么args = params
            //如果target = register 那么args = userParam
            Object[] args = joinPoint.getArgs();
            if (args.length > 0){
                for(int i=0;i<args.length;i++){
                    params=params+","+args[i];
                }
            }
        }
        beforetime = System.currentTimeMillis();
        //打印日志
        log.debug("访问ip为"+ip);
        log.debug("调用方法类型为"+method);
        log.debug("路径为"+URL);
        log.debug("参数为"+params);

        }

    @AfterReturning(returning = "msg",pointcut ="execution(public * com.yinwei.restfulapi.controller.*.*(..))")
    public void afterTime(Object msg){
        long betweenTime = System.currentTimeMillis() - beforetime;
        String a=msg.toString();
        log.debug("耗时：{}",betweenTime);
        log.debug("返回结果:"+a);
    }

}
