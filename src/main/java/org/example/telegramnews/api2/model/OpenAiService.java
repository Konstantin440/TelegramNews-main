package org.example.telegramnews.api2.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
public class OpenAiService {

    String url = "https://api.proxyapi.ru/openai/v1/chat/completions";
    final String apiKey = "sk-YwyW11OKRiSjY0TpjPLSRjuhIbK3ZklR"; // мой апи ключ
//    String model = "deepseek-ai/DeepSeek-R1-Distill-Qwen-32B"; // current model of chatgpt api

    //    String url = "sk-Is65NfZB3weUvsEkhzgCStVpKnLqneLMAC3k8JlkexhW9xySswWnaUAJmlxh"; // мой апи ключ
    String model = "gpt-4.1-mini"; // current model of chatgpt api


    public String getMessage(String readyRequest) {
        String responseAi = ""; //Переменная для хранения ответа от AI.

        responseAi = sendRequest(readyRequest); // Отправляем запрос и получаем ответ от AI.


        return responseAi; // Возвращаем ответ от AI.
    }


    public String sendRequest(String readyRequest) {
        try {
            URL objURL = new URL(url); // // Создаем объект URL с адресом API.
            HttpURLConnection con = (HttpURLConnection) objURL.openConnection(); // Открываем соединение с API.

            con.setRequestMethod("POST"); // Устанавливаем метод запроса на POST.
            con.setRequestProperty("Authorization", "Bearer " + apiKey); // Устанавливаем заголовок авторизации с API ключом.
            con.setRequestProperty("Content-Type", "application/json"); // Устанавливаем тип контента на JSON.

            // Формируем JSON для запроса
            String jsonString = getJsonString(model, readyRequest); // Получаем JSON строку из метода getJsonString.
            con.setDoOutput(true); // Разрешаем отправку данных.

            // Записываем JSON в запрос
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes(StandardCharsets.UTF_8); // Преобразуем строку в массив байт.
                os.write(input, 0, input.length); // Отправляем массив байт в запрос.
            }

            // Проверяем код ответа от API
            int responseCode = con.getResponseCode(); // Получаем код ответа от API.
            System.out.println("\nSending 'POST' request to URL : " + url); // Логируем URL запроса.
            System.out.println("Post parameters : " + jsonString); // Логируем тело запроса.
            System.out.println("Response Code : " + responseCode); // Логируем код ответа.


            // Читаем ответ от API  // Если код ответа OK, читаем из входного потока, иначе - из потока ошибок.
            InputStream inputStream = (responseCode == HttpURLConnection.HTTP_OK) ? con.getInputStream() : con.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream)); /// Создаем BufferedReader для чтения данных.
            String collectJson = br.lines().collect(Collectors.joining("\n")); // Читаем все строки и объединяем в одну.

            // Если код ответа не OK, выводим ошибку
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Ошибка: " + collectJson); // Выводим ошибку в консоль
            }

            return getTextMessageFromJson(collectJson);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // В случае ошибки вернуть null
    }

    public String getTextMessageFromJson(String json) {
        ObjectMapper objectMapper = new ObjectMapper(); //объект ObjectMapper для работы с JSON
        try {
            // Десериализуем JSON в объект ChatResponse.
            ChatResponse chatResponse = objectMapper.readValue(json, ChatResponse.class); //
            return chatResponse.getMessageResponse(); //// Возвращаем ответное сообщение из объекта ChatResponse.
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

    //// Формируем JSON-строку для отправки запроса, используя заданные модель и сообщение.
    private String getJsonString(String model, String message) {
        String body = """
                {
                    "model": "%s",
                    "messages": [
                        {
                            "role": "user",
                            "content": "%s"
                        }
                    ],
                    "temperature": 0.9
                }
                """.formatted(model, message).trim();//Форматируем строку с подстановкой аргументов и убираем лишние пробелы.

        return body; //// Возвращаем сформированную JSON-строку.
    }
}