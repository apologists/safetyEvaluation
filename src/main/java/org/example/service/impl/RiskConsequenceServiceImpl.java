package org.example.service.impl;

import org.example.entity.RiskConsequence;
import org.example.mapper.RiskConsequenceMapper;
import org.example.service.IRiskConsequenceService;
import org.example.dto.RiskConsequenceDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 风险后果说明表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class RiskConsequenceServiceImpl implements IRiskConsequenceService {

    protected RiskConsequenceMapper riskConsequenceMapper;
    @Override
    public IPage<RiskConsequence> page(RiskConsequenceDTO dto) {
        IPage<RiskConsequence> page = Condition.getPage(dto);
        QueryWrapper<RiskConsequence> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, RiskConsequence.class));
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
        return riskConsequenceMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<RiskConsequence> list(RiskConsequenceDTO dto) {
        QueryWrapper<RiskConsequence> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, RiskConsequence.class));
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
        return riskConsequenceMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(RiskConsequenceDTO dto) {
        return riskConsequenceMapper.insert(BeanCopyUtils.copy(dto,RiskConsequence.class));
    }

    @Override
    public Integer updateById(RiskConsequenceDTO dto) {
        return riskConsequenceMapper.updateById(BeanCopyUtils.copy(dto,RiskConsequence.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return riskConsequenceMapper.deleteBatchIds(toIntList);
    }

    @Override
    public RiskConsequence getOne(RiskConsequenceDTO dto) {
        return riskConsequenceMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,RiskConsequence.class)));
    }
}
