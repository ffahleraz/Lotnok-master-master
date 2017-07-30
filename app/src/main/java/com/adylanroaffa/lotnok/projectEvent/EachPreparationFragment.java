package com.adylanroaffa.lotnok.projectEvent;

import android.content.Context;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.Task;
import com.adylanroaffa.lotnok.database.ProjectDatabase;
import com.adylanroaffa.lotnok.database.TimeTableDatabase;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EachPreparationFragment extends Fragment {

    com.shawnlin.numberpicker.NumberPicker hoursPicker;
    com.shawnlin.numberpicker.NumberPicker minutesPicker;

    // new creation variables
    public String newCreationName;
    public String newCreationNotes;
    public String newCreationLoc;
    public DateTime newCreationDeadline;
    public int newCreationSplit;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_project_each_preparation, container, false);

        // Resize popup
        resizePopup();

         /*
        * BACK BUTTON
        * */
        ImageButton backButton = (ImageButton) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        /*
        * SPLIT NUMBER PICKER
        * */
        hoursPicker = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.hours_picker);
        minutesPicker = (com.shawnlin.numberpicker.NumberPicker) view.findViewById(R.id.minutes_picker);

        /*
        * PLAN IT BUTTON
        * */
        Button addDetailButton = (Button) view.findViewById(R.id.confirm_button);
        addDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.event_added);
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

                int height = dm.heightPixels;
                int width = dm.widthPixels;

                getActivity().getWindow().setLayout((int)(width*0.6),(int)(height*0.4));
                //getActivity().finish();

                // TODO : calculate preparations

                // get today's date as a Calendar object
                Calendar now = Calendar.getInstance();
                now.setTime(new Date());

                // calculate days between now and deadline, and calculate range between preparations
                Date nowD = new Date();
                Date thenD = newCreationDeadline.getByDate();
                int remainingDay = (int)( (thenD.getTime() - nowD.getTime()) / (1000 * 60 * 60 * 24));
                int rangeDiv = remainingDay / newCreationSplit;
                int rangeRem = remainingDay % newCreationSplit;

                // move forward so remaining days / split num. is even
                now.add(Calendar.DATE, rangeRem);

                // load timetable database
                List<TimeTableDatabase> timeTableDatas = new Select().from(TimeTableDatabase.class).queryList();

                // create new project database
                ProjectDatabase projectDatabase = new ProjectDatabase();
                projectDatabase.setName(newCreationName);
                projectDatabase.setNotes(newCreationNotes);
                projectDatabase.setLoc(newCreationLoc);
                projectDatabase.setDeadline(newCreationDeadline.getByDate());
                projectDatabase.setSplit(newCreationSplit);
                projectDatabase.setPrepDur((hoursPicker.getValue() * 60) + minutesPicker.getValue());

                // create one preparation task every range days
                for (int i = 0; i < newCreationSplit; i++) {

                    //Toast.makeText(getContext(),"lagi bikin " + i,Toast.LENGTH_SHORT).show();

                    boolean added = false;

                    if (!((i == 0) && (rangeRem == 0))) {
                        now.set(Calendar.HOUR_OF_DAY, 10);
                        now.set(Calendar.MINUTE, 0);
                        now.set(Calendar.SECOND, 0);
                        now.set(Calendar.MILLISECOND, 0);
                    } else {
                        now.add(Calendar.MINUTE, 40);
                    }

                    Date startTime = now.getTime();
                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(startTime);
                    endCal.add(Calendar.HOUR, hoursPicker.getValue());
                    endCal.add(Calendar.MINUTE, minutesPicker.getValue());
                    Date endTime = endCal.getTime();

                    while (!timeIsAvailable(timeTableDatas, startTime, endTime)) {

                        Calendar newStartCal = Calendar.getInstance();
                        newStartCal.setTime(startTime);
                        newStartCal.add(Calendar.MINUTE, 10);
                        startTime = newStartCal.getTime();

                        Calendar newEndCal = Calendar.getInstance();
                        newEndCal.setTime(endTime);
                        newEndCal.add(Calendar.MINUTE, 10);
                        endTime = newEndCal.getTime();

                    }

                    switch (i) {

                        case 0:
                            projectDatabase.setPrep0Start(startTime);
                            break;
                        case 1:
                            projectDatabase.setPrep1Start(startTime);
                            break;
                        case 2:
                            projectDatabase.setPrep2Start(startTime);
                            break;
                        case 3:
                            projectDatabase.setPrep3Start(startTime);
                            break;
                        case 4:
                            projectDatabase.setPrep4Start(startTime);
                            break;
                        case 5:
                            projectDatabase.setPrep5Start(startTime);
                            break;
                        case 6:
                            projectDatabase.setPrep6Start(startTime);
                            break;
                        case 7:
                            projectDatabase.setPrep7Start(startTime);
                            break;

                    }

                    now.add(Calendar.DATE, rangeDiv);

                }

                projectDatabase.save();

                // TODO : pass data for new one time event

            }
        });

        return view;
    }

    public void resizePopup(){
         /*TODO resize the popup window*/

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getActivity().getWindow().setLayout((int)(width*0.9),(int)(height*0.85));
    }

    private boolean timeIsAvailable (List<TimeTableDatabase> timeTableDatas, Date startTime, Date endTime) {

        //Toast.makeText(getContext(),"lagi nyari.",Toast.LENGTH_SHORT).show();

        boolean available = false;

        boolean startValid = true;
        boolean found = false;
        for (TimeTableDatabase timeTableData : timeTableDatas) {

            if ((timeTableData.getStartTime().compareTo(endTime) >= 0) && startValid) {
                available = true;
                found = true;
            }

            if (timeTableData.getEndTime().compareTo(startTime) <= 0) {
                startValid = true;
            } else {
                startValid = false;
            }

        }

        if (startValid) {
            available = true;
        }

        //Toast.makeText(getContext(),"udah nyari.",Toast.LENGTH_SHORT).show();

        return available;
    }

}
