package com.ufm.account.service;

import com.ufm.account.pojo.dto.role.AddResourceToRole;
import com.ufm.account.pojo.dto.role.AddRoleDto;
import com.ufm.account.pojo.dto.role.AddRoleToUserDto;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.pojo.vo.role.RoleVo;

import java.util.List;

/**
 * @ClassName IRoleService
 * @Description
 * @Author 胡蓝天
 * @Date 2019-09-16 13:47
 * @Version 1.0
 */
public interface IRoleService {

    /**
     * 添加角色
     * @Author 胡蓝天
     * @param addRoleDto 角色输入数据对象
     */
    void addRole(AddRoleDto addRoleDto);

    /**
     * 删除角色
     * @Author 胡蓝天
     * @param roleCode 角色代码
     */
    void deleteRole(String roleCode);

    /**
     * 赋予用户角色
     * @Author 胡蓝天
     * @param addRoleToUserDto 添加角色到用户的数据对象
     */
    void addRoleToUser(AddRoleToUserDto addRoleToUserDto);

    /**
     * 获取角色Code列表 - 通过用户ID
     * @Author 胡蓝天
     * @date 2019年09月16日22:43:36
     * @param userId 用户Id
     * @return 角色Code列表
     */
    List<String> getRoleCodeListByUserId(String userId);

    /**
     * 获取角色列表 - 通过用户ID
     * @Author 胡蓝天
     * @param userId 用户Id
     * @return 角色数据列表
     */
    List<RoleVo> getRoleListByUserId(String userId);

    /**
     * 获取用户列表 - 通过角色代码
     * @Author 胡蓝天
     * @param roleCode 角色代码
     * @return 用户数据列表
     */
    List<UserVo> getUserListByRoleCode(String roleCode);

    /**
     * 赋予角色权限
     * @Author 胡蓝天
     * @param addResourceToRole 添加权限到角色的数据对象
     * @return
     */
    void addResourceToRole(AddResourceToRole addResourceToRole);

    /**
     * 获取权限列表 - 通过角色代码
     * @Author 胡蓝天
     * @param roleCode 角色代码
     * @return 权限列表数据
     */
    List<String> getResourceListByRoleCode(String roleCode);
}
