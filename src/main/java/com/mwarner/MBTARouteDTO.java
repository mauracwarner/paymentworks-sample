package com.mwarner;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("route")
public class MBTARouteDTO {

    @Id
    private String id;
    @JsonAlias("long_name")
    private String name;

    public MBTARouteDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
