package com.claudemir.backend.service;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.response.CawlGetResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonServiceTest {

    @Test
    public void add_cawl(){
        SingletonService singletonService = SingletonService.getInstance();
        CawlDto cawlDto = new CawlDto("1", "linux", "activite", List.of("teste"));
        SingletonService.add(cawlDto);
        assertEquals(1, singletonService.getList().size());

        cawlDto = new CawlDto("2", "linux", "done", List.of("http"));
        SingletonService.add(cawlDto);
        assertEquals(2, singletonService.getList().size());
    }


    @Test
    public void find_sucess(){

        SingletonService singletonService = SingletonService.getInstance();
        CawlDto cawlDto = new CawlDto("1", "linux", "activite", List.of("teste"));
        SingletonService.add(cawlDto);

        cawlDto = new CawlDto("2", "linux", "done", List.of("http"));
        SingletonService.add(cawlDto);

        CawlDto result = singletonService.findById("2");
        assertEquals("2", result.getId());
    }

    @Test
    public void not_found(){

        SingletonService singletonService = SingletonService.getInstance();
        CawlDto cawlDto = new CawlDto("1","linux", "activite", List.of("teste"));
        SingletonService.add(cawlDto);
        cawlDto = new CawlDto("2","linux", "done", List.of("http"));
        SingletonService.add(cawlDto);

        try {
            singletonService.findById("3");
            Assertions.assertTrue(Boolean.FALSE);
        } catch (NotFoundException e){
            Assertions.assertTrue(Boolean.TRUE);
        }

    }

}
