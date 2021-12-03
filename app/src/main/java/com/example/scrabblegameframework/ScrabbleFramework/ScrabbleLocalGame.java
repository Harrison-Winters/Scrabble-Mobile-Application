package com.example.scrabblegameframework.ScrabbleFramework;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.widget.TextView;

import com.example.scrabblegameframework.GameFramework.GameMainActivity;
import com.example.scrabblegameframework.GameFramework.LocalGame;
import com.example.scrabblegameframework.GameFramework.actionMessage.GameAction;
import com.example.scrabblegameframework.GameFramework.infoMessage.GameOverInfo;
import com.example.scrabblegameframework.GameFramework.players.GameHumanPlayer;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;
import com.example.scrabblegameframework.GameFramework.utilities.Logger;
import com.example.scrabblegameframework.GameFramework.utilities.MessageBox;
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
    private ScrabbleGameState beginningState;
    int play7;



    public ScrabbleLocalGame() {
        super();
        //int numPlayers = this.getPlayers().length;
        //System.out.println(getPlayers().length+"");
        official = new ScrabbleGameState(0, dictionary,0,0);
        beginningState = new ScrabbleGameState(official);
        play7 = 0;
    }

    public void setActivity(Activity a) {
        for(int i = 0; i < playerNames.length; i++){
            official.addPlayers();
        }
        beginningState = new ScrabbleGameState(official);
        this.activity = a;
        try {
            dictionary = loadDictionary();
        } catch (IOException e) {
            Logger.log("setActivity", "Error Loading dictionary file");
        }

    }

    //add setNumPlayers

    private HashMap loadDictionary() throws IOException {
        InputStream is = this.activity.getResources().openRawResource(R.raw.dictionary);
        dictionary = new HashMap<>(500000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String string = "";

        while (true) {
            try {
                if ((string = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //At this point, the string we have read should be a valid word
            dictionary.put(string, new Boolean(true));
        }
        is.close();
        return dictionary;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        ScrabbleGameState updated = new ScrabbleGameState(official);
        p.sendInfo(updated);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if (official.getCurrPlayerTurn() == playerIdx) {
            return true;
        }
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        if(official.getBag().empty()) {
            int player0Score = official.getPlayerScore(0);
            int player1Score = official.getPlayerScore(1);
            int player2Score = official.getPlayerScore(2);
            int player3Score = official.getPlayerScore(3);

            if (player0Score > player1Score && player0Score > player2Score && player0Score > player3Score) {
                String string = String.format("Player 0 won with %s points\n",player0Score);
                return string;
            }
            else if (player1Score > player0Score && player1Score > player2Score && player1Score > player3Score) {
                String string = String.format("Player 1 won with %s points\n",player1Score);
                return string;
            }
            else if (player2Score > player0Score && player2Score > player1Score && player2Score > player3Score) {
                String string = String.format("Player 2 won with %s points\n",player2Score);
                return string;
            }
            else if (player3Score > player0Score && player3Score > player1Score && player3Score > player2Score) {
                String string = String.format("Player 3 won with %s points\n",player3Score);
                return string;
            }
        }


        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        //use containsKey to see if word is stored in dictionary
        //if(newTurn){
         // beginningState = new ScrabbleGameState(official);
         //}
        //SELECT ACTION
        if (action instanceof ScrabbleSelectHandAction) {
            if (official.select(official.getCurrPlayerTurn(), ((ScrabbleSelectHandAction) action).getIdx())) {
                return true;
            }
        }
        //TOGGLE EXCHANGE
        else if (action instanceof ScrabbleExchangeAction) {
            if (official.exchangeLetters(official.getCurrPlayerTurn())) {

                //ADDED
                beginningState = new ScrabbleGameState(official);
                return true;
            }
        }
        //SUBMIT
        else if (action instanceof ScrabbleSubmitAction) {
            //THIS IS WHERE WORD VERIFICATION WILL HAPPEN
            //ADDED

            //Check if no letters were placed by comparing the gamestate to the start of the turn
            if (official.equals(beginningState)) {
                return false;
            }


            if (checkWord(((ScrabbleSubmitAction) action).getX(), ((ScrabbleSubmitAction) action).getY(), 1, true)
                    && checkWord(((ScrabbleSubmitAction) action).getX(), ((ScrabbleSubmitAction) action).getY(), 2, true)) {
                int x = ((ScrabbleSubmitAction) action).getX();
                int y = ((ScrabbleSubmitAction) action).getY();
                    while (official.getBoard().getBoardSpace(x,y) != null && official.getBoard().getBoardSpace(x, y).getTile() != null) {
                        if (x != 15) {
                            boolean checkAcross = checkWord(x, y, 1, false);
                            if (checkAcross == false) {
                                official = new ScrabbleGameState(beginningState);
                                return false;
                            }
                            x++;
                        }
                    }
                x = ((ScrabbleSubmitAction) action).getX();
                y = ((ScrabbleSubmitAction) action).getY();
                while(official.getBoard().getBoardSpace(x,y) != null && official.getBoard().getBoardSpace(x,y).getTile() != null) {
                    boolean checkAcross = checkWord(x, y, 2, false);
                    if (checkAcross == false) {
                        official = new ScrabbleGameState(beginningState);
                        return false;
                    }
                    y++;
                }
                x = ((ScrabbleSubmitAction) action).getX();
                y = ((ScrabbleSubmitAction) action).getY();
                while(official.getBoard().getBoardSpace(x,y) != null && official.getBoard().getBoardSpace(x,y).getTile() != null) {
                    boolean checkAcross = checkWord(x, y, 1, false);
                    if (checkAcross == false) {
                        official = new ScrabbleGameState(beginningState);
                        return false;
                    }
                    x--;
                }

                x = ((ScrabbleSubmitAction) action).getX();
                y = ((ScrabbleSubmitAction) action).getY();
                while(official.getBoard().getBoardSpace(x,y) != null && official.getBoard().getBoardSpace(x,y).getTile() != null) {
                    boolean checkAcross = checkWord(x, y, 2, false);
                    if (checkAcross == false) {
                        official = new ScrabbleGameState(beginningState);
                        return false;
                    }
                    y--;
                }
                if (official.endTurn(official.getCurrPlayerTurn())) {
                    beginningState = new ScrabbleGameState(official);

                    return true;
                }

            }
            else {
                official = new ScrabbleGameState(beginningState);
                play7 = 0;
                return false;
            }



            //if (official.endTurn(official.getCurrPlayerTurn())) {
            //    newTurn = true;
            //    beginningState = new ScrabbleGameState(official);
            //    return true;
           // }
        }
        //PLAY ACTION
        else if (action instanceof ScrabblePlayAction) {
            //Make copy of the current GameState
            //ScrabbleGameState officialCopy = new ScrabbleGameState(official);

            if (official.getPlayer(official.getCurrPlayerTurn()).getSelected().size() != 1) {
                return false;
            }
            if (official.placeLetter(official.getCurrPlayerTurn(), ((ScrabblePlayAction) action).getX(),
                    ((ScrabblePlayAction) action).getY())) {
                play7++;
                return true;
            } else {

                //official = beginningState;
                official = new ScrabbleGameState(beginningState);
                play7 = 0;
                return false;
            }

        }
        //CLEAR ACTION
        else if (action instanceof ScrabbleClearAction) {
            official = new ScrabbleGameState(beginningState);
            play7 = 0;
            return true;
        }


        return false;
    }


    //Check word
    public boolean checkWord(int startX, int startY, int wordDirection, boolean calcScore) {
        int score = 0;
        int letterScore;
        //BASE CASE: Return true if opposing directions are both null
        //main direction up/down
        if (startY != 0 && startY != 14 && wordDirection == 1) {
            if (official.getBoard().getBoardSpace(startX, startY + 1).getTile() == null && official.getBoard().getBoardSpace(startX, startY - 1).getTile() == null) {
                return true;
            }
        }

        //main direction is right/left
        if (startX != 0 && startX != 14 && wordDirection == 2) {
            if (official.getBoard().getBoardSpace(startX + 1, startY).getTile() == null && official.getBoard().getBoardSpace(startX - 1, startY).getTile() == null) {
                return true;
            }
        }
        String word = "";

        //get the entire word (up/down)
        if (wordDirection == 1) {
            int checkSingleCount = 0;
            int currY = startY;

            //navigate top the top of the "word"
            while (currY != 0 && official.getBoard().getBoardSpace(startX, currY).getTile() != null) {
                currY--;
                checkSingleCount++;
            }
            //add 1 to Y to go back to where the word "starts"
            currY++;

            //make sure there was more than one letter in the word
            if (checkSingleCount == 0) {
                return false;
            }

            //assemble the word
            word = "";
            while (currY != 15 && official.getBoard().getBoardSpace(startX, currY).getTile() != null) {
                word = word + official.getBoard().getBoardSpace(startX, currY).getTile().getLetter();
                letterScore = official.getBoard().getBoardSpace(startX, currY).getTile().getPoints();
                if(official.getBoard().getBoardSpace(startX, currY).getActive()) {
                    switch (official.getBoard().getBoardSpace(startX, currY).getMultiplier()) {
                        case 1:
                            letterScore = letterScore * 2;
                            score = score + letterScore;
                            break;
                        case 2:
                            letterScore = letterScore * 3;
                            score = score + letterScore;
                            break;
                        case 3:
                            score = score + letterScore;
                            score = score * 2;
                            break;
                        case 4:
                            score = score + letterScore;
                            score = score * 3;
                            break;
                        default:
                            score = score + letterScore;
                            break;
                    }
                }
                else{
                    score = score + letterScore;
                }
                currY++;
            }

        }
            //get the entire word (left/right)
            if (wordDirection == 2) {
                int checkSingleCountLeftRight = 0;
                int currX = startX;

                //navigate top the top of the "word"
                while (currX != 0 && official.getBoard().getBoardSpace(currX, startY).getTile() != null) {
                    currX--;
                    checkSingleCountLeftRight++;
                }
                //add 1 to Y to go back to where the word "starts"
                currX++;

                //make sure there was more than one letter in the word
                if (checkSingleCountLeftRight == 0) {
                    return false;
                }

                //assemble the word
                word = "";
                while (currX != 15 && official.getBoard().getBoardSpace(currX, startY).getTile() != null) {
                    word = word + official.getBoard().getBoardSpace(currX, startY).getTile().getLetter();
                    letterScore = official.getBoard().getBoardSpace(currX, startY).getTile().getPoints();
                    if(official.getBoard().getBoardSpace(currX, startY).getActive()) {
                        switch (official.getBoard().getBoardSpace(currX, startY).getMultiplier()) {
                            case 1:
                                letterScore = letterScore * 2;
                                score = score + letterScore;
                                break;
                            case 2:
                                letterScore = letterScore * 3;
                                score = score + letterScore;
                                break;
                            case 3:
                                score = score + letterScore;
                                score = score * 2;
                                break;
                            case 4:
                                score = score + letterScore;
                                score = score * 3;
                                break;
                            default:
                                score = score + letterScore;
                                break;
                        }
                    }
                    else{
                        score = score + letterScore;
                    }
                    currX++;
                }

            }
                //check the created word
            if (dictionary.containsKey(word) == true) {
                int playerTurn = official.getCurrPlayerTurn();
                if(calcScore) {
                    official.setPlayerScore(playerTurn, official.getPlayerScore(playerTurn)+score);

                }
                if(play7 == 7){
                    official.setPlayerScore(playerTurn, official.getPlayerScore(playerTurn)+50);
                    //Pop up window to show "BINGO!!"
                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(activity);
                    //dlgAlert.setTitle("                                                      Bingo!!");
                    //dlgAlert.setMessage("                                                         You get a bonus 50 points");
                    dlgAlert.setTitle("Bingo!!");
                    dlgAlert.setMessage("You get a bonus 50 points");

                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.show();
                    play7 = 0;
                }
                return true;
            }

            return false;


    }
}

