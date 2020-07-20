package com.deonbabushka.dronbabushka.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserToUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long parentId;
    Long childId;
}
