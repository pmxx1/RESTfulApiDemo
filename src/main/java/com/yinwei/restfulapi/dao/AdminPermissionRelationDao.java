package com.yinwei.restfulapi.dao;

import com.yinwei.restfulapi.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
/**
 * create by: yinwei
 * description: TODO 定义admin、permission关系视图的查询
 * create time: 2021/7/26 9:15
 */
public interface AdminPermissionRelationDao extends JpaRepository<Permission,String> {
    /**
     * create by: yinwei
     * description: TODO 查询用户相关权限
     * create time: 2021/7/26 9:16
     * @Param adminId
     * @return List<permission>
     */
    @Query(value = "SELECT id,create_time,name,pid,sort,status,type,value FROM apview where adminid=?1",nativeQuery = true)
    List<Permission> getPermissionList(Long adminId);
}
