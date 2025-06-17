package org.example.telegramnews.config;

import org.example.telegramnews.bot.BotNews;
import org.example.telegramnews.client.NewsClient;
import org.example.telegramnews.service.NewsClientServiceImpl;
import org.example.telegramnews.service.SubscriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(BotNews bot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
        return botsApi;
    }
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public NewsClientServiceImpl newsClientService() {
        NewsClientServiceImpl newsClientService = new NewsClientServiceImpl(new NewsClient(new RestTemplate()));
        return newsClientService;
    }


}
