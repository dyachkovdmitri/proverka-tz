package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.UserToUser;
import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.repos.UserToUserRepo;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MeetScenario {
    @Autowired
    ParentRepo parentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserToUserRepo c2pRepo;

    public ArrayList<SendMessage> onMessage(User parent, Message message) {

        switch (parent.getStage()) {
            case 1: {
                parent.setStage(2);
                userRepo.save(parent);
                return message(parent.getChatId(), "Привет! Давайте знакомится! Как Вас зовут?");
            }
            case 2: {
                parent.setStage(3);
                parent.setName(message.getText());
                parentRepo.save(parent);
                return message(parent.getChatId(), "Давайте попробуем подключить вашего ребенка! Какой у него @аккаунт в телеграмм?");
            }
            case 3: {
                if (message.getText().contains("@")) {
                    parent.setStage(4);
                    parent.setInfo("childName:" + message.getText());
                    parentRepo.save(parent);
                    return message(parent.getChatId(), parent.getName() + "Прекрасно! Я робот и мне запрещено писать первой! Поэтому ребенку придется написать мне первой, для этого нужно в строке поиск найти мой аккаунт @proverka-dz и написать мне все что угодно!");
                } else {
                    return message(parent.getChatId(), "У Вашего ребенка должен быть скачан телеграмм. Аккаунт начинается обычно на @!");     //todo несколько вариантов                      ;
                }
            }
            case 4: {
                UserToUser oneByParentId = c2pRepo.findOneByParentId(parent.getChatId());
                if (oneByParentId != null) {
                    User oneById = userRepo.findOneByChatId(oneByParentId.getChildId());
                    if (oneById != null && oneById.getChatId() != null) {
                        parent.setStage(5);
                        parentRepo.save(parent);
                        return message(parent.getChatId(), "Ура, ваш ребенок написал мне! С нетерпением жду 1 сентября!");
                    } else {
                        return message(parent.getChatId(), "Я все еще жду пока ваше ребенок напишет мне! Ему нужно найти меня по имени"); //todo несколько варинтов
                    }
                }
                return message(parent.getChatId(), "Я все еще жду пока ваше ребенок напишет мне! Ему нужно найти меня по имени");

            }
        }
        return message(parent.getChatId(), "робот запутался и не знает что делать");
    }

    ArrayList<SendMessage> message(Long chatId, String message) {
        ArrayList<SendMessage> msgs = new ArrayList<>();
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        msg.setReplyMarkup(null);
        msgs.add(msg);
        return msgs;
    }

    List<SendMessage> messages(ArrayList<SendMessage> msgs, Long chatId, String message) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setReplyMarkup(null);
        msg.setChatId(chatId);
        msgs.add(msg);
        return msgs;
    }

}





