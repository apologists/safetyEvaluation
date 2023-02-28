package org.example.service.impl;

import org.example.entity.Sil;
import org.example.mapper.SilMapper;
import org.example.service.ISilService;
import org.example.dto.SilDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * sil验算 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class SilServiceImpl implements ISilService {

    protected SilMapper silMapper;
    @Override
    public IPage<Sil> page(SilDTO dto) {
        IPage<Sil> page = Condition.getPage(dto);
        QueryWrapper<Sil> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sil.class));
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
        return silMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Sil> list(SilDTO dto) {
        QueryWrapper<Sil> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sil.class));
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
        return silMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(SilDTO dto) {
        return silMapper.insert(BeanCopyUtils.copy(dto,Sil.class));
    }

    @Override
    public Integer updateById(SilDTO dto) {
        return silMapper.updateById(BeanCopyUtils.copy(dto,Sil.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return silMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Sil getOne(SilDTO dto) {
        return silMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Sil.class)));
    }
}
