package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.User;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

@Service
public class AdminScenario {
    public ArrayList<SendMessage> onMessage(Message message, User user) {return null;}
}
