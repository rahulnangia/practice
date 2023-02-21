package com.prep.tesla.model.enums;

import com.prep.tesla.model.ParamConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ParamType {
    STRING(value -> value),
    BOOLEAN(value -> Boolean.parseBoolean(value)),
    INTEGER(value -> Integer.parseInt(value)),
    DOUBLE(value -> Double.parseDouble(value));

    @Getter
    ParamConverter converter;
}
