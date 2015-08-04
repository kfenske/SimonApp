package com.seankram.simonpractice;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Kayla_2 on 7/29/2015.
 */
public class GamePlay {
    //Counters
    public int gameCounter;
    public int checkButtonClick;
    public int buttonSelect;

    //Number of buttons on the game board
    public final static int BUTTONS = 4;

    //Array size of game play sequence, based on level the player has reached
    public ArrayList<Integer> gameArray;
    public ArrayList<Integer> scoreBoard;

    public GamePlay() {
        gameCounter = 1;
        checkButtonClick = 0;
        gameArray = new ArrayList<Integer>();
    }

    private ArrayList<Integer> newGame() {
        //This returns the array of numbers to
        //animate the buttons that the user needs to repeat.
        //It should be called when the player says "new game"

        Random rand = new Random();

        buttonSelect = rand.nextInt(BUTTONS);

        gameArray.add(buttonSelect);

        return gameArray;
    }

    private boolean checkGameWin(int buttonID) {
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

    private boolean checkGameSequence(int buttonID) {
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
    }
}