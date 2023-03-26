package org.example.service.impl;

import org.example.entity.Sil;
import org.example.mapper.SilMapper;
import org.example.service.ISilService;
import org.example.dto.SilDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.example.utils.ExcelUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * sil验算 服务实现类
 *
 * @author AI
 * @since 2023-03-01
 */
@Service
@AllArgsConstructor
public class SilServiceImpl implements ISilService {

    protected SilMapper silMapper;
    @Override
    public IPage<Sil> page(SilDTO dto) {
        IPage<Sil> page = Condition.getPage(dto);
        QueryWrapper<Sil> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sil.class));
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
        return silMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Sil> list(SilDTO dto) {
        QueryWrapper<Sil> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Sil.class));
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
        return silMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(SilDTO dto) {
        String silType = dto.getSilType();
        double var1 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar1()));
        double var2 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar2()));
        double var3 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar3()));
        double var4 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar4()));
        double var5 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar5()));
        double var6 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar6()));
        double var7 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar7()));
        double i = ((1 - var2) * var4 + (1 - var1) * var5) * ((1 - var2) * var4 + (1 - var1) * var5);

        double j = (var5 / var3) * (var6 / 2 + var7) + ((var4 / var3) * var7);
        double k = (var5 / var3) * (var6 / 3 + var7 + var7) + (var5 / var3) * var7;
        double n = var2 * var4 * var7 + var1 * var5 * (var6 / 2 + var7);

        if (silType.equals("2oo3")) {
            Double l3 = 6 * i * j * k * n;
            dto.setSystemPfd(String.valueOf(l3));
        }
        if (silType.equals("1oo1")) {
            Double l1 = (var5 + var4) * ((var5 / var3) * ((var6 / 2 + var7) + (var4 / var3) * var7));
            dto.setSystemPfd(String.valueOf(l1));
        }
        if (silType.equals("1oo2")) {
            Double l2 = 2 * i * j * k * n;
            dto.setSystemPfd(String.valueOf(l2));
        }
        if (silType.equals("2oo2")) {
            Double l4 = 2 * var3 * ((var5 / var3) * (var6 / 2 + var7) + (var4 / var3) * var7);
            dto.setSystemPfd(String.valueOf(l4));
        }
        return silMapper.insert(BeanCopyUtils.copy(dto, Sil.class));
    }
    @Override
    public Integer updateById(SilDTO dto) {
        return silMapper.updateById(BeanCopyUtils.copy(dto,Sil.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return silMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Sil getOne(SilDTO dto) {
        return silMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Sil.class)));
    }
}
