package com.ufm.account.config;

import com.ufm.common.config.RestConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName Appconfig
 * @Description 程序服务配置
 * @Author 胡蓝天
 * @Date 2019/8/29 3:06 下午
 * @Version 1.0
 */
@Configuration
@Import(value = {RestConfig.class})
public class Appconfig {
}
