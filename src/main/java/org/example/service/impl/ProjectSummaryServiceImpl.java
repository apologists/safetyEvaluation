package org.example.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.example.common.Condition;
import org.example.dto.ProjectSummaryDTO;
import org.example.entity.ProjectSummary;
import org.example.mapper.ProjectSummaryMapper;
import org.example.service.IProjectSummaryService;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-20
 */
@Service
@AllArgsConstructor
public class ProjectSummaryServiceImpl implements IProjectSummaryService {

    protected ProjectSummaryMapper projectSummaryMapper;
    @Override
    public IPage<ProjectSummary> page(ProjectSummaryDTO dto) {
        IPage<ProjectSummary> page = Condition.getPage(dto);
        QueryWrapper<ProjectSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, ProjectSummary.class));
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
        return projectSummaryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<ProjectSummary> list(ProjectSummaryDTO dto) {
        QueryWrapper<ProjectSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, ProjectSummary.class));
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
        return projectSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(ProjectSummaryDTO dto) {
        return projectSummaryMapper.insert(BeanCopyUtils.copy(dto,ProjectSummary.class));
    }

    @Override
    public Integer updateById(ProjectSummaryDTO dto) {
        return projectSummaryMapper.updateById(BeanCopyUtils.copy(dto,ProjectSummary.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return projectSummaryMapper.deleteBatchIds(toIntList);
    }

    @Override
    public ProjectSummary getOne(ProjectSummaryDTO dto) {
        return projectSummaryMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,ProjectSummary.class)));
    }
}
