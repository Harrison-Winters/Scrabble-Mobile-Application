/*
* @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
*
* */

package com.example.scrabblegameframework.ScrabbleFramework;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameState;
import com.example.scrabblegameframework.GameFramework.players.GamePlayer;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class ScrabbleGameState extends GameState {
    private ArrayList<Player> players;
    private int currPlayerTurn; //index for the player whose turn it is
    private Board scrabbleBoard;
    private Bag bag;
    private Timer timer;
    private boolean playedLetter;
    private boolean playedFirstTile;
    private boolean checkRow;
    private int checkRowNum;
    private boolean checkCol;
    private int checkColNum;
    int numPlayers;
    int[] playerScores = new int[4];

    //direction = 0 (none)
    //direction = 1 (up/down)
    //direction = 2 (left/right)
    int direction;
    HashMap<String, Boolean> dictionary;
    int lastX;
    int lastY;


    /**
     * ScrabbleGameState - constructor for the ScrabbleGameState class
     */
    public ScrabbleGameState(int num, HashMap<String, Boolean> dictionary, int x, int y){
        //create 4 new players for the game
        players = new ArrayList<>();
        numPlayers = num;
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        //choose which player's turn it is
        currPlayerTurn = 0;

        playedLetter = false;

        playedFirstTile = false;
        checkRow = false;
        checkRowNum = -1;
        checkCol = false;
        checkColNum = -1;

        //set direction to 0
        direction = 0;

        //create new board
        scrabbleBoard = new Board();

        //create new scrabble bag
        bag = new Bag();

        //create new Timer
        timer = new Timer();

        //Give players their decks
        for (int i = 0; i < 7; i++) {
            for (int q = 0; q < numPlayers; q++) {
                drawRandLetter(players.get(q));
            }
        }

        //added
        this.dictionary = dictionary;
        this.lastX = x;
        this.lastY = y;
        for(int w = 0; w < 4; w++){
            playerScores[w] = 0;
        }
    }

    /**
     * ScrabbleGameState - copy constructor
     * @param s the ScrabbleGameState to deep copy
     */
    public ScrabbleGameState(ScrabbleGameState s){
        //create 4 new players for the game
        players = new ArrayList<>();
        numPlayers = s.numPlayers;
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(s.players.get(i)));
        }
        //choose which player's turn it is
        currPlayerTurn = s.currPlayerTurn;
        //create new board
        scrabbleBoard = new Board(s.scrabbleBoard);
        //create new scrabble bag
        bag = new Bag(s.bag);
        //create new Timer
        timer = new Timer();
        numPlayers = s.numPlayers;
        playedLetter = s.playedLetter;
        playedFirstTile = s.playedFirstTile;
        checkRow = s.checkRow;
        checkRowNum = s.checkRowNum;
        checkCol = s.checkCol;
        checkColNum = s.checkColNum;

        //added
        direction = s.direction;

        //added
        dictionary = s.dictionary;
        lastX = s.lastX;
        lastY = s.lastY;
        for(int w = 0; w < 4; w++){
            playerScores[w] = s.playerScores[w];
        }
    }


    public void addPlayers(){
        players.add(new Player("Player " + numPlayers));
        for (int i = 0; i < 7; i++) {
            drawRandLetter(players.get(numPlayers));
        }
        numPlayers++;
    }


    /**
     * drawRandLetter - Method for drawing random letters
     * @param player Player object which the letters will be drawn to
     * @return true if a valid move
     */
    public boolean drawRandLetter(Player player){
        if (scrabbleBoard.isEmpty()) {
            player.setDeck(bag.getRnd());
            return true;
        }
        if (player != players.get(currPlayerTurn)){
            return false;
        }
        //add a random tile to the player's deck
        player.setDeck(bag.getRnd());
        return true;
    }


    /**
     * drawLetter - Method for drawing specific letters
     * @param player Player object which the letters will be drawn to
     * @return true if a valid move
     */
    public boolean drawLetter(Player player){
        if (scrabbleBoard.isEmpty()) {
            player.setDeck(bag.get());
            return true;
        }
        if (player != players.get(currPlayerTurn)){
            return false;
        }
        //add a random tile to the player's deck
        player.setDeck(bag.get());
        return true;
    }

    /**
     * placeLetter - Action for placing a letter
     * @param playerIdx which player is using this action
     * @return
     */
    public boolean placeLetter(int playerIdx, int x, int y) {
        //Check if it is the players Turn
        if (playerIdx != currPlayerTurn) {
            return false;
        }
        //Tile must be played in the middle on the first turn
        if (scrabbleBoard.isEmpty() && x == 7 && y == 7) {
            Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));

            scrabbleBoard.addToBoard(toPlace, x, y);
            this.lastX = x;
            this.lastY = y;
            scrabbleBoard.getBoardSpace(x,y).select();
            playedLetter = true;
            return true;
        } else {
            //If it is not the first tile played in the game:

            //Make sure tile is not placed on another tile
            if ((scrabbleBoard.getBoardSpace(x, y).getTile() != null)) {
                return false;
            }
            // Check if the tile is the first played this turn

            if (playedFirstTile == false){
                // If true, check that it is connected to another tile
                Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));
                int isConnected = 0;
                //have x
                //have y

                //TOP
                if (x < 14) {
                    if ((scrabbleBoard.getBoardSpace(x + 1, y).getTile() != null)) {
                        isConnected += 1;
                        checkRow = true;
                        checkCol = false;
                        checkRowNum = y;
                    }
                }
                //RIGHT
                if (y < 14) {
                    if (scrabbleBoard.getBoardSpace(x ,y + 1).getTile() != null){
                        isConnected += 1;
                        checkCol = true;
                        checkRow = false;
                        checkColNum = x;
                    }
                }
                //BOTTOM
                if (x > 0) {
                    if (scrabbleBoard.getBoardSpace(x - 1, y).getTile() != null) {
                        isConnected += 1;
                        checkRow = true;
                        checkCol = false;
                        checkRowNum = y;
                    }
                }
                //LEFT
                if (y > 0) {
                    if (scrabbleBoard.getBoardSpace(x,y - 1).getTile() != null){
                        isConnected += 1;
                        checkCol = true;
                        checkRow = false;
                        checkColNum = x;
                    }
                }
                //Once placed, that will determine direction of the word
                //checkRow and checkCol will be set to direction
                if (isConnected > 0) {



                    scrabbleBoard.addToBoard(toPlace, x, y);
                    this.lastX = x;
                    this.lastY = y;
                    scrabbleBoard.getBoardSpace(x,y).select();
                    playedFirstTile = true;
                    return true;
                }
            }
            //If the tile is not the first of the turn, verify that it is in the same row/col and connected to another tile
            if (playedFirstTile && checkRow){
                // If true, check that it is connected to another tile
                Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));
                int isConnected = 0;
                //have x

                //have y
                if (y != checkRowNum) {
                    return false;
                }
                //TOP
                if (x < 14) {
                    if ((scrabbleBoard.getBoardSpace(x + 1, y).getTile() != null)) {
                        isConnected += 1;
                    }
                }
                //RIGHT
                if (y < 14) {
                    if (scrabbleBoard.getBoardSpace(x ,y + 1).getTile() != null){
                        isConnected += 1;
                    }
                }
                //BOTTOM
                if (x > 0) {
                    if (scrabbleBoard.getBoardSpace(x - 1, y).getTile() != null) {
                        isConnected += 1;
                    }
                }
                //LEFT
                if (y > 0) {
                    if (scrabbleBoard.getBoardSpace(x,y - 1).getTile() != null){
                        isConnected += 1;
                    }
                }
                if (isConnected > 0) {
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    this.lastX = x;
                    this.lastY = y;


                    scrabbleBoard.getBoardSpace(x,y).select();
                    playedFirstTile = true;
                    return true;
                }
            }
            if (playedFirstTile && checkCol){
                // If true, check that it is connected to another tile
                Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));
                int isConnected = 0;
                //have x

                //have y
                if (x != checkColNum) {
                    return false;
                }
                //TOP
                if (x < 14) {
                    if ((scrabbleBoard.getBoardSpace(x + 1, y).getTile() != null)) {
                        isConnected += 1;
                    }
                }
                //RIGHT
                if (y < 14) {
                    if (scrabbleBoard.getBoardSpace(x ,y + 1).getTile() != null){
                        isConnected += 1;
                    }
                }
                //BOTTOM
                if (x > 0) {
                    if (scrabbleBoard.getBoardSpace(x - 1, y).getTile() != null) {
                        isConnected += 1;
                    }
                }
                //LEFT
                if (y > 0) {
                    if (scrabbleBoard.getBoardSpace(x,y - 1).getTile() != null){
                        isConnected += 1;
                    }
                }
                if (isConnected > 0) {
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    this.lastX = x;
                    this.lastY = y;
                    scrabbleBoard.getBoardSpace(x,y).select();

                    playedFirstTile = true;
                    return true;
                }
            }
        //VERIFY WORD METHOD
            //Check the player class for direction and firstTilePlaced
            //Start at first letter placed, and go to beginning of word
            //From there, concatenate the letters into a word and check with hash table
            //SUCCESS: calculate points and add to score. Move to next player
            //ERROR: Reset gamestate to how it was at the beginning of the turn
            }
        return false;
    }

    /**
     * clear - action to clear any movement
     * *could take in the official game state as a parameter and re-copy it
     * @return valid move
     */
    public boolean clear(){
        playedLetter = false;
        return false;
    }

    /**
     * exchangeLetter - exchange letter action
     * @param playerIdx index of the player that sent the action
     * @return if it is a valid move
     */
    public boolean exchangeLetters(int playerIdx) {

        //Check if it's the current player's turn
        if (playerIdx != currPlayerTurn) {
            return false;
        }
        else if (playedLetter){
            return false;
        }

        //Check if has anything selected
        if (players.get(playerIdx).getSelected().size() <= 0){
            return false;
        }

        //swap out all letters selected
        int size = players.get(playerIdx).getSelected().size();
        for(int i = 0; i < size; i++){
            int s = players.get(playerIdx).deselectDeck(-1);
            if (s >= 0){
                Tile hold = players.get(playerIdx).removeFromDeck(s);
                //players.get(playerIdx).setDeck(bag.get());
                bag.put(hold);
            }
        }
        endTurn(playerIdx);
        return true;
    }

    /**
     * displayRules - displays the rules
     * @return valid move
     */
    public boolean displayRules(){
        return false;
    }

    /**
     * endTurn - ends the current players turn
     * @param playerIdx index to player attempting to end turn
     * @return if valid move
     */
    public boolean endTurn(int playerIdx){
        if (playerIdx != currPlayerTurn) {
            return false;
        }
        for(int i = 0; i < 7; i++){
            if(players.get(playerIdx).getTile(i) == null){
                drawRandLetter(players.get(currPlayerTurn));
            }
        }


        //players.get(playerIdx).getLettersPlaced().clear();
        players.get(playerIdx).setDirection(0);
        playedFirstTile = false;
        checkRow = false;
        checkRowNum = -1;
        checkCol = false;
        checkColNum = -1;
        currPlayerTurn++;
        if(currPlayerTurn >= numPlayers){
            currPlayerTurn = 0;
        }
        playedLetter = false;
        scrabbleBoard.setAllInactive();
        return true;
    }

    /**
     * toString - prints out the state of the board
     * @return String that reports the state
     */
    @Override
    public String toString(){
        String toReturn = "Current Player turn is: " + (currPlayerTurn + 1);
        toReturn = toReturn +  "\nLetters in the bag are:" + bag.toString();
        toReturn = toReturn +"\nHere are current player's letters: \n";
        for (int i = 0; i < numPlayers; i++ ){
            toReturn = toReturn + players.get(i).toString();
        }
        toReturn = toReturn +"\nHere are current player's score: \n";

        for (int i = 0; i < numPlayers; i++ ){
            String string = String.format("Player %s:",i);
            toReturn = toReturn + string + players.get(i).getScore() +"\n";
        }
        toReturn += "\n\nThis is the state of the board: \n" + scrabbleBoard.toString();
        toReturn = toReturn + "\n\n";

        return toReturn;
    }

    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object){
        if (!(object instanceof ScrabbleGameState)){
            return false;
        }
         ScrabbleGameState gameState = (ScrabbleGameState) object;

                        if(!(this.scrabbleBoard.equals(gameState.scrabbleBoard))){
                            return false;
                        }

        ScrabbleGameState scrabGS = (ScrabbleGameState) object;
        if (scrabGS.currPlayerTurn != currPlayerTurn){
            return false;
        }
        for(int i = 0; i < players.size(); i++){
            if(!(scrabGS.players.get(i).equals(players.get(i)))){
                return false;
            }
        }
        if(!(scrabGS.scrabbleBoard.equals(scrabbleBoard))){
            return false;
        }
        if(!(scrabGS.bag != (bag))){
            return false;
        }
        if(!(scrabGS.timer != (timer))){
            return false;
        }
        if (scrabGS.playedLetter != playedLetter){
            return false;
        }
        return true;
    }

    /**
     * getPlayerTurn - returns the current player index
     * @return int of index
     */
    public int getCurrPlayerTurn(){
        return currPlayerTurn;
    }

    /**
     * getPlayer - returns a player from the players array
     * @param idx index of player to return
     * @return player
     */
    public Player getPlayer(int idx){
        return players.get(idx);
    }

    /**
     * select - adds letters to the selected arraylist, removes if already selected
     * @param playerIdx player adding
     * @param letterIdx index of which letter in had to add to list
     * @return boolean if done successfully
     */
    public boolean select(int playerIdx, int letterIdx){
        if(players.get(playerIdx).isSelected(letterIdx)) {
            players.get(playerIdx).deselectDeck(letterIdx);
            return true;
        }
        else{
            if (players.get(playerIdx).selectDeck(letterIdx)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getSelected - returns the arraylist of selected letters
     * @param playerIdx index of which player to look at
     * @return arraylist of selected indexes
     */
    public ArrayList<Integer> getSelected(int playerIdx){
        return players.get(playerIdx).getSelected();
    }

    public Board getBoard(){
        return scrabbleBoard;
    }

    //Make word
    public String makeWord(int playerIdx, ArrayList<String> letters) {
        return "a";
    }


    public ArrayList<Player> getPlayerList() {
        return players;
    }

    public void setPlayerScore(int player, int score){
        playerScores[player] = score;
    }

    public int getPlayerScore(int player){
        return playerScores[player];
    }

    public Bag getBag() {
        return bag;
    }
}
