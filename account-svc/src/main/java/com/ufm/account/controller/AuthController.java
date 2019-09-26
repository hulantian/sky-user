package com.ufm.account.controller;

import com.ufm.account.pojo.dto.auth.LoginWithUserNameAndPasswordDto;
import com.ufm.account.pojo.vo.auth.AuthAccessToken;
import com.ufm.account.service.IAuthService;
import com.ufm.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/9/11 5:19 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    /**
     * 登录（根据用户名密码登录）
     *
     * @param param
     * @return
     * @menu 权限相关接口
     */
    @PostMapping({"/login", "/loginWithUsernameAndPassword"})
    public Result login(@RequestBody @Validated LoginWithUserNameAndPasswordDto param) {

        AuthAccessToken authAccessToken = authService.login(param.getUsername(), param.getPassword(), param.getClientId());
        return Result.success(authAccessToken);
    }



}

