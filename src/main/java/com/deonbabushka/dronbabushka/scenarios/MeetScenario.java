package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.ChildToParent;
import com.deonbabushka.dronbabushka.entities.Parent;
import com.deonbabushka.dronbabushka.entities.Pupil;
import com.deonbabushka.dronbabushka.repos.ChildToParentRepo;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.PupilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MeetScenario {
    @Autowired
    ParentRepo parentRepo;
    @Autowired
    PupilRepo pupilRepo;
    @Autowired
    ChildToParentRepo c2pRepo;

    List<String> answers = Arrays.asList("-Привет! Давайте знакомится! Как Вас зовут?",//stage 1
            "Приятно познакомиться! Я постараюсь быть Вам полезной.;Как зовут Вашего ребенка?",//stage2
            "Прекрасно имя!)) А в какой класс он пойдет первого сентября?",//stage3
            "А чем еще увлекается Ваш ребенок? Какие нибудь секции или кружки?",//stage4
            "Прекрасное занятие!) Я тоже хочу! Вы всегда можете задать любые вопросы, мне если хотите!;Давайте попробуем подключить вашего ребенка! Какой называется его аккаунт в телеграмм? Это тот который начинается на @. НАпример мой @proverka-dz",//stage5
            "Прекрасно! Я робот и мне запрещено писать первой! Поэтому ребенку придется написать мне первой, для этого нужно в строке поиск найти мой аккаунт @proverka-dz и написать мне все что угодно!",//stage6
            "Ура, ваш ребенок написал мне! С нетерпением жду 1 сентября!");//stage7


    String onMessage(Parent parent, Message message) {
          switch (parent.getStage()) {
            case "1hello": {
                parent.setStage("2child_name");
                parentRepo.save(parent);
               // stage1(parent, message);
                return answers.get(0);
            }
            case "2child_name":{
                stage2(parent,message);
                return answers.get(1);
            }
            case "3child_activity":{
                stage3(parent,message);
                return answers.get(2);
            }
            case "4child_id":{
                stage4(parent,message);
                return answers.get(3);
            }
            case "5child_in":{
                stage5(parent,message);
                return answers.get(4);
            }
        }
        return null;
    }


    void stage2(Parent parent, Message message) {
        parent.setName(message.getText());
        parentRepo.save(parent);
        parent.setAnswer(new Date(System.currentTimeMillis()));
        parent.setStage("3child_activity");
        parentRepo.save(parent);

    }

    void stage3(Parent parent, Message message) {
        Pupil pupil = new Pupil();
        pupil.setName(message.getText());
        pupilRepo.save(pupil);
        ChildToParent c2p = new ChildToParent();
        c2p.setChildNick(message.getText());
        c2p.setParentId(parent.getChatId());
        c2p.setChildId(pupil.getId());
        c2pRepo.save(c2p);
        parent.setAnswer(new Date(System.currentTimeMillis()));
        parent.setStage("4child_id");
        parentRepo.save(parent);
    }

    void stage4(Parent parent, Message message) {

        parent.setAnswer(new Date(System.currentTimeMillis()));
        parentRepo.save(parent);
        parent.setStage("5child_in");
    }

    void stage5(Parent parent, Message message) {
        parent.setStage("61september");
        parent.setAnswer(new Date(System.currentTimeMillis()));
        parentRepo.save(parent);
    }

    void stage6(Parent parent, Message message) {
//        parent.setStage("61september");
//        parent.setAnswer(new Date(System.currentTimeMillis()));
        parentRepo.save(parent);
    }
}





