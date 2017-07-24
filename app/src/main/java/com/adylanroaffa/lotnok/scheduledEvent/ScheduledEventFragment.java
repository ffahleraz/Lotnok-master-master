package com.adylanroaffa.lotnok.scheduledEvent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.oneTimeEvent.DatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduledEventFragment extends Fragment {

    public ScheduledEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled_event, container, false);

        // resize the popup
        resizePopup();

        /* sementara ilangin dulu
        /*
        * CATEGORY SPINNER
        * *
        Spinner spinner = (Spinner) view.findViewById(R.id.one_time_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        */

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
        * NAME EDIT TEXT
        * */
        final EditText nameET = (EditText) view.findViewById(R.id.name_editText);

        /*
        * CREATE SCHEDULE BUTTON
        * */
        Button createScheduleButton = (Button) view.findViewById(R.id.confirm_button);

        // click listener
        createScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nameET.getText().toString().matches("")) {

                    ChooseTypeFragment chooseTypeFragment = new ChooseTypeFragment();

                    // pass data for new one time event
                    chooseTypeFragment.newCreationName = nameET.getText().toString();

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,chooseTypeFragment).addToBackStack(null).commit();

                } else {
                    Toast.makeText(getContext(),"Please enter event name.",Toast.LENGTH_SHORT).show();
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

        getActivity().getWindow().setLayout((int)(width*0.9),(int)(height*0.6));
    }

}
