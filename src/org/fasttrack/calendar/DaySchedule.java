package org.fasttrack.calendar;

import java.util.ArrayList;
import java.util.List;

public class DaySchedule {
    private DayOfWeek dayOfWeek;
    private List<String> activities;

    public DaySchedule(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.activities = new ArrayList<>();
    }

    public DaySchedule(DayOfWeek dayOfWeek, List<String> activities) {
        this.dayOfWeek = dayOfWeek;
        this.activities = activities;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public boolean addActivity(String activity) {
        return this.activities.add(activity);
    }
}
