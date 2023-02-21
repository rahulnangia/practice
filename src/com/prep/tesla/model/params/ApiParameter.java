package com.prep.tesla.model.params;

import java.util.Map;

public class ApiParameter extends Parameter{

    String url;

    Map<String, Parameter> queryParams;

    String valuePath;

    @Override
    public String getStringValue() {

        // logic to call the API to get the value from response using the valuePath
        return null;
    }
}
