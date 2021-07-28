package com.yinwei.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
/**
 * create by: yinwei
 * description: TODO springboot启动类
 * create time: 2021/7/7 13:39
 */

//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EntityScan( basePackages = {"com.yinwei.restfulapi.entity"})
public class RestfulapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
	}

}
