package com.yinwei.restfulapi.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
/**
 * create by: yinwei
 * description: TODO 用户权限关系实体类
 * create time: 2021/7/23 13:20
 */
@Data
@Entity
@Table(name="adminpermissionrelation")
public class AdminPermissionRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin adminId;
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permissionId;

    private String type;

    private static final Long serialVersionUID = 1L;

}