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
        //System.out.println(getPlayers().length+"");
        official = new ScrabbleGameState(2);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ScrabbleGameState updated = new ScrabbleGameState(official);
        p.sendInfo(updated);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(official.getCurrPlayerTurn() == playerIdx){
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
            if(official.select(official.getCurrPlayerTurn(), ((ScrabbleSelectHandAction) action).getIdx())){
                return true;
            }
        }
        //TOGGLE EXCHANGE
        else if(action instanceof ScrabbleExchangeAction){
            if (official.exchangeLetters(official.getCurrPlayerTurn())) {
                    return true;
            }
        }
        //SUBMIT
        else if(action instanceof ScrabbleSubmitAction){
            if(official.endTurn(official.getCurrPlayerTurn())){
                return true;
            }
        }
        //PLAY ACTION
        else if(action instanceof ScrabblePlayAction){
            if(official.getPlayer(official.getCurrPlayerTurn()).getSelected().size() != 1){
                return false;
            }
            if(official.placeLetter(official.getCurrPlayerTurn(), ((ScrabblePlayAction) action).getX(),
                    ((ScrabblePlayAction) action).getY())) {
                return true;
            }
            return false;
        }
        return false;
    }
}
