package com.claudemir.backend.service;

import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.response.CawlGetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SingletonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingletonService.class);

    private static SingletonService instance;

    private List<CawlGetResponse> list;

    public static SingletonService getInstance(CawlGetResponse value) {
        if (instance == null) {
            instance = new SingletonService(value);
        } else {
            instance.list.add(value);
        }
        return instance;
    }

    public SingletonService(CawlGetResponse value) {
        this.list = new ArrayList<>();
        this.list.add(value);
    }

    public List<CawlGetResponse> getList() {
        return list;
    }

    public void setList(List<CawlGetResponse> list) {
        this.list = list;
    }

    public CawlGetResponse findById(String value) {
            return this.list.stream().peek(num -> System.out.println("will filter " + num.getId()))
                    .filter(x -> x.getId().equalsIgnoreCase(value))
                    .findFirst().orElseThrow(() -> new NotFoundException("NOT FOUND ID " + value ));


    }
}
