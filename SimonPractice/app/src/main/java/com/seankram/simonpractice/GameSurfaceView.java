package com.seankram.simonpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by seankram on 7/29/15.
 */
public class GameSurfaceView extends SurfaceView implements Runnable {

    TextView levelText;

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;

    int level = 1;
    int consecRight = 0;
    int consecWrong = 0;

    private boolean isRunning = false;
    private Thread gameThread;
    private SurfaceHolder holder;

    private final static int MAX_FPS = 40;
    private final static int FRAME_PERIOD = 1000 / MAX_FPS;

    public GameSurfaceView(Context context) {
        super(context);

        levelText = (TextView) findViewById(R.id.levelText);

        b1 = (ImageButton) findViewById(R.id.b1);
        b2 = (ImageButton) findViewById(R.id.b2);
        b3 = (ImageButton) findViewById(R.id.b3);
        b4 = (ImageButton) findViewById(R.id.b4);

        b1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        b1.setImageResource(R.drawable.b1lit);
                    }
                    case MotionEvent.ACTION_UP: {
                        b1.setImageResource(R.drawable.b1);
                    }
                }
                return true;
            }
        });

        b2.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        b2.setImageResource(R.drawable.b2lit);
                    }
                    case MotionEvent.ACTION_UP: {
                        b2.setImageResource(R.drawable.b2);
                    }
                }
                return true;
            }
        });

        b3.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        b3.setImageResource(R.drawable.b3lit);
                    }
                    case MotionEvent.ACTION_UP: {
                        b3.setImageResource(R.drawable.b3);
                    }
                }
                return true;
            }
        });

        b4.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        b4.setImageResource(R.drawable.b4lit);
                    }
                    case MotionEvent.ACTION_UP: {
                        b4.setImageResource(R.drawable.b4);
                    }
                }
                return true;
            }
        });

        holder = getHolder();
    }

    @Override
    public void run() {
        while (isRunning) {
            // Make sure surface is ready
            if (! holder.getSurface().isValid()) {
                continue;
            }

        }
    }

    public void resume() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        isRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


}
