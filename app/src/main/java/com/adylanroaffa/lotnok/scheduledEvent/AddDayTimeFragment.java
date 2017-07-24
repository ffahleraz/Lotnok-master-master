package com.adylanroaffa.lotnok.scheduledEvent;

/**
 * Created by ffahleraz on 7/24/17.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.PopupActivity;
import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.oneTimeEvent.AddDetailsFragment;

import java.util.Date;

public class AddDayTimeFragment extends Fragment {

    private DateTime newCreationStartTime;
    private DateTime newCreationEndTime = new DateTime();

    private TimePicker startTimePicker;
    private TimePicker endTimePicker;

    private int selectedDay = 0;

    private Spinner spinner;

    // new creation variables
    public String newCreationName;
    public int newCreationFrequency;

    public AddDayTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled_event_add_day_time, container, false);

        //RESIZE POPUP
        ((PopupActivity)getActivity()).resizePopup();

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
        * DAY SPINNER
        * */
        spinner = (Spinner) view.findViewById(R.id.day_spinner);

        // specify items
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.day_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                selectedDay = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*
        * TIME PICKERS
        * setting it to 24 hours format
        * */
        // from
        startTimePicker = (TimePicker) view.findViewById(R.id.from_time);
        startTimePicker.setIs24HourView(true);

        // until
        endTimePicker = (TimePicker) view.findViewById(R.id.until_time);
        endTimePicker.setIs24HourView(true);

        /*
        * ADD DETAILS BUTTON
        * */
        Button addDetailButton = (Button) view.findViewById(R.id.add_details_button);
        addDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddDetailsScheduledFragment addDetailsScheduledFragment = new AddDetailsScheduledFragment();

                // resolve new time data
                newCreationStartTime.setHour(startTimePicker.getHour());
                newCreationStartTime.setMinute(startTimePicker.getMinute());

                newCreationEndTime.setYear(newCreationStartTime.getYear());
                newCreationEndTime.setMonth(newCreationStartTime.getMonth());
                newCreationEndTime.setDay(newCreationStartTime.getDay());
                newCreationEndTime.setHour(endTimePicker.getHour());
                newCreationEndTime.setMinute(endTimePicker.getMinute());

                // pass data for new one time event
                if (newCreationStartTime.getByDate().before(newCreationEndTime.getByDate())) {

                    addDetailsScheduledFragment.newCreationName = newCreationName;
                    addDetailsScheduledFragment.newCreationStartTime = newCreationStartTime;
                    addDetailsScheduledFragment.newCreationEndTime = newCreationEndTime;
                    addDetailsScheduledFragment.newCreationFrequency = newCreationFrequency;
                    addDetailsScheduledFragment.newCreationDay = selectedDay;

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,addDetailsScheduledFragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(),"End time must be after start time.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}