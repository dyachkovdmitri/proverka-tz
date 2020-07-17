package com.deonbabushka.dronbabushka.helpers;

import java.time.LocalDateTime;
import java.util.Date;

public  class DateHelper {
    public static Integer getDay() {
        return LocalDateTime.now().getDayOfWeek().getValue();
    }

    public static Integer getMinutesNow() {
        return LocalDateTime.now().getMinute() + LocalDateTime.now().getHour() * 60;
    }
}
