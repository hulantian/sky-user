package com.ufm.account.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ufm.account.entity.UserEntity;
import com.ufm.account.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName UserServiceTest
 * @Description 用户实现类测试
 * @Author 胡蓝天
 * @Date 2019/9/3 3:14 下午
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void insertUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setOrgId("1");
        userEntity.setRootOrgId("1");
        userEntity.setPhone("18982081213");
        userEntity.setEmail("51616256465@qq.com");
        userEntity.setUsername("胡蓝天");
        userMapper.insert(userEntity);
        System.out.println(userEntity.getId());
    }

    @Test
    public void getUser(){
        UserEntity userEntity = userMapper.selectById("1168789503402078210");
        System.out.println(userEntity);
    }

    @Test
    public void getUserList(){
       List<UserEntity> userEntities = userMapper.selectList(Wrappers.lambdaQuery());
        System.out.println(userEntities);
    }

}
