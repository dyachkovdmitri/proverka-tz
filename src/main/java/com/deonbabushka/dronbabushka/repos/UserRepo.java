package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.Pupil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Pupil, Long> {

}
