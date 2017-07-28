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

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EachPreparationFragment extends Fragment {

    com.shawnlin.numberpicker.NumberPicker hoursPicker;
    com.shawnlin.numberpicker.NumberPicker minutesPicker;

    // new creation variables
    public String newCreationName;
    public DateTime newCreationDeadline;
    public int newCreationSplit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

                EachPreparationFragment eachPreparationFragment = new EachPreparationFragment();

                // TODO : calculate preparations



                // TODO : pass data for new one time event


                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, eachPreparationFragment).addToBackStack(null).commit();
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
