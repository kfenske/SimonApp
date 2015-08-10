package com.seankram.simonpractice;

import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by Darryl on 8/7/2015.
 */
public class TopScores {

    public static String[][] winners = new String[10][2];
    private static boolean methodAHasRunOnce = false;

    //constructor
    public void topScores(){

    }

    //creates array and sets initial array values
    public String[][] initialWinners(){
        for (int x = 0; x < 10; x++){
            winners[x][0] = "Simon Player Name";
            winners[x][1] = "00";
        }
        return winners;
    }

    //checkScore determines whether player's score is top ten
    //if it is top ten, then it sorts the array of winners by score
    public boolean checkScore(int playerScore){

        Log.d("PLAYER SCORE", Integer.toString(playerScore));
        Log.d("tenth score", winners[9][1]);

        //int x sets the index for the tenth row of the array
        int x = 9;

        //if the player's score is top ten return true and sort it into the array
        if (playerScore > Integer.parseInt(winners[x][1])){    //this will be modified to include the user's name supplied on a fragment

            //move the rows down one until the correct row is found for the player's score
            while (x>0 && (playerScore > Integer.parseInt(winners[x-1][1]))) {
                winners[x][0] = winners[x-1][0];
                winners[x][1] = winners[x-1][1];

                x--;
            }

            //enter the player's name and score
            winners[x][0] = "BIG BAD DAWG WINNER";
            winners[x][1] = Integer.toString(playerScore);

            return true;
        } else {
            return false;
        }
    }

    //this method is called to supply the current array of winners
    public String[][] updatedWinners(){

        if(!TopScores.methodAHasRunOnce) {
            this.winners = initialWinners();
            TopScores.methodAHasRunOnce = true;

        }
        return winners;
    }
}

