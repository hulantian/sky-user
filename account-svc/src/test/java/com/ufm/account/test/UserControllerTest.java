package com.ufm.account.test;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserControllerTest
 * @Description 控制器类测试
 * @Author 胡蓝天
 * @Date 2019/9/3 3:54 下午
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserList() throws Exception {
        String uri = "/user/v1/getUserList";
        sendJsonPost(uri,null);
    }


    public  void sendJsonPost(String uri, Object param) throws Exception {
        mockMvc.perform(post(uri) //POST请求地址
                .contentType(MediaType.APPLICATION_JSON)  //请求方式为JSON
                .content(JSONObject.toJSONString(param)) //设置请求参数
        ).andExpect(status().isOk()) //返回状态是200
                .andDo(print()); //打印请求和响应的内容
    }

    public void sendJsonPost(String uri, Object param, HttpHeaders headers) throws Exception {
        mockMvc.perform(post(uri) //POST请求地址
                .contentType(MediaType.APPLICATION_JSON)  //请求方式为JSON
                .content(JSONObject.toJSONString(param)) //设置请求参数
                .headers(headers)
        ).andExpect(status().isOk()) //返回状态是200
                .andDo(print()); //打印请求和响应的内容
    }

}
