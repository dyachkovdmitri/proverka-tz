package com.deonbabushka.dronbabushka.scenarios;

import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.entities.User;
import com.deonbabushka.dronbabushka.entities.UserToUser;
import com.deonbabushka.dronbabushka.repos.ParentRepo;
import com.deonbabushka.dronbabushka.repos.UserRepo;
import com.deonbabushka.dronbabushka.repos.UserToUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    UserToUserRepo u2uRepo;
    @Autowired
    AdminScenario adminScenario;

    @Autowired
    ChildRegScenario childRegScenario;
    private final Integer ADMIN =93745;


    public List<SendMessage> onMessage(Message message) {
        User user = userRepo.findOneByChatId(message.getChatId());
        if(user==null){user = createUser(message);}

        switch (user){
            case(user.getStage().equals(ADMIN)):{return adminScenario.onMessage(message,user);}
            case(user.getParent()==null):{return whois(message,user);}//непонятно кто
            case(user.getParent()&&user.getStage()>4):{return sendMessageToAdmin(message, user); break;}//родитель зареган и задает странный вопрос
            case(user.getParent()&&user.getStage()<5):{return meetScenario.onMessage(user, message);} //родитель не закончил регистрацию
            case(!user.getParent()&&user.getStage()>5):{return homework(message);}//ребенок с законченной регистрацией
            case(!user.getParent()&&user.getStage()<5):{return childRegScenario.onMessage(user, message);}//ребенок не закончил регистрацию
            default: sendMessageToAdmin(message, user);
        }
return null;
    }

    private List<SendMessage> sendMessageToAdmin(Message message, User user) {
        return message(203523943L, user.toString()+"->"+ message.getText());
    }

    private List<SendMessage> whois(Message message, User user) {

            List<SendMessage> res = checkParents(user);//не чей то он ребенок?
            if(message.getText().equalsIgnoreCase("Родитель")){
                user.setParent(true);
                userRepo.save(user);
                return meetScenario.onMessage(user,message);
            } else if (message.getText().equalsIgnoreCase("Ученик")){
                user.setParent(false);
                userRepo.save(user);
                return meetScenario.onMessage(user,message);
            } else if (res != null) {
                return res; //родитель нашелся
            } else {
                return message(message.getChatId(), "А вы ребенок или родитель","Родитель","Ученик",null);}

    }

    private List<SendMessage> regParent(Message message) {
        return null;
    }

    ArrayList<SendMessage> message(Long chatId, String message) {
        ArrayList<SendMessage> msgs = new ArrayList<>();
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setReplyMarkup(null);
        msg.setChatId(chatId);
        msgs.add(msg);
        return msgs;
    }


    ArrayList<SendMessage> message(Long chatId, String message, String first, String second, String third) {
        ArrayList<SendMessage> msgs = new ArrayList<>();
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        msgs.add(msg);
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        ArrayList<KeyboardRow> rows = new ArrayList();
        KeyboardRow row = new KeyboardRow();
        if (first != null) {
            row.add(first);
            if (second != null) {
                row.add(second);
            }
            if (third != null) {
                row.add(third);
            }
        }
        rows.add(row);
        keyboard.setKeyboard(rows);
        msg.setReplyMarkup(keyboard);
        return msgs;
    }

    List<SendMessage> messages(ArrayList<SendMessage> msgs, Long chatId, String message) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        msg.setReplyMarkup(null);
        msgs.add(msg);
        return msgs;
    }

    private List<SendMessage> checkParents(User user) {
        User parent = userRepo.findOneByInfoContaining(user.getUserName());
        if (parent != null) {
            UserToUser u2u = new UserToUser();
            u2u.setParentId(parent.getChatId());
            u2u.setChildId(user.getChatId());
            user.setParent(false);
            userRepo.save(user);
            userRepo.save(parent);
            u2uRepo.save(u2u);
            parent.setStage(4);

            userRepo.save(parent);
            ArrayList<SendMessage> msgs = message(user.getChatId(), "Привет! Спасибо, что зашел! Твоего родителя зовут " + parent.getName() + "?");
            return messages(msgs, parent.getChatId(), "Ваше ребенок написал мне");

        }
        return null;
    }

    private User createUser(Message message) {
       User user  = new User();
        user.setChatId(message.getChatId());
        user.setUserName(message.getFrom().getUserName());
        user.setLastName(message.getFrom().getLastName());
        user.setFirstName(message.getFrom().getFirstName());
        user.setStage(1);
        parentRepo.save(user);
        return user;
    }

    //stage 0 - нет в базе, пишем в базу, проверяем нет ли родителей, если находим родителей сообщаем ребенку и родетлям присваиваем stage 3


}
