package com.tutorial.springboot.controller;


import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import com.tutorial.springboot.Domain.BlogInformation;
import com.tutorial.springboot.Domain.CafeInformation;
import com.tutorial.springboot.Domain.Newsinformation;
import com.tutorial.springboot.api.BlogSearchApi;
import com.tutorial.springboot.api.CafeSearchApi;
import com.tutorial.springboot.api.NewsSearchApi;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {

    @RequestMapping("/Seasch/{Search_Target}")
    public String testapi(@PathVariable String Search_Target) {
        String string ="";
        BlogSearchApi blogSearchApi = new BlogSearchApi();
        NewsSearchApi newsSearchApi = new NewsSearchApi();
        CafeSearchApi cafeSearchApi = new CafeSearchApi();

        String blogSearchResult = blogSearchApi.Search(Search_Target);
        String newsSearchResult = newsSearchApi.Search(Search_Target);
        String cafeSearchResult = cafeSearchApi.Search(Search_Target);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Newsinformation newsinformation = gson.fromJson(newsSearchResult, Newsinformation.class);
        CafeInformation cafeInformation = gson.fromJson(cafeSearchResult, CafeInformation.class);
        BlogInformation blogInformation = gson.fromJson(blogSearchResult, BlogInformation.class);

        return cafeInformation.getTotal().toString();
    }
    @RequestMapping("/news/{Search_Target}")
    public String newsInfo(@PathVariable String Search_Target) {
        NewsSearchApi newsSearchApi = new NewsSearchApi();
        String newsSearchResult = newsSearchApi.Search(Search_Target);
        return newsSearchResult;
    }
}
