package com.example.scrabblegameframework.ScrabbleFramework;

import android.view.MotionEvent;
import android.view.View;

import com.example.scrabblegameframework.GameFramework.GameMainActivity;
import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameHumanPlayer;

public class ScrabbleHumanPlayer extends GameHumanPlayer {
    private int layoutid;

    public ScrabbleHumanPlayer(String name, int layout) {
        super(name);
        this.layoutid = layout;

    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutid);
        //Set up any Click or Touch listeners here
    }

    public boolean onTouch(View v, MotionEvent event){
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN){
            int x = (int) event.getX();
            int y = (int) event.getY();
            return true;
        }

        return false;
    }
}
