package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class ParsedHomeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer subjectId;
    String task;
    Integer userId;
    Date date;
    //Blob image;
    String status;
    String mark;
}
