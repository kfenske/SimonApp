package com.seankram.simonpractice;

/**
 * Created by Q on 8/12/2015.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class FXPlayer {

    private MediaPlayer mPlayer;

    public void stop(){
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context c, View v){
        stop();

        switch(v.getId()){
            case R.id.b1:
                mPlayer = MediaPlayer.create(c, R.raw.greentwo);
                break;
            case R.id.b2:
                mPlayer = MediaPlayer.create(c, R.raw.redtwo);
                break;
            case R.id.b3:
                mPlayer = MediaPlayer.create(c, R.raw.yellowtwo);
                break;
            case R.id.b4:
                mPlayer = MediaPlayer.create(c, R.raw.bluetwo);
                break;
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
                mp.release();
            }
        });

        mPlayer.start();
    }

    public void playButton(Context c, int btnToPlay) {
        stop();

        switch(btnToPlay) {
            case R.id.b1:
                mPlayer = MediaPlayer.create(c, R.raw.greentwo);
                break;
            case R.id.b2:
                mPlayer = MediaPlayer.create(c, R.raw.redtwo);
                break;
            case R.id.b3:
                mPlayer = MediaPlayer.create(c, R.raw.yellowtwo);
                break;
            case R.id.b4:
                mPlayer = MediaPlayer.create(c, R.raw.bluetwo);
                break;
        }

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
                mp.release();
            }
        });

        mPlayer.start();
    }
}
