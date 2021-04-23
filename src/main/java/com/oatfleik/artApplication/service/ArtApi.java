package com.oatfleik.artApplication.service;

import com.oatfleik.artApplication.configuration.ArtApplicationConfig;
import com.oatfleik.artApplication.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ArtApi {

    @Autowired
    private ArtApplicationConfig config;

    @Autowired
    private RestTemplate restTemplate;

    public Response queryForArt(String query){
        String url = config.getSearchUrl() + query;
        ResponseEntity<Response> entity = restTemplate.getForEntity(url, Response.class);

        Response body = entity.getBody();
        return body;
    }
}
