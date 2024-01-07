package com.claudemir.backend.service;

import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.response.CawlGetResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonServiceTest {

    @Test
    public void add_cawl(){
        CawlGetResponse cawlGetResponse = new CawlGetResponse("1", "activite", List.of("teste"));
        SingletonService singletonService = SingletonService.getInstance(cawlGetResponse);
        assertEquals(1, singletonService.getList().size());

        cawlGetResponse = new CawlGetResponse("2", "done", List.of("http"));
        singletonService = SingletonService.getInstance(cawlGetResponse);
        assertEquals(2, singletonService.getList().size());
    }


    @Test
    public void find_sucess(){

        CawlGetResponse cawlGetResponse = new CawlGetResponse("1", "activite", List.of("teste"));
        SingletonService singletonService = SingletonService.getInstance(cawlGetResponse);

        cawlGetResponse = new CawlGetResponse("2", "done", List.of("http"));
        singletonService = SingletonService.getInstance(cawlGetResponse);

        CawlGetResponse result = singletonService.findById("2");
        assertEquals("2", result.getId());
    }

    @Test
    public void not_found(){

        CawlGetResponse cawlGetResponse = new CawlGetResponse("1", "activite", List.of("teste"));
        SingletonService singletonService = SingletonService.getInstance(cawlGetResponse);
        cawlGetResponse = new CawlGetResponse("2", "done", List.of("http"));
        singletonService = SingletonService.getInstance(cawlGetResponse);

        try {
            singletonService.findById("3");
            Assertions.assertTrue(Boolean.FALSE);
        } catch (NotFoundException e){
            Assertions.assertTrue(Boolean.TRUE);
        }

    }

}
