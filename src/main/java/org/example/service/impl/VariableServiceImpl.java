package org.example.service.impl;

import org.example.entity.Variable;
import org.example.mapper.VariableMapper;
import org.example.service.IVariableService;
import org.example.dto.VariableDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 变量表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class VariableServiceImpl implements IVariableService {

    protected VariableMapper variableMapper;
    @Override
    public IPage<Variable> page(VariableDTO dto) {
        IPage<Variable> page = Condition.getPage(dto);
        QueryWrapper<Variable> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Variable.class));
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
        return variableMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Variable> list(VariableDTO dto) {
        QueryWrapper<Variable> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Variable.class));
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
        return variableMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(VariableDTO dto) {
        return variableMapper.insert(BeanCopyUtils.copy(dto,Variable.class));
    }

    @Override
    public Integer updateById(VariableDTO dto) {
        return variableMapper.updateById(BeanCopyUtils.copy(dto,Variable.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return variableMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Variable getOne(VariableDTO dto) {
        return variableMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Variable.class)));
    }
}
