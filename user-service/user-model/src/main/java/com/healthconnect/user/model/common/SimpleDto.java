package com.healthconnect.user.model.common;

public class SimpleDto {

    private long id;
    private String name;

    public SimpleDto() {

    }
    public SimpleDto(long id) {
        this.id = id;
    }
    

    public SimpleDto(long id,String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

