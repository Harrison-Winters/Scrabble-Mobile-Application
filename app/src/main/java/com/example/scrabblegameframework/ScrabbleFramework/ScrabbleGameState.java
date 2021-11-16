/*
* @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
*
* */

package com.example.scrabblegameframework.ScrabbleFramework;

import com.example.scrabblegameframework.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Timer;

public class ScrabbleGameState extends GameState {
    private ArrayList<Player> players;
    private int currPlayerTurn; //index for the player whose turn it is
    private Board scrabbleBoard;
    private Bag bag;
    private Timer timer;
    private boolean playedLetter;
    int numPlayers;



    /**
     * ScrabbleGameState - constructor for the ScrabbleGameState class
     */
    public ScrabbleGameState(int num){
        //create 4 new players for the game
        players = new ArrayList<>();
        numPlayers = num;
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + i));
        }
        //choose which player's turn it is
        currPlayerTurn = 0;

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
        //Not Players Turn
        if (playerIdx != currPlayerTurn) {
            return false;
        }

        //Does not play in the middle on first turn
        else if (scrabbleBoard.isEmpty() && x == 7 && y == 7) {
            Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));

            scrabbleBoard.addToBoard(toPlace, x, y);
            playedLetter = true;
            return true;
        } else if (scrabbleBoard.isEmpty()) {
            return false;
        }
        //Other checks if needed
        Tile toPlace = new Tile(players.get(playerIdx).removeFromDeck(players.get(playerIdx).deselectDeck(-1)));
        //playedLetter = true;
        //scrabbleBoard.addToBoard(toPlace, x, y);


        //added by Harrison
        //add 1 to player's numLettersPlaced
        players.get(playerIdx).setNumLettersPlaced(1);

        //adds to players hashmap of placed letters
        //if it's the first tile placed then add the surrounding tiles to player's connectedTiles
        if (players.get(playerIdx).getNumLettersPlaced() == 1) {

            //add top space (above) first
            if (y != 0) {
                Tile aboveTile = scrabbleBoard.getBoardSpace(x, y - 1).getTile();
                String above = null;
                if (aboveTile != null) {
                    above = aboveTile.getLetter();
                    players.get(playerIdx).setConnectedLetters(0, above);
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
            }

            //add right space second (index 1 of connectedLetters)
            if (x != 14) {
                Tile rightTile = scrabbleBoard.getBoardSpace(x + 1, y).getTile();
                String right = null;
                if (rightTile != null) {
                    right = rightTile.getLetter();
                    players.get(playerIdx).setConnectedLetters(1, right);
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
            }

            //add below space third (index 2 of connectedLetters)
            if (y != 14) {
                Tile belowTile = scrabbleBoard.getBoardSpace(x, y + 1).getTile();
                String below = null;
                if (belowTile != null) {
                    below = belowTile.getLetter();
                    players.get(playerIdx).setConnectedLetters(2, below);
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
            }

            //add left space second (index 3 of connectedLetters)
            if (x != 0) {
                Tile leftTile = scrabbleBoard.getBoardSpace(x - 1, y).getTile();
                String left = null;
                if (leftTile != null) {
                    left = leftTile.getLetter();
                    players.get(playerIdx).setConnectedLetters(3, left);
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
            }

            //if there's no surrounding letter on the board
            return false;
        }

        //Otherwise if it's the player's second tile placed, now's the time to decide on a direction
        else if (players.get(playerIdx).getNumLettersPlaced() == 2) {
            int checkAbove = x * 10 + y - 1;
            boolean isAbove = players.get(playerIdx).getLettersPlaced().containsKey(checkAbove);
            int checkRight = (x + 1) * 10 + y;
            boolean isRight = players.get(playerIdx).getLettersPlaced().containsKey(checkRight);
            int checkBelow = x * 10 + y + 1;
            boolean isBelow = players.get(playerIdx).getLettersPlaced().containsKey(checkBelow);
            int checkLeft = (x - 1)* 10 + y;
            boolean isLeft = players.get(playerIdx).getLettersPlaced().containsKey(checkLeft);


            if(isAbove) {
                //set direction (1 = above)
                players.get(playerIdx).setDirection(1);

                //Finally add the letter
                players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                scrabbleBoard.addToBoard(toPlace, x, y);
                playedLetter = true;
                return true;
            }

            else if(isRight) {
                //set direction (2 = right)
                players.get(playerIdx).setDirection(2);

                //Finally add the letter
                players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                scrabbleBoard.addToBoard(toPlace, x, y);
                playedLetter = true;
                return true;
            }

            else if(isBelow) {
                //set direction (3 = below)
                players.get(playerIdx).setDirection(3);

                //Finally add the letter
                players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                scrabbleBoard.addToBoard(toPlace, x, y);
                playedLetter = true;
                return true;
            }

            else if (isLeft) {
                //set direction (4 = left)
                players.get(playerIdx).setDirection(4);

                //Finally add the letter
                players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                scrabbleBoard.addToBoard(toPlace, x, y);
                playedLetter = true;
                return true;
            }

            //otherwise, the letters are separated, return false
            //FOR LATER: clear the board
            else {
                return false;
            }


        }

        //if the move is greater than < 2, check whether the direction is consistent
        else if (players.get(playerIdx).getNumLettersPlaced() > 2) {
            int currDirection = players.get(playerIdx).getDirection();

            //above
            if (currDirection == 1) {
                int isAbove = x * 10 +  y - 1;
                if (players.get(playerIdx).getLettersPlaced().containsKey(isAbove)) {
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;


                    return true;
                }
                return false;
            }

            //right
            else if (currDirection == 2) {
                int isRight = (x + 1) * 10 + y;
                if (players.get(playerIdx).getLettersPlaced().containsKey(isRight)) {
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
                return false;
            }

            //below
            else if (currDirection == 3) {
                int isBelow = x* 10 + y + 1;
                if (players.get(playerIdx).getLettersPlaced().containsKey(isBelow)) {
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
                return false;
            }

            //left
            else if (currDirection == 4) {
                int isLeft = (x - 1)*10 + y;
                if (players.get(playerIdx).getLettersPlaced().containsKey(isLeft)) {
                    //Finally add the letter
                    players.get(playerIdx).addLettersPlaced(toPlace.getLetter(), x, y);
                    scrabbleBoard.addToBoard(toPlace, x, y);
                    playedLetter = true;
                    return true;
                }
                return false;
            }

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
        //added
        players.get(playerIdx).setNumLettersPlaced(0);
        players.get(playerIdx).getLettersPlaced().clear();
        players.get(playerIdx).setDirection(0);

        currPlayerTurn++;
        if(currPlayerTurn >= numPlayers){
            currPlayerTurn = 0;
        }
        playedLetter = false;
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
        if(!(scrabGS.bag.equals(bag))){
            return false;
        }
        if(!(scrabGS.timer.equals(timer))){
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


    //Check word
    public boolean checkWord(int playerIdx, String word) {
        return true;
    }

}
