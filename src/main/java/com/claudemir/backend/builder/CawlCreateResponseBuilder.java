package com.claudemir.backend.builder;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.response.CawlCreateResponse;

public class CawlCreateResponseBuilder {

    public CawlCreateResponse create(CawlDto cawlDto){
        return new CawlCreateResponse(cawlDto.getId());
    }
}
