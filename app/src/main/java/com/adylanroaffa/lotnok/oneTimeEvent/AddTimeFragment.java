package com.adylanroaffa.lotnok.oneTimeEvent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.PopupActivity;
import com.adylanroaffa.lotnok.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddTimeFragment extends Fragment {

    public DateTime newCreationStartTime;
    private DateTime newCreationEndTime = new DateTime();
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;

    // new creation variables
    public String newCreationName;

    public AddTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_time_add_time, container, false);

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

                AddDetailsFragment addDetailsFragment = new AddDetailsFragment();

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

                    addDetailsFragment.newCreationName = newCreationName;
                    addDetailsFragment.newCreationStartTime = newCreationStartTime;
                    addDetailsFragment.newCreationEndTime = newCreationEndTime;

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,addDetailsFragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(),"End time must be after start time.",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}


