package com.adylanroaffa.lotnok;

/**
 * Created by Adylan Roaffa on 7/5/2017.
 */

public class Task implements Comparable<Task> {

    String name;
    String notes;
    String loc;
    DateTime startTime;
    DateTime endTime;
    DateTime dueTime;
    boolean done;
    boolean isProject;
    int split;
    int splitID;

    /*public constructor
    *   @param
    *   String taskName
    *   String taskDeadline
    *   String timeLeft
    */
    public Task (String taskName, String taskNotes, String taskLoc, DateTime taskStartTime, DateTime taskEndTime, DateTime dueTime, boolean done, boolean isProject, int split, int splitID) {
        this.name = taskName;
        this.notes = taskNotes;
        this.loc = taskLoc;
        this.startTime = taskStartTime;
        this.endTime = taskEndTime;
        this.dueTime = dueTime;
        this.done = done;
        this.isProject = isProject;
        this.split = split;
        this.splitID = splitID;
    }

    public String getName(){
        return name;
    }

    public String getNotes(){
        return notes;
    }

    public String getLoc(){
        return loc;
    }

    public DateTime getStartTime(){ return startTime; }

    public DateTime getEndTime(){
        return endTime;
    }

    public DateTime getDueTime(){
        return dueTime;
    }

    public boolean getDone() { return done; }

    public boolean getIsProject() { return isProject; }

    @Override
    public int compareTo(Task o) {
        return getStartTime().getByDate().compareTo(o.getStartTime().getByDate());
    }

}
