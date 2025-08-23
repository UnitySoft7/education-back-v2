package com.school.schedule.query.api.response;

import lombok.Data;

@Data
public class ScheduleClassResponse {
    String lundi; String mardi; String mercredi; String jeudi; String vendredi;
    String samedi; String dimanche;
}
