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

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.R;
import com.adylanroaffa.lotnok.database.OneTimeDatabase;

import java.util.Date;

/**
 * Created by ffahleraz on 7/29/17.
 */

public class AddDetailsProjectFragment extends Fragment {

    // new creation variables
    public String newCreationName;
    public DateTime newCreationDeadline;

    public AddDetailsProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_add_details, container, false);

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

                SetPreparationFragment setPreparationFragment = new SetPreparationFragment();

                // pass data for new one time event
                setPreparationFragment.newCreationName = newCreationName;
                setPreparationFragment.newCreationDeadline = newCreationDeadline;
                setPreparationFragment.newCreationNotes = notesET.getText().toString();
                setPreparationFragment.newCreationLoc = locET.getText().toString();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, setPreparationFragment).addToBackStack(null).commit();

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
