package com.seankram.simonpractice;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
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
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View.OnClickListener;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private FXPlayer buttonPlayer = new FXPlayer();

    TextView levelText;
    TextView howToPlayText;

    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    ImageButton b4;

    Button startButton;
    Button resumeButton;

    boolean inputReady = false;

    Animation flashButton;

    public GamePlay game;
    public TopScores topTen;

    String newName;
    String newScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelText = (TextView) findViewById(R.id.levelText);
        howToPlayText = (TextView) findViewById(R.id.howToPlay);

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
        howToPlayText.setOnClickListener(this);
        startButton.setOnClickListener(this);
        resumeButton.setOnClickListener(this);

        flashButton = AnimationUtils.loadAnimation(this, R.anim.bflash);

        topTen = new TopScores();
        //String[][] winners = topTen.updatedWinners();

        game = new GamePlay();
        updateLevel();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startButton) {
            game.newGame();
            updateLevel();
            flashButtons();
            inputReady = true;
        }
        else if (v.getId() == R.id.howToPlay) {
            howToPlay();
        }
        else {
            switch (v.getId()) {
                case R.id.b1:
                    buttonPlayer.play(this, v);
                    if (inputReady) {
                        game.inputArray.add(1);
                        game.checkAnswer();
                    }
                    break;
                case R.id.b2:
                    buttonPlayer.play(this, v);
                    if (inputReady) {
                        game.inputArray.add(2);
                        game.checkAnswer();
                    }
                    break;
                case R.id.b3:
                    buttonPlayer.play(this, v);
                    if (inputReady) {
                        game.inputArray.add(3);
                        game.checkAnswer();
                    }
                    break;
                case R.id.b4:
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
    }

    protected void flashButtons() {
        updateLevel();

        int[] btnsToFlash = new int[game.gameArray.size()];
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

    protected void setScore(String name) {
        newName = name;
        newScore = String.valueOf(game.storeScore);
        String[] newEntry = {newName, newScore};
        topTen.setScore(newEntry);
    }

    protected void howToPlay() {

        LayoutInflater layout = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        final View popupView = layout.inflate(R.layout.how_to_play_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.update();

        final OnClickListener btnOkListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        };

        Button btnOK = (Button)popupView.findViewById(R.id.how_to_play_ok);

        btnOK.setOnClickListener(btnOkListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
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
                if (nameEntry.getText() != null) {
                    String str = nameEntry.getEditableText().toString();
                    setScore(str);
                }
                popupWindow.dismiss();
            }
        };

        Button btnEnter = (Button)popupView.findViewById(R.id.enter_score);

        btnEnter.setOnClickListener(btnEnterListener);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    // Updates the display to show what level the user is on,
    // that is, how large their current pattern is.
    protected void updateLevel() {
        levelText.setText("Level: " + game.gameCounter);
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
        switch(item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_top_scores:
                startActivity(new Intent(getApplicationContext(),TopScoresActivity.class));
                return true;
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}