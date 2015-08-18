package com.seankram.simonpractice;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class SplashScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        Button btnExit = (Button) findViewById(R.id.btnQuit);
        btnExit.setOnClickListener(this);

        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        String fontPath = "fonts/spincycle_ot.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);

        titleTextView.setTypeface(tf);
        btnPlay.setTypeface(tf);
        btnExit.setTypeface(tf);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                startGame();
                break;
            case R.id.btnQuit:
                finish();
                break;
        }
    }

    public void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
