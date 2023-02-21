package com.prep.tesla.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseConditionTreeNode {
    public abstract boolean evaluate() throws Exception;

    int getHeight(){
        return 0;
    }
}
