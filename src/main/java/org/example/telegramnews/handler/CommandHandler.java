package org.example.telegramnews.handler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface CommandHandler {

    String getCommand(); //получение команды
    void sendMessage(Update update, TelegramLongPollingBot bot) throws TelegramApiException;  // Выполняет действие

}


