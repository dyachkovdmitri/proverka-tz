package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.entities.Pupil;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Date;

@Service
public class FirstFilter {
    @Autowired
    ParentRepo parentRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    MeetScenario meetScenario;
    @Autowired
    HomeWorkScenario hwScenario;

    public String onMessage(Message message) {
        User user = userRepo.findOneByChatId(message.getChatId());
        if(user==null){
            createUser( message);
        }
        if(user.getStage().equals("81september")){
            return meetScenario.onMessage(user, message);

        } else { return hwScenario.onMessage(user, message);//проверка домашнего задания
        }
    }

    private User createUser(Message message) {
        User user = new User();
        user.setChatId(message.getChatId());
        user.setUserName(message.getFrom().getUserName());
        user.setLastName(message.getFrom().getLastName());
        user.setFirstName(message.getFrom().getFirstName());
        user.setStage("1hello");
        parentRepo.save(user);
        return user;
    }


}
