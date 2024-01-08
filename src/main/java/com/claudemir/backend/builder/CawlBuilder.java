package com.claudemir.backend.builder;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.request.CawlCreateRequest;
import com.claudemir.backend.response.CawlGetResponse;

public class CawlBuilder {

    public CawlDto toCawlDto(CawlCreateRequest cawlCreateRequest){
        CawlDto cawlDto = new CawlDto();
        cawlDto.setKeyword(cawlCreateRequest.getKeyword());
        return cawlDto;
    }

}
