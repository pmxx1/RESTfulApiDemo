package com.yinwei.restfulapi.service;

import com.yinwei.restfulapi.common.JwtTokenUtil;
import com.yinwei.restfulapi.dao.AdminDao;
import com.yinwei.restfulapi.dao.AdminPermissionRelationDao;
import com.yinwei.restfulapi.entity.Admin;
import com.yinwei.restfulapi.entity.Permission;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * create by: yinwei
 * description: TODO 用户管理service类
 * create time: 2021/7/23 13:13
 */
@Service
@Slf4j
@Transactional
public class AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminCacheService adminCacheService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminPermissionRelationDao adminPermissionRelationDao;



    /**
     * create by: yinwei
     * description: TODO 通过用户名获取用户相关信息
     * create time: 2021/7/23 13:13
     * @Param username
     * @return Admin
     */
    public Admin getAdminByUsername(String username) {
        Admin admin =adminCacheService.getAdmin(username);
        if(admin!=null){
            return admin;
        }
        Admin admin1=adminDao.getAdminByUsername(username);
        if(admin1!=null){
            admin=admin1;
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }
    /**
     * create by: yinwei
     * description: TODO 注册用户
     * create time: 2021/7/23 13:15
     * @Param username，password
     * @return admin
     */
    public Admin register(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setStatus("1");
        //查询是否有相同用户名的用户
        List<Admin> adminList = adminDao.findAllByUsername(username);
        if (adminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(password);
        admin.setPassword(encodePassword);
        adminDao.save(admin);
        return admin;
    }
    /**
     * create by: yinwei
     * description: TODO 用户登陆
     * create time: 2021/7/23 13:15
     * @Param username，password
     * @return token
     */
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    /**
     * create by: yinwei
     * description: TODO 通过id获取权限列表
     * create time: 2021/7/23 13:16
     * @Param adminid
     * @return list<permission>
     */

    public List<Permission> getPermissionList(Long adminId) {
        return adminPermissionRelationDao.getPermissionList(adminId);
    }
    /**
     * create by: yinwei
     * description: TODO 通过id获取用户
     * create time: 2021/7/30 13:43
     * @Param adminId
     * @return admin
     */
    public Admin getItem(Long adminId) {
        return adminDao.findById(adminId);
    }
    /**
     * create by: yinwei
     * description: TODO 刷新token
     * create time: 2021/7/30 13:57
     * @Param token
     * @return token
     */
    public String refreshToken(String token) {
        return jwtTokenUtil.refreshToken(token);
    }
}