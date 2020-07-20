package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.UserToUser;
import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.entities.Pupil;
import com.deonbabushka.dronbabushka.repos.ChildToParentRepo;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.List;

@Service
public class MeetScenario {
    @Autowired
    ParentRepo parentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ChildToParentRepo c2pRepo;

    String onMessage(User parent, Message message) {
        switch (parent.getStage()) {
            case "1hello": {
                parent.setStage("2child_name");
                parentRepo.save(parent);
                return "Привет! Давайте знакомится! Как Вас зовут?";
            }
            case "2child_name": {
                stage2(parent, message);
                parent.setStage("3child_activity");
                parentRepo.save(parent);
                return "Приятно познакомиться! Я постараюсь быть Вам полезной.;Как зовут Вашего ребенка?";
            }
            case "3child_activity": {
                stage3(parent, message);
                parent.setStage("4child_id");
                parentRepo.save(parent);
                return "Прекрасно имя!)) А чем еще увлекается Ваш ребенок? Какие нибудь секции или кружки?";
            }
            case "4child_id": {
                stage4(parent, message);
                parent.setStage("5irobot");
                parentRepo.save(parent);
                return "Прекрасное занятие!) Я тоже хочу! Вы всегда можете задать любые вопросы, мне если хотите!;Давайте попробуем подключить вашего ребенка! Какой называется его аккаунт в телеграмм? Это тот который начинается на @.";
            }
            case "5irobot": {
                UserToUser oneByParentId = c2pRepo.findOneByParentId(parent.getChatId());
                User pupil = userRepo.findOneByChatId(oneByParentId.getChildId());
                pupil.setUserName(message.getText());
                userRepo.save(pupil);
                parent.setStage("6childok");
                parentRepo.save(parent);
                return "Прекрасно! Я робот и мне запрещено писать первой! Поэтому ребенку придется написать мне первой, для этого нужно в строке поиск найти мой аккаунт @proverka-dz и написать мне все что угодно!";
            }
            case "6childok": {
                stage5(parent, message);
                UserToUser oneByParentId = c2pRepo.findOneByParentId(parent.getChatId());
                Pupil oneById = pupilRepo.findOneById(oneByParentId.getChildId());
                if (oneById != null && oneById.getChatId() != null) {
                    return "Ура, ваш ребенок написал мне! С нетерпением жду 1 сентября!";
                } else {
                    return "Я все еще жду пока ваше ребенок напишет мне! Tму надо скачать телеграм, и найти меня по имени";
                }
            }
        }
        return null;
    }

    String onMessage(Pupil pupil, Message message) {
        UserToUser oneByParentId = c2pRepo.findOneByChildId(pupil.getId());
        if(oneByParentId!=null){
            User parent = parentRepo.findOneByChatId(oneByParentId.getParentId());
            if(parent!=null){
                return "Привет! Твоего родителя зовут?"+parent.getName();
            }

        }
      return "Привет! Твою маму зовут?";
    }


    void stage2(User parent, Message message) {
        parent.setName(message.getText());
        parentRepo.save(parent);
    }

    void stage3(User parent, Message message) {
        Pupil pupil = new Pupil();
        pupil.setName(message.getText());
        pupilRepo.save(pupil);
        UserToUser c2p = new UserToUser();
        c2p.setChildNick(message.getText());
        c2p.setParentId(parent.getChatId());
        c2p.setChildId(pupil.getId());
        c2pRepo.save(c2p);
        parentRepo.save(parent);
    }

    void stage4(User parent, Message message) {


    }

    void stage5(User parent, Message message) {


    }

    void stage6(User parent, Message message) {

    }
}





