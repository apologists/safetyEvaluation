package org.example.service.impl;

import org.example.entity.Formula;
import org.example.mapper.FormulaMapper;
import org.example.service.IFormulaService;
import org.example.dto.FormulaDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 变量公式表 服务实现类
 *
 * @author AI
 * @since 2023-03-11
 */
@Service
@AllArgsConstructor
public class FormulaServiceImpl implements IFormulaService {

    protected FormulaMapper formulaMapper;
    @Override
    public IPage<Formula> page(FormulaDTO dto) {
        IPage<Formula> page = Condition.getPage(dto);
        QueryWrapper<Formula> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Formula.class));
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
        return formulaMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Formula> list(FormulaDTO dto) {
        QueryWrapper<Formula> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Formula.class));
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
        return formulaMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(FormulaDTO dto) {
        return formulaMapper.insert(BeanCopyUtils.copy(dto,Formula.class));
    }

    @Override
    public Integer updateById(FormulaDTO dto) {
        return formulaMapper.updateById(BeanCopyUtils.copy(dto,Formula.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return formulaMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Formula getOne(FormulaDTO dto) {
        return formulaMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Formula.class)));
    }
}
