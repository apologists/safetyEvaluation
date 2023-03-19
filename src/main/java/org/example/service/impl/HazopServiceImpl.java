package org.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.example.common.Condition;
import org.example.dto.HazopDTO;
import org.example.dto.ProjectDTO;
import org.example.dto.UnitDTO;
import org.example.entity.Hazop;
import org.example.entity.Project;
import org.example.entity.Unit;
import org.example.mapper.HazopMapper;
import org.example.service.IHazopService;
import org.example.service.IProjectService;
import org.example.service.IUnitService;
import org.example.utils.BeanCopyUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2023-02-28
 */
@Service
@AllArgsConstructor
public class HazopServiceImpl implements IHazopService {

    protected HazopMapper hazopMapper;
    @Override
    public IPage<Hazop> page(HazopDTO dto) {
        IPage<Hazop> page = Condition.getPage(dto);
        QueryWrapper<Hazop> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Hazop.class));
        if (StrUtil.isNotEmpty(dto.getColumn()) && StrUtil.isNotEmpty(dto.getKeywords())) {
        queryWrapper.like(dto.getColumn(),dto.getKeywords());
        }
        if (StrUtil.isNotEmpty(dto.getOrderAsc())) {
        queryWrapper.orderByAsc(dto.getOrderAsc());
        }
        if (StrUtil.isNotEmpty(dto.getOrderDesc())) {
        queryWrapper.orderByDesc(dto.getOrderDesc());
        }
        if (dto.getStartTime() != null) {
        queryWrapper.gt("created",dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
        queryWrapper.lt("created",dto.getEndTime());
        }
        return hazopMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Hazop> list(HazopDTO dto) {
        QueryWrapper<Hazop> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Hazop.class));
        if (StrUtil.isNotEmpty(dto.getColumn()) && StrUtil.isNotEmpty(dto.getKeywords())) {
        queryWrapper.like(dto.getColumn(),dto.getKeywords());
        }
        if (StrUtil.isNotEmpty(dto.getOrderAsc())) {
        queryWrapper.orderByAsc(dto.getOrderAsc());
        }
        if (StrUtil.isNotEmpty(dto.getOrderDesc())) {
        queryWrapper.orderByDesc(dto.getOrderDesc());
        }
        if (dto.getStartTime() != null) {
        queryWrapper.gt("created",dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
        queryWrapper.lt("created",dto.getEndTime());
        }
        return hazopMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(HazopDTO dto) {
        return hazopMapper.insert(BeanCopyUtils.copy(dto,Hazop.class));
    }

    @Override
    public Integer updateById(HazopDTO dto) {
        return hazopMapper.updateById(BeanCopyUtils.copy(dto,Hazop.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return hazopMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Hazop getOne(HazopDTO dto) {
        return hazopMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Hazop.class)));
    }
}
