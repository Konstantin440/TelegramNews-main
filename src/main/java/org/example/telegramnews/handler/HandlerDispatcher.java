package org.example.telegramnews.handler;


import org.example.telegramnews.bot.BotNews;
import org.example.telegramnews.entity.Article;
import org.example.telegramnews.service.NewsClientServiceImpl;
import org.example.telegramnews.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Service

public class HandlerDispatcher {
    private final List<CommandHandler> commandHandlers = new ArrayList<>();

    @Autowired
    public HandlerDispatcher(StartCommand startCommand) {
        this.commandHandlers.add(startCommand);
    }


    public boolean dispatch(Update update, BotNews bot) throws TelegramApiException {
        String commandText = update.getMessage().getText();
        for (CommandHandler commandHandler : commandHandlers) {
            if (commandHandler.getCommand().equals(commandText)) {
                commandHandler.sendMessage(update, bot);
                return true;
            }
        }
        commandNotFound(update, bot);
        return false;
    }

    public void answerOnText(Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        if (update.getMessage().getText() != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Функционал бота ограничен только отправкой новостей");
            bot.execute(sendMessage);

        }
    }

    private void commandNotFound(Update update, TelegramLongPollingBot bot) throws TelegramApiException {
        if (update.getMessage().getText() != null) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Команда не найдена");
            bot.execute(sendMessage);
        }
    }
}


//Создать метод который принимает лист ,массив,и 2 числа
//после найдет сумму всех чисел их и вернет обратно лист с длинной  как (сумма чисел всех этих данных)
//
//    public List<Integer> task1 (List<Integer> numbers, int[] massive, int first, int second) {
//        int sum = 0;
//        for (int i = 0; i < numbers.size(); i++) {
//            sum += numbers.get(i) + massive[i] + first + second;
//        }
//        int[] sumNumbers = new int[sum];
//        List<Integer> integers = new ArrayList<>();
//        integers.addAll(integers);
//
//        return integers;
//    }
////
    //Задачи 2
    //создать метод который принимает 3 строки и 1 массив строк
    //после вернет длинну всех 3 строк +  длинну всех строк из массива
//
//    public int task2(String a, String b, String c, String[] massive) {
//        return a.length() + b.length() + c.length() + massive.length;
//    }
//
////    //Задача 3
////    //Создать метод который принимает сканер и 2 числа
////    //сам метод используя сканнер принимает от нас знак + или - например
////    //и производит матем вычисление с этими числами в зависимости от знака
////    //после вернет обратно результат
////
//    public int task3(Scanner scanner, int a, int b) {
//        int result = 0;
//        System.out.println("Вводим знак");
//        char c = scanner.nextLine().charAt(0);
//
//        if (c == '+') {
//            result = a + b;
//        } else if (c == '-') {
//            result = a - b;
//        }
//        return result;
//    }
//
//

