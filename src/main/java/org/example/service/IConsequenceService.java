package org.example.service;

import org.example.entity.Consequence;
import org.example.dto.ConsequenceDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 后果节点表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IConsequenceService  {

        IPage<Consequence> page(ConsequenceDTO dto) ;

        List<Consequence> list(ConsequenceDTO dto) ;

        Integer save(ConsequenceDTO dto) ;

        Integer updateById(ConsequenceDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Consequence getOne(ConsequenceDTO dto) ;
}
