package com.claudemir.backend.service;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.response.CawlGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SingletonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonService.class);

    private static SingletonService instance;

    private List<CawlDto> list;

    public static void add(CawlDto cawlDto) {
        instance.list.add(cawlDto);
    }

    public static SingletonService getInstance() {
        if (instance == null) {
            instance = new SingletonService();
        }
        return instance;
    }

    public SingletonService(CawlDto cawlDto) {
        this.list = new ArrayList<>();
        this.list.add(cawlDto);
    }

    public SingletonService() {
        this.list = new ArrayList<>();
    }

    public List<CawlDto> getList() {
        return list;
    }

    public void setList(List<CawlDto> list) {
        this.list = list;
    }

    public CawlDto findById(String value) {
            return this.list.stream().peek(num -> System.out.println("will filter " + num.getId()))
                    .filter(x -> x.getId().equalsIgnoreCase(value))
                    .findFirst().orElseThrow(() -> new NotFoundException("NOT FOUND ID " + value ));


    }
}
