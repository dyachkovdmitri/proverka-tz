package com.deonbabushka.dronbabushka.telegram;


import com.deonbabushka.dronbabushka.scenarios.FirstFilter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://tlgrm.ru/docs/bots/api#replykeyboardmarkup

public class Bot extends TelegramLongPollingBot {
    FirstFilter filter;

    public Bot(DefaultBotOptions options, FirstFilter filter) {
        super(options);
        this.filter = filter;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("message1 " + update.getMessage().getText());

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        for (Update update : updates) {
            System.out.println("message1 " + update.getMessage().getText());
            List<SendMessage> messes= filter.onMessage(update.getMessage());
            if(messes == null) {messes = new ArrayList<>(); messes.add(new SendMessage(update.getMessage().getChatId(), "минуточку..."));}

            for (SendMessage mess : messes) {
                try {
                    execute(mess);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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

