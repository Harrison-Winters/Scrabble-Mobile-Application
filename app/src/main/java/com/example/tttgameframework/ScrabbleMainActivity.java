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

import java.util.ArrayList;

public class ScrabbleMainActivity extends GameMainActivity {
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();



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