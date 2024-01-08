package com.claudemir.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class CawlDto {

    private String id;

    private String keyword;
    private String status;

    private List<String> urls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public CawlDto(){}

    public CawlDto(String keyword){
        this.keyword = keyword;
        this.urls = new ArrayList<>();
    }

    public CawlDto(String id, String keyword, String status, List<String> urls) {
        this.id = id;
        this.keyword = keyword;
        this.status = status;
        this.urls = urls;
    }
}
