package org.example.service.impl;

import org.example.dto.SilDTO;
import org.example.entity.Lopa;
import org.example.entity.Sil;
import org.example.mapper.LopaMapper;
import org.example.service.ILopaService;
import org.example.dto.LopaDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.service.ISilService;
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
 * @since 2022-08-28
 */
@Service
@AllArgsConstructor
public class LopaServiceImpl implements ILopaService {

    protected LopaMapper lopaMapper;

    private ISilService silService;
    @Override
    public IPage<Lopa> page(LopaDTO dto) {
        IPage<Lopa> page = Condition.getPage(dto);
        QueryWrapper<Lopa> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Lopa.class));
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
        return lopaMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Lopa> list(LopaDTO dto) {
        QueryWrapper<Lopa> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Lopa.class));
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
        return lopaMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(LopaDTO dto) {
        return lopaMapper.insert(BeanCopyUtils.copy(dto,Lopa.class));
    }

    @Override
    public Integer updateById(LopaDTO dto) {
        SilDTO silDTO = new SilDTO();
        silDTO.setLevel(dto.getLevel());
        List<Sil> list = silService.list(silDTO);
        int accidentRate = Integer.parseInt(dto.getIgnitionProbability())
                *Integer.parseInt(dto.getExposureProbability())
                *Integer.parseInt(dto.getLethalityRate())
                *Integer.parseInt(dto.getProtectionRate());
        int allowAccidentRate = accidentRate / Integer.parseInt(dto.getAllowAccidentRate());
        dto.setAccidentRate(Integer.toString(accidentRate));
        dto.setAllowAccidentRate(Integer.toString(allowAccidentRate));
        list.forEach( sil -> {
          if(allowAccidentRate > (1/sil.getFactorHigth()) && allowAccidentRate < (1/sil.getFactorLow())){
              dto.setSilGrade(sil.getSilGrade());
          }else {
              dto.setSilGrade(list.get(list.size()-1).getSilGrade());
          }
        });
        return lopaMapper.updateById(BeanCopyUtils.copy(dto,Lopa.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return lopaMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Lopa getOne(LopaDTO dto) {
        return lopaMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Lopa.class)));
    }
}
