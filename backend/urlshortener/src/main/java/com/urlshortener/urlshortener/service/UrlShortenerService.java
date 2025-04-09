package com.urlshortener.urlshortener.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortener.urlshortener.model.UrlMapping;
import com.urlshortener.urlshortener.repository.UrlMappingrepository;


@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingrepository repository;

    private String baseUrl = " http://localhost:8080/api/"; 
    
    

    public String shortenUrl(String originalUrl) {
        String shortUrl = generateRandomString(6);
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        repository.save(urlMapping);
        return baseUrl + shortUrl; 
    }
    public Optional<String> getOriginalUrl(String shortUrl) {
        UrlMapping mapping = repository.findByShortUrl(shortUrl).orElse(null);
             return (mapping != null) ? Optional.ofNullable(mapping.getOriginalUrl()) : Optional.empty();
    }





    private String generateRandomString(int length){

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stb = new StringBuilder();
        Random random = new Random();
        int i=0 ;
        while(i<length){
            stb.append(characters.charAt(random.nextInt(characters.length())));
            i++;
        }
        return stb.toString();

    }



}
