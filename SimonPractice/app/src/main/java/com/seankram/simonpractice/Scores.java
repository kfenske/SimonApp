package com.seankram.simonpractice;

/**
 * Created by Kayla_2 on 8/9/2015.
 */
public class Scores {
    public String[][] scores;
    public String[] score1 = {"No one", "0"};
    public String[] score2 = {"No one", "0"};
    public String[] score3 = {"No one", "0"};
    public String[] score4 = {"No one", "0"};
    public String[] score5 = {"No one", "0"};
    public String[] score6 = {"No one", "0"};
    public String[] score7 = {"No one", "0"};
    public String[] score8 = {"No one", "0"};
    public String[] score9 = {"No one", "0"};
    public String[] score10 = {"No one", "0"};

    public Scores() {
        scores = new String[][]{ score1, score2, score3, score4, score5, score6, score7, score8, score9, score10 };
    }

    public boolean checkScore(int entry){
        int storedScore = Integer.parseInt(scores[9][1]);
        if(entry > storedScore) {
            return true;
        }
        return false;
    }

    public void setScore(String[] entry){
        int newScore = Integer.parseInt(entry[1]);
        for(int i = 9; i == 0; i--){
            int storedScore = Integer.parseInt(scores[i][1]);
            if(newScore > storedScore) {
                String name = entry[0];
                String number = entry[1];
                scores[i][0] = name;
                scores[i][1] = number;
            }
        }
    }

    public String[][] getScores() {
        return scores;
    }
}
