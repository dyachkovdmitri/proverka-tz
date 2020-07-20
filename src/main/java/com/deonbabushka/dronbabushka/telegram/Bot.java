package com.deonbabushka.dronbabushka.telegram;


import com.deonbabushka.dronbabushka.scenarios.FirstFilter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Bot extends TelegramLongPollingBot {
    FirstFilter filter;

    public Bot(DefaultBotOptions options, FirstFilter filter) {
        super(options);
        this.filter = filter;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("message1 " + update.getMessage().getText());
        Message message = update.getMessage();
        String answer = filter.onMessage(update.getMessage());
        SendMessage message1 = new SendMessage();
        message1.setChatId(message.getChatId());
        message1.setText(answer);
        try {
            execute(message1);
        } catch (TelegramApiException e) {

        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            System.out.println("message1 " + update.getMessage().getText());
            Message message = update.getMessage();
            String answer = filter.onMessage(update.getMessage());
            SendMessage message1 = new SendMessage();
            message1.setChatId(message.getChatId());
            message1.setText(answer);
            try {
                execute(message1);
            } catch (TelegramApiException e) {

            }
        }


    }

    @Override
    public String getBotUsername() {
        return "dronBabushkabot";
    }

    @Override
    public String getBotToken() {
        return "911235262:AAErB_kYKeC5NgM_MFCFGwVHMhCmTytHgB8";
    }

}

