package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.Parent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepo extends CrudRepository<Parent,Long> {
    public Parent findOneByChatId(Long chatId);
}
