package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameComputerPlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

public class DumbComputerPlayer extends GameComputerPlayer {
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public DumbComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        sleep(5);
        ScrabbleSubmitAction end = new ScrabbleSubmitAction(this);
        ScrabbleExchangeAction exchange = new ScrabbleExchangeAction(this);
        int xVal =(int)(15*Math.random());
        int yVal =(int)(15*Math.random());
        ScrabblePlayAction play = new ScrabblePlayAction(this,xVal,yVal);
        game.sendAction(play);
        game.sendAction(end);
    }
}

