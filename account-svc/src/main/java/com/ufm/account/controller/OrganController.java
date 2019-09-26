package com.ufm.account.controller;

import com.ufm.account.pojo.dto.organ.AddOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganListDto;
import com.ufm.account.pojo.dto.organ.UpdateOrganDto;
import com.ufm.account.pojo.vo.OrganVo;
import com.ufm.account.service.IOrganService;
import com.ufm.common.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织相关接口
 * @ClassName OrganController
 * @Author 胡蓝天
 * @Date 2019/9/11 3:23 下午
 * @Version 1.0
 */
@RestController
@RequestMapping("/organ/")
public class OrganController {

    @Autowired
    private IOrganService organService;

    /**
      * 新增组织
      * @Tag 1.0.0
      * @Date 2019/9/19 16:43
      * @Params organDto
      * @Return com.ufm.common.api.Result
      */
    @PostMapping("v1/addOrgan")
    public Result addOrgan(@RequestBody @Validated AddOrganDto organDto){
        String organId = organService.addOrgan(organDto);

        return Result.success("organId",organId);
    }

    /**
      * 删除组织
      * @Tag 1.0.0
      * @Date 2019/9/19 16:39
      * @Params organDto
      * @Return com.ufm.common.api.Result
      */
    @PostMapping("v1/deleteOrgan")
    public Result deleteOrgan(@RequestBody @Validated GetOrganDto organDto){
        organService.deleteOrgan(organDto);

        return Result.success();
    }

    /**
      * 修改组织
      * @Tag 1.0.0
      * @Date 2019/9/19 16:39
      * @Params organDto
      * @Return com.ufm.common.api.Result
      */
    @PostMapping("v1/updateOrgan")
    public Result updateOrgan(@RequestBody @Validated UpdateOrganDto organDto){
        String organId = organService.updateOrgan(organDto);

        return Result.success("organId",organId);
    }

    /**
      * 获取组织列表
      * @Tag 1.0.0
      * @Date 2019/9/19 16:41
      * @Params organDto
      * @Return com.ufm.common.api.Result<java.util.List<com.ufm.account.pojo.vo.OrganVo>>
      */
    @PostMapping("v1/getOrganList")
    public Result<List<OrganVo>> getOrganList(@RequestBody @Validated GetOrganListDto organDto){
        List<OrganVo> organVos = organService.getOrganList(organDto);
        return Result.success(organVos);
    }

    /**
      * 获取组织
      * @Tag 1.0.0
      * @Date 2019/9/19 16:41
      * @Params getOrganDto
      * @Return com.ufm.common.api.Result<com.ufm.account.pojo.vo.OrganVo>
      */
    @PostMapping("v1/getOrgan")
    public Result<OrganVo> getOrgan(@RequestBody @Validated GetOrganDto getOrganDto){
        OrganVo organVo = organService.getOrgan(getOrganDto.getOrganId());
        return Result.success(organVo);
    }


    /**
      * 测试方法
      * @done
      * @Tag 1.0.0
      * @Date 2019/9/25 16:38
      * @Params
      * @Return java.lang.String
      */
    @GetMapping("v1/helloWorld")
    public String helloWorld(){
        return "Hello World!";
    }

}
