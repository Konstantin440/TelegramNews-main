package org.example.telegramnews.entity;

import lombok.Data;

@Data
public class Article {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;


}