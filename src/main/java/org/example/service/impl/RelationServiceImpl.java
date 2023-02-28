package org.example.service.impl;

import org.example.dto.MatrixSummaryDTO;
import org.example.dto.RiskDTO;
import org.example.dto.RiskGradeDTO;
import org.example.entity.MatrixSummary;
import org.example.entity.Relation;
import org.example.entity.Risk;
import org.example.entity.RiskGrade;
import org.example.mapper.RelationMapper;
import org.example.mapper.RiskMapper;
import org.example.service.IMatrixSummaryService;
import org.example.service.IRelationService;
import org.example.dto.RelationDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.service.IRiskGradeService;
import org.example.service.IRiskService;
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
 * @since 2022-08-20
 */
@Service
@AllArgsConstructor
public class RelationServiceImpl implements IRelationService {

    protected RelationMapper relationMapper;

    private IRiskGradeService riskGradeService;

    protected RiskMapper riskMapper;
    @Override
    public IPage<Relation> page(RelationDTO dto) {
        IPage<Relation> page = Condition.getPage(dto);
        QueryWrapper<Relation> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Relation.class));
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
        return relationMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Relation> list(RelationDTO dto) {
        QueryWrapper<Relation> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Relation.class));
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
        return relationMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(RelationDTO dto) {
//        MatrixSummaryDTO matrixSummaryDTO = new MatrixSummaryDTO();
//        matrixSummaryDTO.setProjectId(dto.getProjectId());
//        List<MatrixSummary> list = matrixSummaryService.list(matrixSummaryDTO);
//        MatrixSummary matrixSummary = list.get(0);
//        RelationDTO relationDTO = new RelationDTO();
//        relationDTO.setProjectId(dto.getProjectId());
//        List<Relation> relationList = this.list(relationDTO);
//        if(matrixSummary.getLongitudinal() < (relationList.size()+1)){
//            return null;
//        }
//        RiskDTO riskDTO = new RiskDTO();
//        riskDTO.setProjectId(dto.getProjectId());
//        RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
//        riskGradeDTO.setProjectId(dto.getProjectId());
//        riskGradeDTO.setLongitudinal(relationList.size()+1);
//        List<RiskGrade> list2 = riskGradeService.list(riskGradeDTO);
//        riskGradeDTO.setFrequencyLevel(dto.getFrequencyValue());
//        list2.forEach(riskGrade -> {
//            riskGradeDTO.setRiskGradeId(riskGrade.getRiskGradeId());
//            riskGradeService.updateById(riskGradeDTO);
//        });
        return relationMapper.insert(BeanCopyUtils.copy(dto,Relation.class));
    }

    private QueryWrapper<Risk> getRiskQueryWrapper(RiskDTO dto) {
        QueryWrapper<Risk> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Risk.class));
        if (StrUtil.isNotEmpty(dto.getColumn()) && StrUtil.isNotEmpty(dto.getKeywords())) {
            queryWrapper.like(dto.getColumn(), dto.getKeywords());
        }
        if (StrUtil.isNotEmpty(dto.getOrderAsc())) {
            queryWrapper.orderByAsc(dto.getOrderAsc());
        }
        if (StrUtil.isNotEmpty(dto.getOrderDesc())) {
            queryWrapper.orderByDesc(dto.getOrderDesc());
        }
        if (dto.getStartTime() != null) {
            queryWrapper.gt("created", dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            queryWrapper.lt("created", dto.getEndTime());
        }
        return queryWrapper;
    }

    @Override
    public Integer updateById(RelationDTO dto) {
        RelationDTO relationDTO = new RelationDTO();
        relationDTO.setProjectId(dto.getProjectId());
        List<Relation> relationList = this.list(relationDTO);
        RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
        riskGradeDTO.setProjectId(dto.getProjectId());
        riskGradeDTO.setLongitudinal(relationList.size()+1);
        List<RiskGrade> list2 = riskGradeService.list(riskGradeDTO);
        riskGradeDTO.setFrequencyLevel(dto.getFrequencyValue());
        list2.forEach(riskGrade -> {
            riskGradeDTO.setRiskGradeId(riskGrade.getRiskGradeId());
            riskGradeService.updateById(riskGradeDTO);
        });
        return relationMapper.updateById(BeanCopyUtils.copy(dto,Relation.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return relationMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Relation getOne(RelationDTO dto) {
        return relationMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Relation.class)));
    }
}
