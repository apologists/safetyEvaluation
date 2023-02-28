package org.example.service.impl;

import org.example.entity.BaseSummary;
import org.example.mapper.BaseSummaryMapper;
import org.example.service.IBaseSummaryService;
import org.example.dto.BaseSummaryDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-25
 */
@Service
@AllArgsConstructor
public class BaseSummaryServiceImpl implements IBaseSummaryService {

    protected BaseSummaryMapper baseSummaryMapper;
    @Override
    public IPage<BaseSummary> page(BaseSummaryDTO dto) {
        IPage<BaseSummary> page = Condition.getPage(dto);
        QueryWrapper<BaseSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, BaseSummary.class));
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
        return baseSummaryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<BaseSummary> list(BaseSummaryDTO dto) {
        QueryWrapper<BaseSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, BaseSummary.class));
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
        return baseSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(BaseSummaryDTO dto) {
        return baseSummaryMapper.insert(BeanCopyUtils.copy(dto,BaseSummary.class));
    }

    @Override
    public Integer updateById(BaseSummaryDTO dto) {
        return baseSummaryMapper.updateById(BeanCopyUtils.copy(dto,BaseSummary.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return baseSummaryMapper.deleteBatchIds(toIntList);
    }

    @Override
    public BaseSummary getOne(BaseSummaryDTO dto) {
        return baseSummaryMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,BaseSummary.class)));
    }
}
