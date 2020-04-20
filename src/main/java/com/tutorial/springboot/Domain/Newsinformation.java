package com.tutorial.springboot.Domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class Newsinformation {
    private List<item>items;
    private DateTimeFormat lastBuild;
    private Integer total;
    private Integer start;
    private Integer display;

    @Data
    public class item{
        private String title;
        private String link;
        private String description;
    }
}
