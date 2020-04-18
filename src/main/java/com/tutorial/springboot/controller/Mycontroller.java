package com.tutorial.springboot.controller;



import com.tutorial.springboot.api.BlogSearchApi;
import com.tutorial.springboot.api.CafeSearchApi;
import com.tutorial.springboot.api.NewsSearchApi;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
    @RequestMapping("/{Search_Target}")
    public String index(@PathVariable String Search_Target) {
        BlogSearchApi blogSearchApi = new BlogSearchApi();
        NewsSearchApi newsSearchApi = new NewsSearchApi();
        CafeSearchApi cafeSearchApi = new CafeSearchApi();

        JSONObject blogSearchResult = null;
        JSONObject newsSearchResult = null;
        JSONObject cafeSearchResult = null;
        try {
            blogSearchResult = new JSONObject(blogSearchApi.Search(Search_Target));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            newsSearchResult = new JSONObject(newsSearchApi.Search(Search_Target));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            cafeSearchResult = new JSONObject(cafeSearchApi.Search(Search_Target));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            return  "blog" + blogSearchResult.get("total").toString()+"\n news"+newsSearchResult.get("total")+"cafe"+cafeSearchResult.get("total") ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "sd";
    }

}
