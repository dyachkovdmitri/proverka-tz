package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.ParsedHomeTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface HomeTaskRepo extends CrudRepository<ParsedHomeTask,Integer> {
   @Query(value="select id from Pupil\n" +
           "where id not in (select user_id from home_task_image where date>?1 and date<?2 and image is not null)\n" +
           "and user_id in (?3)", nativeQuery = true)
    List<Integer> unknownImageHomeTask(Date begin, Date end, ArrayList<Integer> ids);
    @Query(value="select id from Pupil\n" +
            "where id not in (select user_id from parsed_home_task where date>?1 and date<?2 and image is not null)\n" +
            "and in user_id in (?3)", nativeQuery = true)
    List<Integer> unknownParsed(Date begin, Date end, ArrayList<Integer> ids);
}
