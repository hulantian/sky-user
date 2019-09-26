package com.ufm.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ufm.account.entity.UserEntity;
import com.ufm.account.enums.ErrorCode;
import com.ufm.account.mapper.UserMapper;
import com.ufm.account.pojo.dto.user.AddUserDto;
import com.ufm.account.pojo.dto.user.DeleteUserDto;
import com.ufm.account.pojo.dto.user.UpdateUserDto;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.service.IUserService;
import com.ufm.common.constant.GlobalConst;
import com.ufm.common.error.ServiceExcepion;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务方法实现类
 * @Author 胡蓝天
 * @Date 2019/8/29 3:23 下午
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public String addUser(AddUserDto userDto) {
        //判断手机号已存在且未删除
        LambdaQueryWrapper<UserEntity> phoneWrapper = new LambdaQueryWrapper<>();
        phoneWrapper.eq(UserEntity::getPhone, userDto.getPhone());
        phoneWrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        if (userMapper.selectOne(phoneWrapper) != null) {
            throw new ServiceExcepion(ErrorCode.USERPHONE_EXIST);
        }

        //判断用户名已存在且未删除
        LambdaQueryWrapper<UserEntity> usernameWrapper = new LambdaQueryWrapper<>();
        usernameWrapper.eq(UserEntity::getUsername, userDto.getUsername());
        usernameWrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        if (userMapper.selectOne(usernameWrapper) != null) {
            throw new ServiceExcepion(ErrorCode.USERNAMWE_XIST);
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userMapper.insert(userEntity);
        return userEntity.getId();
    }

    @Override
    public UserVo getUserById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
//        wrapper.select(UserEntity::getUsername,UserEntity::getPhone);
        wrapper.eq(UserEntity::getId, userId);
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        UserEntity userEntity = userMapper.selectOne(wrapper);
        //判断用户是否存在
        if (userEntity == null) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }
        return covert(userEntity);
    }

    @Override
    public List<UserVo> getUserListByOrgId(String OrgId) {
        if (StringUtils.isEmpty(OrgId)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getOrgId, OrgId);
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<UserEntity> userEntityList = userMapper.selectList(wrapper);
        List<UserVo> userVos = userEntityList
                .stream()
                .map(this::covert).collect(Collectors.toList());
        return userVos;
    }

    @Override
    @Transactional
    public String updateUser(UpdateUserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        //判断用户是否存在且未被删除
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getId, userDto.getUserId());
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        int counts = userMapper.update(userEntity, wrapper);
        if (counts < 1) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }
        return userDto.getUserId();
    }

    @Override
    @Transactional
    public String deleteUser(DeleteUserDto deleteUserDto) {
        if (StringUtils.isEmpty(deleteUserDto.getPhone()) && StringUtils.isEmpty(deleteUserDto.getUserId())) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(deleteUserDto, userEntity);
        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<>();
        if (deleteUserDto.getUserId() != null) {
            wrapper.eq(UserEntity::getId, deleteUserDto.getUserId());
        } else if (deleteUserDto.getPhone() != null) {
            wrapper.eq(UserEntity::getPhone, deleteUserDto.getPhone());
        }
        //判断用户是否存在且未被删除
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        UserEntity entity = userMapper.selectOne(wrapper);
        if (entity == null) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }
        entity.setIsDelete(GlobalConst.IS_DELETED);
        //todo 删除用户角色

        userMapper.update(entity, wrapper);
        return null;
    }

    @Override
    @Transactional
    public UserVo updateUserStatus(String userId, int status) {
        if (StringUtils.isEmpty(userId)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        LambdaUpdateWrapper<UserEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserEntity::getId, userId);
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus(status);
        int updateCounts = userMapper.update(userEntity, wrapper);
        if (updateCounts < 1) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }
        UserVo userVo = getUserById(userId);
        return userVo;
    }

//ddgg
    @Override
    public List<UserVo> getUserListById(List<String> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        List<UserEntity> userVoList = userMapper.selectBatchIds(userIdList);
        List<UserVo> userVos = userVoList
                .stream()
                .map(this::covert).collect(Collectors.toList());
        return userVos;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, username);
        wrapper.eq(UserEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<UserEntity> entity = userMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(entity)) {
            throw new ServiceExcepion(ErrorCode.USER_ERROR_PARAM);
        }
        return entity.get(0);
    }

    /**
     * 实体转换为传输对象
     *
     * @param userEntity
     * @return
     */
    private UserVo covert(UserEntity userEntity) {
        if (Objects.nonNull(userEntity)) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(userEntity, userVo);
            return userVo;
        } else {
            return null;
        }
    }

}
