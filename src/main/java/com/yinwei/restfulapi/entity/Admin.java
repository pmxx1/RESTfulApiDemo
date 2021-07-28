package com.yinwei.restfulapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
/**
 * create by: yinwei
 * description: TODO admin实体类
 * create time: 2021/7/23 13:18
 */
@Data
@Entity
@Table(name="admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private String status;

    private static final long serialVersionUID = 1L;

}