package org.example.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class VariableMatrix {
    private Map<String,String> variableMatrixList;
    private Map<String,String> variableMatrixData;
}
