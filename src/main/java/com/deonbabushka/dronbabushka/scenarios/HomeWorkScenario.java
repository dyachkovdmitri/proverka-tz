package com.deonbabushka.dronbabushka.scenarios;


import com.deonbabushka.dronbabushka.entities.Pupil;
import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.repos.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Date;
import java.util.List;

@Service
public class HomeWorkScenario {

@Autowired
    ScheduleRepo scheduleRepo;
    List<Pupil> getUsersNeedToAttention() {
        Integer lesson = getLessonNumberNow();
        List<Integer> free = scheduleRepo.findFree(lesson, new Date(System.currentTimeMillis()).getDay());

         //нет уроков
         //прошел срок выполнения дз
         //прошел срок ожидания ответа
        return null;
    }
    Integer getLessonNumberNow() {
    return 2;
    }

    String onMessage(User pupil, Message message) {

        return null;
    }


//    String getMessage(Pupil pupil) {
//
//    }


}

//Привет! Готов!?
//ДА) Нет(
//С Чего начнем?
//Русский!
//Ок. Пришли скриншот задания.
//Все получил, спасибо!
//Давай начнем с истории, как ты сказал)
//Делай и присылай фото)) За полчасика успеешь?
//ВСе спасибо получил! Говто к следующему уроку?
//Какой следующий урок будем делать?
//-Математика?
//
//Молодец!








