package org.example.service.impl;

import org.example.entity.RiskGrade;
import org.example.mapper.RiskGradeMapper;
import org.example.service.IRiskGradeService;
import org.example.dto.RiskGradeDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 风险等级说明 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class RiskGradeServiceImpl implements IRiskGradeService {

    protected RiskGradeMapper riskGradeMapper;
    @Override
    public IPage<RiskGrade> page(RiskGradeDTO dto) {
        IPage<RiskGrade> page = Condition.getPage(dto);
        QueryWrapper<RiskGrade> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, RiskGrade.class));
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
        return riskGradeMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<RiskGrade> list(RiskGradeDTO dto) {
        QueryWrapper<RiskGrade> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, RiskGrade.class));
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
        return riskGradeMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(RiskGradeDTO dto) {
        return riskGradeMapper.insert(BeanCopyUtils.copy(dto,RiskGrade.class));
    }

    @Override
    public Integer updateById(RiskGradeDTO dto) {
        return riskGradeMapper.updateById(BeanCopyUtils.copy(dto,RiskGrade.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return riskGradeMapper.deleteBatchIds(toIntList);
    }

    @Override
    public RiskGrade getOne(RiskGradeDTO dto) {
        return riskGradeMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,RiskGrade.class)));
    }
}
