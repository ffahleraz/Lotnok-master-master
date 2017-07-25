package com.adylanroaffa.lotnok.scheduledEvent;

/**
 * Created by ffahleraz on 7/24/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.adylanroaffa.lotnok.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseTypeFragment extends Fragment {

    private boolean canConfirm = false;
    private int newCreationFrequency;

    // new creation variables
    public String newCreationName;

    public ChooseTypeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_scheduled_event_choose_type, container, false);

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

        /**
         *  RADIO BUTTON GROUP
         */
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);

        // checked change listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                canConfirm = true;

                switch(checkedId) {
                    case R.id.radio_daily:
                        newCreationFrequency = 0;
                        break;
                    case R.id.radio_weekly:
                        newCreationFrequency = 1;
                        break;
                    case R.id.radio_monthly:
                        newCreationFrequency = 2;
                        break;
                    case R.id.radio_yearly:
                        newCreationFrequency = 3;
                        break;
                }
            }
        });

        /*
        * NEXT BUTTON
        * */
        final Button nextButton = (Button) view.findViewById(R.id.confirm_button);

        // click listener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (canConfirm) {

                    switch(newCreationFrequency) {
                        case 0:
                            AddTimeScheduledFragment addTimeScheduledFragment = new AddTimeScheduledFragment();

                            // pass data for new scheduled event
                            addTimeScheduledFragment.newCreationName = newCreationName;
                            addTimeScheduledFragment.newCreationFrequency = newCreationFrequency;

                            FragmentManager fragmentManager0 = getFragmentManager();
                            fragmentManager0.beginTransaction().replace(R.id.container, addTimeScheduledFragment).addToBackStack(null).commit();
                            break;
                        case 1:
                            AddDayTimeFragment addDayTimeFragment = new AddDayTimeFragment();

                            // pass data for new scheduled event
                            addDayTimeFragment.newCreationName = newCreationName;
                            addDayTimeFragment.newCreationFrequency = newCreationFrequency;

                            FragmentManager fragmentManager1 = getFragmentManager();
                            fragmentManager1.beginTransaction().replace(R.id.container, addDayTimeFragment).addToBackStack(null).commit();
                            break;
                        case 2:
                            DatePickerStaticFragment datePickerStaticFragment = new DatePickerStaticFragment();

                            // pass data for new scheduled event
                            datePickerStaticFragment.newCreationName = newCreationName;
                            datePickerStaticFragment.newCreationFrequency = newCreationFrequency;

                            FragmentManager fragmentManager2 = getFragmentManager();
                            fragmentManager2.beginTransaction().replace(R.id.container, datePickerStaticFragment).addToBackStack(null).commit();
                            break;
                        case 3:
                            DatePickerScheduledFragment datePickerScheduledFragment = new DatePickerScheduledFragment();

                            // pass data for new scheduled event
                            datePickerScheduledFragment.newCreationName = newCreationName;
                            datePickerScheduledFragment.newCreationFrequency = newCreationFrequency;

                            FragmentManager fragmentManager3 = getFragmentManager();
                            fragmentManager3.beginTransaction().replace(R.id.container, datePickerScheduledFragment).addToBackStack(null).commit();
                            break;
                    }

                } else {
                    Toast.makeText(getContext(),"Please select event frequency.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }

}
