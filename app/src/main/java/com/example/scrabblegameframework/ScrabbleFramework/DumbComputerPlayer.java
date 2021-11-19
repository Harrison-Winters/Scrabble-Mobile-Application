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

    int x;
    int y;
    private boolean hasPlayed;
    public DumbComputerPlayer(String name) {
            super(name);
            hasPlayed = false;
            int x = 0;
            int y = 0;
        }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        //sleep(2);
        if(info instanceof ScrabbleGameState) {
            ScrabbleGameState state = new ScrabbleGameState(((ScrabbleGameState) info));

            if(hasPlayed) {
                hasPlayed = false;
                ScrabbleSubmitAction end = new ScrabbleSubmitAction(this, x , y);
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
                                    //added
                                    this.x = play.getX();
                                    this.y = play.getY();

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

