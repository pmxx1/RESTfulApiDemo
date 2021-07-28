package com.yinwei.restfulapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * create by: yinwei
 * description: TODO 权限实体类
 * create time: 2021/7/23 13:20
 */
@Data
@Entity
@Table(name="permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pid;

    private String name;

    private String value;


    private String type;

    private String status;

    private Date createTime;

    private String sort;

    private static final Long serialVersionUID = 1L;
}