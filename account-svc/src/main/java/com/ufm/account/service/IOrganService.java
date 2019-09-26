package com.ufm.account.service;

import com.ufm.account.pojo.dto.organ.AddOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganListDto;
import com.ufm.account.pojo.dto.organ.UpdateOrganDto;
import com.ufm.account.pojo.vo.OrganVo;

import java.util.List;

/**
 * @ClassName IOrganService
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/9/11 3:23 下午
 * @Version 1.0
 */
public interface IOrganService {

    /**
     * 新增组织
     * @param organDto
     * @return
     */
    String addOrgan(AddOrganDto organDto);

    /**
     * 删除组织
     * @param organDto
     * @return
     */
    String deleteOrgan(GetOrganDto organDto);

    /**
     * 修改组织
     * @param organDto
     * @return
     */
    String updateOrgan(UpdateOrganDto organDto);

    /**
     * 获取组织列表
     * @return
     */
    List<OrganVo> getOrganList(GetOrganListDto parentOrgan);

    /**
     * 获取组织信息
     * @param organId
     * @return
     */
    OrganVo getOrgan(String organId);

}
