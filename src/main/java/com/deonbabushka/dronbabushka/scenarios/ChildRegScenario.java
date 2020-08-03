package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.entities.UserToUser;
import com.deonbabushka.dronbabushka.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

@Service
public class ChildRegScenario {

    @Autowired
    UserRepo userRepo;

    public ArrayList<SendMessage> onMessage(User child, Message message) {
        switch (child.getStage()) {
            case 1: {
                child.setStage(2);
                userRepo.save(child);
                return message(child.getChatId(), "Привет! Давайте знакомится! Как Вас зовут?");
            }
            case 2: {
                child.setStage(3);
                child.setName(message.getText());
                parentRepo.save(child);
                return message(child.getChatId(), "Давайте попробуем подключить вашего ребенка! Какой у него @аккаунт в телеграмм?");
            }
            case 3: {
                if (message.getText().contains("@")) {
                    child.setStage(4);
                    child.setInfo("childName:" + message.getText());
                    parentRepo.save(child);
                    return message(child.getChatId(), child.getName() + "Прекрасно! Я робот и мне запрещено писать первой! Поэтому ребенку придется написать мне первой, для этого нужно в строке поиск найти мой аккаунт @proverka-dz и написать мне все что угодно!");
                } else {
                    return message(child.getChatId(), "У Вашего ребенка должен быть скачан телеграмм. Аккаунт начинается обычно на @!");     //todo несколько вариантов                      ;
                }
            }
            case 4: {
                UserToUser oneByParentId = c2pRepo.findOneByParentId(child.getChatId());
                if (oneByParentId != null) {
                    User oneById = userRepo.findOneByChatId(oneByParentId.getChildId());
                    if (oneById != null && oneById.getChatId() != null) {
                        child.setStage(5);
                        parentRepo.save(child);
                        return message(child.getChatId(), "Ура, ваш ребенок написал мне! С нетерпением жду 1 сентября!");
                    } else {
                        return message(child.getChatId(), "Я все еще жду пока ваше ребенок напишет мне! Ему нужно найти меня по имени"); //todo несколько варинтов
                    }
                }
                return message(child.getChatId(), "Я все еще жду пока ваше ребенок напишет мне! Ему нужно найти меня по имени");

            }
        }
        return message(child.getChatId(), "робот запутался и не знает что делать");
    }
}
