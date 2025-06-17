package org.example.telegramnews.client;

import lombok.RequiredArgsConstructor;
import org.example.telegramnews.entity.NewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class NewsClient {
    private final RestTemplate restTemplate;


    private String apiKey = "351a0ed4ce844e969d6c91a39a982ef3";

    public NewsResponse getNewsResponse(LocalDate fromDate, String keyword) {
        String url = UriComponentsBuilder.fromHttpUrl("https://newsapi.org/v2/everything")
                .queryParam("q", keyword)
                .queryParam("from", fromDate.toString())
                .queryParam("language", "ru")
                .queryParam("sortBy", "publishedAt")
                .queryParam("apiKey", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, NewsResponse.class);
    }
}
