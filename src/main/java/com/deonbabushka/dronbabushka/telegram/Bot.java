package com.deonbabushka.dronbabushka.telegram;

import com.deonbabushka.dronbabushka.dialogs.MainParser;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;


public class Bot extends TelegramLongPollingBot {
    MainParser parser;

    public Bot(DefaultBotOptions options, MainParser parser) {
        super(options);
        this.parser = parser;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("message1 " + update.getMessage().getText());
        Message message = update.getMessage();
        SendMessage message1 = new SendMessage();
        message1.setChatId(message.getChatId());
        message1.setText("привет " + message.getText());
        try {
            execute(message1);
        } catch (TelegramApiException e) {

        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
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

