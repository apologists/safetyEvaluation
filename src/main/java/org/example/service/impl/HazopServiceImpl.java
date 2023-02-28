package org.example.service.impl;

import org.example.dto.*;
import org.example.entity.*;
import org.example.mapper.HazopMapper;
import org.example.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;

import java.util.*;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-28
 */
@Service
@AllArgsConstructor
public class HazopServiceImpl implements IHazopService {

    protected HazopMapper hazopMapper;

    private IRiskGradeService riskGradeService;

    private ILopaService lopaService;

    private ICaseSummaryService caseService;

    @Override
    public IPage<Hazop> page(HazopDTO dto) {
        IPage<Hazop> page = Condition.getPage(dto);
        QueryWrapper<Hazop> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Hazop.class));
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
        return hazopMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Hazop> list(HazopDTO dto) {
        QueryWrapper<Hazop> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Hazop.class));
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
        return hazopMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(HazopDTO dto) {
        return hazopMapper.insert(BeanCopyUtils.copy(dto,Hazop.class));
    }

    @Override
    public Integer updateById(HazopDTO dto) {
        RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
        riskGradeDTO.setProjectId(dto.getProjectId());
        List<RiskGrade> list = riskGradeService.list(riskGradeDTO);
        if (dto.getRiskSeverity() != null && dto.getRelationShips() != null) {
            int grade = Integer.parseInt(dto.getRiskSeverity()) * Integer.parseInt(dto.getRelationShips());
            for (int i = 0; i<list.size(); i++) {
                if(i<list.size()-1 && Integer.parseInt(list.get(i).getFrequencyLevel()) * Integer.parseInt(list.get(i).getSeverity()) < grade){
                    if (Integer.parseInt(list.get(i+1).getFrequencyLevel()) * Integer.parseInt(list.get(i+1).getSeverity()) > grade) {
                        dto.setRiskGrade(list.get(i).getRiskGrade());
                        if(list.get(i).getRiskGrade().equals("很高")){
                            LopaDTO lopaDTO = new LopaDTO();
                            lopaDTO.setProjectId(dto.getProjectId());
                            lopaDTO.setEventIe(dto.getPullOffNode());
                            lopaService.save(lopaDTO);
                        }
                    }
                }else {
                    dto.setRiskGrade(list.get(i).getRiskGrade());
                    if(list.get(i).getRiskGrade().equals("很高")){
                        LopaDTO lopaDTO = new LopaDTO();
                        lopaDTO.setProjectId(dto.getProjectId());
                        lopaDTO.setEventIe(dto.getPullOffNode());
                        lopaService.save(lopaDTO);
                    }
                }
            }
        }
        CaseSummaryDTO caseSummaryDTO = new CaseSummaryDTO();
        caseSummaryDTO.setAbnormalCauses(dto.getAbnormalCauses());
        caseSummaryDTO.setDeviation(dto.getDeviation());
        caseSummaryDTO.setAdverseOutComes(dto.getAdverseOutComes());
        caseSummaryDTO.setRiskGrade(dto.getRiskGrade());
        caseSummaryDTO.setPullOffNode(dto.getPullOffNode());
        caseSummaryDTO.setExistingMeasures(dto.getExistingMeasures());
        caseSummaryDTO.setRelationShips(dto.getRelationShips());
        caseSummaryDTO.setRiskSeverity(dto.getRiskSeverity());
        caseSummaryDTO.setSuggestedActions(dto.getSuggestedActions());
        caseService.save(caseSummaryDTO);
        return hazopMapper.updateById(BeanCopyUtils.copy(dto,Hazop.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return hazopMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Hazop getOne(HazopDTO dto) {
        return hazopMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Hazop.class)));
    }
}
