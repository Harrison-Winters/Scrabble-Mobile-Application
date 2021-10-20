/*
* @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
*
* */

package com.example.tttgameframework;

import com.example.tttgameframework.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class ScrabbleGameState extends GameState {
    private Player[] players;
    private int playerTurn; //index for the player whose turn it is
    private Board scrabbleBoard;
    private Bag bag;
    private Timer timer;

    public ScrabbleGameState(){
        //create 4 new players for the game
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player("Player" + i);
        }
        //choose which player's turn it is
        playerTurn = -1;

        //create new board
        scrabbleBoard = new Board();

        //create new scrabble bag
        bag = new Bag();

        //create new Timer
        timer = new Timer();

        //Give players their decks
        for (int i = 0; i < 7; i++) {
            for (int q = 0; q < 4; q++) {
                drawLetter(players[q]);
            }
        }

    }
    //deep copy constructor
    public ScrabbleGameState(ScrabbleGameState s){

        //create 4 new players for the game
        players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = s.players[i];
        }

        //choose which player's turn it is
        playerTurn = s.playerTurn + 1;

        //create new board
        scrabbleBoard = new Board(s.scrabbleBoard);

        //create new scrabble bag
        bag = new Bag(s.bag);

        //create new Timer
        timer = new Timer();
        timer = s.timer;
    };

    public boolean drawRandLetter(Player player){
        if (playerTurn < 0) {
            player.setDeck(bag.get());
            return true;
        }
        if (player != players[playerTurn]){
            return false;
        }
        //add a random tile to the player's deck
        player.setDeck(bag.get());
        return true;
    }

    /**
     * drawLetter - Method for drawing specific letters
     * @param player
     * @return true if a valid move
     */

    public boolean drawLetter(Player player){
        if (playerTurn < 0) {
            player.setDeck(bag.get());
            return true;
        }
        if (player != players[playerTurn]){
            return false;
        }
        //add a random tile to the player's deck
        player.setDeck(bag.get());
        return true;
    }

    public boolean placeLetter(Tile tile){
        Random rnd = new Random();
        scrabbleBoard.addToBoard(tile, rnd.nextInt(), rnd.nextInt());
        return true;
    };

    public boolean clear(){
        return false;
    };

    public boolean exchangeLetter(int player) {

        //Check if it's the current player's turn
        if (player != playerTurn) {
            return false;
        }

        //Remove tiles from player's deck
        ArrayList<Tile> temp = new ArrayList<>();
        temp = players[player].getDeck();
        players[player].removeFromDeck(temp.get(0));
        //add tiles back to bag
        players[player].setDeck(bag.get());

        //end player's turn

        endTurn(players[player]);

        return true;


    }

    public boolean displayRules(){
        return false;
    }

    public boolean endTurn(Player player){
        if (player != players[playerTurn]) {
            return false;
        }
        if (player == players[0])
        {
        return true;
        }
        return true;
    }

    @Override
    public String toString(){
        String toReturn = new String("Current Player turn is: " + (playerTurn + 1));
        toReturn = toReturn +  "\nLetters in the bag are:" + bag.toString();
        toReturn = toReturn +"\nHere are current player's letters: ";

        for (int i = 0; i < 4; i++ ){
            toReturn = toReturn + "\nPlayer" + (i + 1);

            toReturn = toReturn + players[i].toString();
        }

        toReturn = toReturn + "\n\n";

        return toReturn;
    }

}
