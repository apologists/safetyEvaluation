package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

/**
 * 实体类
 *
 * @author AI
 * @since 2022-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SDGEdges {

  private String from;
  private String to;
  private boolean dashes;
}
