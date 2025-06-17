package org.example.telegramnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelegramNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramNewsApplication.class, args);
    }

}
