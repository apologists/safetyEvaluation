package org.example.service;

import org.example.entity.ProjectSummary;
import org.example.dto.ProjectSummaryDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-20
 */
public interface IProjectSummaryService  {

        IPage<ProjectSummary> page(ProjectSummaryDTO dto) ;

        List<ProjectSummary> list(ProjectSummaryDTO dto) ;

        Integer save(ProjectSummaryDTO dto) ;

        Integer updateById(ProjectSummaryDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        ProjectSummary getOne(ProjectSummaryDTO dto) ;
}
