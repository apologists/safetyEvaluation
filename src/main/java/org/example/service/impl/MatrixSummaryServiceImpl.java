package org.example.service.impl;

import org.example.dto.RelationDTO;
import org.example.dto.RiskDTO;
import org.example.dto.RiskGradeDTO;
import org.example.entity.MatrixSummary;
import org.example.mapper.MatrixSummaryMapper;
import org.example.service.IMatrixSummaryService;
import org.example.dto.MatrixSummaryDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.service.IRelationService;
import org.example.service.IRiskGradeService;
import org.example.service.IRiskService;
import org.example.utils.BeanCopyUtils;
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
public class MatrixSummaryServiceImpl implements IMatrixSummaryService {

    protected MatrixSummaryMapper matrixSummaryMapper;
    private IRiskService riskService;
    private IRiskGradeService riskGradeService;
    private IRelationService relationService;
    @Override
    public IPage<MatrixSummary> page(MatrixSummaryDTO dto) {
        IPage<MatrixSummary> page = Condition.getPage(dto);
        QueryWrapper<MatrixSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, MatrixSummary.class));
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
        return matrixSummaryMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<MatrixSummary> list(MatrixSummaryDTO dto) {
        QueryWrapper<MatrixSummary> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, MatrixSummary.class));
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
        return matrixSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(MatrixSummaryDTO dto) {
        Integer horizontal = dto.getHorizontal();
        Integer longitudinal = dto.getLongitudinal();
        for(int i = 1; i<= horizontal; i++){
            RiskDTO riskDTO = new RiskDTO();
            riskDTO.setProjectId(dto.getProjectId());
            riskService.save(riskDTO);
            for (int j = 1; j <= longitudinal; j++) {
                RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
                riskGradeDTO.setRiskGradeCode(i*j);
                riskGradeDTO.setProjectId(dto.getProjectId());
                riskGradeDTO.setLongitudinal(j);
                riskGradeDTO.setHorizontal(i);
                riskGradeService.save(riskGradeDTO);
            }
        }
        for (int j = 1; j <= longitudinal; j++) {
            RelationDTO relationDTO = new RelationDTO();
            relationDTO.setProjectId(dto.getProjectId());
            relationService.save(relationDTO);
        }
        return matrixSummaryMapper.insert(BeanCopyUtils.copy(dto,MatrixSummary.class));
    }

    public Integer save2(MatrixSummaryDTO dto) {
        Integer horizontal = dto.getHorizontal();
        Integer longitudinal = dto.getLongitudinal();
        for(int i = 1; i<= horizontal; i++){
            for (int j = 1; j <= longitudinal; j++) {
                RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
                riskGradeDTO.setRiskGradeCode(i*j);
                riskGradeDTO.setProjectId(dto.getProjectId());
                riskGradeDTO.setLongitudinal(j);
                riskGradeDTO.setHorizontal(i);
                riskGradeService.save(riskGradeDTO);
            }
        }
        return matrixSummaryMapper.insert(BeanCopyUtils.copy(dto,MatrixSummary.class));
    }

    @Override
    public Integer updateById(MatrixSummaryDTO dto) {
        return matrixSummaryMapper.updateById(BeanCopyUtils.copy(dto,MatrixSummary.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return matrixSummaryMapper.deleteBatchIds(toIntList);
    }

    @Override
    public MatrixSummary getOne(MatrixSummaryDTO dto) {
        return matrixSummaryMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,MatrixSummary.class)));
    }
}
