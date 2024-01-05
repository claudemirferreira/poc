package com.axreng.backend.service;

import com.axreng.backend.response.CawlGetResponse;
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

    public SingletonService(CawlGetResponse value){
        this.list = new ArrayList<>();
        this.list.add(value);
    }

    public List<CawlGetResponse> getList() {
        return list;
    }

    public void setList(List<CawlGetResponse> list) {
        this.list = list;
    }
}
