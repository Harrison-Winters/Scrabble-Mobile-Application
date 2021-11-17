package com.example.scrabblegameframework.ScrabbleFramework;

import android.app.Activity;

import com.example.scrabblegameframework.GameFramework.LocalGame;
import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;
import com.example.scrabblegameframework.GameFramework.utilities.Logger;
import com.example.scrabblegameframework.R;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleClearAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSelectHandAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ScrabbleLocalGame extends LocalGame {
    private ScrabbleGameState official;
    private Activity activity;
    private HashMap<String, Boolean> dictionary;
    private boolean newTurn;
    private ScrabbleGameState beginningState;


    public ScrabbleLocalGame() {
        super();
        //int numPlayers = this.getPlayers().length;
        //System.out.println(getPlayers().length+"");
        official = new ScrabbleGameState(2);
        newTurn = false;
        beginningState = new ScrabbleGameState(official);
    }

    public void setActivity(Activity a){
        this.activity = a;
        //official.setNumPlayers(2);
        try {
            loadDictionary();
        } catch (IOException e) {
          Logger.log("setActivity", "Error Loading dictionary file");
        }
    }

    //add setNumPlayers

    private void loadDictionary() throws IOException {
        InputStream is = this.activity.getResources().openRawResource(R.raw.dictionary);
        dictionary = new HashMap<>(500000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String string = "";

        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            //At this point, the string we have read should be a valid word
            dictionary.put(string, new Boolean(true));
        }
        is.close();
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
        //use containsKey to see if word is stored in dictionary
        //if(newTurn){
          //  beginningState = new ScrabbleGameState(official);
       // }
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
            //THIS IS WHERE WORD VERIFICATION WILL HAPPEN

            if(official.endTurn(official.getCurrPlayerTurn())){
                newTurn = true;
                beginningState = new ScrabbleGameState(official);
               return true;
            }
        }
        //PLAY ACTION
        else if(action instanceof ScrabblePlayAction){
            //Make copy of the current GameState
            //ScrabbleGameState officialCopy = new ScrabbleGameState(official);

            if(official.getPlayer(official.getCurrPlayerTurn()).getSelected().size() != 1){
                return false;
            }
            if(official.placeLetter(official.getCurrPlayerTurn(), ((ScrabblePlayAction) action).getX(),
                    ((ScrabblePlayAction) action).getY())) {
                official.getPlayer(official.getCurrPlayerTurn()).setScore(1);
                return true;
            }
            else {

                //official = beginningState;

                return false;
            }

        }
        //CLEAR ACTION
        else if(action instanceof ScrabbleClearAction){
            official = new ScrabbleGameState(beginningState);
            return true;
        }
        return false;
    }
}
