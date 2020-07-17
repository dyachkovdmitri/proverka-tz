package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;
import java.util.Date;

@Data
@Entity
public class HomeTaskImage {
    @Id
    Integer id;
    Date date;
    String image;
    //Blob image;
}
