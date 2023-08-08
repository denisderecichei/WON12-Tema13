package org.fasttrack;

import org.fasttrack.calendar.DailyPlanner;
import org.fasttrack.calendar.DayOfWeek;
import org.fasttrack.calendar.DaySchedule;
import org.fasttrack.exception.NoActivitiesForDayException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        DaySchedule monday = new DaySchedule(DayOfWeek.MONDAY);
        monday.addActivity("work");
        monday.addActivity("kayak");
        monday.addActivity("movie");

        DaySchedule tuesday = new DaySchedule(DayOfWeek.TUESDAY);
        tuesday.addActivity("work");
        tuesday.addActivity("kayak");
        tuesday.addActivity("movie");

        DailyPlanner planner = new DailyPlanner();
        planner.addActivity(monday);
        planner.addActivity(tuesday);

        planner.addActivity(DayOfWeek.MONDAY, "jogging");
//        planner.removeActivity(DayOfWeek.MONDAY, "jogging");

        List<String> activitiesForMonday = planner.getActivities(DayOfWeek.MONDAY);
        List<String> activitiesForTuesday = planner.getActivities(DayOfWeek.TUESDAY);

        System.out.println("Monday: " + activitiesForMonday);
        System.out.println("Tuesday: " + activitiesForTuesday);

        List<String> errorMessages = new ArrayList<>();
        Map<DayOfWeek, List<String>> activities = new HashMap<>();
        try {
            activities = planner.endPlanning();
        } catch (NoActivitiesForDayException e) {
            errorMessages.add(e.getMessage());
        }
        System.out.println("ERRORS: " + errorMessages);
        System.out.println("THE DAYS: " + activities);

    }
}
