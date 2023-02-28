package org.example.service.impl;

import org.example.entity.Case;
import org.example.mapper.CaseMapper;
import org.example.service.ICaseService;
import org.example.dto.CaseDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 案例库表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class CaseServiceImpl implements ICaseService {

    protected CaseMapper caseMapper;
    @Override
    public IPage<Case> page(CaseDTO dto) {
        IPage<Case> page = Condition.getPage(dto);
        QueryWrapper<Case> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Case.class));
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
        return caseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Case> list(CaseDTO dto) {
        QueryWrapper<Case> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Case.class));
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
        return caseMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(CaseDTO dto) {
        return caseMapper.insert(BeanCopyUtils.copy(dto,Case.class));
    }

    @Override
    public Integer updateById(CaseDTO dto) {
        return caseMapper.updateById(BeanCopyUtils.copy(dto,Case.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return caseMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Case getOne(CaseDTO dto) {
        return caseMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Case.class)));
    }
}
