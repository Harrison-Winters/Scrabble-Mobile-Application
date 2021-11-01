package com.example.tttgameframework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.gameConfiguration.GameConfig;
import com.example.tttgameframework.GameFramework.gameConfiguration.GamePlayerType;
import com.example.tttgameframework.GameFramework.infoMessage.GameState;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class ScrabbleMainActivity extends GameMainActivity {

    private static final String TAG = "ScrabbleMainActivity";
    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();


        //MODIFY THIS

        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new Player(name);
            }
        });

        // red-on-yellow GUI
        playerTypes.add(new GamePlayerType("Local Human Player (yellow-red)") {
            public GamePlayer createPlayer(String name) {
                return new TTTHumanPlayer1(name, R.layout.ttt_human_player1_flipped);
            }
        });



        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new TTTComputerPlayer1(name);
            }
        });

        // smarter computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new TTTComputerPlayer2(name);
            }
        });


        // smarter computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new TTTComputerPlayer3(name);
            }
        });


        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 2,2, "Tic-Tac-Toe", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Computer", 3); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI

        //done!
        return defaultConfig;

        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Step 1: Find the object of elements to interact
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("Test!!");

        View runTestButton = (View) findViewById(R.id.runTestButton);

        //Step 2: Set up listeners for the objects

        runTestButton.setOnClickListener(new View.OnClickListener() {
            //3 what to do with a click
            public void onClick(View v) {
                editText.setText("");
                //first
                ScrabbleGameState firstInstance = new ScrabbleGameState();
                //second
                ScrabbleGameState secondInstance = new ScrabbleGameState(firstInstance);
                //secondInstance.exchangeLetter(1);

                //print the states
                editText.setText(firstInstance.toString() + secondInstance.toString());


            }
        });
    }
*/

}