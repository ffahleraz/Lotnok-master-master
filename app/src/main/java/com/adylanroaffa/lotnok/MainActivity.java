package com.adylanroaffa.lotnok;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adylanroaffa.lotnok.database.OneTimeDatabase;
import com.adylanroaffa.lotnok.database.ProjectDatabase;
import com.adylanroaffa.lotnok.database.ScheduledDatabase;
import com.adylanroaffa.lotnok.database.TimeTableDatabase;
import com.raizlabs.android.dbflow.config.*;
import com.raizlabs.android.dbflow.sql.language.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks;
    private RecyclerView rv;
    private TaskAdapter taskAdapter;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create recycler view
        rv = (RecyclerView) findViewById(R.id.rv);

        // get current date
        calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM");
        int date = calendar.get(Calendar.DATE);

        // set recycler view
        RecyclerView.LayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        //set type font
        TextView timeline =(TextView) findViewById(R.id.timeline);
        timeline.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));

        TextView upComingTaskName =(TextView) findViewById(R.id.upcoming_task_name);
        upComingTaskName.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));

        TextView upComingTaskDeadline =(TextView) findViewById(R.id.upcoming_task_deadline);
        upComingTaskDeadline.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));

        TextView upNext =(TextView) findViewById(R.id.up_next);
        upNext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));

        TextView location =(TextView) findViewById(R.id.Tag_Location);
        location.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));

        TextView upComingTaskDeadlineTime =(TextView) findViewById(R.id.upcoming_task_deadline_time);
        upComingTaskDeadlineTime.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));

        // set top date circle text
        TextView dateToday = (TextView) findViewById(R.id.date_today);
        dateToday.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));
        dateToday.setText(""+date);

        // load database
        FlowManager.init(new FlowConfig.Builder(this).build());

        // initialize data, labels text, and adapter for the rv
        initData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newTask_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PopupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        updateData();

    }

    private void initData() {

        tasks = new ArrayList<>();

        // Load data from database (kalo ude debug di uncomment lah ye)
        //updateData();

        /*/ Delete all data in database (buat debug aeeee)
        List<OneTimeDatabase> oneTimeDatas = new Select().from(OneTimeDatabase.class).queryList();
        for (OneTimeDatabase oneTimeData : oneTimeDatas) {
            oneTimeData.delete();
        }

        List<ScheduledDatabase> scheduledDatas = new Select().from(ScheduledDatabase.class).queryList();
        for (ScheduledDatabase scheduledData : scheduledDatas) {
            scheduledData.delete();
        }
        /*/

        updateRVAdapter();

    }

    private void updateData() {

        /**
         *  Clear tasks timetable list
         */
        tasks.clear();

        /**
         *  Load one time events
          */
        List<OneTimeDatabase> oneTimeDatas = new Select().from(OneTimeDatabase.class).queryList();
        for (OneTimeDatabase oneTimeData : oneTimeDatas) {

            // get startTime as DateTime
            DateTime startTime = new DateTime();
            DateTime endTime = new DateTime();
            startTime.setByDate(oneTimeData.getStartTime());
            endTime.setByDate(oneTimeData.getEndTime());

            tasks.add(new Task(oneTimeData.getName(), oneTimeData.getNotes(), oneTimeData.getLoc(), startTime, endTime, startTime, false, false, 0, 0));
        }

        /**
         *  Load projects
         */
        List<ProjectDatabase> projectDatas = new Select().from(ProjectDatabase.class).queryList();
        for (ProjectDatabase projectData : projectDatas) {

            // get each preparations
            for (int i = 0; i < projectData.getSplit(); i++) {

                DateTime startTime = new DateTime();
                DateTime endTime = new DateTime();
                DateTime dueTime = new DateTime();
                dueTime.setByDate(projectData.getDeadline());

                switch (i) {

                    case 0:
                        startTime.setByDate(projectData.getPrep0Start());
                        break;
                    case 1:
                        startTime.setByDate(projectData.getPrep1Start());
                        break;
                    case 2:
                        startTime.setByDate(projectData.getPrep2Start());
                        break;
                    case 3:
                        startTime.setByDate(projectData.getPrep3Start());
                        break;
                    case 4:
                        startTime.setByDate(projectData.getPrep4Start());
                        break;
                    case 5:
                        startTime.setByDate(projectData.getPrep5Start());
                        break;
                    case 6:
                        startTime.setByDate(projectData.getPrep6Start());
                        break;
                    case 7:
                        startTime.setByDate(projectData.getPrep7Start());
                        break;

                }

                Calendar endCal = Calendar.getInstance();
                endCal.setTime(startTime.getByDate());
                endCal.add(Calendar.MINUTE, projectData.getPrepDur());
                endTime.setByDate(endCal.getTime());

                tasks.add(new Task(projectData.getName(), projectData.getNotes(), projectData.getLoc(), startTime, endTime, dueTime, false, true, projectData.getSplit(), i));

            }

        }

        /**
         *  Load scheduled events
          */
        List<ScheduledDatabase> scheduledDatas = new Select().from(ScheduledDatabase.class).queryList();
        for (ScheduledDatabase scheduledData : scheduledDatas) {

            /**
             *  Get event's properties except day (day may be null except for weekly events)
              */
            String name = scheduledData.getName();

            // get event's start and end time as a Date Time
            DateTime startTime = new DateTime();
            DateTime endTime = new DateTime();
            startTime.setByDate(scheduledData.getStartTime());
            endTime.setByDate(scheduledData.getEndTime());

            String notes = scheduledData.getNotes();
            String loc = scheduledData.getLoc();
            int frequency = scheduledData.getFrequency();

            // get today's date and calculate timeline day boundary (next 60 days)
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            Calendar then = Calendar.getInstance();
            then.setTime(new Date());
            then.add(Calendar.DATE, 60);
            //
            switch (frequency) {

                case 0:

                    // while now still <= then
                    while (now.compareTo(then) <= 0) {

                        DateTime currentStartTime = new DateTime();
                        currentStartTime.setByDate(now.getTime());
                        currentStartTime.setHour(startTime.getHour());
                        currentStartTime.setMinute(startTime.getMinute());

                        DateTime currentEndTime = new DateTime();
                        currentEndTime.setByDate(now.getTime());
                        currentEndTime.setHour(endTime.getHour());
                        currentEndTime.setMinute(endTime.getMinute());

                        tasks.add(new Task(name, notes, loc, currentStartTime, currentEndTime, currentStartTime, false, false, 0, 0));

                        now.add(Calendar.DATE, 1);
                    }

                    break;

                case 1:

                    // get event's day
                    int day = scheduledData.getDay();

                    // trav until next selected date
                    while (now.get(Calendar.DAY_OF_WEEK) != (day + 1)) {

                        now.add(Calendar.DATE, 1);

                    }

                    // create all tasks in 60 days range
                    while (now.compareTo(then) <= 0) {

                        DateTime currentStartTime = new DateTime();
                        currentStartTime.setByDate(now.getTime());
                        currentStartTime.setHour(startTime.getHour());
                        currentStartTime.setMinute(startTime.getMinute());

                        DateTime currentEndTime = new DateTime();
                        currentEndTime.setByDate(now.getTime());
                        currentEndTime.setHour(endTime.getHour());
                        currentEndTime.setMinute(endTime.getMinute());

                        tasks.add(new Task(name, notes, loc, currentStartTime, currentEndTime, currentStartTime, false, false, 0, 0));

                        now.add(Calendar.DATE, 7);
                    }

                    break;

                case 2:

                    // trav until next selected date
                    while (now.get(Calendar.DATE) != startTime.getDay()) {

                        now.add(Calendar.DATE, 1);

                    }

                    // create all tasks in 60 days range
                    while (now.compareTo(then) <= 0) {

                        DateTime currentStartTime = new DateTime();
                        currentStartTime.setByDate(now.getTime());
                        currentStartTime.setHour(startTime.getHour());
                        currentStartTime.setMinute(startTime.getMinute());

                        DateTime currentEndTime = new DateTime();
                        currentEndTime.setByDate(now.getTime());
                        currentEndTime.setHour(endTime.getHour());
                        currentEndTime.setMinute(endTime.getMinute());

                        tasks.add(new Task(name, notes, loc, currentStartTime, currentEndTime, currentStartTime, false, false, 0, 0));

                        now.add(Calendar.MONTH, 1);
                    }

                    break;

                case 3:

                    // trav until next selected month and date
                    if (((now.get(Calendar.MONTH) + 1) == startTime.getMonth()) && (now.get(Calendar.DATE) <= startTime.getDay())) {

                        // jump to selected date
                        now.set(Calendar.DATE, startTime.getDay());

                    } else if (((now.get(Calendar.MONTH) + 1) == startTime.getMonth()) && (now.get(Calendar.DATE) > startTime.getDay())) {

                        // jump to selected first day of selected month in the next year
                        now.add(Calendar.YEAR, 1);
                        now.set(Calendar.MONTH, startTime.getMonth());
                        now.set(Calendar.DATE, startTime.getDay());

                    } else {

                        // trav until next selected month
                        while ((now.get(Calendar.MONTH) + 1) != startTime.getMonth()) {

                            now.add(Calendar.MONTH, 1);
                            now.set(Calendar.DATE, 1);

                        }

                        // trav until next selected date
                        while (now.get(Calendar.DATE) != startTime.getDay()) {

                            now.add(Calendar.DATE, 1);

                        }

                    }

                    // create all tasks in 60 days range
                    while (now.compareTo(then) <= 0) {

                        DateTime currentStartTime = new DateTime();
                        currentStartTime.setByDate(now.getTime());
                        currentStartTime.setHour(startTime.getHour());
                        currentStartTime.setMinute(startTime.getMinute());

                        DateTime currentEndTime = new DateTime();
                        currentEndTime.setByDate(now.getTime());
                        currentEndTime.setHour(endTime.getHour());
                        currentEndTime.setMinute(endTime.getMinute());

                        tasks.add(new Task(name, notes, loc, currentStartTime, currentEndTime, currentStartTime, false, false, 0, 0));

                        now.add(Calendar.YEAR, 1);
                    }

                    break;

            }

        }

        /**
         *  Sort tasks list by start date, and remove tasks that has passed
          */
        Collections.sort(tasks);
        Date now = new Date();
        Iterator<Task> iter = tasks.iterator();
        while (iter.hasNext()) {
            Task nextTask = iter.next();
            if (nextTask.getStartTime().getByDate().compareTo(now) < 0) {
                iter.remove();
            }
        }

        /**
         *  Add tasks timetable to a database
         */

        // Delete all data in database
        List<TimeTableDatabase> timeTableDatas = new Select().from(TimeTableDatabase.class).queryList();
        for (TimeTableDatabase timeTableData : timeTableDatas) {
            timeTableData.delete();
        }

        // Fill database with new data
        iter = tasks.iterator();
        while (iter.hasNext()) {
            Task nextTask = iter.next();

            TimeTableDatabase timeTableData = new TimeTableDatabase();
            timeTableData.setName(nextTask.getName());
            timeTableData.setStartTime(nextTask.getStartTime().getByDate());
            timeTableData.setEndTime(nextTask.getEndTime().getByDate());
            timeTableData.setDueTime(nextTask.getDueTime().getByDate());
            timeTableData.setNotes(nextTask.getNotes());
            timeTableData.setLoc(nextTask.getLoc());
            timeTableData.setDone(nextTask.getDone());
            timeTableData.setIsProject(nextTask.getIsProject());
            timeTableData.save();

        }

        if (tasks.size() > 0) {

            Task topTask = tasks.get(0);
            tasks.remove(0);

            TextView upComingTaskName =(TextView) findViewById(R.id.upcoming_task_name);
            upComingTaskName.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));
            upComingTaskName.setText(topTask.getName());

            TextView upComingTaskDeadline =(TextView) findViewById(R.id.upcoming_task_deadline);
            upComingTaskDeadline.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/avenir-light.ttf"));
            upComingTaskDeadline.setText(topTask.getNotes());

            TextView upNext =(TextView) findViewById(R.id.up_next);
            upNext.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));
            upNext.setText("Until " + topTask.getEndTime().print());

            TextView location =(TextView) findViewById(R.id.Tag_Location);
            location.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));
            location.setText(topTask.getLoc());

            TextView upComingTaskDeadlineTime =(TextView) findViewById(R.id.upcoming_task_deadline_time);
            upComingTaskDeadlineTime.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/DIN Regular.ttf"));
            upComingTaskDeadlineTime.setText(String.format("%02d", topTask.getStartTime().getHour()) + ":" + String.format("%02d", topTask.getStartTime().getMinute()));

        }

        /**
         *  Refresh task adapter data
         */
        taskAdapter.notifyDataSetChanged();
        updateRVAdapter();

    }

    private void updateRVAdapter(){

        taskAdapter = new TaskAdapter(this,tasks);
        rv.setAdapter(taskAdapter);

    }
}
