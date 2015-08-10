package com.seankram.simonpractice;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Kayla_2 on 7/29/2015.
 * Updated by Sean on 8/5/2015.
 */
public class GamePlay {
    //Counters
    public int gameCounter;
    public int checkButtonClick;
    public int buttonSelect;
    public int storeScore;

    //Number of buttons on the game board
    public final static int BUTTONS = 4;

    //Array size of game play sequence, based on level the player has reached
    public ArrayList<Integer> gameArray;
    public ArrayList<Integer> inputArray;
    public ArrayList<Integer> scoreBoard;

    public boolean playerWon;
    public boolean playerLost;

    public GamePlay() {
        gameCounter = 1;
        checkButtonClick = 0;
        gameArray = new ArrayList<>();
        scoreBoard = new ArrayList<>();
    }

    public void newGame() {
        //This returns the array of numbers to
        //animate the buttons that the user needs to repeat.
        //It should be called when the player says "new game"

        Random rand = new Random();

        buttonSelect = rand.nextInt(BUTTONS) + 1;

        gameArray.add(buttonSelect);

        inputArray = new ArrayList<>();
        checkButtonClick = 0;
        playerLost = false;
    }

    public void checkAnswer() {
        if (gameArray.get(checkButtonClick) != inputArray.get(checkButtonClick)) {
            gotItWrong();
        } else if (gameArray.size() == inputArray.size()) {
            gotItRight();
        } else {
            checkButtonClick++;
            playerWon = false;
        }
    }

    public void gotItRight() {
        gameCounter++;
        playerWon = true;
        newGame();
    }

    public void gotItWrong() {
        // MESSAGE FOR Darryl Kukor: Push gameCounter into your TopScoresActivity!
        // scoreBoard.add(gameCounter);
        storeScore = gameCounter;
        gameCounter = 1;
        gameArray.clear();
        playerLost = true;
    }

    /*public boolean checkGameWin(int buttonID) {
        //This method should be called each time a button is clicked by the user.
        //The value passed in is the ID number of the button (int value of 1-4)
        while((checkButtonClick + 1) != gameArray.size())
        {
            boolean isCorrect = checkGameSequence(buttonID);
            if(!isCorrect)
            {
                //As the gameCounter increases with each game played, when the user
                //fails, that value is stored in the ArrayList scoreBoard and the gameCounter is reset
                scoreBoard.add(gameCounter);
                gameCounter = 1;

                //This resets the ArrayList to empty so that when newGame() is called
                //the ArrayList is adding values to a new empty array
                gameArray.clear();
                return false;
            }
        }

        //If the user makes it through the while loop without failing
        //that assumes they won the game and the gameCounter is increased
        gameCounter++;
        return true;
    }

    public boolean checkGameSequence(int buttonID) {
        //This method checks that the button clicked is equal to the
        //corresponding value in the randomly generated array
        if (gameArray.get(checkButtonClick) == buttonID)
        {
            checkButtonClick++;
            return true;
        }
        else {
            //If the user clicks the wrong button, the checkButtonClick is reset
            checkButtonClick = 0;
            return false;
        }
    }*/
}
