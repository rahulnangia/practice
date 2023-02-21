package com.prep.tesla.model;

public interface OperatorEvaluator<T> {
    boolean evaluate(T operandA, T operandB);
}
