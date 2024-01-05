package com.axreng.backend;

import com.axreng.backend.request.CawlCreateRequest;
import com.axreng.backend.service.CawlService;
import com.google.gson.Gson;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main(String[] args) {

        final CawlService cawlService = new CawlService();
        final Gson gson = new Gson();

        post("/crawl", (req, res) -> {
            res.type("application/json");
            CawlCreateRequest cawlCreateRequest = gson.fromJson(req.body(), CawlCreateRequest.class);
            if ( !validateKeyword (cawlCreateRequest.getKeyword())) {
                res.status(400);
                return "o tamanho keyword não pode ser menor que 4 e maior que 32";
            }
            return gson.toJson(cawlService.post(cawlCreateRequest));
        });

        get("/crawl/:id", (request, response) -> {
            response.type("application/json");
            return gson.toJson(cawlService.get(request.params("id")));
        });
    }

    public static boolean validateKeyword(String value){
        return value.length() > 3 && value.length() < 33;
    }
}
