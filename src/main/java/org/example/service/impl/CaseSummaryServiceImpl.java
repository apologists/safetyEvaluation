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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 案例库表 服务实现类
 *
 * @author AI
 * @since 2023-03-11
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
        return caseSummaryMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(CaseSummaryDTO dto) {
        return caseSummaryMapper.insert(BeanCopyUtils.copy(dto,CaseSummary.class));
//        List<CaseSummary> list = list(dto);
//        CaseSummary caseSummary = list.get(0);
//        Field[] fields = dto.getClass().getDeclaredFields();
//        List<Integer> list1 = new ArrayList<>();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String value = field.get(dto).toString();
//            String name = field.getName();
//            if (name.equals("rateFlow") &&  caseSummary.getRateFlow().equals(value)){
//                list1.add(1-(Integer.parseInt(caseSummary.getRateFlow()) - Integer.parseInt(value))
//                        / (Math.max(Integer.parseInt(caseSummary.getRateFlow()),Integer.parseInt(value)))
//                        - (Math.min(Integer.parseInt(caseSummary.getRateFlow()),Integer.parseInt(value))));
//
//            }
//            if(name.equals("temperature") &&  caseSummary.getTemperature().equals(value)){
//                list1.add(1-(Integer.parseInt(caseSummary.getTemperature()) - Integer.parseInt(value))
//                        / (Math.max(Integer.parseInt(caseSummary.getTemperature()),Integer.parseInt(value)))
//                        - (Math.min(Integer.parseInt(caseSummary.getTemperature()),Integer.parseInt(value))));
//            }
//            if(name.equals("pressure") &&  caseSummary.getPressure().equals(value)){
//                list1.add(1-(Integer.parseInt(caseSummary.getPressure()) - Integer.parseInt(value))
//                        / (Math.max(Integer.parseInt(caseSummary.getPressure()),Integer.parseInt(value)))
//                        - (Math.min(Integer.parseInt(caseSummary.getPressure()),Integer.parseInt(value))));
//            }
//            if(name.equals("cause") &&  caseSummary.getCause().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("consequence") &&  caseSummary.getConsequence().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("deviation") &&  caseSummary.getDeviation().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("equipmentMaterialType") &&  caseSummary.getEquipmentMaterialType().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("matter") &&  caseSummary.getMatter().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("measure") &&  caseSummary.getMeasure().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("equipmentType") &&  caseSummary.getEquipmentType().equals(value)){
//                list1.add(1);
//            }
//            if(name.equals("operationProcessType") &&  caseSummary.getOperationProcessType().equals(value)){
//                list1.add(1);
//            }
//            final int[] count = {0};
//            list1.forEach(x -> { count[0] = x + count[0];
//            });
//            dto.setSimilarity(String.valueOf(count[0]));
//        }
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
