package com.claudemir.backend.builder;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.response.CawlGetResponse;

public class CawlGetResponseBuilder {

    public CawlGetResponse create(CawlDto cawlDto){
        return new CawlGetResponse(cawlDto.getId(), cawlDto.getStatus(), cawlDto.getUrls());
    }
}
