package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    public long id = 1;
    public long projectId;
    public long userId;
    public LocalDate date;
    public int hours;

    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.id = timeEntryId;
        this.hours = hours;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry() {

    }

    public long getId() {
        return this.id;
    }

    public boolean equals(Object o) {
        TimeEntry other = (TimeEntry) o;
        return (
               other.userId == userId
            && other.projectId == projectId
            && other.date.equals(date)
            && other.hours == hours
            && other.id == id
        );
    }

    public long getProjectId() {
        return this.projectId;
    }

    public long getUserId() {
        return this.userId;
    }

    public int getHours() {
        return this.hours;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
