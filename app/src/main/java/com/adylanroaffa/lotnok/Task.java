package com.adylanroaffa.lotnok;

/**
 * Created by Adylan Roaffa on 7/5/2017.
 */

public class Task implements Comparable<Task> {

    String taskName;
    String taskNotes;
    String taskLoc;
    DateTime taskStartTime;
    DateTime taskEndTime;

    /*public constructor
    *   @param
    *   String taskName
    *   String taskDeadline
    *   String timeLeft
    */
    public Task (String taskName, String taskNotes, String taskLoc, DateTime taskStartTime, DateTime taskEndTime){
        this.taskName = taskName;
        this.taskNotes = taskNotes;
        this.taskLoc = taskLoc;
        this.taskStartTime = taskStartTime;
        this.taskEndTime = taskEndTime;
    }

    public String getTaskName(){
        return taskName;
    }

    public String getTaskNotes(){
        return taskNotes;
    }

    public String getTaskLoc(){
        return taskLoc;
    }

    public DateTime getTaskStartTime(){
        return taskStartTime;
    }

    public DateTime getTaskEndTime(){
        return taskEndTime;
    }

    @Override
    public int compareTo(Task o) {
        return getTaskStartTime().getByDate().compareTo(o.getTaskStartTime().getByDate());
    }

}
