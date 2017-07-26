package com.adylanroaffa.lotnok.scheduledEvent;

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

import java.util.Date;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.scheduledEvent.ChooseTypeFragment;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import com.adylanroaffa.lotnok.R;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerStaticFragment extends Fragment {

    private boolean canConfirm = false;

    // new creation variables
    public int newCreationFrequency;
    public String newCreationName;
    public DateTime newCreationStartTime = new DateTime();

    public DatePickerStaticFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_scheduled_event_date_picker_static, container, false);

        // resize the popup window
        resizePopup();

        /*
        * MATERIAL CALENDAR VIEW
        * */
        MaterialCalendarView mcv = (MaterialCalendarView) view.findViewById(R.id.date_pic);
        //mcv.setTopbarVisible(false);
        mcv.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                canConfirm = true;
                newCreationStartTime.setByDate(date.getDate());
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
        * ADD DETAILS BUTTON
        * */
        final Button addTimeButton = (Button) view.findViewById(R.id.add_time_button);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (canConfirm) {

                    AddTimeScheduledFragment addTimeScheduledFragment = new AddTimeScheduledFragment();

                    // pass data for new one time event
                    addTimeScheduledFragment.newCreationName = newCreationName;
                    addTimeScheduledFragment.newCreationStartTime = newCreationStartTime;
                    addTimeScheduledFragment.newCreationFrequency = newCreationFrequency;

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, addTimeScheduledFragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(),"Please select a date.",Toast.LENGTH_SHORT).show();
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
