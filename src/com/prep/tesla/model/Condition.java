package com.prep.tesla.model;

import com.prep.tesla.model.enums.Operator;
import com.prep.tesla.model.params.Parameter;

public class Condition {
    Operator operator;

    Parameter operandA;

    Parameter operandB;

    public boolean evaluate() throws Exception {
        return operator.evaluate(operandA, operandB);
    }
}
