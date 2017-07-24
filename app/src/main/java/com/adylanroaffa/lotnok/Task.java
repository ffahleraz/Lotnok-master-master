package com.adylanroaffa.lotnok;

/**
 * Created by Adylan Roaffa on 7/5/2017.
 */

public class Task {

    /*task name variable*/
    String mTaskName;
    /*task deadline variable*/
    String mTaskDeadline;
    /*how much time left to the deadline*/
    String mTimeLeft;


    /*public constructor
    *   @param
    *   String taskName
    *   String taskDeadline
    *   String timeLeft
    */
    public Task (String taskName, String taskDeadline, String timeLeft){
        this.mTaskName = taskName;
        this.mTaskDeadline = taskDeadline;
        this.mTimeLeft = timeLeft;
    }


    /*TODO : get the task name of a task*/
    public String getTaskName(){
        return mTaskName;
    }

    /*TODO : get when the deadline is*/
    public String getTaskDeadline(){
        return mTaskDeadline;
    }

    /*TODO : get how much time left*/
    public String getTimeLeft(){
        return mTimeLeft;
    }

}
