package com.example.scrabblegameframework.ScrabbleFramework;

public class Tile {
    //define variables
    private String letter;
    private int points;

    /**
     * Tile - constructor for the Tile object
     * @param l letter for the tile
     * @param p points for the tile
     */
    public Tile(String l, int p){
        letter = l;
        points = p;
    }

    /**
     * Tile - copy constructor
     * @param other tile object to copy
     */
    public Tile(Tile other){
        this.letter= other.letter;
        this.points = other.points;
    }

    /**
     * getLetter - get the letter of the tile
     * @return letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * getPoints - get the points of the tile
     * @return points
     */
    public int getPoints(){
        return points;
    }

    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Tile)) {
            return false;
        }
        Tile t = (Tile) object;
        if(!(t.letter.equals(letter))){
            return false;
        }
        return true;
    }
}