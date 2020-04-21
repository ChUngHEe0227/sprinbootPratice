package com.tutorial.springboot.Domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;


public class WebdocumentInfomation {

    private List<item> items;
    private DateTimeFormat lastBuild;
    private Integer total;
    private Integer start;
    private Integer display;

    @Data
    public class item{
        private String title;
        private String link;
        private String description;
        private DateTimeFormat postdate;
    }
    public Integer ItemDeduplication(){
        Integer Duplicate=0;
        for (int i=0;i<items.size();i++){
            for (int j=i+1;j<items.size();j++){
                if(items.get(i).description.equals(items.get(j).description)) {
                    items.remove(j);
                    Duplicate++;
                    j--;
                }
            }
        }
        return  Duplicate+1;
    }
}
