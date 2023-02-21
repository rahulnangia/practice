package com.prep.tesla.model.enums;

import com.prep.tesla.model.BaseConditionTreeNode;
import com.prep.tesla.model.OperatorEvaluator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LogicalOperator {
    AND((operandA, operandB) -> operandA && operandB, true, false), OR((operandA, operandB) -> operandA || operandB, false, true);

    OperatorEvaluator<Boolean> evaluator;

    @Getter
    boolean starterValue;

    @Getter
    boolean breakValue;

    public boolean evaluate(BaseConditionTreeNode lhs, Boolean rhs) throws Exception {
        return evaluator.evaluate(lhs.evaluate(), rhs);
    }



}
