package org.example.service.impl;

import org.example.entity.Unit;
import org.example.mapper.UnitMapper;
import org.example.service.IUnitService;
import org.example.dto.UnitDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 单元表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class UnitServiceImpl implements IUnitService {

    protected UnitMapper unitMapper;
    @Override
    public IPage<Unit> page(UnitDTO dto) {
        IPage<Unit> page = Condition.getPage(dto);
        QueryWrapper<Unit> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Unit.class));
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
        return unitMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Unit> list(UnitDTO dto) {
        QueryWrapper<Unit> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Unit.class));
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
        return unitMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(UnitDTO dto) {
        return unitMapper.insert(BeanCopyUtils.copy(dto,Unit.class));
    }

    @Override
    public Integer updateById(UnitDTO dto) {
        return unitMapper.updateById(BeanCopyUtils.copy(dto,Unit.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return unitMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Unit getOne(UnitDTO dto) {
        return unitMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Unit.class)));
    }
}
