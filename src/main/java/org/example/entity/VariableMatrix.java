package org.example.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class VariableMatrix {
    private List<Map<String,String>> variableMatrixData;
    private Map<String,String> variableMatrixList;
}
