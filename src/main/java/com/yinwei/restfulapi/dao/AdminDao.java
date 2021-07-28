package com.yinwei.restfulapi.dao;

import com.yinwei.restfulapi.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by: yinwei
 * description: TODO 定义于admin相关的jpa中原本没有的方法
 * create time: 2021/7/27 11:19
 */
@Transactional
@Repository
public interface AdminDao extends JpaRepository<Admin,String> {
    Admin getAdminByUsername(String username);

    List<Admin> findAllByUsername(String username);

}
