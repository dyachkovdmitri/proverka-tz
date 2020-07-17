package com.deonbabushka.dronbabushka.helpers;


import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class HelpController {
//    @Autowired
//    HomeScenario scenario;
//    @Autowired
//    PupilRepo pupilRepo;
//    @Autowired
//    HomeTaskRepo htRepo;
//    @Autowired
//    UTTRepo uttRepo;
//
//    @Autowired
//    ScheduleRepo schRepo;
//
//    @GetMapping("/")
//    List<TaskDb> findAll() {
//        return scenario.findAllTasks();
//    }
//
//    @GetMapping("/init")
//    String init() {
//        Pupil pupil = new Pupil();
//        pupil.setName("Дима");
//        pupilRepo.save(pupil);
//
//        ParsedHomeTask ht = new ParsedHomeTask();
//        ht.setDate(new Date(System.currentTimeMillis()));
//        ht.setExersize("наизусть");
//        ht.setPage(1);
//        ht.setSubjectId(1);
//        ht.setUserId(1);
//        htRepo.save(ht);
//
//        UserTaskType utt = new UserTaskType();
//        utt.setTaskType(1);
//        utt.setUserId(1);
//        uttRepo.save(utt);
//
//        Schedule scheduler = new Schedule();
//        scheduler.setUserId(1);
//        scheduler.setDay(0);
//        scheduler.setMinutesStart(0);
//        scheduler.setTaskType(1);
//        schRepo.save(scheduler);
//
//        return "allOK";
//
//    }
}
