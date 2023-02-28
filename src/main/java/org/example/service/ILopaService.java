package org.example.service;

import org.example.entity.Lopa;
import org.example.dto.LopaDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-28
 */
public interface ILopaService  {

        IPage<Lopa> page(LopaDTO dto) ;

        List<Lopa> list(LopaDTO dto) ;

        Integer save(LopaDTO dto) ;

        Integer updateById(LopaDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Lopa getOne(LopaDTO dto) ;
}
