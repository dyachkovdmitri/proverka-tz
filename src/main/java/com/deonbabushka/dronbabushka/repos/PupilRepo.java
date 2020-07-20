package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.Pupil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface PupilRepo extends CrudRepository<Pupil, Integer> {
    Pupil findOneByChatId(Long chatId);
    Pupil findOneByUserName(String userName);

    Pupil findOneById(Long id);
}
