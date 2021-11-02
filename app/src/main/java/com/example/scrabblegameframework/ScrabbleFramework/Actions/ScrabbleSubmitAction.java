package com.example.scrabblegameframework.ScrabbleFramework.Actions;

import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;

public class ScrabbleSubmitAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ScrabbleSubmitAction(GamePlayer player) {
        super(player);
    }
}
