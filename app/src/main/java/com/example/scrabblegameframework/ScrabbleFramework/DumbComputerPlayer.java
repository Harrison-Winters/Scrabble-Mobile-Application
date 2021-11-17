package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.infoMessage.IllegalMoveInfo;
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
    private boolean hasPlayed;
    public DumbComputerPlayer(String name) {
            super(name);
            hasPlayed = false;
        }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        //sleep(2);
        if(info instanceof ScrabbleGameState) {
            ScrabbleGameState state = new ScrabbleGameState(((ScrabbleGameState) info));
            ScrabbleSubmitAction end = new ScrabbleSubmitAction(this);
            if(hasPlayed) {
                hasPlayed = false;
                game.sendAction(end);
            }
            else if (state.getSelected(playerNum).size() == 0) {
                //ScrabbleExchangeAction exchange = new ScrabbleExchangeAction(this);
                ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, 0);
                game.sendAction(select);
            }
            else {
                Random rnd = new Random();
                if(rnd.nextInt(4) > 0) {
                /*(int xVal = (int) (rnd.nextInt(15));
                int yVal = (int) (rnd.nextInt(15));
                ScrabblePlayAction play = new ScrabblePlayAction(this, xVal, yVal);
                xVal = (int) (rnd.nextInt(15));
                yVal = (int) (rnd.nextInt(15));*/
                    for (int i = 0; i < 14; i++) {
                        for (int j = 0; j < 15; j++) {
                            if (state.getBoard().getBoardSpace(i, j).getTile() != null) {
                                ScrabblePlayAction play = null;
                                //Below
                                if (state.getBoard().getBoardSpace(i, j - 1).getTile() == null) {
                                    play = new ScrabblePlayAction(this, i, j - 1);
                                }
                                //Above
                                else if (state.getBoard().getBoardSpace(i, j + 1).getTile() == null) {
                                    play = new ScrabblePlayAction(this, i, j + 1);
                                }
                                //Left
                                else if (state.getBoard().getBoardSpace(i - 1, j).getTile() == null) {
                                    play = new ScrabblePlayAction(this, i - 1, j);
                                }
                                //Right
                                else if (state.getBoard().getBoardSpace(i + 1, j).getTile() == null) {
                                    play = new ScrabblePlayAction(this, i + 1, j);
                                }
                                if (play != null) {
                                    hasPlayed = true;
                                    game.sendAction(play);
                                    return;
                                }
                            }
                        }
                    }
                }
                else {
                    if (state.getSelected(playerNum).size() == 1) {
                        ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, 3);
                        game.sendAction(select);
                    }
                    else if (state.getSelected(playerNum).size() == 2) {
                        ScrabbleSelectHandAction select = new ScrabbleSelectHandAction(this, 6);
                        game.sendAction(select);
                    }
                    else{
                        ScrabbleExchangeAction ex = new ScrabbleExchangeAction(this);
                        hasPlayed = false;
                        game.sendAction(ex);
                    }
                }
            }
        }
        else{
            hasPlayed = false;
        }
    }
}

