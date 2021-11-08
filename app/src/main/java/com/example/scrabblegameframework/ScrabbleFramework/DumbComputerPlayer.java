package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameComputerPlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSelectHandAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

import java.util.Random;

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
        sleep(2);
        if(info instanceof ScrabbleGameState) {
            ScrabbleGameState state = new ScrabbleGameState(((ScrabbleGameState) info));
            ScrabbleSubmitAction end = new ScrabbleSubmitAction(this);
            if (state.getSelected(playerNum).size() == 0) {
                //ScrabbleExchangeAction exchange = new ScrabbleExchangeAction(this);
                ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, 0);
                game.sendAction(select);
            }
            else {
                Random rnd = new Random();
                int xVal = (int) (rnd.nextInt(15));
                int yVal = (int) (rnd.nextInt(15));
                ScrabblePlayAction play = new ScrabblePlayAction(this, xVal, yVal);
                game.sendAction(play);
                game.sendAction(end);
            }
        }
    }
}

