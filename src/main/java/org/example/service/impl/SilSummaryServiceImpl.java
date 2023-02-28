package org.example.service.impl;

import org.example.entity.SilSummary;
import org.example.mapper.SilSummaryMapper;
import org.example.service.ISilSummaryService;
import org.example.dto.SilSummaryDTO;
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
 * @since 2022-10-06
 */
@Service
@AllArgsConstructor
public class SilSummaryServiceImpl implements ISilSummaryService {

    protected SilSummaryMapper silSummaryMapper;
    @Override
    public IPage<SilSummary> page(SilSummaryDTO dto) {
        IPage<SilSummary> page = Condition.getPage(dto);
        QueryWrapper<SilSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, SilSummary.class));
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
        return silSummaryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SilSummary> list(SilSummaryDTO dto) {
        QueryWrapper<SilSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, SilSummary.class));
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
        return silSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(SilSummaryDTO dto) {
        return silSummaryMapper.insert(BeanCopyUtils.copy(dto,SilSummary.class));
    }

    @Override
    public Integer updateById(SilSummaryDTO dto) {
        return silSummaryMapper.updateById(BeanCopyUtils.copy(dto,SilSummary.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return silSummaryMapper.deleteBatchIds(toIntList);
    }

    @Override
    public SilSummary getOne(SilSummaryDTO dto) {
        return silSummaryMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,SilSummary.class)));
    }
}
