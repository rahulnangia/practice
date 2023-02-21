package com.prep.tesla.model;

import com.prep.tesla.model.enums.LogicalOperator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MultiConditionTreeNode extends BaseConditionTreeNode {
    //sorted list of TreeNodes by height
    @OneToMany
    List<BaseConditionTreeNode> conditions;

    LogicalOperator logicalOperator;


    @Column
    Integer height;

    @Override
    public boolean evaluate() throws Exception {
        boolean result = logicalOperator.isStarterValue();
        int maxHeight = 0;
        for(BaseConditionTreeNode condition: conditions){
            result = logicalOperator.evaluate(condition, result);
            maxHeight = Math.max(maxHeight, condition.getHeight());
            if(result == logicalOperator.isBreakValue()){
                break;
            }
        }
        return result;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
