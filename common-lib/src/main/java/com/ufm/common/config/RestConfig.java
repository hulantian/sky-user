package com.ufm.common.config;

import com.ufm.common.error.ExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;

/**
 * @ClassName RestConfig
 * @Description 微服务配置
 * @Author 胡蓝天
 * @Date 2019/8/29 2:58 下午
 * @Version 1.0
 */
@Configuration
@Import(value = {ExceptionHandler.class,LocalDateTimeSerializerConfig.class})
public class RestConfig  implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
}
