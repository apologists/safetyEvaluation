package org.example.service.impl;

import org.example.common.R;
import org.example.dto.MatrixSummaryDTO;
import org.example.dto.RelationDTO;
import org.example.dto.RiskGradeDTO;
import org.example.entity.MatrixSummary;
import org.example.entity.Relation;
import org.example.entity.Risk;
import org.example.entity.RiskGrade;
import org.example.mapper.RiskMapper;
import org.example.service.IMatrixSummaryService;
import org.example.service.IRelationService;
import org.example.service.IRiskGradeService;
import org.example.service.IRiskService;
import org.example.dto.RiskDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-22
 */
@Service
@AllArgsConstructor
public class   RiskServiceImpl implements IRiskService {

    protected RiskMapper riskMapper;

    private IRiskGradeService riskGradeService;


    @Override
    public IPage<Risk> page(RiskDTO dto) {
        IPage<Risk> page = Condition.getPage(dto);
        QueryWrapper<Risk> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Risk.class));
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
        return riskMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Risk> list(RiskDTO dto) {
        QueryWrapper<Risk> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Risk.class));
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
        return riskMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(RiskDTO dto) {
        return riskMapper.insert(BeanCopyUtils.copy(dto,Risk.class));
    }

    @Override
    public Integer updateById(RiskDTO dto) {
        RiskDTO riskDTO = new RiskDTO();
        riskDTO.setProjectId(dto.getProjectId());
        List<Risk> riskList = this.list(riskDTO);
        RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
        riskGradeDTO.setProjectId(dto.getProjectId());
        riskGradeDTO.setHorizontal(riskList.size()+1);
        List<RiskGrade> list1 = riskGradeService.list(riskGradeDTO);
        riskGradeDTO.setSeverity(dto.getSeverity());
        list1.forEach(riskGrade -> {
            riskGradeDTO.setRiskGradeId(riskGrade.getRiskGradeId());
            riskGradeService.updateById(riskGradeDTO);
        });
        return riskMapper.updateById(BeanCopyUtils.copy(dto,Risk.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return riskMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Risk getOne(RiskDTO dto) {
        return riskMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Risk.class)));
    }
}
