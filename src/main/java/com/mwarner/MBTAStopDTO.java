package com.mwarner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@JsonIgnoreProperties(ignoreUnknown = true)
@Type("stop")
public class MBTAStopDTO {

    @Id
    private String id;
    private String name;

    public MBTAStopDTO() {
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
