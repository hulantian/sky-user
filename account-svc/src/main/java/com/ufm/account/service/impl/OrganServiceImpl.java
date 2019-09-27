package com.ufm.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ufm.account.entity.OrganizationEntity;
import com.ufm.account.enums.ErrorCode;
import com.ufm.account.mapper.OrganizationMapper;
import com.ufm.account.pojo.dto.organ.AddOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganDto;
import com.ufm.account.pojo.dto.organ.GetOrganListDto;
import com.ufm.account.pojo.dto.organ.UpdateOrganDto;
import com.ufm.account.pojo.vo.OrganVo;
import com.ufm.account.pojo.vo.UserVo;
import com.ufm.account.service.IOrganService;
import com.ufm.account.service.IUserService;
import com.ufm.common.constant.GlobalConst;
import com.ufm.common.error.ServiceExcepion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织服务方法实现类
 * @ClassName OrganServiceImpl
 * @Author 胡蓝天
 * @Date 2019/9/20 14:29
 * @Version 1.0
 */
@Service
@Transactional
@AllArgsConstructor
public class OrganServiceImpl implements IOrganService {

    public static final String ROOT_ORGAN_ID = "-1";

    private OrganizationMapper organizationMapper;
    private IUserService userService;

    @Override
    public String addOrgan(AddOrganDto organDto) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrganizationEntity::getName, organDto.getName());
        wrapper.eq(OrganizationEntity::getPid, organDto.getPid());
        wrapper.eq(OrganizationEntity::getIsDelete, GlobalConst.NON_DELETED);
        if (selectOneOrgan(wrapper) != null) {
            throw new ServiceExcepion(ErrorCode.ORGANNIZATION_EXIST);
        } else {
            OrganizationEntity organizationEntity = new OrganizationEntity();
            if (StringUtils.isEmpty(organDto.getPid())) {
                organDto.setPid(ROOT_ORGAN_ID);
            }
            organizationMapper.insert(organDto.covert(organizationEntity));
            return organizationEntity.getId();
        }
    }

    @Override
    public String deleteOrgan(GetOrganDto organDto) {
        List<UserVo> userVoList = userService.getUserListByOrgId(organDto.getOrganId());
        if (userVoList != null && userVoList.size() > 0) {
            throw new ServiceExcepion(ErrorCode.ORGANNIZATION_EXIST_MEMBER);
        } else {
            OrganizationEntity organizationEntity = new OrganizationEntity();
            organizationEntity.setIsDelete(GlobalConst.IS_DELETED);
            LambdaQueryWrapper<OrganizationEntity> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(OrganizationEntity::getId, organDto.getOrganId());
            organizationMapper.update(organizationEntity, wrapper3);

            LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrganizationEntity::getPid, organDto.getOrganId());

            List<OrganizationEntity> organizationEntityList = organizationMapper.selectList(wrapper);
            if (!CollectionUtils.isEmpty(organizationEntityList)) {
                List<String> childIdList = organizationEntityList.stream()
                        .map(OrganizationEntity::getId).collect(Collectors.toList());
                for (String organId : childIdList) {
                    deleteOrgan(new GetOrganDto(organId));
                }
            }
        }
        /*List<OrganizationEntity> organizationEntityList = new ArrayList<>();
        getSubOrganList(organDto, organizationEntityList);

        for (OrganizationEntity organizationEntity : organizationEntityList) {
            organizationEntity.setIsDelete(GlobalConst.IS_DELETED);
            LambdaQueryWrapper<OrganizationEntity> wrapper3 = new LambdaQueryWrapper<>();
            wrapper3.eq(OrganizationEntity::getId, organDto.getOrganId());
            organizationMapper.update(organizationEntity, wrapper3);
        }*/
        return "";
    }

    @Override
    public String updateOrgan(UpdateOrganDto organDto) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.ne(OrganizationEntity::getId, organDto.getOrganId());
        wrapper.eq(OrganizationEntity::getName, organDto.getName());
        wrapper.eq(OrganizationEntity::getPid, organDto.getPid());
        wrapper.eq(OrganizationEntity::getIsDelete, GlobalConst.NON_DELETED);
        if (selectOneOrgan(wrapper) != null) {
            throw new ServiceExcepion(ErrorCode.ORGANNIZATION_EXIST);
        } else {
            LambdaQueryWrapper<OrganizationEntity> wrapper2 = new LambdaQueryWrapper<>();
            wrapper2.eq(OrganizationEntity::getId, organDto.getOrganId());
            wrapper2.eq(OrganizationEntity::getIsDelete, GlobalConst.NON_DELETED);
            int count = organizationMapper.update(organDto.covert(new OrganizationEntity()), wrapper2);
            if (count == 0) {
                throw new ServiceExcepion(ErrorCode.ORGANNIZATION_DELETED);
            }
            return "";
        }
    }


    public void getSubOrganList(GetOrganDto organDto, List<OrganizationEntity> allOrganList) {
        List<UserVo> userVoList = userService.getUserListByOrgId(organDto.getOrganId());
        if (userVoList != null && userVoList.size() > 0) {
            throw new ServiceExcepion(ErrorCode.ORGANNIZATION_EXIST_MEMBER);
        } else {
            LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(OrganizationEntity::getPid, organDto.getOrganId());
            List<OrganizationEntity> organizationEntityList = organizationMapper.selectList(wrapper);

            allOrganList.addAll(organizationEntityList);

            List<String> childIdList = organizationEntityList.stream()
                    .map(OrganizationEntity::getId).collect(Collectors.toList());
            for (String organId : childIdList) {
                getSubOrganList(new GetOrganDto(organId), allOrganList);
            }
        }
    }

    @Override
    public List<OrganVo> getOrganList(GetOrganListDto parentOrgan) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(parentOrgan.getPid())) {
            wrapper.eq(OrganizationEntity::getPid, parentOrgan.getPid());
        }
        wrapper.eq(OrganizationEntity::getIsDelete, GlobalConst.NON_DELETED);
        List<OrganizationEntity> organizationEntityList = organizationMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(organizationEntityList)) {
            return null;
        }
        List<OrganVo> organVoList = organizationEntityList
                .stream()
                .map(organizationEntity -> organizationEntity.covert(new OrganVo())).collect(Collectors.toList());
        return organVoList;
    }

    @Override
    public OrganVo getOrgan(String organId) {
        LambdaQueryWrapper<OrganizationEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrganizationEntity::getId, organId);
        wrapper.eq(OrganizationEntity::getIsDelete, GlobalConst.NON_DELETED);
        OrganizationEntity organizationEntity = organizationMapper.selectOne(wrapper);
        return organizationEntity.covert(new OrganVo());
    }

    /**
     * 查询一条组织的方法，用来判断是否存在相同组织
     *
     * @Date 2019/9/25 10:19
     * @Params queryWrapper
     * @Return com.ufm.account.entity.OrganizationEntity
     */
    private OrganizationEntity selectOneOrgan(LambdaQueryWrapper queryWrapper) {
        queryWrapper.last(GlobalConst.ONE_LIMIT);
        return organizationMapper.selectOne(queryWrapper);
    }
}
