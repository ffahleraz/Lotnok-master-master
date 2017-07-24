package com.adylanroaffa.lotnok;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.database.OneTimeDatabase;
import com.raizlabs.android.dbflow.config.*;
import com.raizlabs.android.dbflow.sql.language.*;

import java.util.ArrayList;
import java.util.List;

import static com.adylanroaffa.lotnok.R.id.day;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks;
    private RecyclerView rv;
    private TaskAdapter taskAdapter;
    private Calendar calendar;

    private int taskNum = 0;
    private int deadlineNum = 0;

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

        // set top date circle text
        TextView dateToday = (TextView) findViewById(R.id.date_today);
        dateToday.setText(""+date);

        // set top month & day text
        TextView dayToday = (TextView) findViewById(R.id.day_today);
        dayToday.setText(format.format(calendar.getTime()));

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

        /* Load data from database (kalo ude debug di uncomment lah ye)
        List<OneTimeDatabase> oneTimeDatas = new Select().from(OneTimeDatabase.class).queryList();
        for (OneTimeDatabase oneTimeData : oneTimeDatas) {
            tasks.add(new Task(oneTimeData.getName(), "Besok", "33 Jancok"));
        }
        */

        // Delete all data in database (buat debug aeeee)
        List<OneTimeDatabase> oneTimeDatas = new Select().from(OneTimeDatabase.class).queryList();
        for (OneTimeDatabase oneTimeData : oneTimeDatas) {
            oneTimeData.delete();
        }

        updateLabels();
        updateRVAdapter();

    }

    private void updateData() {

        List<OneTimeDatabase> oneTimeDataList = new Select().from(OneTimeDatabase.class).queryList();
        for (OneTimeDatabase oneTimeData : oneTimeDataList) {

            // get startTime as DateTime
            DateTime startTime = new DateTime();
            startTime.setByDate(oneTimeData.getStartTime());

            tasks.add(new Task(oneTimeData.getName(), startTime.print(), "33 Jancok"));
        }

        taskAdapter.notifyDataSetChanged();
        updateLabels();
        updateRVAdapter();

    }

    private void updateLabels() {

        // set tasks label
        taskNum = tasks.size();
        TextView taskTV = (TextView) findViewById(R.id.task_label);
        taskTV.setText("YOU HAVE " + taskNum + " TASKS TODAY");

        // set deadlines label
        TextView deadlineTV = (TextView) findViewById(R.id.deadline_label);
        deadlineTV.setText("YOU HAVE " + deadlineNum + " DEADLINES TODAY");

    }

    private void updateRVAdapter(){

        taskAdapter = new TaskAdapter(this,tasks);
        rv.setAdapter(taskAdapter);

    }
}
