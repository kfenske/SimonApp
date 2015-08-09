package com.seankram.simonpractice;

/**
 * Created by Kayla_2 on 8/9/2015.
 */
public class Scores {
    public String[][] scores;
    public String[] score1;
    public String[] score2;
    public String[] score3;
    public String[] score4;
    public String[] score5;
    public String[] score6;
    public String[] score7;
    public String[] score8;
    public String[] score9;
    public String[] score10;

    public Scores() {
        scores = new String[][]{ score1, score2, score3, score4, score5, score6, score7, score8, score9, score10 };
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
