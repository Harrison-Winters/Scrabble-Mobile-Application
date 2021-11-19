package com.example.scrabblegameframework.ScrabbleFramework;

import android.util.Log;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameComputerPlayer;
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
    private boolean hasPlayed;
    public SmartComputerPlayer(String name) {
        super(name);
        hasPlayed = false;
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        //sleep(2);
        if(info instanceof ScrabbleGameState) {
            ScrabbleGameState state = new ScrabbleGameState(((ScrabbleGameState) info));
            Board scrabbleBoard = state.getBoard();
            ScrabbleSubmitAction end = new ScrabbleSubmitAction(this, 0, 0);
            Random rnd = new Random();
            hasPlayed = false;
            int x=0;
            int y=0;
            //Find a x,y combo that passes through the criteria of a valid move
            while (!hasPlayed) {
                for (int i = 0; i < 15; i++){
                    for (int j = 0; j<15; j++){
                        // place on an empty tile
                        if (scrabbleBoard.getBoardSpace(x,y).getTile() == null){
                            //check for spaces around that tile
                            int isConnected = 0;
                            //TOP
                            if (x < 14) {
                                if ((scrabbleBoard.getBoardSpace(i + 1, j).getTile() != null)) {
                                    isConnected += 1;
                                }
                            }
                            //RIGHT
                            if (y < 14) {
                                if (scrabbleBoard.getBoardSpace(i ,j + 1).getTile() != null){
                                    isConnected += 1;
                                }
                            }
                            //BOTTOM
                            if (x > 0) {
                                if (scrabbleBoard.getBoardSpace(i - 1, j).getTile() != null) {
                                    isConnected += 1;
                                }
                            }
                            //LEFT
                            if (y > 0) {
                                if (scrabbleBoard.getBoardSpace(i,j - 1).getTile() != null){
                                    isConnected += 1;
                                }
                            }
                            if (isConnected > 0) {
                                ScrabblePlayAction play = new ScrabblePlayAction(this, i, j);
                                game.sendAction(play);
                                game.sendAction(end);
                            }
                        }
                    }
                }
              }
            }
        }
    }
