package com.seankram.simonpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Kayla_2 on 8/12/2015.
 */
public class SettingsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        LinearLayout aboutButton = (LinearLayout) findViewById(R.id.about_button_layout);
        LinearLayout howToPlayButton = (LinearLayout) findViewById(R.id.how_to_play_button_layout);
        LinearLayout clearScores = (LinearLayout) findViewById(R.id.clear_scores_button_layout);
        LinearLayout changeTheme = (LinearLayout) findViewById(R.id.change_themes_button_layout);

        aboutButton.setOnClickListener(this);
        howToPlayButton.setOnClickListener(this);
        clearScores.setOnClickListener(this);
        changeTheme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.how_to_play_button_layout:
                howToPlay();
                break;
            case R.id.about_button_layout:
                aboutSimon();
                break;
        }
    }

    protected void howToPlay() {

        LayoutInflater layout = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = layout.inflate(R.layout.how_to_play_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.update();

        final View.OnClickListener btnOkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        };

        Button btnOK = (Button)popupView.findViewById(R.id.how_to_play_ok);

        btnOK.setOnClickListener(btnOkListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    protected void aboutSimon() {

        LayoutInflater layout = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = layout.inflate(R.layout.about_simon_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.update();

        final View.OnClickListener btnOkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        };

        Button btnOK = (Button)popupView.findViewById(R.id.about_simon_ok);

        btnOK.setOnClickListener(btnOkListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}
