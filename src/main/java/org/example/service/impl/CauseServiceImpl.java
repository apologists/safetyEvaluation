package org.example.service.impl;

import org.example.entity.Cause;
import org.example.mapper.CauseMapper;
import org.example.service.ICauseService;
import org.example.dto.CauseDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 原因节点表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class CauseServiceImpl implements ICauseService {

    protected CauseMapper causeMapper;
    @Override
    public IPage<Cause> page(CauseDTO dto) {
        IPage<Cause> page = Condition.getPage(dto);
        QueryWrapper<Cause> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Cause.class));
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
        return causeMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Cause> list(CauseDTO dto) {
        QueryWrapper<Cause> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Cause.class));
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
        return causeMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(CauseDTO dto) {
        return causeMapper.insert(BeanCopyUtils.copy(dto,Cause.class));
    }

    @Override
    public Integer updateById(CauseDTO dto) {
        return causeMapper.updateById(BeanCopyUtils.copy(dto,Cause.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return causeMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Cause getOne(CauseDTO dto) {
        return causeMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Cause.class)));
    }
}
