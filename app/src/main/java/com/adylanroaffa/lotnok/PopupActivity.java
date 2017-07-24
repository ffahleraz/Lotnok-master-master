package com.adylanroaffa.lotnok;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;

/**
 * Created by Adylan Roaffa on 7/12/2017.
 */

public class PopupActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        Drawable background = getResources().getDrawable(R.drawable.shape);

        getWindow().setLayout((int)(width*0.9),(int)(height*0.6));
        getWindow().setBackgroundDrawable(background);

        SelectTaskFragment selectTaskFragment = new SelectTaskFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.container,selectTaskFragment).commit();
    }

    public void resizePopup(){
         /*TODO resize the popup window*/

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int height = dm.heightPixels;
        int width = dm.widthPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.75));
    }

    public void updateData() {

    }

}