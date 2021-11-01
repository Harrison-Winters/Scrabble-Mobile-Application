package com.example.tttgameframework;

import android.view.View;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;

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
}
