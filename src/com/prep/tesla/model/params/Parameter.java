package com.prep.tesla.model.params;

import com.prep.tesla.model.enums.ParamType;

public abstract class Parameter {
    ParamType dataType;

    String defaultValue;

    public Comparable getValue() {
        return dataType.getConverter().convert(getStringValue());
    }

    public abstract String getStringValue();


}
