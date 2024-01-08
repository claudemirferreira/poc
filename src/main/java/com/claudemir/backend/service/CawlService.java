package com.claudemir.backend.service;

import com.claudemir.backend.dto.CawlDto;
import com.claudemir.backend.enuns.StatusEnum;
import com.claudemir.backend.util.BufferedReaderUtil;
import com.claudemir.backend.util.GenerateIdUtil;
import com.claudemir.backend.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CawlService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CawlService.class);

    private SingletonService instance;

    private StringUtil stringUtil = new StringUtil();

    private String BASE_URL = System.getenv("BASE_URL");

    public CawlService(){
        instance = SingletonService.getInstance();
    }

    public CawlDto post(CawlDto cawlDto){
        cawlDto.setId(GenerateIdUtil.gerarCodigo());
        findUrl(cawlDto);
        return cawlDto;
    }

    public CompletableFuture<Void> findUrl(CawlDto cawlDto) {
        return CompletableFuture.runAsync(() -> {
            LOGGER.info("Iniciando o método assíncrono...");
            LOGGER.info("BASE_URL = {}", BASE_URL);
            if (BASE_URL == null){
                LOGGER.info("set valeu BASE_URL = {}", BASE_URL);
                BASE_URL = "http://hiring.axreng.com/";
            }
            try {
                extractLinks(BufferedReaderUtil.getContent(BASE_URL), cawlDto);
                //set status done

            } catch (IOException e) {
                LOGGER.error("{}", e.getMessage());
                throw new RuntimeException(e);
            }
            LOGGER.info("2 Método assíncrono concluído.");
        });
    }

    public CawlDto get(String id){
       return instance.findById(id);
    }

    public void extractLinks(String content, CawlDto cawlDto) {
        String regex = "\\b(?:https?|ftp):\\/\\/[-A-Z0-9+&@#\\/%?=~_|!:,.;]*[-A-Z0-9+&@#\\/%=~_|]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        cawlDto.setStatus(StatusEnum.ACTIVE.toString());
        SingletonService.add(cawlDto);

        while (matcher.find()) {
            String link = matcher.group();
            System.out.println("Link encontrado: " + link);
            try {
                // check nesse link tem palavra
                if ( getContent(link)){
                    cawlDto.getUrls().add(link);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // seta status done
        cawlDto.setStatus(StatusEnum.DONE.name());
    }

    public boolean getContent(String url) throws IOException {
        String content = BufferedReaderUtil.getContent(url);
        LOGGER.info("{}", url);
        LOGGER.info("{}", content);
        return stringUtil.containValue(content, url);
    }

}
