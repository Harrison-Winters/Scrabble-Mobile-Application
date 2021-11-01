package com.example.scrabblegameframework.ScrabbleFramework;

/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Tile> deck;
    private int score;

    /**
     * Player - constructor for the Player object
     * @param n player's name
     */
    public Player(String n){
        name = n;
        deck = new ArrayList<Tile>();
        score = 0;
    }

    /**
     * Player - copy constructor
     * @param other Player object to be copied
     */
    public Player(Player other){
        name = other.name;
        deck = new ArrayList<>();
        for(int i = 0; i < other.deck.size(); i++){
            deck.add(new Tile(other.deck.get(i)));
        }
    }

    /**
     * setDeck - adds tile into players hand
     * @param t tile to add
     */
    public void setDeck(Tile t) {
        if (deck.size() >= 7 ) {
            return;
        }
        deck.add(t);
    }

    /**
     * removeFromDeck - removes a tile from the player's deck
     * @param idx index in the deck of tile to be removed
     * @return tile removed
     */
    public Tile removeFromDeck(int idx) {
        Tile toReturn = deck.get(idx);
        deck.remove(idx);
        return toReturn;
    }

    /**
     * getDeck - returns the player's deck
     * @return player's deck
     */
    public ArrayList<Tile> getDeck() {
        return deck;
    }

    /**
     * getTile - returns a specific tile from the player's deck
     * @param idx index of tile to return
     * @return tile specified
     */
    public Tile getTile(int idx){
        return deck.get(idx);
    }


    /**
     * toString - reports the state of the player's hand
     * @return
     */
    @Override
    public String toString() {
        String toReturn = name + ":";
        for (int i = 0; i < deck.size(); i++) {
            toReturn =  toReturn +  "-" + deck.get(i).getLetter();
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
        if(p.deck.size() < deck.size()){
            return false;
        }
        for(int i = 0; i < deck.size(); i++){
            if(!(p.deck.get(i).equals(deck.get(i)))){
                return false;
            }
        }
        return true;
    }
}


