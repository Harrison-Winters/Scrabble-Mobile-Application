package com.example.scrabblegameframework.ScrabbleFramework;

import com.example.scrabblegameframework.GameFramework.LocalGame;
import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

public class ScrabbleLocalGame extends LocalGame {
    private ScrabbleGameState official;

    public ScrabbleLocalGame() {
        official = new ScrabbleGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ScrabbleGameState updated = new ScrabbleGameState(official);
        p.sendInfo(updated);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(official.getPlayerTurn() == playerIdx){
            return true;
        }
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof ScrabbleExchangeAction){
            official.exchangeLetter(official.getPlayerTurn(), ((ScrabbleExchangeAction) action).getLetterIndex());
        }
        else if(action instanceof ScrabbleSubmitAction){
            official.endTurn(official.getPlayerTurn());
        }
        else if(action instanceof ScrabblePlayAction){
            official.placeLetter(official.getPlayerTurn(), ((ScrabblePlayAction) action).getLetterIndex(),
                    ((ScrabblePlayAction) action).getX(), ((ScrabblePlayAction) action).getY());
        }
        return false;
    }
}
