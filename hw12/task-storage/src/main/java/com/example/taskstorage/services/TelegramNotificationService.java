package com.example.taskstorage.services;

import com.example.taskstorage.aop.TrackUserAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${tg.chatId}")
    private String chatId;

    @TrackUserAction
    public void sendTelegramMessage(String message) {
        String emoji = "\uD83C\uDF1F"; // это эмодзи "🌟"
        String msg= emoji + message;
        String result = "https://api.telegram.org/bot" + botToken + "/sendMessage?chat_id=" + chatId + "&text=" + msg;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(result, String.class);
    }
}
