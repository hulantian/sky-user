package com.ufm.account.controller;

import com.ufm.account.entity.UserEntity;
import com.ufm.account.pojo.dto.user.*;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.service.IUserService;
import com.ufm.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @menu 用户
 * @Author 胡蓝天
 * @Date 2019/8/29 3:23 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService userService;

   /**
    * MethodNameDesc 添加用户
    * @Author 胡蓝天
    * @Date 2019/9/23 16:54
    */

    @PostMapping("v1/addUser")
    public Result addUser(@RequestBody @Validated AddUserDto userDto) {
        String userId = userService.addUser(userDto);


        return Result.success("userId", userId);
    }

  /**
   * MethodNameDesc根据用户ID获取用户
   * @Author 胡蓝天
   * @Date 2019/9/23 16:54
   */

    @PostMapping("v1/getUserById")
    public Result<UserVo> getUserById(@RequestBody  GetUserDto getUserDto) {
        UserVo userVo = userService.getUserById(getUserDto.getUserId());
        return Result.success(userVo);
    }

   /**
    * MethodNameDesc 根据用户名获取用户
    * @Author 胡蓝天
    * @Date 2019/9/23 16:54
    */

    @PostMapping("v1/getUserByName")
    public Result<UserEntity> getUserByName(@RequestBody  GetUserDto getUserDto) {
        UserEntity userVo = userService.getUserByUsername(getUserDto.getPhone());
        return Result.success(userVo);
    }
  /**
   * MethodNameDesc 根据机构ID获取用户
   * @Author 胡蓝天
   * @Date 2019/9/23 16:54
   */

    @PostMapping("v1/getUserListByOrgId")
    public Result<List<UserVo>> getUserListByOrgId(@RequestBody  GetUserDto getUserDto) {
        List<UserVo> userVos = userService.getUserListByOrgId(getUserDto.getOrgId());
        return Result.success(userVos);
    }

  /**
   * MethodNameDesc 删除用户
   * @Author 胡蓝天
   * @Date 2019/9/23 16:53
   */

    @PostMapping("v1/deleteUser")
    public Result deleteUser(@RequestBody  DeleteUserDto deleteUserDto){
        String deleteUserId = userService.deleteUser(deleteUserDto);

        return Result.success("userId",deleteUserId);
    }

    /**
     * MethodNameDesc 更新用户
     * @Author 胡蓝天
     * @Date 2019/9/23 16:53
     */

    @PostMapping("v1/updateUser")
    public Result updateUser(@RequestBody @Validated UpdateUserDto updateUserDto){
        String userId = userService.updateUser(updateUserDto);

        return Result.success("userId",userId);
    }

    /**
     * MethodNameDesc 更新用户状态
     * @Author 胡蓝天
     * @Date 2019/9/23 16:53
     */

    @PostMapping("v1/updateUserStatus")
    public Result updateUserStatus(@RequestBody  UpdateUserStatusDto  updateUserStatusDto){
        UserVo userVo = userService.updateUserStatus(updateUserStatusDto.getUserId(),updateUserStatusDto.getStatus());

        return Result.success(userVo);
    }

    /**
     * MethodNameDesc根据批量用户获取用户列表
     * @Author 胡蓝天
     * @Date 2019/9/23 16:54
     */

    @PostMapping("v1/getUserListById")
    public Result<List<UserVo>> getUserListById(@RequestBody @Validated GetUserListDto userIdList) {
        List<UserVo> userVos = userService.getUserListById(userIdList.getUserId());
        return Result.success(userVos);
    }
}
