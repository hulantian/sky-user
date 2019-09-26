package com.ufm.account.controller;

import com.ufm.account.pojo.dto.system.AddSystemDto;
import com.ufm.account.pojo.dto.system.DeleteSystemDto;
import com.ufm.account.pojo.vo.system.SystemVo;
import com.ufm.account.service.ISystemService;
import com.ufm.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统接口
 * @Author 胡蓝天
 * @date 2019-09-24 15:02
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class SystemController {

    @Autowired
    private ISystemService systemService;

    /**
     * 添加系统信息
     * @Author 胡蓝天
     * @date 2019-09-24 15:03
     */
    @PostMapping("v1/addSystem")
    public Result addSystem(@RequestBody @Validated AddSystemDto addSystemDto) {
        systemService.addSystem(addSystemDto);
        return Result.success();
    }

    /**
     * 删除系统信息
     * @Author 胡蓝天
     * @date 2019-09-24 15:03
     */
    @PostMapping("v1/deleteSystem")
    public Result deleteSystem(@RequestBody @Validated DeleteSystemDto deleteSystemDto) {
        systemService.deleteSystem(deleteSystemDto.getCode());
        return Result.success();
    }

    /**
     * 获取系统信息列表
     * @Author 胡蓝天
     * @date 2019-09-24 15:03
     */
    @PostMapping("v1/getSystemList")
    public Result<List<SystemVo>> getSystemList() {
        List<SystemVo> systemVos = systemService.getSystemList();
        return Result.success(systemVos);
    }

}
