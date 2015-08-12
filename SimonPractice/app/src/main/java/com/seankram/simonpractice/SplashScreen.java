package com.seankram.simonpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class SplashScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        View btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);

        View btnExit = findViewById(R.id.btnQuit);
        btnExit.setOnClickListener(this);
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
