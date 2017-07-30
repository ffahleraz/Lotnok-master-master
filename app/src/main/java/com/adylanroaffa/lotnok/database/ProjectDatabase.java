package com.adylanroaffa.lotnok.database;

/**
 * Created by ffahleraz on 7/27/17.
 */

import com.raizlabs.android.dbflow.annotation.*;
import com.raizlabs.android.dbflow.structure.*;
import java.util.Date;

@Table (database = MyDatabase.class)
public class ProjectDatabase extends BaseModel {

    // data key
    @Column
    @PrimaryKey (autoincrement = true)
    int id;

    // project name
    @Column
    String name;

    // project deadline date & time
    @Column
    Date deadline;

    // project notes
    @Column
    String notes;

    // project location
    @Column
    String loc;

    // project split
    @Column
    int split = 0;

    // project duration in minutes
    @Column
    int prepDur = 0;

    // event completion
    @Column
    boolean done = false;

    /**
     *  Preparations (0 - 7)
     */

    // preparations
    @Column
    Date prep0Start;
    @Column
    Date prep1Start;
    @Column
    Date prep2Start;
    @Column
    Date prep3Start;
    @Column
    Date prep4Start;
    @Column
    Date prep5Start;
    @Column
    Date prep6Start;
    @Column
    Date prep7Start;

    // get methods

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Date getDeadline() { return this.deadline; }

    public String getNotes() { return this.notes; }

    public String getLoc() { return this.loc; }

    public int getSplit() { return this.split; }

    public boolean getDone() { return this.done; }

    public int getPrepDur() {return this.prepDur; }

    // get prep start

    public Date getPrep0Start() { return this.prep0Start; }

    public Date getPrep1Start() { return this.prep1Start; }

    public Date getPrep2Start() { return this.prep2Start; }

    public Date getPrep3Start() { return this.prep3Start; }

    public Date getPrep4Start() { return this.prep4Start; }

    public Date getPrep5Start() { return this.prep5Start; }

    public Date getPrep6Start() { return this.prep6Start; }

    public Date getPrep7Start() { return this.prep7Start; }

    // set methods

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public void setNotes(String notes) { this.notes = notes; }

    public void setLoc(String loc) { this.loc = loc; }

    public void setSplit(int split) { this.split = split; }

    public void setDone(boolean done) { this.done = done; }

    public void setPrepDur(int prepDur) { this.prepDur = prepDur; }

    // set prep start

    public void setPrep0Start(Date prep0Start) { this.prep0Start = prep0Start; }

    public void setPrep1Start(Date prep1Start) { this.prep1Start = prep1Start; }

    public void setPrep2Start(Date prep2Start) { this.prep2Start = prep2Start; }

    public void setPrep3Start(Date prep3Start) { this.prep3Start = prep3Start; }

    public void setPrep4Start(Date prep4Start) { this.prep4Start = prep4Start; }

    public void setPrep5Start(Date prep5Start) { this.prep5Start = prep5Start; }

    public void setPrep6Start(Date prep6Start) { this.prep6Start = prep6Start; }

    public void setPrep7Start(Date prep7Start) { this.prep7Start = prep7Start; }

}