package com.seankram.simonpractice;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewDebug;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

public class TopScoresActivity extends Activity {
    //declare variables for the widgets
    private TextView scoreBoard[][] = new TextView[10][3];
    String[][] winners;

    TextView player_name_score1;
    TextView player_name_score2;
    TextView player_name_score3;
    TextView player_name_score4;
    TextView player_name_score5;
    TextView player_name_score6;
    TextView player_name_score7;
    TextView player_name_score8;
    TextView player_name_score9;
    TextView player_name_score10;

    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;
    TextView score6;
    TextView score7;
    TextView score8;
    TextView score9;
    TextView score10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        //get references to the widgets
        scoreBoard[0][0] = (TextView) findViewById(R.id.label_score1);
        scoreBoard[0][1] = (TextView) findViewById(R.id.player_name_score1);
        scoreBoard[0][2] = (TextView) findViewById(R.id.score1);

        scoreBoard[1][0] = (TextView) findViewById(R.id.label_score2);
        scoreBoard[1][1] = (TextView) findViewById(R.id.player_name_score2);
        scoreBoard[1][2] = (TextView) findViewById(R.id.score2);

        scoreBoard[2][0] = (TextView) findViewById(R.id.label_score3);
        scoreBoard[2][1] = (TextView) findViewById(R.id.player_name_score3);
        scoreBoard[2][2] = (TextView) findViewById(R.id.score3);

        scoreBoard[3][0] = (TextView) findViewById(R.id.label_score4);
        scoreBoard[3][1] = (TextView) findViewById(R.id.player_name_score4);
        scoreBoard[3][2] = (TextView) findViewById(R.id.score4);

        scoreBoard[4][0] = (TextView) findViewById(R.id.label_score5);
        scoreBoard[4][1] = (TextView) findViewById(R.id.player_name_score5);
        scoreBoard[4][2] = (TextView) findViewById(R.id.score5);

        scoreBoard[5][0] = (TextView) findViewById(R.id.label_score6);
        scoreBoard[5][1] = (TextView) findViewById(R.id.player_name_score6);
        scoreBoard[5][2] = (TextView) findViewById(R.id.score6);

        scoreBoard[6][0] = (TextView) findViewById(R.id.label_score7);
        scoreBoard[6][1] = (TextView) findViewById(R.id.player_name_score7);
        scoreBoard[6][2] = (TextView) findViewById(R.id.score7);

        scoreBoard[7][0] = (TextView) findViewById(R.id.label_score8);
        scoreBoard[7][1] = (TextView) findViewById(R.id.player_name_score8);
        scoreBoard[7][2] = (TextView) findViewById(R.id.score8);

        scoreBoard[8][0] = (TextView) findViewById(R.id.label_score9);
        scoreBoard[8][1] = (TextView) findViewById(R.id.player_name_score9);
        scoreBoard[8][2] = (TextView) findViewById(R.id.score9);

        scoreBoard[9][0] = (TextView) findViewById(R.id.label_score10);
        scoreBoard[9][1] = (TextView) findViewById(R.id.player_name_score10);
        scoreBoard[9][2] = (TextView) findViewById(R.id.score10);

        //create topTen array object
        winners = TopScores.winners;

        //load values to the widgets
        for (int x = 0; x < scoreBoard.length; x++){
            scoreBoard[x][0].setText(Integer.toString(x+1));
            scoreBoard[x][1].setText(winners[x][0]);
            scoreBoard[x][2].setText(winners[x][1]);
        }
    }
}

