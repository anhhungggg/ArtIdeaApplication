package com.oatfleik.artApplication.controller;

import com.oatfleik.artApplication.model.Response;
import com.oatfleik.artApplication.service.ArtApi;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/art")
@RestController
public class IndexController {

    private ArtApi artApi = new ArtApi();

    @GetMapping
    public Response artSearch(@RequestParam (defaultValue = "a")String query){
        return artApi.queryForArt(query);
    }
}
