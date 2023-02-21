package com.prep.tesla.model;

public class SingleConditionTreeNode extends BaseConditionTreeNode {

    Condition condition;

    public boolean evaluate() throws Exception {
        return condition.evaluate();
    }
}
