package com.seankram.simonpractice;

import android.animation.ObjectAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    TextView levelText;

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;

    Button startButton;
    Button resumeButton;

    int level = 1;
    int numClick = 0;
    boolean inputReady = false;
    ArrayList<Integer> input;
    ArrayList<Integer> pattern;

    Random r;

    Animation flashButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelText = (TextView) findViewById(R.id.levelText);

        b1 = (ImageButton) findViewById(R.id.b1);
        b2 = (ImageButton) findViewById(R.id.b2);
        b3 = (ImageButton) findViewById(R.id.b3);
        b4 = (ImageButton) findViewById(R.id.b4);
        startButton = (Button) findViewById(R.id.startButton);
        resumeButton = (Button) findViewById(R.id.resumeButton);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        startButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);

        flashButton = AnimationUtils.loadAnimation(this, R.anim.bflash);

        levelText.setText("Level: " + level);

        pattern = new ArrayList<>();
        r = new Random();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                if (inputReady) {
                    input.add(1);
                    checkAnswer();
                }
                break;
            case R.id.b2:
                if (inputReady) {
                    input.add(2);
                    checkAnswer();
                }
                break;
            case R.id.b3:
                if (inputReady) {
                    input.add(3);
                    checkAnswer();
                }
                break;
            case R.id.b4:
                if (inputReady) {
                    input.add(4);
                    checkAnswer();
                }
                break;
            case R.id.startButton:
                level = 1;
                levelText.setText("Level: " + level);
                pattern.clear();
                playGame();
                break;
            case R.id.resumeButton:
                playGame();
                resumeButton.setVisibility(View.INVISIBLE);
                break;
        }
    }

    protected void playGame() {
        if (level > pattern.size()) {
            pattern.add(r.nextInt(4) + 1);
        } else if (pattern.size() > 1) {
            pattern.remove(pattern.get(level));
        }

        flashButtons();

        input = new ArrayList<>();
        numClick = 0;
        inputReady = true;
    }

    protected void checkAnswer() {
        if (pattern.get(numClick) != input.get(numClick)) {
            gotItWrong();
        } else if (pattern.size() == input.size()) {
            gotItRight();
        } else {
            numClick++;
        }
    }

    protected void gotItRight() {
        level++;
        levelText.setText("Level: " + level);
        playGame();
    }

    protected void gotItWrong() {
        inputReady = false;
        resumeButton.setVisibility(View.VISIBLE);
        if (level > 1) {
            level--;
            levelText.setText("Level: " + level);
        }
    }

    protected void flashButtons() {
        int[] btnsToFlash = new int[pattern.size()];
        for (int i = 0; i < pattern.size(); i++) {

            switch (pattern.get(i)) {
                case 1:
                    btnsToFlash[i] = R.id.b1;
                    break;
                case 2:
                    btnsToFlash[i] = R.id.b2;
                    break;
                case 3:
                    btnsToFlash[i] = R.id.b3;
                    break;
                case 4:
                    btnsToFlash[i] = R.id.b4;
                    break;
            }
        }

        ArrayList<ObjectAnimator> anims = new ArrayList<>();

        int i = 0;
        for (int viewId : btnsToFlash) {
            ImageButton imgBtn = (ImageButton) findViewById(viewId);
            ObjectAnimator flashBtn = ObjectAnimator.ofFloat(imgBtn, "alpha", 1.0f, 0.0f);
            flashBtn.setRepeatCount(1);
            flashBtn.setRepeatMode(ObjectAnimator.REVERSE);
            flashBtn.setDuration(300);
            flashBtn.setStartDelay((i * 500) + 250);
            anims.add(flashBtn);
            i++;
        }

        for (ObjectAnimator anim : anims) {
            anim.start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}