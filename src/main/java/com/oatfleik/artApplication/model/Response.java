package com.oatfleik.artApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("total")
    private String total;
    @JsonProperty("objectIDs")
    private String[] objectIDs;
}
