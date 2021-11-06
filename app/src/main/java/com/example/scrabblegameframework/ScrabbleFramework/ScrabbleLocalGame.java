package com.example.scrabblegameframework.ScrabbleFramework;

import com.example.scrabblegameframework.GameFramework.LocalGame;
import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSelectHandAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

public class ScrabbleLocalGame extends LocalGame {
    private ScrabbleGameState official;

    public ScrabbleLocalGame() {
        //System.out.println(players.length);
        official = new ScrabbleGameState(2);
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
        //SELECT ACTION
        if(action instanceof ScrabbleSelectHandAction){
            if(official.select(official.getPlayerTurn(), ((ScrabbleSelectHandAction) action).getIdx())){
                return true;
            }
        }
        //TOGGLE EXCHANGE
        else if(action instanceof ScrabbleExchangeAction){
            if (official.exchangeLetters(official.getPlayerTurn())) {
                    return true;
            }
        }
        //SUBMIT
        else if(action instanceof ScrabbleSubmitAction){
            if(official.endTurn(official.getPlayerTurn())){
                return true;
            }
        }
        //PLAY ACTION
        else if(action instanceof ScrabblePlayAction){
            if(official.getPlayer(official.getPlayerTurn()).getSelected().size() != 1){
                return false;
            }
            if(official.placeLetter(official.getPlayerTurn(), ((ScrabblePlayAction) action).getX(),
                    ((ScrabblePlayAction) action).getY())) {
                return true;
            }
            return false;
        }
        //PLAY TOUCH ACTION
        return false;
    }
}
