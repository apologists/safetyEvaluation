package org.example.service;

import org.example.entity.Model;
import org.example.dto.ModelDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 模型 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IModelService  {

        IPage<Model> page(ModelDTO dto) ;

        List<Model> list(ModelDTO dto) ;

        Integer save(ModelDTO dto) ;

        Integer updateById(ModelDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Model getOne(ModelDTO dto) ;
}
