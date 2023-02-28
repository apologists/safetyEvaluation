package org.example.service;

import org.example.entity.Hazop;
import org.example.dto.HazopDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2023-02-28
 */
public interface IHazopService  {

        IPage<Hazop> page(HazopDTO dto) ;

        List<Hazop> list(HazopDTO dto) ;

        Integer save(HazopDTO dto) ;

        Integer updateById(HazopDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Hazop getOne(HazopDTO dto) ;
}
