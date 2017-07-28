package com.adylanroaffa.lotnok.projectEvent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adylanroaffa.lotnok.DateTime;
import com.adylanroaffa.lotnok.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EachPreparationFragment extends Fragment {

    // new creation variables
    public String newCreationName;
    public DateTime newCreationDeadline;
    public int newCreationSplit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_each_preparation, container, false);
    }


}
