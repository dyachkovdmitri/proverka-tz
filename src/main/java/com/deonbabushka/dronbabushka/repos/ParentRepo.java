package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends CrudRepository<User,Long> {
    public User findOneByChatId(Long chatId);
}
