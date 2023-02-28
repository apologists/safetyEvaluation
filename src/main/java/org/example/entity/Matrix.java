package org.example.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Matrix {
    private List<Map<String,String>> matrixData;
    private Map<String,String> matrixList;
}
