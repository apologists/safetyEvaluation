package org.example.entity;

import lombok.Data;

import java.util.List;

/**
 * 实体类
 *
 * @author AI
 * @since 2022-08-25
 */
@Data
public class SDGSummary  {

  private List<SDGNode> nodes;
  private List<SDGEdges> edges;
}
