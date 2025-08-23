package com.school.schedule.core.common;

import java.util.List;

public class Days {

    public static String monday() {
        return "MONDAY";
    }

    public static String tuesday() {
        return "TUESDAY";
    }

    public static String wednesday() {
        return "WEDNESDAY";
    }

    public static String thursday() {
        return "THURSDAY";
    }

    public static String friday() {
        return "FRIDAY";
    }

    public static String saturday() {
        return "SATURDAY";
    }

    public static String sunday() {
        return "SUNDAY";
    }

    public static List<String> allDays() {
        return List.of(
                monday(),
                tuesday(),
                wednesday(),
                thursday(),
                friday(),
                saturday(),
                sunday()
        );
    }
}

