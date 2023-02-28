package org.example.service;

import org.example.entity.Variable;
import org.example.dto.VariableDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 变量表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IVariableService  {

        IPage<Variable> page(VariableDTO dto) ;

        List<Variable> list(VariableDTO dto) ;

        Integer save(VariableDTO dto) ;

        Integer updateById(VariableDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Variable getOne(VariableDTO dto) ;
}
