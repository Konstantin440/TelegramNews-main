package org.example.telegramnews.bot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.telegramnews.handler.HandlerDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
@Getter
public class BotNews extends TelegramLongPollingBot {
    private final HandlerDispatcher handlerDispatcher;


    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;


    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        boolean checkCommand = update.hasMessage() && update.getMessage().hasText() &&
                update.getMessage().getText().startsWith("/");

        if (checkCommand) {
            handlerDispatcher.dispatch(update,this);
        }
        else {
            handlerDispatcher.answerOnText(update,this);
        }
    }

    }

