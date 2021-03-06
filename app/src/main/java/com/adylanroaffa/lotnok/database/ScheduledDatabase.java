package com.adylanroaffa.lotnok.database;

/**
 * Created by ffahleraz on 7/24/17.
 */

import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.*;
import java.util.Date;

@Table (database = MyDatabase.class)
public class ScheduledDatabase extends BaseModel {

    // data key
    @Column
    @PrimaryKey (autoincrement = true)
    int id;

    // event name
    @Column
    String name;

    // start time for each repetition (year, month, date kosongin aja)
    @Column
    Date startTime;

    // end time for each repetition (year, month, date kosongin aja)
    @Column
    Date endTime;

    // event notes
    @Column
    String notes;

    // event location
    @Column
    String loc;

    // schedule frequency (0 for daily, 1 for weekly, 2 for monthly, 3 for yearly)
    @Column
    int frequency;

    // schedule day (for weekly schedule, 0 to 6)
    @Column
    int day = -1;

    // event completion
    @Column
    boolean done = false;

    // get methods

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Date getStartTime() { return this.startTime; }

    public Date getEndTime() { return this.endTime; }

    public String getNotes() { return this.notes; }

    public String getLoc() { return this.loc; }

    public int getFrequency() { return this.frequency; }

    public int getDay() { return this.day; }

    public boolean getDone() { return this.done; }

    // set methods

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public void setNotes(String notes) { this.notes = notes; }

    public void setLoc(String loc) { this.loc = loc; }

    public void setFrequency(int frequency) { this.frequency = frequency; }

    public void setDay(int day) { this.day = day; }

    public void setDone(boolean done) { this.done = done; }

}
