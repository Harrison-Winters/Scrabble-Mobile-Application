package com.example.scrabblegameframework.ScrabbleFramework;

/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */

import java.util.ArrayList;

public class Player {

    private String name;
    private Tile[] deck;
    private int deckSize;
    private int score;
    private ArrayList<Integer> selected;

    /**
     * Player - constructor for the Player object
     * @param n player's name
     */
    public Player(String n){
        name = n;
        deck = new Tile[7];
        score = 0;
    }

    /**
     * Player - copy constructor
     * @param other Player object to be copied
     */
    public Player(Player other){
        name = other.name;
        selected = new ArrayList<>();
        deck = new Tile[7];
        for(int i = 0; i < 7; i++){
            deck[i] = new Tile(other.deck[i]);
        }
    }

    /**
     * setDeck - adds tile into players hand
     * @param t tile to add
     */
    public void setDeck(Tile t) {
        if (deckSize >= 7 ) {
            return;
        }
        for(int i = 0; i < 7; i++){
            if (deck[i] == null){
                deck[i] = t;
                return;
            }
        }
    }

    /**
     * selectDeck - adds index of tiles that are selected in hand
     * @param idx index of tile selected
     */
    public boolean selectDeck(int idx){
        if(deck[idx] == null){
            return false;
        }
        selected.add(idx);
        return true;
    }

    /**
     * selectDeck - removes indexes of tiles selected in hand
     * @param idx index of tile selected
     */
    public int deselectDeck(int idx){
        int toReturn = -1;
        for(int i = 0; i < selected.size(); i++){
            if(selected.get(i) == idx){
                toReturn = selected.get(i);
                selected.remove(i);
            }
        }
        if(selected.isEmpty()){
        }
        return toReturn;
    }

    /**
     * removeFromDeck - removes a tile from the player's deck
     * @param idx index in the deck of tile to be removed
     * @return tile removed
     */
    public Tile removeFromDeck(int idx) {
        Tile toReturn = new Tile(deck[idx]);
        deck[idx] = null;
        deckSize--;
        return toReturn;
    }

    /**
     * getDeck - returns the player's deck
     * @return player's deck
     */
    public Tile[] getDeck() {
        return deck;
    }

    /**
     * getTile - returns a specific tile from the player's deck
     * @param idx index of tile to return
     * @return tile specified
     */
    public Tile getTile(int idx){
        return deck[idx];
    }


    /**
     * toString - reports the state of the player's hand
     * @return
     */
    @Override
    public String toString() {
        String toReturn = name + ":";
        for (int i = 0; i < 7; i++) {
            toReturn =  toReturn +  "-" + deck[i].getLetter();
        }
        toReturn = toReturn + "\n";
        return toReturn;
    }

    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Player)) {
            return false;
        }
        Player p = (Player) object;
        if(!(p.name.equals(name))){
            return false;
        }
        if(p.score != score){
            return false;
        }
        if(p.deckSize < deckSize){
            return false;
        }
        for(int i = 0; i < deckSize; i++){
            if(!(p.deck[i].equals(deck[i]))){
                return false;
            }
        }
        return true;
    }

    public int getScore(){
        return score;
    }

    public ArrayList<Integer> getSelected(){
        return selected;
    }
}


