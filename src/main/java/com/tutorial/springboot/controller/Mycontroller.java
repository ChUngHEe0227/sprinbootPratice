package com.tutorial.springboot.controller;

import com.tutorial.springboot.api.BlogSearchApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
    @RequestMapping("/{Search_Target}")
    public String index(@PathVariable String Search_Target) {
        BlogSearchApi blogSearchApi =new BlogSearchApi();

        return  blogSearchApi.Search(Search_Target);
    }

}
