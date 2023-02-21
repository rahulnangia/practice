package com.prep.tesla.model.enums;

import com.prep.tesla.model.OperatorEvaluator;
import com.prep.tesla.model.params.Parameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Operator {
    EQUALS(diff -> diff == 0), GREATER_THAN(diff -> diff > 0), LESS_THAN( diff -> diff < 0);

    DiffEvaluator evaluator;

    public boolean evaluate(Parameter operandA, Parameter operandB) throws Exception {
        Comparable a = operandA.getValue();
        Comparable b = operandB.getValue();
        int diff;
        if(a==null && b==null){
            diff = 0;
        }else if(a== null || b == null){
            diff = a==null ? -1 : 1;
        }else{
            if(a.getClass().equals(b.getClass())){
                throw new Exception("invalid comparison");
            }
            diff = a.compareTo(b);
        }


        return evaluator.evaluate(diff);
    }

    interface DiffEvaluator{
        boolean evaluate(int diff);
    }

}
