package com.ufm.account.service;

import com.ufm.account.pojo.dto.system.AddSystemDto;
import com.ufm.account.pojo.vo.system.SystemVo;

import java.util.List;

/**
 *
 * @Author 胡蓝天
 * @date 2019-09-16 15:14
 * @version 1.0
 */
public interface ISystemService {
    /**
     * 添加系统信息
     * @Author 胡蓝天
     * @date 2019-09-24 15:06
     */
    String addSystem(AddSystemDto addSystemDto);

    /**
     * 删除系统信息
     * @Author 胡蓝天
     * @param code 系统编码
     * @date 2019-09-24 15:07
     */
    String deleteSystem(String code);

    /**
     * 获取系统信息列表
     * @Author 胡蓝天
     * @date 2019-09-24 15:07
     * @return 返回当前可用系统信息列表
     */
    List<SystemVo> getSystemList();

    /**
     * 查看某个系统信息是否已经被删除
     * @Author 胡蓝天
     * @date 2019-09-24 15:07
     * @param code 系统编码
     * @return 已经被删除或查不到返回true，否则返回false
     */
    boolean checkSystemDelete(String code);

}
