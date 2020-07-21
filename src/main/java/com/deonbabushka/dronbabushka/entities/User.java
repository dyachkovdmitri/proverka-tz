package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity(name = "friend")
public class User {
    @Id
    Long chatId;
    Boolean parent;
    String name;
    Integer stage;
    String
            userName,
            lastName,
            firstName;
    String info;
    public void addInfo(String info){
        this.info+=";"+info;
    }
}
