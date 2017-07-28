package com.adylanroaffa.lotnok.scheduledEvent;

/**
 * Created by ffahleraz on 7/24/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.database.OneTimeDatabase;
import com.adylanroaffa.lotnok.database.ScheduledDatabase;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDetailsScheduledFragment extends Fragment {

    // new creation variables
    public String newCreationName;
    public DateTime newCreationStartTime;
    public DateTime newCreationEndTime;
    public int newCreationFrequency;
    public int newCreationDay;

    public AddDetailsScheduledFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled_event_add_details, container, false);

        // Resize popup
        resizePopup();

        /*
        * LOCATION EDIT TEXT
        * */
        final EditText locET = (EditText) view.findViewById(R.id.location_editText);

        /*
        * NOTES EDIT TEXT
        * */
        final EditText notesET = (EditText) view.findViewById(R.id.notes_editText);
        notesET.setHorizontallyScrolling(false);
        notesET.setMaxLines(2);

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
        * DONE BUTTON
        * */
        Button doneButton = (Button) view.findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().setContentView(R.layout.event_added);
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

                int height = dm.heightPixels;
                int width = dm.widthPixels;

                getActivity().getWindow().setLayout((int)(width*0.6),(int)(height*0.4));
                //getActivity().finish();

                // ADD NEW SCHEDULED EVENT TO DATABASE
                ScheduledDatabase newScheduledData = new ScheduledDatabase();
                newScheduledData.setName(newCreationName);
                newScheduledData.setStartTime(newCreationStartTime.getByDate());
                newScheduledData.setEndTime(newCreationEndTime.getByDate());
                newScheduledData.setNotes(notesET.getText().toString());
                newScheduledData.setLoc(locET.getText().toString());
                newScheduledData.setFrequency(newCreationFrequency);
                newScheduledData.setDay(newCreationDay);
                newScheduledData.setDone(false);
                newScheduledData.save();
                //

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
