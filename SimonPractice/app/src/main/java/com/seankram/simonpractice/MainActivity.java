package com.seankram.simonpractice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    int level = 1;
    int consecRight = 0;
    int consecWrong = 0;
    int numClick = 0;
    boolean didClick = false;
    boolean isRunning = false;
    boolean inputReady = false;
    ArrayList<Integer> input;
    ArrayList<Integer> pattern;

    Thread thread;
    Handler h;

    // protected GameSurfaceView gameView;

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

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        startButton.setOnClickListener(this);

        levelText.setText("Level: " + level);

        /* gameView = new GameSurfaceView(this);
        setContentView(gameView); */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                if (inputReady) {
                    input.add(1);
                    didClick = true;
                }
                break;
            case R.id.b2:
                if (inputReady) {
                    input.add(2);
                    didClick = true;
                }
                break;
            case R.id.b3:
                if (inputReady) {
                    input.add(3);
                    didClick = true;
                }
                break;
            case R.id.b4:
                if (inputReady) {
                    input.add(4);
                    didClick = true;
                }
                break;
            case R.id.startButton:
                levelText.setText("Level clicked!");
                Log.i("Pattern", "Start clicked.");
                /*if (!isRunning) {
                    isRunning = true;
                    consecRight = 0;
                    consecWrong = 0;
                    playGame();
                } */

                pattern = new ArrayList<Integer>();
                Random r = new Random();

                for (int i = 0; i < level + 2; i++) {
                    pattern.add(r.nextInt(4) + 1);
                    Log.i("Pattern", pattern.get(i).toString());
                }

                flashButtons();

                break;
        }
    }

    protected void playGame() {
        while (isRunning) {
            pattern = new ArrayList<Integer>();
            Random r = new Random();

            for (int i = 0; i < level + 2; i++) {
                pattern.add(r.nextInt(4) + 1);
            }

            flashButtons();

            input = new ArrayList<Integer>();
            numClick = 0;
            inputReady = true;

            long t = System.currentTimeMillis();
            long end = t + 10000;

            while (System.currentTimeMillis() < end) {
                if (didClick) {
                    if (pattern.get(numClick) != input.get(numClick)) {
                        gotItWrong();
                        break;
                    } else if (pattern.size() == input.size()) {
                        gotItRight();
                        break;
                    } else {
                        numClick++;
                    }
                    didClick = false;
                }
            }

            if (input.size() < pattern.size()) {
                gotItWrong();
            }

        }
        isRunning = false;
    }

    protected void gotItRight() {
        consecRight++;
        consecWrong = 0;
        if (consecRight == 5) {
            level++;
            levelText.setText("Level: " + level);
            consecRight = 0;
        }
    }

    protected void gotItWrong() {
        consecWrong++;
        consecRight = 0;
        if (consecWrong == 3 && level > 1) {
            level--;
            levelText.setText("Level: " + level);
            consecWrong = 0;
        }
    }

    protected void flashButtons() {
        for (int i = 0; i < pattern.size(); i++) {

            switch (pattern.get(i)) {
                case 1:
                    b1.setPressed(true);
                    // while (System.currentTimeMillis() < end) {}
                    b1.setPressed(false);
                    break;
                case 2:
                    b2.setPressed(true);
                    // while (System.currentTimeMillis() < end) {}
                    b2.setPressed(false);
                    break;
                case 3:
                    b3.setPressed(true);
                    // while (System.currentTimeMillis() < end) {}
                    b3.setPressed(false);
                    break;
                case 4:
                    b4.setPressed(true);
                    // while (System.currentTimeMillis() < end) {}
                    b4.setPressed(false);
                    break;
            }
        }
    }

        /*h = new Handler();
        Runnable runnable = new Runnable(){

            public void run() {

        };

        thread = new Thread(runnable);
        thread.start();*/


    @Override
    protected void onResume() {
        super.onResume();
        // gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // gameView.pause();
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

/* class StopperThread extends Thread {
    private long time = 0;

    public boolean isStop = false;

    public StopperThread(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        long start = 0;

        while(true) {
            start = System.currentTimeMillis();
            try {
                Thread.sleep(this.time);
            } catch (InterruptedException e) {
                this.time -= (System.currentTimeMillis() - start);
                if (this.time > 0) {
                    continue;
                }
            }
            isStop = true;
            break;
        }
    }
}
*/