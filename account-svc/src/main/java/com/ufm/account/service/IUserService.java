package com.ufm.account.service;

import com.ufm.account.entity.UserEntity;
import com.ufm.account.pojo.dto.user.*;
import com.ufm.account.pojo.vo.UserVo;

import java.util.List;

/**
 * @ClassName IUserService
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 3:23 下午
 * @Version 1.0
 */
public interface IUserService {

    /**
     * MethodNameDesc 添加用户
     * @Author 胡蓝天
     * @Date 2019/9/23 17:07
     */

    String addUser(AddUserDto userDto);

    /**
     * MethodNameDesc根据用户id查询用户
     * @Author 胡蓝天
     * @Date 2019/9/23 17:18
     */

    UserVo getUserById(String userId);

    /**
     * MethodNameDesc根据机构id查询用户
     *
     * @Author 胡蓝天
     * @Date 2019/9/23 17:19
     */

    List<UserVo> getUserListByOrgId(String OrgId);

    /**
     * 修改用户信息
     * @Author 胡蓝天
     * @Date 2019/9/23 17:19
     */
    String updateUser(UpdateUserDto userDto);

    /**
     * 根据用户名称获取用户信息
     *
     * @Author 胡蓝天
     * @Date 2019/9/23 17:19
     */
    UserEntity getUserByUsername(String username);

    /**
     * MethodNameDesc删除用户
     * @Author 胡蓝天
     * @Date 2019/9/23 17:20
     */

    String deleteUser(DeleteUserDto deleteUserDto);

    /**
     * 更新用户状态
     *
     * @Author 胡蓝天
     * @Date 2019/9/23 17:20
     */
    UserVo updateUserStatus(String userId, int status);

    /**
     * 获取用户信息
     * @Author 胡蓝天
     * @Date 2019/9/23 17:20
     */
    List<UserVo> getUserListById(List<String> userIdList);
}
