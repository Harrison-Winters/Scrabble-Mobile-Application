package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.scrabblegameframework.GameFramework.players.GameComputerPlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleClearAction;
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
    int lastX;
    int lastY;
    int counter;
    int indx;

    private boolean hasPlayed;


    public DumbComputerPlayer(String name) {
            super(name);
            hasPlayed = false;
            int lastX = 0;
            int lastY = 0;
            int counter = 0;
            int indx = 0;
        }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        //sleep(2);
        if (info instanceof ScrabbleGameState) {
            ScrabbleGameState state = new ScrabbleGameState(((ScrabbleGameState) info));

            if (hasPlayed) {
                hasPlayed = false;
                if (counter < 5000) {

                    ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, indx);
                    ScrabbleSubmitAction end = new ScrabbleSubmitAction(this, lastX, lastY);
                    game.sendAction(end);
                    counter++;
                } else {
                    ScrabbleExchangeAction exchange = new ScrabbleExchangeAction(this);

                    game.sendAction(exchange);
                    counter = 0;
                }
            }
            else {
                Random rndX = new Random();
                Random rndY = new Random();

                int i = rndX.nextInt(15);
                int j = rndY.nextInt(15);

                if (state.getBoard().getBoardSpace(i,j) != null && state.getBoard().getBoardSpace(i  , j ).getTile() != null) {
                    ScrabblePlayAction play = null;
                    //Above
                    // if (j > 0) {
                        if (state.getBoard().getBoardSpace(i,j - 1) != null && state.getBoard().getBoardSpace(i, j - 1).getTile() == null) {
                            play = new ScrabblePlayAction(this, i, j - 1);
                        }
                    //}
                    //Below
                    //else if (j < 14) {
                        if (state.getBoard().getBoardSpace(i,j + 1) != null && state.getBoard().getBoardSpace(i, j + 1).getTile() == null) {
                            play = new ScrabblePlayAction(this, i, j + 1);
                        }
                    //}
                    //Left
                    //else if (i > 0){
                        if (state.getBoard().getBoardSpace(i - 1,j) != null && state.getBoard().getBoardSpace(i - 1, j).getTile() == null) {
                            play = new ScrabblePlayAction(this, i - 1, j);
                        }
                    //}
                    //Right
                    //else if (i < 14){
                        if (state.getBoard().getBoardSpace(i + 1,j) != null && state.getBoard().getBoardSpace(i + 1, j).getTile() == null) {
                            play = new ScrabblePlayAction(this, i + 1, j);
                        }
                    //}
                    if (play != null) {
                        hasPlayed = true;
                        //added
                        this.lastX = play.getX();
                        this.lastY = play.getY();

                        //added
                        Random rndIdx = new Random();
                        indx = rndIdx.nextInt(7);

                        ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, indx);
                        game.sendAction(select);

                        game.sendAction(play);

                        return;
                    }
                }
                if (((ScrabbleGameState) info).getCurrPlayerTurn() == 0) {
                    return;
                }
                else {
                    ScrabbleClearAction clear = new ScrabbleClearAction(this);
                    game.sendAction(clear);
                }
            }
        }
    }
}