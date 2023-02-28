package org.example.service.impl;

import org.example.entity.Project;
import org.example.mapper.ProjectMapper;
import org.example.service.IProjectService;
import org.example.dto.ProjectDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 项目表 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    protected ProjectMapper projectMapper;
    @Override
    public IPage<Project> page(ProjectDTO dto) {
        IPage<Project> page = Condition.getPage(dto);
        QueryWrapper<Project> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Project.class));
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
        return projectMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Project> list(ProjectDTO dto) {
        QueryWrapper<Project> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Project.class));
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
        return projectMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(ProjectDTO dto) {
        return projectMapper.insert(BeanCopyUtils.copy(dto,Project.class));
    }

    @Override
    public Integer updateById(ProjectDTO dto) {
        return projectMapper.updateById(BeanCopyUtils.copy(dto,Project.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return projectMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Project getOne(ProjectDTO dto) {
        return projectMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Project.class)));
    }
}
