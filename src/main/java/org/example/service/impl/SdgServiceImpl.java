package org.example.service.impl;

import org.example.dto.CauseDTO;
import org.example.dto.ConsequenceDTO;
import org.example.entity.Cause;
import org.example.entity.Consequence;
import org.example.entity.Sdg;
import org.example.entity.SdgSummary;
import org.example.mapper.SdgMapper;
import org.example.service.ICauseService;
import org.example.service.IConsequenceService;
import org.example.service.ISdgService;
import org.example.dto.SdgDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Resource
    private ICauseService causeService;
    @Resource
    private IConsequenceService consequenceService;
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
    public SdgSummary getOne(SdgDTO dto) {
        List<Sdg> list = list(new SdgDTO()
                .setProjectId(dto.getProjectId())
                .setUnitId(dto.getUnitId())
                .setModelId(dto.getModelId())
        );

        List<Cause> newCauseList = new ArrayList<>();
        List<Consequence> newConsequenceList = new ArrayList<>();
        getList(newCauseList,newConsequenceList,dto);
        list.forEach(sdg -> {
            SdgDTO sdgDTO = new SdgDTO()
                    .setProjectId(sdg.getProjectId())
                    .setModelId(sdg.getModelId())
                    .setUnitId(sdg.getUnitId())
                    .setVariableName(sdg.getVariableName())
                    .setVariableNameEn(sdg.getVariableNameEn())
                    .setPullDirection(sdg.getPullDirection());
            getList(newCauseList,newConsequenceList,sdgDTO);

        });
        return new SdgSummary()
                .setCauseList(newCauseList)
                .setConsequenceList(newConsequenceList)
                .setProjectId(dto.getProjectId())
                .setModelId(dto.getModelId())
                .setUnitId(dto.getUnitId())
                .setVariableName(dto.getVariableName());
    }

    public void getList(List<Cause> newCauseList,List<Consequence> newConsequenceList,SdgDTO dto){
        CauseDTO cause = new CauseDTO()
                .setProjectId(dto.getProjectId())
                .setModelId(dto.getModelId())
                .setUnitId(dto.getUnitId())
                .setVariableName(dto.getVariableName());

        List<Cause> causeList = causeService.list(cause);

        ConsequenceDTO consequenceDTO = new ConsequenceDTO()
                .setProjectId(dto.getProjectId())
                .setModelId(dto.getModelId())
                .setUnitId(dto.getUnitId())
                .setVarialeName(dto.getVariableName());
        List<Consequence> consequenceList = consequenceService.list(consequenceDTO);

        if(dto.getPullDirection().equals("straight")){
            newConsequenceList.addAll(consequenceList.stream().filter(x -> x.getStraight() != null && !Objects.equals(x.getStraight(), "")).collect(Collectors.toList()));
            newCauseList.addAll(causeList.stream().filter(x -> x.getStraight() != null && !Objects.equals(x.getStraight(), "")).collect(Collectors.toList()));
        }else {
            newCauseList.addAll(causeList.stream().filter(x -> x.getBurden() != null && !Objects.equals(x.getBurden(), "")).collect(Collectors.toList()));
            newConsequenceList.addAll(consequenceList.stream().filter(x -> x.getBurden() != null && !Objects.equals(x.getBurden(), "")).collect(Collectors.toList()));
        }
    }

}
