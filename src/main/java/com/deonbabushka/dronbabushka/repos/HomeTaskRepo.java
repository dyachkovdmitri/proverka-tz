package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.ParsedHomeTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeTaskRepo extends CrudRepository<ParsedHomeTask,Integer> {
}
