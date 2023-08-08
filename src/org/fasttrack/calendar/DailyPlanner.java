package org.fasttrack.calendar;

import org.fasttrack.exception.NoActivitiesForDayException;
import org.fasttrack.exception.NoActivityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyPlanner {
    List<DaySchedule> scheduleForTheWeek;

    public DailyPlanner(List<DaySchedule> scheduleForTheWeek) {
        this.scheduleForTheWeek = scheduleForTheWeek;
    }

    public DailyPlanner() {
        this.scheduleForTheWeek = new ArrayList<>();
    }

    public void addActivity(DaySchedule schedule) {
        this.scheduleForTheWeek.add(schedule);
    }

    public void addActivity(DayOfWeek day, String activity) {
        if (activity == null ) {
            throw new NoActivityException("Activitatea nu poate fi nula");
        }
 /*        boolean isAdded = false;
       for (DaySchedule currDaySch: scheduleForTheWeek) {
            if (currDaySch.getDayOfWeek().equals(day)) {
                List<String> currActivities = currDaySch.getActivities();
                currActivities.add(activity);
                isAdded = true;
            }
        }
        if (isAdded == false) {
            DaySchedule newDaySchedule = new DaySchedule(day);
            newDaySchedule.getActivities().add(activity);
            scheduleForTheWeek.add(newDaySchedule);
        }*/
        DaySchedule myDaySchedule = getOrCreateDaySchedule(day);
        myDaySchedule.addActivity(activity);
    }

    public void removeActivity(DayOfWeek day, String activity) {
        if (activity == null ) {
            throw new NoActivityException("Activitatea nu poate fi nula");
        }
        for(DaySchedule currDaySchedule: scheduleForTheWeek) {
            if (currDaySchedule.getDayOfWeek().equals(day)) {
                List<String> currActivities = currDaySchedule.getActivities();
                boolean wasDeleted = currActivities.remove(activity);
                if (wasDeleted == false) {
                    throw new NoActivityException("Activitatea nu exista");
                }
            }
        }
    }

    public List<String> getActivities(DayOfWeek day) {
//        for (DaySchedule currDaySchedule: scheduleForTheWeek) {
//            if (currDaySchedule.getDayOfWeek().equals(day)) {
//                return currDaySchedule.getActivities();
//            }
//        }
//        return new ArrayList<>();
        return getOrCreateDaySchedule(day).getActivities();
    }

    public Map<DayOfWeek, List<String>> endPlanning() throws NoActivitiesForDayException {
        Map<DayOfWeek, List<String>> activitiesPerDay = new HashMap<>();
        List<DayOfWeek> allDays = List.of(DayOfWeek.values());
        for (DayOfWeek day: allDays) {
            List<String> allActivies = getActivities(day);
            if (allActivies.isEmpty()) {
                throw new NoActivitiesForDayException("Nu am activitati pt ziua " + day.name());
            }
            activitiesPerDay.put(day, allActivies);
        }
        return activitiesPerDay;
    }

    public DaySchedule getOrCreateDaySchedule(DayOfWeek day) {
        for (DaySchedule currDay: scheduleForTheWeek) {
            if (currDay.getDayOfWeek().equals(day)) {
                return currDay;
            }
        }
        DaySchedule newDaySchedule = new DaySchedule(day);
        scheduleForTheWeek.add(newDaySchedule);
        return newDaySchedule;
    }
}
