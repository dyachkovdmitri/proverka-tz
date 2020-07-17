package com.deonbabushka.dronbabushka.repos;

import com.deonbabushka.dronbabushka.entities.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
    @Query(value = "select * from schedule where user_id not in(select user_id from schedule where (lesson=?1 or lesson=?1+1 or lesson=?1-1)and day=?2)", nativeQuery = true)
    List<Integer> findFree(Integer lesson, Integer day);
}
