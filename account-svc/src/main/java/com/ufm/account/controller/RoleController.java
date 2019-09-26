package com.ufm.account.controller;

import com.ufm.account.pojo.dto.role.*;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.pojo.vo.role.RoleVo;
import com.ufm.account.service.IRoleService;
import com.ufm.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色相关接口
 * @ClassName RoleController
 * @Author 胡蓝天
 * @Date 2019-09-16 12:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 添加角色
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param addRoleDto 添加角色的数据
     * @return success or error
     */
    @PostMapping("v1/addRole")
    public Result addRole(@Validated @RequestBody AddRoleDto addRoleDto) {

        roleService.addRole(addRoleDto);
        return Result.success();
    }

    /**
     * 删除角色
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param deleteRoleDto 删除角色的数据
     * @return success or error
     */
    @PostMapping("v1/deleteRole")
    public Result deleteRole(@Validated @RequestBody DeleteRoleDto deleteRoleDto) {

        roleService.deleteRole(deleteRoleDto.getCode());
        return Result.success();
    }

    /**
     * 给用户添加角色
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param addRoleToUserDto 添加角色到用户的数据
     * @return success or error
     */
    @PostMapping("v1/addRoleToUser")
    public Result addRoleToUser(@Validated @RequestBody AddRoleToUserDto addRoleToUserDto) {

        roleService.addRoleToUser(addRoleToUserDto);
        return Result.success();
    }

    /**
     * 获取角色列表 - 通过用户ID
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param getRoleListByUserIdDto 获取用户角色列表的数据
     * @return 角色数据列表 or error
     */
    @PostMapping("v1/getRoleListByUserId")
    public Result getRoleListByUserId(@Validated @RequestBody GetRoleListByUserIdDto getRoleListByUserIdDto) {

        List<RoleVo> roleList = roleService.getRoleListByUserId(getRoleListByUserIdDto.getUserId());
        return Result.success(roleList);
    }

    /**
     * 获取用户列表 - 通过角色代码
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param getUserListByRoleCodeDto 获取用户列表的数据
     * @return 用户数据列表 or error
     */
    @PostMapping("v1/getUserListByRoleCode")
    public Result getUserListByRoleCode(@Validated @RequestBody GetUserListByRoleCodeDto getUserListByRoleCodeDto) {

        List<UserVo> userList = roleService.getUserListByRoleCode(getUserListByRoleCodeDto.getRoleCode());
        return Result.success(userList);
    }

    /**
     * 为角色添加权限
     * @undone
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param addResourceToRole 添加权限到角色的数据
     * @return success or error
     */
    @PostMapping("v1/addResourceToRole")
    public Result addResourceToRole(@Validated @RequestBody AddResourceToRole addResourceToRole) {

        roleService.addResourceToRole(addResourceToRole);
        return Result.success();
    }

    /**
     * 获取权限列表 - 通过角色代码
     * @undone
     * @Tag 1.0.0
     * @Author 胡蓝天
     * @Date 2019-09-16 12:07
     * @param getResourceListByRoleCodeDto 获取权限列表的数据
     * @return 权限数据列表 or error
     */
    @PostMapping("v1/getResourceListByRoleCode")
    public Result getResourceListByRoleCode(@Validated @RequestBody GetResourceListByRoleCodeDto getResourceListByRoleCodeDto) {

        List<String> resourceList = roleService.getResourceListByRoleCode(getResourceListByRoleCodeDto.getRoleCode());
        return Result.success(resourceList);
    }
}
