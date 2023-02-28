package org.example.service;

import org.example.entity.Cause;
import org.example.dto.CauseDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 原因节点表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface ICauseService  {

        IPage<Cause> page(CauseDTO dto) ;

        List<Cause> list(CauseDTO dto) ;

        Integer save(CauseDTO dto) ;

        Integer updateById(CauseDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Cause getOne(CauseDTO dto) ;
}
