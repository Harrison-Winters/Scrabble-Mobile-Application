package com.example.scrabblegameframework.ScrabbleFramework.Actions;

import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;

public class ScrabbleSubmitAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */


    int x;
    int y;
    public ScrabbleSubmitAction(GamePlayer player, int x, int y) {
        super(player);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
