package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类
 *
 * @author AI
 * @since 2022-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SDGNode {
  private String id;
  private String label;
  private Integer borderWidth;
  private String shape;

  public SDGNode(String id, String label){
    this.id = id;
    this.label = label;
    this.borderWidth = 2;
    this.shape = "circle";
  }

  public SDGNode(String id, String label,String shape){
    this.id = id;
    this.label = label;
    this.borderWidth = 2;
    this.shape = shape;
  }

}
