package com.example.scrabblegameframework.ScrabbleFramework.Actions;

import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;

public class ScrabbleExchangeAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public ScrabbleExchangeAction(GamePlayer player) {
        super(player);
    }
}
