package com.seankram.simonpractice;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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

        TextView aboutButtonTitle = (TextView) findViewById(R.id.aboutButtonTitle);
        TextView howToPlayButtonTitle = (TextView) findViewById(R.id.howToPlayButtonTitle);
        TextView clearScoresButtonTitle = (TextView) findViewById(R.id.clearScoresButtonTitle);
        TextView changeThemesButtonTitle = (TextView) findViewById(R.id.changeThemesButtonTitle);

        String fontPath = "fonts/spincycle_ot.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        aboutButtonTitle.setTypeface(tf);
        howToPlayButtonTitle.setTypeface(tf);
        clearScoresButtonTitle.setTypeface(tf);
        changeThemesButtonTitle.setTypeface(tf);

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
            case R.id.clear_scores_button_layout:
                clearScores();
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

    protected void clearScores() {
        LayoutInflater layout = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = layout.inflate(R.layout.clear_scores_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.update();

        final View.OnClickListener btnOkListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.topTen = new TopScores();
                popupWindow.dismiss();
            }
        };

        final View.OnClickListener btnCancelListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        };

        Button btnOK = (Button)popupView.findViewById(R.id.clear_scores_ok);
        Button btnCancel = (Button)popupView.findViewById(R.id.clear_scores_cancel);

        btnOK.setOnClickListener(btnOkListener);
        btnCancel.setOnClickListener(btnCancelListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}
