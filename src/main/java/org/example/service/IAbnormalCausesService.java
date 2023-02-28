package org.example.service;

import org.example.entity.AbnormalCauses;
import org.example.dto.AbnormalCausesDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-25
 */
public interface IAbnormalCausesService  {

        IPage<AbnormalCauses> page(AbnormalCausesDTO dto) ;

        List<AbnormalCauses> list(AbnormalCausesDTO dto) ;

        Integer save(AbnormalCausesDTO dto) ;

        Integer updateById(AbnormalCausesDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        AbnormalCauses getOne(AbnormalCausesDTO dto) ;
}
