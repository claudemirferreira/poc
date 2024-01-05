package com.claudemir.backend.service;

import com.claudemir.backend.response.CawlGetResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SingletonServiceTest {

    @Test
    public void add_cawl(){
        CawlGetResponse cawlGetResponse = new CawlGetResponse("1", "activite", List.of("teste"));
        SingletonService singletonService = SingletonService.getInstance(cawlGetResponse);
        Assertions.assertEquals(1, singletonService.getList().size());

        cawlGetResponse = new CawlGetResponse("2", "done", List.of("http"));
        singletonService = SingletonService.getInstance(cawlGetResponse);
        Assertions.assertEquals(2, singletonService.getList().size());
    }

}
