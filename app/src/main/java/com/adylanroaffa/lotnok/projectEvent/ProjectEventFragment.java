package com.adylanroaffa.lotnok.projectEvent;


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
public class ProjectEventFragment extends Fragment {

    public ProjectEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_event, container, false);

        // resize the popup
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
        * NAME EDIT TEXT
        **/
        final EditText nameET = (EditText) view.findViewById(R.id.name_editText);

        /*
        * SET DEADLINE BUTTON
        * */
        Button pickDateButton = (Button) view.findViewById(R.id.confirm_button);

        // click listener
        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!nameET.getText().toString().matches("")) {

                    SetDeadlineDateFragment setDeadlineDateFragment = new SetDeadlineDateFragment();

                    // pass data for new one time event
                    setDeadlineDateFragment.newCreationName = nameET.getText().toString();

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container,setDeadlineDateFragment).addToBackStack(null).commit();

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
