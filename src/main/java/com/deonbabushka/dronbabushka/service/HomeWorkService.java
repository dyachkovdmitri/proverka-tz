package com.deonbabushka.dronbabushka.service;

import com.deonbabushka.dronbabushka.repos.HomeTaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HomeWorkService {
    @Autowired
    HomeTaskRepo htRepo;
List<Integer> getUserWithoutScreenShot(ArrayList<Integer> ids){
    Date begin = new Date(System.currentTimeMillis());
    begin.setHours(23);
    begin.setMinutes(59);
    begin.setSeconds(59);
    Date end = new Date(System.currentTimeMillis()+24*60*60*1000);
    end.setHours(23);
    end.setMinutes(59);
    end.setSeconds(59);
    return htRepo.unknownImageHomeTask(begin,end,ids);
}

    List<Integer> getUserWithoutParsed(ArrayList<Integer> ids){
        Date begin = new Date(System.currentTimeMillis());
        begin.setHours(23);
        begin.setMinutes(59);
        begin.setSeconds(59);
        Date end = new Date(System.currentTimeMillis()+24*60*60*1000);
        end.setHours(23);
        end.setMinutes(59);
        end.setSeconds(59);
        return htRepo.unknownImageHomeTask(begin,end,ids);
    }
}

