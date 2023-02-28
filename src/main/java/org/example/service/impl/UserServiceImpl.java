package org.example.service.impl;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.IUserService;
import org.example.dto.UserDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;

/**
 * 用户表 服务实现类
 *
 * @author AI
 * @since 2020-06-19
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    protected UserMapper userMapper;
    @Override
    public IPage<User> page(UserDTO dto) {
        IPage<User> page = Condition.getPage(dto);
        QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, User.class));
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
        return userMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<User> list(UserDTO dto) {
        QueryWrapper<User> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, User.class));
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
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(UserDTO dto) {
        return userMapper.insert(BeanCopyUtils.copy(dto,User.class));
    }

    @Override
    public Integer updateById(UserDTO dto) {
        return userMapper.updateById(BeanCopyUtils.copy(dto,User.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return userMapper.deleteBatchIds(toIntList);
    }

    @Override
    public User getOne(UserDTO dto) {
        return userMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,User.class)));
    }
}
