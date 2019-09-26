package com.ufm.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ufm.account.entity.RoleEntity;
import com.ufm.account.entity.RoleResourceEntity;
import com.ufm.account.entity.UserRoleEntity;
import com.ufm.account.enums.ErrorCode;
import com.ufm.account.mapper.RoleMapper;
import com.ufm.account.mapper.RoleResourceMapper;
import com.ufm.account.mapper.UserRoleMapper;
import com.ufm.account.pojo.dto.role.AddResourceToRole;
import com.ufm.account.pojo.dto.role.AddRoleDto;
import com.ufm.account.pojo.dto.role.AddRoleToUserDto;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.pojo.vo.role.RoleVo;
import com.ufm.account.service.IRoleService;
import com.ufm.common.code.CodeConstant;
import com.ufm.common.constant.GlobalConst;
import com.ufm.common.error.ServiceExcepion;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description 角色服务方法实现类
 * @Author 胡蓝天
 * @Date 2019-09-16 14:13
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SystemServiceImpl systemService;

    @Override
    public void addRole(AddRoleDto addRoleDto) {

        // 判断角色code是否已经存在
        if (checkRoleIfExists(addRoleDto.getCode())) {
            throw new ServiceExcepion(ErrorCode.ROLE_EXISTS);
        }

        // 判断对应的系统编码是否存在
        if (!checkSysIfExists(addRoleDto.getSysCode())) {
            throw new ServiceExcepion(CodeConstant.SYSTEM_NOT_EXISTS);
        }

        RoleEntity entity = new RoleEntity();
        BeanUtils.copyProperties(addRoleDto, entity);
        roleMapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(String roleCode) {

        LambdaUpdateWrapper<RoleEntity> roleWrapper = new LambdaUpdateWrapper<>();
        roleWrapper.eq(RoleEntity::getCode, roleCode)
                    .eq(RoleEntity::getIsDelete, GlobalConst.NON_DELETED)
                    .set(RoleEntity::getIsDelete, GlobalConst.IS_DELETED);

        // 先删角色表
        int num = roleMapper.update(null, roleWrapper);
        if (num == 0) {
            throw new ServiceExcepion(ErrorCode.ROLE_NOT_EXISTS);
        }

        // 删除 用户-角色表
        LambdaUpdateWrapper<UserRoleEntity> userRoleWrapper = new LambdaUpdateWrapper<>();
        userRoleWrapper.eq(UserRoleEntity::getRoleCode, roleCode);
        userRoleMapper.delete(userRoleWrapper);

        // 删除 角色-权限表
        LambdaUpdateWrapper<RoleResourceEntity> roleResWrapper = new LambdaUpdateWrapper<>();
        roleResWrapper.eq(RoleResourceEntity::getRoleCode, roleCode);
        roleResourceMapper.delete(roleResWrapper);
    }

    @Override
    public void addRoleToUser(AddRoleToUserDto addRoleToUserDto) {

        // 检查用户是否存在
        if (userService.getUserById(addRoleToUserDto.getUserId()) == null) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }

        // 检查角色是否存在
        if (!checkRoleIfExists(addRoleToUserDto.getRoleCode())) {
            throw new ServiceExcepion(ErrorCode.ROLE_NOT_EXISTS);
        }

        // 检查用户是否已经存在该角色
        if (checkRoleIfExistsInUser(addRoleToUserDto.getRoleCode(), addRoleToUserDto.getUserId())) {
            throw new ServiceExcepion(ErrorCode.ROLE_EXISTS_IN_USER);
        }

        UserRoleEntity entity = new UserRoleEntity();
        BeanUtils.copyProperties(addRoleToUserDto, entity);
        userRoleMapper.insert(entity);
    }

    @Override
    public List<String> getRoleCodeListByUserId(String userId) {

        // 检查用户是否存在
        if (userService.getUserById(userId) == null) {
            throw new ServiceExcepion(ErrorCode.USER_UNFOUND);
        }

        LambdaQueryWrapper<UserRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleEntity::getUserId, userId);

        List<UserRoleEntity> userRoleEntities = userRoleMapper.selectList(wrapper);
        return userRoleEntities.stream().map(UserRoleEntity::getRoleCode).collect(Collectors.toList());
    }

    @Override
    public List<RoleVo> getRoleListByUserId(String userId) {

        List<String> roleCodeList = getRoleCodeListByUserId(userId);
        if (roleCodeList.isEmpty()) {
            return new ArrayList<RoleVo>();
        }
        else {

            LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(RoleEntity::getCode, roleCodeList);
            wrapper.eq(RoleEntity::getIsDelete, GlobalConst.NON_DELETED);

            List<RoleEntity> roleList = roleMapper.selectList(wrapper);
            return roleList.stream().map(this::convertToRoleVo).collect(Collectors.toList());
        }
    }

    @Override
    public List<UserVo> getUserListByRoleCode(String roleCode) {

        // 检查角色是否存在
        if (!checkRoleIfExists(roleCode)) {
            throw new ServiceExcepion(ErrorCode.ROLE_NOT_EXISTS);
        }

        // 从 用户-角色 表，获取对应关系的用户ID数组
        LambdaQueryWrapper<UserRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRoleEntity::getRoleCode, roleCode);

        List<UserRoleEntity> userRoleEntities = userRoleMapper.selectList(wrapper);
        List<String> userIdList = userRoleEntities.stream().map(UserRoleEntity::getUserId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIdList)) {
            return new ArrayList<UserVo>();
        }

        // 从 用户 表，获取用户数据数组
        return userService.getUserListById(userIdList);
    }

    @Override
    public void addResourceToRole(AddResourceToRole addResourceToRole) {

        // TODO 判断权限是否存在

        // 检查角色是否存在
        if (!checkRoleIfExists(addResourceToRole.getRoleCode())) {
            throw new ServiceExcepion(ErrorCode.ROLE_NOT_EXISTS);
        }

        // 检查角色是否已经有该权限
        if (checkResourceIfExistsInRole(addResourceToRole.getResourceId(), addResourceToRole.getRoleCode())) {
            throw new ServiceExcepion(ErrorCode.ROLE_ALREADY_HAVE_RESOURCE);
        }

        RoleResourceEntity entity = new RoleResourceEntity();
        entity.setResId(addResourceToRole.getResourceId());
        entity.setRoleCode(addResourceToRole.getRoleCode());
        roleResourceMapper.insert(entity);
    }

    @Override
    public List<String> getResourceListByRoleCode(String roleCode) {

        // 检查角色是否存在
        if (!checkRoleIfExists(roleCode)) {
            throw new ServiceExcepion(ErrorCode.ROLE_NOT_EXISTS);
        }

        // 从 角色-权限 表，获取对应关系的用户ID数组
        LambdaQueryWrapper<RoleResourceEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleResourceEntity::getRoleCode, roleCode);

        List<RoleResourceEntity> roleResourceEntities = roleResourceMapper.selectList(wrapper);
        List<String> resIdList = roleResourceEntities.stream().map(RoleResourceEntity::getResId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resIdList)) {
            return null;
        }

        // TODO 从 权限 表，获取权限数据数组


        return resIdList;
    }

    /**
     * 查询对应roleCode的角色是否存在
     * 注意，仅仅对比is_delete = 1的角色
     * @Author 胡蓝天
     * @param roleCode 角色代码
     * @return true-存在 false-不存在
     */
    private boolean checkRoleIfExists(String roleCode) {

        // 仅仅在未删除的角色里查询有没有重复的roleCode
        LambdaUpdateWrapper<RoleEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(RoleEntity::getCode, roleCode);
        wrapper.eq(RoleEntity::getIsDelete, GlobalConst.NON_DELETED);

        RoleEntity entity = roleMapper.selectOne(wrapper);
        return entity != null;
    }

    /**
     * 查询对应编码的系统是否存在
     * @Author 胡蓝天
     * @param sysCode 系统编码
     * @return true-存在 false-不存在
     */
    private boolean checkSysIfExists(String sysCode) {

        return !systemService.checkSystemDelete(sysCode);
    }

    /**
     * 查询对应用户是否已经存在某角色
     * @Author 胡蓝天
     * @param roleCode 角色代码
     * @param userId 用户ID
     * @return true-存在 false-不存在
     */
    private boolean checkRoleIfExistsInUser(String roleCode, String userId) {

        LambdaUpdateWrapper<UserRoleEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserRoleEntity::getRoleCode, roleCode);
        wrapper.eq(UserRoleEntity::getUserId, userId);

        UserRoleEntity entity = userRoleMapper.selectOne(wrapper);
        return entity != null;
    }

    /**
     * 查询对应角色是否已经存在某权限
     * @Author 胡蓝天
     * @param resourceId 权限ID
     * @param roleCode 角色代码
     * @return true-存在 false-不存在
     */
    private boolean checkResourceIfExistsInRole(String resourceId, String roleCode) {

        LambdaUpdateWrapper<RoleResourceEntity> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(RoleResourceEntity::getResId, resourceId);
        wrapper.eq(RoleResourceEntity::getRoleCode, roleCode);

        RoleResourceEntity entity = roleResourceMapper.selectOne(wrapper);
        return entity != null;
    }

    /**
     * 转换角色entity到vo
     * @Author 胡蓝天
     * @param entity 角色entity对象
     * @return 角色vo对象
     */
    private RoleVo convertToRoleVo (RoleEntity entity) {

        if (entity == null) {
            return null;
        }

        RoleVo userVo = new RoleVo();
        BeanUtils.copyProperties(entity, userVo);
        return userVo;
    }
}
