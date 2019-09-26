package com.ufm.account.service.impl;

import cn.hutool.core.lang.UUID;
import com.ufm.account.constant.CacheKeyConst;
import com.ufm.account.entity.UserEntity;
import com.ufm.account.enums.ErrorCode;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.pojo.vo.auth.AuthAccessToken;
import com.ufm.account.pojo.vo.auth.AuthRefreshToken;
import com.ufm.account.pojo.vo.auth.Authority;
import com.ufm.account.service.IAuthService;
import com.ufm.account.service.IRoleService;
import com.ufm.account.service.IUserService;
import com.ufm.common.constant.GlobalConst;
import com.ufm.common.error.ServiceExcepion;
import com.ufm.common.service.ICacheService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName AuthServiceImpl
 * @Description 权限服务实现类
 * @Author 胡蓝天
 * @Date 2019/9/16 18:13
 * @Version 1.0
 */
@Service
public class AuthServiceImpl implements IAuthService {


    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 登录令牌失效时间，默认24小时
     */
    @Value("${accent.auth.access-token-expire-second:86400}")
    private long accessTokenExpireSeconds;

    /**
     * 刷新令牌失效时间，默认24小时
     */
    @Value("${accent.auth.refresh-token-expire-second:86400}")
    private long refrshTokenExpireSeconds;

    @Override
    public AuthAccessToken login(String username, String password, String clientId) {

        UserEntity userEntity = userService.getUserByUsername(username);
        if (Objects.isNull(userEntity)){
            throw new ServiceExcepion(ErrorCode.AUTH_USER_NOT_FOUND);
        }
        if (Objects.equals(userEntity.getStatus(), GlobalConst.STATUS_STOPPED)){
            throw new ServiceExcepion(ErrorCode.AUTH_USER_STOPED);
        }
        if (!passwordEncoder.matches(password,userEntity.getPassword())){
            throw new ServiceExcepion(ErrorCode.AUTH_USER_PASSWORD_NOT_MATCHED);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userEntity,userVo);

        return loginSuccess(userVo,clientId);
    }


    /**
     * 登录成功，创建令牌信息
     * @param userVo 用户
     * @param clientId 客户端标识
     * @return
     */
    private AuthAccessToken loginSuccess(UserVo userVo,String clientId){

        //判断是否存在权属对象,如果存在则先删除
        Authority authority = getAuthority(userVo.getId(),clientId);
        if (Objects.nonNull(authority)){
            cacheService.deleteCache(cacheService.serializeKey(CacheKeyConst.AUTH_ACCESS_TOKEN,authority.getAccessToken()));
            cacheService.deleteCache(cacheService.serializeKey(CacheKeyConst.AUTH_REFRESH_TOKEN,authority.getRefreshToken()));
            cacheService.deleteCache(cacheService.serializeKey(CacheKeyConst.AUTH_AUTHORITY,authority.getUserVo().getId(),authority.getClientId()));
        }

        String accessToken = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        AuthAccessToken authAccessToken = AuthAccessToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(accessTokenExpireSeconds)
                .clientId(clientId)
                .build();

        AuthRefreshToken authRefreshToken = AuthRefreshToken.builder()
                .refreshToken(refreshToken)
                .expiresIn(refrshTokenExpireSeconds)
                .clientId(clientId)
                .build();

        List<String> roleList = roleService.getRoleCodeListByUserId(userVo.getId());

        authority = Authority.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(refrshTokenExpireSeconds)
                .userVo(userVo)
                .roleList(roleList)
                .clientId(clientId)
                .build();

        cacheService.putCache(cacheService.serializeKey(CacheKeyConst.AUTH_ACCESS_TOKEN,accessToken),authAccessToken,accessTokenExpireSeconds);
        cacheService.putCache(cacheService.serializeKey(CacheKeyConst.AUTH_REFRESH_TOKEN,refreshToken),authRefreshToken,refrshTokenExpireSeconds);
        cacheService.putCache(cacheService.serializeKey(CacheKeyConst.AUTH_AUTHORITY,userVo.getId(),clientId),authority,refrshTokenExpireSeconds);
        return authAccessToken;
    }

    /**
     * 获取权属对象
     * @param userId 用户Id
     * @param clientId 客户端Id
     * @return
     */
    private Authority getAuthority(String userId,String clientId){
        return cacheService.getObectCache(cacheService.serializeKey(CacheKeyConst.AUTH_AUTHORITY,userId,clientId),Authority.class);
    }
}
