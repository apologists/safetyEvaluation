package org.example.service;

import org.example.entity.Formula;
import org.example.dto.FormulaDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-20
 */
public interface IFormulaService  {

        IPage<Formula> page(FormulaDTO dto) ;

        List<Formula> list(FormulaDTO dto) ;

        Integer save(FormulaDTO dto) ;

        Integer updateById(FormulaDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Formula getOne(FormulaDTO dto) ;
}
