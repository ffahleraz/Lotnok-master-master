package com.adylanroaffa.lotnok;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.adylanroaffa.lotnok.oneTimeEvent.OneTimeFragment;
import com.adylanroaffa.lotnok.scheduledEvent.ScheduledEventFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTaskFragment extends Fragment {

    public SelectTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_task, container, false);

        /*
        * One-Time BUTTON
        * */
        Button oneTimeButton = (Button) view.findViewById(R.id.one_time_button);
        oneTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OneTimeFragment oneTimeFragment= new OneTimeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,oneTimeFragment).addToBackStack(null).commit();
            }
        });

        /*
        * Scheduled BUTTON
        * */
        Button scheduledButton = (Button) view.findViewById(R.id.scheduled_button);
        scheduledButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ScheduledEventFragment scheduledEventFragment= new ScheduledEventFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,scheduledEventFragment).addToBackStack(null).commit();
            }
        });

        /*
        * Projects BUTTON
        * */
        Button nextbutton = (Button) view.findViewById(R.id.projects_button);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO

            }
        });

        /*
        * CLOSE BUTTON
        * */
        ImageButton closeButton = (ImageButton) view.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

}
