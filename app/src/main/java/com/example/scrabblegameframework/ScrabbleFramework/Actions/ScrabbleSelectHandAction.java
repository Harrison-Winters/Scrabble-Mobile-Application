package com.example.scrabblegameframework.ScrabbleFramework.Actions;

import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;

public class ScrabbleSelectHandAction extends GameAction {
    private int idx;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ScrabbleSelectHandAction(GamePlayer player, int i) {
        super(player);
        idx = i;
    }

    public int getIdx(){
        return idx;
    }
}
