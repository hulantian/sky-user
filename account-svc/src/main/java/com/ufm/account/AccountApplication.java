package com.ufm.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @ClassName account
 * @Description 账户服务启动类
 * @Author 胡蓝天
 * @Date 2019/8/29 3:03 下午
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.ufm.account.mapper")
@ComponentScan({"com.ufm.account","com.ufm.common.service"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class,args);
    }
}
