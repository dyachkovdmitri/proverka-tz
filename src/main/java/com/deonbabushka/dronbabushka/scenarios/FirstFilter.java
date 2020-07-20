package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.Parent;
import com.deonbabushka.dronbabushka.entities.Pupil;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.PupilRepo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Date;

@Service
public class FirstFilter {
    @Autowired
    ParentRepo parentRepo;
    @Autowired
    PupilRepo pupilRepo;
    @Autowired
    MeetScenario meetScenario;
    @Autowired
    HomeWorkScenario hwScenario;

    public String onMessage(Message message) {
        Parent parent = parentRepo.findOneByChatId(message.getChatId());
        if (parent == null) { //первое сообщение
            parent = createNewParent(message, parent);
            return meetScenario.onMessage(parent, message);
        } else if (!parent.getStage().equals("61september")) {//процесс знакомства
            return meetScenario.onMessage(parent, message);
        } else {
            Pupil pupil = pupilRepo.findOneByChatId(message.getChatId());
            return hwScenario.onMessage(pupil, message);//проверка домашнего задания
        }
    }

    private Parent createNewParent(Message message, Parent parent) {
        parent = new Parent();
        parent.setChatId(message.getChatId());
        parent.setAnswer(new Date(System.currentTimeMillis()));
        parent.setFirstName(message.getFrom().getUserName());
        parent.setLastName(message.getFrom().getLastName());
        parent.setFirstName(message.getFrom().getFirstName());
        parent.setStage("1hello");
        parentRepo.save(parent);
        return parent;
    }


}
