package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findOneByChatId(Long chatId);
    User findOneByUserName(String userName);
        }