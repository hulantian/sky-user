package com.ufm.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.ufm.account.entity.SystemInfoEntity;
import com.ufm.account.mapper.SystemInfoMapper;
import com.ufm.account.pojo.dto.system.AddSystemDto;
import com.ufm.account.pojo.dto.system.DeleteSystemDto;
import com.ufm.account.pojo.vo.system.SystemVo;
import com.ufm.account.service.ISystemService;
import com.ufm.common.code.CodeConstant;
import com.ufm.common.constant.GlobalConst;
import com.ufm.common.error.Constant;
import com.ufm.common.error.ServiceExcepion;
import javafx.print.Collation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统接口实现
 * @Author 胡蓝天
 * @date 2019-09-16 15:13
 * @version 1.0
 */
@Service
public class SystemServiceImpl implements ISystemService {
    @Autowired
    private SystemInfoMapper systemInfoMapper;

    @Override
    public String addSystem(AddSystemDto addSystemDto) {

        LambdaQueryWrapper<SystemInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfoEntity::getCode, addSystemDto.getCode());
        wrapper.eq(SystemInfoEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<SystemInfoEntity> tempList = systemInfoMapper.selectList(wrapper);

        if (!CollectionUtils.isEmpty(tempList)) {
            throw new ServiceExcepion(CodeConstant.SYSTEM_EXISTS);
        }

        SystemInfoEntity systemInfoEntity = new SystemInfoEntity();
        BeanUtil.copyProperties(addSystemDto, systemInfoEntity);
        systemInfoMapper.insert(systemInfoEntity);
        return null;
    }


    @Override
    public String deleteSystem(String code) {
        LambdaQueryWrapper<SystemInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfoEntity::getCode, code);
        wrapper.eq(SystemInfoEntity::getIsDelete, GlobalConst.NON_DELETED);

        SystemInfoEntity tempEntity = new SystemInfoEntity();
        tempEntity.setIsDelete(GlobalConst.IS_DELETED);

        int count = systemInfoMapper.update(tempEntity, wrapper);
        if (count == 0) {
            throw new ServiceExcepion(CodeConstant.SYSTEM_NOT_EXISTS);
        }
        return null;
    }

    @Override
    public List<SystemVo> getSystemList() {
        LambdaQueryWrapper<SystemInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfoEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<SystemInfoEntity> systemInfoEntities = systemInfoMapper.selectList(wrapper);
        List<SystemVo> systemVos = systemInfoEntities.stream()
                .map(this::covert)
                .collect(Collectors.toList());
        return systemVos;
    }

    @Override
    public boolean checkSystemDelete(String code) {
        LambdaQueryWrapper<SystemInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemInfoEntity::getCode, code);
        wrapper.eq(SystemInfoEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<SystemInfoEntity> tempList = systemInfoMapper.selectList(wrapper);

        boolean isDelete = false;
        if (CollectionUtils.isEmpty(tempList)) {
            isDelete = true;
        }
        return isDelete;
    }

    /**
     * 实体转换为传输对象
     * @param systemInfoEntity 实体对象
     * @return
     */
    private SystemVo covert(SystemInfoEntity systemInfoEntity) {
        if (Objects.nonNull(systemInfoEntity)) {
            SystemVo systemVo = new SystemVo();
            BeanUtil.copyProperties(systemInfoEntity, systemVo);
            return systemVo;
        } else  {
            return null;
        }
    }
}
