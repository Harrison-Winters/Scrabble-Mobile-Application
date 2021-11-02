package com.example.scrabblegameframework.ScrabbleFramework.Actions;

import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;

public class ScrabblePlayAction extends GameAction {
    private int letterIndex;
    private int x;
    private int y;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ScrabblePlayAction(GamePlayer player, int idx) {
        super(player);
        letterIndex = idx;
    }

    public void setLocation(int col, int row){
        x = col;
        y = row;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getLetterIndex(){
        return letterIndex;
    }
}
