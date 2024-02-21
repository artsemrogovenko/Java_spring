package com.example.taskstorage.observer;

import com.example.taskstorage.services.TelegramNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TaskCompleteListener implements ApplicationListener<TaskCompleteEvent> {
    @Autowired
    private TelegramNotificationService telegramNotificationService;
    @Override
    public void onApplicationEvent(TaskCompleteEvent event) {
        // TODO сделато что то с event.getTask();
        telegramNotificationService.sendTelegramMessage(event.getTask().toString());
    }

}
