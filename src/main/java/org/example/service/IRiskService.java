package org.example.service;

import org.example.entity.Risk;
import org.example.dto.RiskDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-22
 */
public interface IRiskService  {

        IPage<Risk> page(RiskDTO dto) ;

        List<Risk> list(RiskDTO dto) ;

        Integer save(RiskDTO dto) ;

        Integer updateById(RiskDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Risk getOne(RiskDTO dto) ;
}
