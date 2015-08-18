package com.seankram.simonpractice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnTouchListener {

    private FXPlayer buttonPlayer;

    TextView levelText;

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;

    Button startButton;

    int currentButton;
    int[] btnsToFlash;
    int counter;

    boolean inputReady = false;

    Animation flashButton;

    public GamePlay game;
    public static TopScores topTen;

    String newName;
    String newScore;

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

        b1.setOnTouchListener(this);
        b2.setOnTouchListener(this);
        b3.setOnTouchListener(this);
        b4.setOnTouchListener(this);
        startButton.setOnTouchListener(this);

        buttonPlayer = new FXPlayer();

        flashButton = AnimationUtils.loadAnimation(this, R.anim.bflash);

        topTen = new TopScores();
        //String[][] winners = topTen.updatedWinners();

        String fontPath = "fonts/spincycle_ot.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        levelText.setTypeface(tf);
        startButton.setTypeface(tf);

        game = new GamePlay();
        updateLevel();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v.getId() == R.id.startButton) {
                v.setPressed(true);
            } else {
                switch (v.getId()) {
                    case R.id.b1:
                        v.setPressed(true);
                        buttonPlayer.play(this, v);
                        if (inputReady) {
                            game.inputArray.add(1);
                            game.checkAnswer();
                        }
                        break;
                    case R.id.b2:
                        v.setPressed(true);
                        buttonPlayer.play(this, v);
                        if (inputReady) {
                            game.inputArray.add(2);
                            game.checkAnswer();
                        }
                        break;
                    case R.id.b3:
                        v.setPressed(true);
                        buttonPlayer.play(this, v);
                        if (inputReady) {
                            game.inputArray.add(3);
                            game.checkAnswer();
                        }
                        break;
                    case R.id.b4:
                        v.setPressed(true);
                        buttonPlayer.play(this, v);
                        if (inputReady) {
                            game.inputArray.add(4);
                            game.checkAnswer();
                        }
                        break;
                }

                if (game.playerLost) {
                    updateLevel();
                    inputReady = false;
                    Toast.makeText(this, "Sorry, you lost!", Toast.LENGTH_SHORT).show();

                    if(topTen.checkScore(game.storeScore)){
                        enterHighScore();
                    }

                } else if (game.playerWon) {
                    flashButtons();
                    inputReady = true;
                }
            }
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (v.getId() == R.id.startButton) {
                v.setPressed(false);
                game.gameCounter = 1;
                game.gameArray.clear();

                game.newGame();
                updateLevel();
                flashButtons();
                inputReady = true;
            } else {
                v.setPressed(false);
            }
        }

        return false;
    }

    protected void flashButtons() {
        updateLevel();

        btnsToFlash = new int[game.gameArray.size()];
        for (int i = 0; i < game.gameArray.size(); i++) {

            switch (game.gameArray.get(i)) {
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

        counter = 0;
        for (int viewId : btnsToFlash) {
            ImageButton imgBtn = (ImageButton) findViewById(viewId);
            ObjectAnimator flashBtn = ObjectAnimator.ofFloat(imgBtn, "alpha", 1.0f, 0.0f);
            flashBtn.setRepeatCount(1);
            flashBtn.setRepeatMode(ObjectAnimator.REVERSE);
            flashBtn.setDuration(300);
            flashBtn.setStartDelay((counter * 500) + 500);
            anims.add(flashBtn);
            counter++;
        }

        counter = 0;
        for (ObjectAnimator anim : anims) {
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    currentButton = btnsToFlash[counter];
                    buttonPlayer.playButton(getApplicationContext(), currentButton);
                    counter++;
                }
            });
            anim.start();
        }
    }

    protected void setScore(String name) {
        newName = name;
        newScore = String.valueOf(game.storeScore);
        String[] newEntry = {newName, newScore};
        topTen.setScore(newEntry);
    }

    protected void enterHighScore() {

        LayoutInflater layout = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = layout.inflate(R.layout.highscore_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.update();

        final OnClickListener btnEnterListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText nameEntry = (EditText) popupView.findViewById(R.id.enter_name_edit_text);
                if (nameEntry.getText().length() != 0) {
                    String str = nameEntry.getEditableText().toString();
                    setScore(str);
                    popupWindow.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your name!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Button btnEnter = (Button) popupView.findViewById(R.id.enter_score);

        btnEnter.setOnClickListener(btnEnterListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    // Updates the display to show what level the user is on,
    // that is, how large their current pattern is.
    protected void updateLevel() {
        levelText.setText("SCORE: " + (game.gameCounter - 1));
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.action_top_scores:
                startActivity(new Intent(getApplicationContext(), TopScoresActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}