package com.adylanroaffa.lotnok.projectEvent;

import android.content.Context;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.PopupActivity;
import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.oneTimeEvent.AddDetailsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetDeadlineTimeFragment extends Fragment {

    private TimePicker deadlineTimePicker;

    // new creation variables
    public String newCreationName;
    public DateTime newCreationDeadline;

    public SetDeadlineTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_project_set_deadline_time, container, false);

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
        * DEADLINE TIME PICKERS
        * setting it to 24 hours format
        * */
        deadlineTimePicker = (TimePicker) view.findViewById(R.id.deadline_time);
        deadlineTimePicker.setIs24HourView(true);

        /*
        * PLAN IT BUTTON
        * */
        Button addDetailButton = (Button) view.findViewById(R.id.confirm_button);
        addDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddDetailsProjectFragment addDetailsProjectFragment = new AddDetailsProjectFragment();

                // resolve new time data
                newCreationDeadline.setHour(deadlineTimePicker.getHour());
                newCreationDeadline.setMinute(deadlineTimePicker.getMinute());

                // pass data for new one time event
                addDetailsProjectFragment.newCreationName = newCreationName;
                addDetailsProjectFragment.newCreationDeadline = newCreationDeadline;

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, addDetailsProjectFragment).addToBackStack(null).commit();
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
