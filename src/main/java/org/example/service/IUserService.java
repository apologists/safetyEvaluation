package org.example.service;

import org.example.entity.User;
import org.example.dto.UserDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户表 服务类
 *
 * @author AI
 * @since 2020-06-19
 */
public interface IUserService  {

        IPage<User> page(UserDTO dto) ;

        List<User> list(UserDTO dto) ;

        Integer save(UserDTO dto) ;

        Integer updateById(UserDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        User getOne(UserDTO dto) ;
}
