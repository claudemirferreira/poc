package com.claudemir.backend;

import com.claudemir.backend.builder.CawlCreateResponseBuilder;
import com.claudemir.backend.builder.CawlGetResponseBuilder;
import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.request.CawlCreateRequest;
import com.claudemir.backend.response.CawlCreateResponse;
import com.claudemir.backend.response.CawlGetResponse;
import com.claudemir.backend.service.CawlService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(CawlService.class);
    public static void main(String[] args) {

        final CawlService cawlService = new CawlService();
        final Gson gson = new Gson();
        final CawlCreateResponseBuilder cawlCreateResponseBuilder = new CawlCreateResponseBuilder();
        final CawlGetResponseBuilder cawlGetResponseBuilder = new CawlGetResponseBuilder();

        post("/crawl", (req, res) -> {
            res.type("application/json");
            CawlCreateRequest cawlCreateRequest = gson.fromJson(req.body(), CawlCreateRequest.class);
            if ( !validateKeyword (cawlCreateRequest.getKeyword())) {
                res.status(400);
                return "o tamanho keyword não pode ser menor que 4 e maior que 32";
            }
            CawlCreateResponse cawlCreateResponse = cawlCreateResponseBuilder.create(cawlService.post(new CawlDto( cawlCreateRequest.getKeyword())));
            return gson.toJson(cawlCreateResponse);
        });

        get("/crawl/:id", (request, response) -> {
            response.type("application/json");
            try {
                CawlGetResponse cawlGetResponse = cawlGetResponseBuilder.create(cawlService.get(request.params("id")));
              return gson.toJson(cawlGetResponse);
            } catch (NotFoundException e){
                LOGGER.error("{}", e.getMessage());
            }
            response.status(404);
            return "crawl not found: " + request.params("id");
        });
    }

    public static boolean validateKeyword(String value){
        return value.length() > 3 && value.length() < 33;
    }
}
