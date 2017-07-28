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

    // event completion
    @Column
    boolean done = false;

    // get methods

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public Date getDeadline() { return this.deadline; }

    public String getNotes() { return this.notes; }

    public boolean getDone() { return this.done; }

    // set methods

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setDeadline(Date startTime) { this.deadline = deadline; }

    public void setNotes(String notes) { this.notes = notes; }

    public void setDone(boolean done) { this.done = done; }

}