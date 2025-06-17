package org.example.telegramnews.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.telegramnews.api2.model.OpenAiService;
import org.example.telegramnews.bot.BotNews;
import org.example.telegramnews.entity.Article;


import org.example.telegramnews.service.NewsClientServiceImpl;
import org.example.telegramnews.service.SubscriptionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Optional;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class NewsScheduler {
    private static final Logger log = LoggerFactory.getLogger(NewsScheduler.class);
    private final BotNews botNews;
    private final NewsClientServiceImpl newsClientService;
    private final SubscriptionService subscriptionService;
    private final OpenAiService openAiService;



    @SneakyThrows
    @Scheduled(cron = "0 * * * * *") // Каждую минуту
    public void sendNewsEveryDay() {
        log.info("Метод стартовал");
        Optional<Article> latestJavaNews = newsClientService.getLatestJavaNews();
        if (latestJavaNews.isPresent()) {
            String messageText = newsClientService.formatNewsMessage(latestJavaNews.get());
            Set<Long> allSubscribers = subscriptionService.getSubscribers();

            log.info("Получены подписчики " + allSubscribers);
            for (Long chatId : allSubscribers) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(messageText);
                sendMessage.setParseMode("HTML"); // Если используем HTML в форматировании
                botNews.execute(sendMessage);
            }
        } else {
            responseAI();
        }
    }


    @SneakyThrows
    public void responseAI() {
        String message = openAiService.getMessage("Расскажи лайфхак про JAVA. Сообщение начни со слов," +
                " вместо новостей я расскажу лайфхак. В конце сообщения не предлагай помощь ");
        Set<Long> allSubscribers = subscriptionService.getSubscribers();
        for (Long chatId : allSubscribers) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);
            sendMessage.setParseMode("MARKDOWN");
            botNews.execute(sendMessage);
        }
    }
}


