package org.example.service;

import org.example.entity.Relation;
import org.example.dto.RelationDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-20
 */
public interface IRelationService  {

        IPage<Relation> page(RelationDTO dto) ;

        List<Relation> list(RelationDTO dto) ;

        Integer save(RelationDTO dto) ;

        Integer updateById(RelationDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Relation getOne(RelationDTO dto) ;
}
