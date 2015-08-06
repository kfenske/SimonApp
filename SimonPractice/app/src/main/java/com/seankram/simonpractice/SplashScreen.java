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

        View btnHighScores = findViewById(R.id.btnHighScores);
        btnHighScores.setOnClickListener(this);

        View btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                startGame();
                break;
            case R.id.btnHighScores:
                viewHS();
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }

    public void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void viewHS() {
        //Intent intent = new Intent(this, HighScores.class);
        //startActivity(intent);
    }
}
