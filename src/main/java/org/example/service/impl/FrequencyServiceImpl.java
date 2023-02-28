package org.example.service.impl;

import org.example.entity.Frequency;
import org.example.mapper.FrequencyMapper;
import org.example.service.IFrequencyService;
import org.example.dto.FrequencyDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 频率说明表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class FrequencyServiceImpl implements IFrequencyService {

    protected FrequencyMapper frequencyMapper;
    @Override
    public IPage<Frequency> page(FrequencyDTO dto) {
        IPage<Frequency> page = Condition.getPage(dto);
        QueryWrapper<Frequency> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Frequency.class));
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
        return frequencyMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Frequency> list(FrequencyDTO dto) {
        QueryWrapper<Frequency> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Frequency.class));
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
        return frequencyMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(FrequencyDTO dto) {
        return frequencyMapper.insert(BeanCopyUtils.copy(dto,Frequency.class));
    }

    @Override
    public Integer updateById(FrequencyDTO dto) {
        return frequencyMapper.updateById(BeanCopyUtils.copy(dto,Frequency.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return frequencyMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Frequency getOne(FrequencyDTO dto) {
        return frequencyMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Frequency.class)));
    }
}
