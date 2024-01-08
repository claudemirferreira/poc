package com.claudemir.backend.enuns;

public enum StatusEnum {
    ACTIVE("active"),
    DONE("done");

    private final String name;

    private StatusEnum(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
