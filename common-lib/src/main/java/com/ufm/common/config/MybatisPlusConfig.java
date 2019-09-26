package com.ufm.common.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 胡蓝天
 * @version V1.0
 * @Package com.ufm.zhian.user.config
 * @Description: Mybatis plus 配置
 * @date 2019/1/7 4:54 PM
 */
@Configuration
@MapperScan("com.ufm.*.mapper")
public class MybatisPlusConfig {

    /**
     * 配置分页拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
