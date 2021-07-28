package com.yinwei.restfulapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * create by: yinwei
 * description: TODO 定义people实体类
 * create time: 2021/7/14 9:31
 */
@Data
@Entity
@Table(name="people")
public class People implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Column(name = "sex", nullable = true, length = 255)
    private String sex;
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    @Column(name = "birthday", nullable = true, length = 255)
    private String birthday;
    @Column(name = "age", nullable = true, length = 255)
    private int age;
    public People(){

    }
    public People(Long id,String name,String sex,String email,String birthday,int age){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.email=email;
        this.birthday=birthday;
        this.age=age;
    }
}
