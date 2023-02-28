package org.example.service.impl;

import org.example.entity.CaseSummary;
import org.example.mapper.CaseSummaryMapper;
import org.example.service.ICaseSummaryService;
import org.example.dto.CaseSummaryDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-10-05
 */
@Service
@AllArgsConstructor
public class CaseSummaryServiceImpl implements ICaseSummaryService {

    protected CaseSummaryMapper caseSummaryMapper;
    @Override
    public IPage<CaseSummary> page(CaseSummaryDTO dto) {
        IPage<CaseSummary> page = Condition.getPage(dto);
        QueryWrapper<CaseSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, CaseSummary.class));
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
        return caseSummaryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<CaseSummary> list(CaseSummaryDTO dto) {
        List<CaseSummary> list = new ArrayList<>();
        QueryWrapper<CaseSummary> queryWrapper = new QueryWrapper<>();
        if(dto.getPullOffNode() == null || dto.getPullOffNode().equals("")){
            return caseSummaryMapper.selectList(queryWrapper);
        }else {
            CaseSummaryDTO dto1 = new CaseSummaryDTO();
            dto1.setPullOffNode(dto.getPullOffNode());
            QueryWrapper<CaseSummary> queryWrapper1 = Condition.getQueryWrapper(BeanCopyUtils.copy(dto1, CaseSummary.class));
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
            list.addAll(caseSummaryMapper.selectList(queryWrapper1));

            CaseSummaryDTO dto2 = new CaseSummaryDTO();
            dto2.setAbnormalCauses(dto.getPullOffNode());
            QueryWrapper<CaseSummary> queryWrapper2 = Condition.getQueryWrapper(BeanCopyUtils.copy(dto2, CaseSummary.class));
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
            list.addAll(caseSummaryMapper.selectList(queryWrapper2));

            CaseSummaryDTO dto3 = new CaseSummaryDTO();
            dto3.setAdverseOutComes(dto.getPullOffNode());
            QueryWrapper<CaseSummary> queryWrapper3 = Condition.getQueryWrapper(BeanCopyUtils.copy(dto3, CaseSummary.class));
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
            list.addAll(caseSummaryMapper.selectList(queryWrapper3));
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Integer save(CaseSummaryDTO dto) {
        return caseSummaryMapper.insert(BeanCopyUtils.copy(dto,CaseSummary.class));
    }

    @Override
    public Integer updateById(CaseSummaryDTO dto) {
        return caseSummaryMapper.updateById(BeanCopyUtils.copy(dto,CaseSummary.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return caseSummaryMapper.deleteBatchIds(toIntList);
    }

    @Override
    public CaseSummary getOne(CaseSummaryDTO dto) {
        return caseSummaryMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,CaseSummary.class)));
    }
}
