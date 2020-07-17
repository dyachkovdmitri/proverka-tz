package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.Pupil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface PupilRepo extends CrudRepository<Pupil,Integer> {

}
