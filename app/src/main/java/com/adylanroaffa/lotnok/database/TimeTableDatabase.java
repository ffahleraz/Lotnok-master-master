package com.adylanroaffa.lotnok.database;

/**
 * Created by ffahleraz on 7/23/17.
 */

import com.adylanroaffa.lotnok.DateTime;
import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.*;
import java.util.Date;

@Table ( database = MyDatabase.class)
public class TimeTableDatabase extends BaseModel {

    // data key
    @Column
    @PrimaryKey
    int id;

    // event name
    @Column
    String name;

    // start date & time
    @Column
    Date startTime;

    // end date & time
    @Column
    Date endTime;

    // event notes
    @Column
    String notes;

    // event location
    @Column
    String loc;

    // event completion
    @Column
    boolean done = false;

    // event due date (for projects)
    @Column
    Date dueTime;

    // get methods

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Date getStartTime() { return this.startTime; }

    public Date getEndTime() { return this.endTime; }

    public String getNotes() { return this.notes; }

    public String getLoc() { return this.loc; }

    public boolean getDone() { return this.done; }

    public Date getDueTime() { return this.dueTime; }

    // set methods

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public void setNotes(String notes) { this.notes = notes; }

    public void setLoc(String loc) { this.loc = loc; }

    public void setDone(boolean done) { this.done = done; }

    public void setDueTime(Date dueTime) { this.dueTime = dueTime; }

}
