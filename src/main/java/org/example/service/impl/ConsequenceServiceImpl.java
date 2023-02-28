package org.example.service.impl;

import org.example.entity.Consequence;
import org.example.mapper.ConsequenceMapper;
import org.example.service.IConsequenceService;
import org.example.dto.ConsequenceDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 后果节点表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class ConsequenceServiceImpl implements IConsequenceService {

    protected ConsequenceMapper consequenceMapper;
    @Override
    public IPage<Consequence> page(ConsequenceDTO dto) {
        IPage<Consequence> page = Condition.getPage(dto);
        QueryWrapper<Consequence> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Consequence.class));
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
        return consequenceMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Consequence> list(ConsequenceDTO dto) {
        QueryWrapper<Consequence> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Consequence.class));
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
        return consequenceMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(ConsequenceDTO dto) {
        return consequenceMapper.insert(BeanCopyUtils.copy(dto,Consequence.class));
    }

    @Override
    public Integer updateById(ConsequenceDTO dto) {
        return consequenceMapper.updateById(BeanCopyUtils.copy(dto,Consequence.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return consequenceMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Consequence getOne(ConsequenceDTO dto) {
        return consequenceMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Consequence.class)));
    }
}
