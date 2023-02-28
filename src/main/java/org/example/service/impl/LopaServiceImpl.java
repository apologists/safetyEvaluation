package org.example.service.impl;

import org.example.entity.Lopa;
import org.example.mapper.LopaMapper;
import org.example.service.ILopaService;
import org.example.dto.LopaDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * lopa分析 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class LopaServiceImpl implements ILopaService {

    protected LopaMapper lopaMapper;
    @Override
    public IPage<Lopa> page(LopaDTO dto) {
        IPage<Lopa> page = Condition.getPage(dto);
        QueryWrapper<Lopa> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Lopa.class));
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
        return lopaMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Lopa> list(LopaDTO dto) {
        QueryWrapper<Lopa> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Lopa.class));
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
        return lopaMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(LopaDTO dto) {
        return lopaMapper.insert(BeanCopyUtils.copy(dto,Lopa.class));
    }

    @Override
    public Integer updateById(LopaDTO dto) {
        return lopaMapper.updateById(BeanCopyUtils.copy(dto,Lopa.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return lopaMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Lopa getOne(LopaDTO dto) {
        return lopaMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Lopa.class)));
    }
}
