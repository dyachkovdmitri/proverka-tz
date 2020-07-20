package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Data
@Entity
public class User {
    @Id
    Long chatId;
    Boolean isParent;
    String name;
    String stage,
            userName,
            lastName,
            firstName;
}
