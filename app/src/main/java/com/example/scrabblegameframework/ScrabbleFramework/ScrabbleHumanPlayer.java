package com.example.scrabblegameframework.ScrabbleFramework;

import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.scrabblegameframework.GameFramework.GameMainActivity;
import com.example.scrabblegameframework.GameFramework.infoMessage.GameInfo;
import com.example.scrabblegameframework.GameFramework.players.GameHumanPlayer;
import com.example.scrabblegameframework.R;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleClearAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleExchangeAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabblePlayAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSelectHandAction;
import com.example.scrabblegameframework.ScrabbleFramework.Actions.ScrabbleSubmitAction;

import java.util.ArrayList;

public class ScrabbleHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {
    private int layoutid;

    //Buttons and stuff
    private Button  resetButton     = null;
    private Button  submitButton    = null;
    private ImageButton bagButton   = null;
    private Button handButton0      = null;
    private Button handButton1      = null;
    private Button handButton2      = null;
    private Button handButton3      = null;
    private Button handButton4      = null;
    private Button handButton5      = null;
    private Button handButton6      = null;
    private Button handButtons[];
    private GameSurfaceView boardView     = null;
    private TextView p0Score        = null;
    private TextView p1Score        = null;
    private TextView p2Score        = null;
    private TextView p3Score        = null;
    private TextView scoreTexts[];

    //colors
    private final int red       = 0xFFFF0000;
    private final int green     = 0xFF00FF00;
    private final int white     = 0xFFFFFFFF;
    private int playerColors[];

    public ScrabbleHumanPlayer(String name, int layout) {
        super(name);
        this.layoutid = layout;

        playerColors = new int[4];
        playerColors[0] = 0xFFFF0000;
        playerColors[1] = 0xFF0000FF;
        playerColors[2] = 0xFFFFFF00;
        playerColors[3] = 0xFF00FF00;
        scoreTexts = new TextView[4];
        handButtons = new Button[7];
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        Log.i("Info Received", info + "");
        //didn't receive a ScrabbleGameState
        if(!(info instanceof ScrabbleGameState)){
            flash(red, 50);
            return;
        }

        ScrabbleGameState state = (ScrabbleGameState) info;
        int turn = state.getPlayerTurn();
        //set the Score
        for (int i = 0; i < 4; i++){
            if(i < state.numPlayers) {
                scoreTexts[i].setTextColor(playerColors[i]);
                scoreTexts[i].setText(allPlayerNames[i] + ": " + state.getPlayer(i).getScore());
                scoreTexts[i].setBackgroundColor(0x00FFFFFF);
            }
            else{
                scoreTexts[i].setText("");
            }
        }
        scoreTexts[turn].setTextColor(white);
        scoreTexts[turn].setBackgroundColor(playerColors[turn]);

        //Set the hand
        for(int q = 0; q < 7; q++){
            if(state.getPlayer(playerNum).getTile(q) == null){
                handButtons[q].setText(" ");
                handButtons[q].setVisibility(View.GONE);
            }
            else{
                handButtons[q].setVisibility(View.VISIBLE);
                handButtons[q].setText(state.getPlayer(playerNum).getTile(q).getLetter());
            }
            handButtons[q].setTextColor(0xFFFFFFFF);
        }
        ArrayList<Integer> selected = state.getSelected(playerNum);
        for(int w = 0; w < selected.size(); w++){
            handButtons[selected.get(w)].setTextColor(playerColors[playerNum]);
        }
        boardView.setState((ScrabbleGameState) info);
        boardView.invalidate();
        //in case switch statement
        switch (turn){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;
        activity.setContentView(layoutid);

        //Initialize widget references
        resetButton     = (Button)activity.findViewById(R.id.ResetButton);
        submitButton    = (Button)activity.findViewById(R.id.SubmitButton);
        //exitRulesButton = (Button)activity.findViewById(R.id.closeSetting);
        bagButton       = (ImageButton)activity.findViewById(R.id.scrabbleBagButton);
        handButton0     = (Button)activity.findViewById(R.id.Tile0);
        handButton1     = (Button)activity.findViewById(R.id.Tile1);
        handButton2     = (Button)activity.findViewById(R.id.Tile2);
        handButton3     = (Button)activity.findViewById(R.id.Tile3);
        handButton4     = (Button)activity.findViewById(R.id.Tile4);
        handButton5     = (Button)activity.findViewById(R.id.Tile5);
        handButton6     = (Button)activity.findViewById(R.id.Tile6);
        handButtons[0]  = handButton0;
        handButtons[1]  = handButton1;
        handButtons[2]  = handButton2;
        handButtons[3]  = handButton3;
        handButtons[4]  = handButton4;
        handButtons[5]  = handButton5;
        handButtons[6]  = handButton6;
        p0Score         = (TextView)activity.findViewById(R.id.player0Score);
        p1Score         = (TextView)activity.findViewById(R.id.player1Score);
        p2Score         = (TextView)activity.findViewById(R.id.player2Score);
        p3Score         = (TextView)activity.findViewById(R.id.player3Score);
        scoreTexts[0] = p0Score;
        scoreTexts[1] = p1Score;
        scoreTexts[2] = p2Score;
        scoreTexts[3] = p3Score;
        //boardView
        boardView = (GameSurfaceView) activity.findViewById(R.id.gameSurfaceView);
        //Set up any Click or Touch listeners
        resetButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        bagButton.setOnClickListener(this);
        handButton0.setOnClickListener(this);
        handButton1.setOnClickListener(this);
        handButton2.setOnClickListener(this);
        handButton3.setOnClickListener(this);
        handButton4.setOnClickListener(this);
        handButton5.setOnClickListener(this);
        handButton6.setOnClickListener(this);
        //set up onTouch Listeners
        boardView.setOnTouchListener(this);
    }


    public boolean onTouch(View v, MotionEvent motionEvent){
        if(motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN){
            float x = motionEvent.getX();
            float y = motionEvent.getY();

            int height = boardView.getHeight()/ 15;
            int width = boardView.getWidth() / 15;

            //used to convert x,y coordinates to an array index
            int coordX = 0;
            int coordY = 0;

            //convert x,y coordinates to array indices
            for (int i = 1; i <= 15; i++) {
                if (x > width * i) {
                    coordX = i;
                }
            }
            for (int i = 1; i <= 15; i++) {
                if (y > height * i) {
                    coordY = i;
                }
            }
            ScrabblePlayAction play = new ScrabblePlayAction(this, coordX, coordY);
            game.sendAction(play);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view == resetButton){
            ScrabbleClearAction clear = new ScrabbleClearAction(this);
            game.sendAction(clear);
        }
        if(view == submitButton){
            ScrabbleSubmitAction submit = new ScrabbleSubmitAction(this);
            game.sendAction(submit);
        }
        if(view == bagButton){
            ScrabbleExchangeAction ex = new ScrabbleExchangeAction(this);
            game.sendAction(ex);
        }
        if(view == handButton0){
            ScrabbleSelectHandAction zero = new ScrabbleSelectHandAction(this, 0);
            game.sendAction(zero);
        }
        if(view == handButton1){
            ScrabbleSelectHandAction one = new ScrabbleSelectHandAction(this, 1);
            game.sendAction(one);
        }
        if(view == handButton2){
            ScrabbleSelectHandAction two = new ScrabbleSelectHandAction(this, 2);
            game.sendAction(two);
        }
        if(view == handButton3){
            ScrabbleSelectHandAction three = new ScrabbleSelectHandAction(this, 3);
            game.sendAction(three);
        }
        if(view == handButton4){
            ScrabbleSelectHandAction four = new ScrabbleSelectHandAction(this, 4);
            game.sendAction(four);
        }
        if(view == handButton5){
            ScrabbleSelectHandAction five = new ScrabbleSelectHandAction(this, 5);
            game.sendAction(five);
        }
        if(view == handButton6){
            ScrabbleSelectHandAction six = new ScrabbleSelectHandAction(this, 6);
            game.sendAction(six);
        }
    }
}
