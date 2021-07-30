package com.yinwei.restfulapi.controller;

import com.yinwei.restfulapi.common.CommonResult;
import com.yinwei.restfulapi.entity.Admin;
import com.yinwei.restfulapi.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * create by: yinwei
 * description: TODO 用户管理contrller
 * create time: 2021/7/23 11:22
  * @Param: null
 * @return
 */
@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    /**
     * create by: yinwei
     * description: TODO 用户注册
     * create time: 2021/7/23 11:24
      * @Param: username password
     * @return CommonResult 用户信息
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Admin> register(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        Admin admin = adminService.register(username,password);
        if (admin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(admin);
    }
    /**
     * create by: yinwei
     * description: TODO 用户登陆
     * create time: 2021/7/23 11:29
      * @Param: null
     * @return CommonResult
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String username,@RequestParam String password,HttpServletRequest request) {
        String token = adminService.login(username,password);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
    /**
     * create by: yinwei
     * description: TODO 刷新token
     * create time: 2021/7/30 13:48
     * @Param
     * @return
     */
    @RequestMapping(value="/refreshToken",method=RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request){
        String token=request.getHeader(tokenHeader);
        String refreshToken=adminService.refreshToken(token);
        if(refreshToken==null){
            return CommonResult.failed("token已过期");
        }
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",refreshToken);
        tokenMap.put("tokenHead",tokenHead);
        return CommonResult.success(tokenMap);
    }
}
