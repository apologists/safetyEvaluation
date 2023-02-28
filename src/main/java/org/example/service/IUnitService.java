package org.example.service;

import org.example.entity.Unit;
import org.example.dto.UnitDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 单元表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IUnitService  {

        IPage<Unit> page(UnitDTO dto) ;

        List<Unit> list(UnitDTO dto) ;

        Integer save(UnitDTO dto) ;

        Integer updateById(UnitDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Unit getOne(UnitDTO dto) ;
}
