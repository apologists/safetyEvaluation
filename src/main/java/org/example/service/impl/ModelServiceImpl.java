package org.example.service.impl;

import org.example.entity.Model;
import org.example.mapper.ModelMapper;
import org.example.service.IModelService;
import org.example.dto.ModelDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 模型 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class ModelServiceImpl implements IModelService {

    protected ModelMapper modelMapper;
    @Override
    public IPage<Model> page(ModelDTO dto) {
        IPage<Model> page = Condition.getPage(dto);
        QueryWrapper<Model> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Model.class));
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
        return modelMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Model> list(ModelDTO dto) {
        QueryWrapper<Model> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Model.class));
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
        return modelMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(ModelDTO dto) {
        return modelMapper.insert(BeanCopyUtils.copy(dto,Model.class));
    }

    @Override
    public Integer updateById(ModelDTO dto) {
        return modelMapper.updateById(BeanCopyUtils.copy(dto,Model.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return modelMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Model getOne(ModelDTO dto) {
        return modelMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Model.class)));
    }
}
