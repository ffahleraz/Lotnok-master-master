package com.adylanroaffa.lotnok.projectEvent;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.adylanroaffa.lotnok.oneTimeEvent.AddTimeFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetDeadlineDateFragment extends Fragment {

    private boolean canConfirm = false;

    // new creation variables
    public String newCreationName;
    private DateTime newCreationDeadline = new DateTime();

    public SetDeadlineDateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_project_set_deadline_date, container, false);

        // resize the popup window
        resizePopup();

        /*
        * MATERIAL CALENDAR VIEW
        * */
        MaterialCalendarView mcv = (MaterialCalendarView) view.findViewById(R.id.date_pic);
        //Date today = new Date();
        //mcv.state().edit().setMinimumDate(CalendarDay.from(today));
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                Calendar nowCal = Calendar.getInstance();
                nowCal.set(Calendar.HOUR_OF_DAY, 0);
                nowCal.set(Calendar.MINUTE, 0);
                nowCal.set(Calendar.SECOND, 0);
                nowCal.set(Calendar.MILLISECOND, 0);
                Date now = nowCal.getTime();

                Calendar selectedCal = Calendar.getInstance();
                selectedCal.setTime(date.getDate());
                selectedCal.set(Calendar.HOUR_OF_DAY, 0);
                selectedCal.set(Calendar.MINUTE, 0);
                selectedCal.set(Calendar.SECOND, 0);
                selectedCal.set(Calendar.MILLISECOND, 0);
                Date selectedDate = selectedCal.getTime();

                if (now.compareTo(selectedDate) <= 0) {
                    canConfirm = true;
                }

                newCreationDeadline.setByDate(date.getDate());
            }
        });

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
        * ADD TIME BUTTON
        * */
        final Button addTimeButton = (Button) view.findViewById(R.id.add_time_button);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (canConfirm) {

                    SetDeadlineTimeFragment setDeadlineTimeFragment = new SetDeadlineTimeFragment();

                    // pass data for new one time event
                    setDeadlineTimeFragment.newCreationName = newCreationName;
                    setDeadlineTimeFragment.newCreationDeadline = newCreationDeadline;

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, setDeadlineTimeFragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(),"Please select a date (must be today or later).",Toast.LENGTH_SHORT).show();
                }

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

}
