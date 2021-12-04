package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameComputerPlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleClearAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSelectHandAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

import java.util.Random;

public class SmartComputerPlayer extends GameComputerPlayer {
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

    public SmartComputerPlayer(String name) {
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
            int currPlayerIndex = ((ScrabbleGameState) info).getCurrPlayerTurn();
            if (((ScrabbleGameState) info).getBoard().isEmpty()) {
                sleep(2);
                ScrabbleSelectHandAction selectFirst = new ScrabbleSelectHandAction(this, 0);
                game.sendAction(selectFirst);
                ScrabbleExchangeAction swap = new ScrabbleExchangeAction(this);
                game.sendAction(swap);
                return;
            }

            if (hasPlayed) {
                hasPlayed = false;
                if (counter < 5000) {
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
                //pick a random spot on the board
                Random rndX = new Random();
                Random rndY = new Random();

                int i = rndX.nextInt(15);
                int j = rndY.nextInt(15);
                //checks if that space is not empty
                if (state.getBoard().getBoardSpace(i, j) != null &&
                        state.getBoard().getBoardSpace(i, j).getTile() != null) {
                    //if not empty, check spaces surrounding it
                    ScrabblePlayAction playFirstTile = null;
                    //Above
                    //if (j > 0) {
                        if (state.getBoard().getBoardSpace(i, j - 1) != null &&
                                state.getBoard().getBoardSpace(i, j - 1).getTile() == null) {
                            playFirstTile = new ScrabblePlayAction(this, i, j - 1);
                        }
                    //}
                    //Below
                    //else if (j < 14) {
                        if (state.getBoard().getBoardSpace(i, j + 1) != null &&
                                state.getBoard().getBoardSpace(i, j + 1).getTile() == null) {
                            playFirstTile = new ScrabblePlayAction(this, i, j + 1);
                        }
                    //}
                    //Left
                    //else if (i > 0){
                        if (state.getBoard().getBoardSpace(i - 1, j) != null &&
                                state.getBoard().getBoardSpace(i - 1, j).getTile() == null) {
                            playFirstTile = new ScrabblePlayAction(this, i - 1, j);
                        }
                    //}
                    //Right
                    //else if (i < 14){
                        if (state.getBoard().getBoardSpace(i + 1, j) != null &&
                                state.getBoard().getBoardSpace(i + 1, j).getTile() == null) {
                            playFirstTile = new ScrabblePlayAction(this, i + 1, j);
                        }
                    //}
                    //if it finds a place to put it, loop through a choose a random tile in its hand and play it
                    if (playFirstTile != null) {
                        //store coord of tile
                        this.lastX = playFirstTile.getX();
                        this.lastY = playFirstTile.getY();
                        //gets a random tile from the player's hand
                        Random rndIdx = new Random();
                        indx = rndIdx.nextInt(7);
                        //selects and tries to play the tile
                        ScrabbleSelectHandAction selectFirstTile = new ScrabbleSelectHandAction(this, indx);
                        game.sendAction(selectFirstTile);
                        game.sendAction(playFirstTile);
                        //choose another tile that is next to the other one
                        //if not empty, check spaces surrounding it
                        ScrabblePlayAction playSecondTile = null;
                        //Above
                        if (state.getBoard().getBoardSpace(this.lastX, this.lastY - 1) != null &&
                                state.getBoard().getBoardSpace(this.lastX, this.lastY - 1).getTile() == null) {
                            playSecondTile = new ScrabblePlayAction(this, this.lastX, this.lastY - 1);
                        }
                        //Below
                        else if (state.getBoard().getBoardSpace(this.lastX, this.lastY + 1) != null &&
                                state.getBoard().getBoardSpace(this.lastX, this.lastY + 1).getTile() == null) {
                            playSecondTile = new ScrabblePlayAction(this, this.lastX, this.lastY + 1);
                        }
                        //Left
                        else if (state.getBoard().getBoardSpace(this.lastX - 1, this.lastY) != null &&
                                state.getBoard().getBoardSpace(this.lastX - 1, this.lastY).getTile() == null) {
                            playSecondTile = new ScrabblePlayAction(this, this.lastX - 1, this.lastY);
                        }
                        //Right
                        else if (state.getBoard().getBoardSpace(this.lastX + 1, this.lastY) != null &&
                                state.getBoard().getBoardSpace(this.lastX + 1, this.lastY).getTile() == null) {
                            playSecondTile = new ScrabblePlayAction(this, this.lastX + 1, this.lastY);
                        }
                        if (playSecondTile != null) {
                            hasPlayed = true;
                            //randomly choose from the remaining 6 tiles
                            indx = rndIdx.nextInt(6);
                            ScrabbleSelectHandAction selectSecondTile = new ScrabbleSelectHandAction(this, indx);
                            //place the tile
                            game.sendAction(selectSecondTile);
                            game.sendAction(playSecondTile);
                            //end
                            return;
                        }
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