package org.example.service.impl;

import org.example.entity.Sdg;
import org.example.mapper.SdgMapper;
import org.example.service.ISdgService;
import org.example.dto.SdgDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * sdg拉偏表 服务实现类
 *
 * @author AI
 * @since 2023-03-11
 */
@Service
@AllArgsConstructor
public class SdgServiceImpl implements ISdgService {

    protected SdgMapper sdgMapper;
    @Override
    public IPage<Sdg> page(SdgDTO dto) {
        IPage<Sdg> page = Condition.getPage(dto);
        QueryWrapper<Sdg> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sdg.class));
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
        return sdgMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Sdg> list(SdgDTO dto) {
        QueryWrapper<Sdg> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sdg.class));
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
        return sdgMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(SdgDTO dto) {
        return sdgMapper.insert(BeanCopyUtils.copy(dto,Sdg.class));
    }

    @Override
    public Integer updateById(SdgDTO dto) {
        return sdgMapper.updateById(BeanCopyUtils.copy(dto,Sdg.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return sdgMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Sdg getOne(SdgDTO dto) {
        return sdgMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Sdg.class)));
    }
}
