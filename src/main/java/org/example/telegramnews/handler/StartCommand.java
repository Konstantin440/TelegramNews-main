package org.example.telegramnews.handler;

import lombok.RequiredArgsConstructor;
import org.example.telegramnews.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class StartCommand implements CommandHandler {
    private final SubscriptionService subscriptionService;


    @Autowired
    public StartCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public void sendMessage(Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        Long chatId = update.getMessage().getChatId();
        subscriptionService.subscribe(chatId);

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Добро пожаловать в бота для получения новостей по JAVA! " +
                "Каждый день вы будете получать свежие новости из вселенной IT. Спасибо, что вы с нами!");
        bot.execute(message);
    }
}