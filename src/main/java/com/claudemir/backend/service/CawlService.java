package com.claudemir.backend.service;

import com.claudemir.backend.exception.NotFoundException;
import com.claudemir.backend.request.CawlCreateRequest;
import com.claudemir.backend.response.CawlCreateResponse;
import com.claudemir.backend.response.CawlGetResponse;
import com.claudemir.backend.util.BufferedReaderUtil;
import com.claudemir.backend.util.GenerateIdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CawlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CawlService.class);

    private SingletonService instance;

    private String BASE_URL = System.getenv("BASE_URL");

    public CawlService(){
        instance = SingletonService.getInstance();
    }

    public CawlCreateResponse post(CawlCreateRequest cawlCreateRequest){
        String id = GenerateIdUtil.gerarCodigo();
        findUrl(id);
        return new CawlCreateResponse(id);
    }

    public CompletableFuture<Void> findUrl(String codigo) {
        return CompletableFuture.runAsync(() -> {
            LOGGER.info("Iniciando o método assíncrono...");
            LOGGER.info("BASE_URL = {}", BASE_URL);
            if (BASE_URL == null){
                LOGGER.info("set valeu BASE_URL = {}", BASE_URL);
                BASE_URL = "http://hiring.axreng.com/";
            }
            try {
                extractLinks(BufferedReaderUtil.getContent(BASE_URL));
                //set status done
                CawlCreateResponse cawlCreateResponse = new CawlCreateResponse(codigo);

            } catch (IOException e) {
                LOGGER.error("{}", e.getMessage());
                throw new RuntimeException(e);
            }
            LOGGER.info("2 Método assíncrono concluído.");
        });
    }

    public CawlGetResponse get(String id){
       return instance.findById(id);
    }

    public void extractLinks(String content) {
        String regex = "\\b(?:https?|ftp):\\/\\/[-A-Z0-9+&@#\\/%?=~_|!:,.;]*[-A-Z0-9+&@#\\/%=~_|]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String link = matcher.group();
            System.out.println("Link encontrado: " + link);

            // check nesse link tem palavra

        }
        // seta status done
    }

}
