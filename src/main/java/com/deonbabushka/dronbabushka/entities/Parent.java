package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class Parent {
    @Id
    Long chatId;
    String name;
    Date answer;
    Date que;
    String stage,userName,lastName,firstName;
}
