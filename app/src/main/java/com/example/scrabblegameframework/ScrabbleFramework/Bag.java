/**
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */
package com.example.scrabblegameframework.ScrabbleFramework;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private ArrayList<Tile> tiles;
    private Random rnd;

    /**
     * Bag - constructor for the bag object
     */
    public Bag (){
        tiles = new ArrayList<>();
        rnd = new Random();
        fillBag();
    }

    /**
     * Bag - copy constructor
     * @param other bag object to be copied
     */
    public Bag(Bag other){
        tiles = new ArrayList<>();
        for (int i = 0; i < other.tiles.size(); i++) {
            //tile to be added
            Tile temp = new Tile(other.tiles.get(i));
            //add to new ArrayList
            tiles.add(temp);
        }
        rnd = new Random();
    }

    /**
     * put - adds a tile to the bag
     * @param newTile tile object to add to the bag
     */
    public void put(Tile newTile){
        tiles.add(newTile);
    }

    /**
     * get - Helper to get specific tiles instead of random, for debugging
     * @return
     */
    public Tile get(){
        Tile temp = new Tile(tiles.get(0));
        tiles.remove(0);
        return temp;
    }

    /**
     * getRnd - Helper to get specific tiles instead of random, for debugging
     * @return
     */
    public Tile getRnd(){
        int index = rnd.nextInt(tiles.size());
        Tile temp = tiles.get(index);
        tiles.remove(index);
        return temp;
    }

    public boolean empty(){
        if(tiles.isEmpty()){
            return true;
        }
        return false;
    }


    /**
     * toString - reports the current state of the bag
     * @return string of whats in the bag
     */
    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < tiles.size(); i++) {
            toReturn = toReturn + "-" + tiles.get(i).getLetter();
        }

        return toReturn;
    }

    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Bag)) {
            return false;
        }
        Bag t = (Bag) object;
        if (t.tiles.size() < tiles.size()){
            return false;
        }
        for(int i = 0; i < tiles.size(); i++){
            if(!(t.tiles.get(i).equals(tiles.get(i)))){
                return false;
            }
        }
        return true;
    }

    /**
     * fillBag - helper method to fill the bag up
     */
    public void fillBag(){
        /*
        0 Points - Blank tile.
        1 Point - A, E, I, L, N, O, R, S, T and U.
        2 Points - D and G.
        3 Points - B, C, M and P.
        4 Points - F, H, V, W and Y.
        5 Points - K.
        8 Points - J and X.
        10 Points - Q and Z.
         */

        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));
        tiles.add(new Tile("A", 1));

        tiles.add(new Tile("B", 3));
        tiles.add(new Tile("B", 3));

        tiles.add(new Tile("C", 3));
        tiles.add(new Tile("C", 3));

        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));
        tiles.add(new Tile("D", 2));

        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));
        tiles.add(new Tile("E", 1));

        tiles.add(new Tile("F", 4));
        tiles.add(new Tile("F", 4));

        tiles.add(new Tile("G", 2));
        tiles.add(new Tile("G", 2));
        tiles.add(new Tile("G", 2));

        tiles.add(new Tile("H", 4));
        tiles.add(new Tile("H", 4));

        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));
        tiles.add(new Tile("I", 1));

        tiles.add(new Tile("J", 8));

        tiles.add(new Tile("K", 5));

        tiles.add(new Tile("L", 1));
        tiles.add(new Tile("L", 1));
        tiles.add(new Tile("L", 1));
        tiles.add(new Tile("L", 1));

        tiles.add(new Tile("M", 3));
        tiles.add(new Tile("M", 3));

        tiles.add(new Tile("N", 1));
        tiles.add(new Tile("N", 1));
        tiles.add(new Tile("N", 1));
        tiles.add(new Tile("N", 1));
        tiles.add(new Tile("N", 1));
        tiles.add(new Tile("N", 1));

        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));
        tiles.add(new Tile("O", 1));

        tiles.add(new Tile("P", 3));
        tiles.add(new Tile("P", 3));

        tiles.add(new Tile("Q", 10));

        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));
        tiles.add(new Tile("R", 1));

        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));
        tiles.add(new Tile("S", 1));

        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));
        tiles.add(new Tile("T", 1));

        tiles.add(new Tile("U", 1));
        tiles.add(new Tile("U", 1));
        tiles.add(new Tile("U", 1));
        tiles.add(new Tile("U", 1));

        tiles.add(new Tile("V", 4));
        tiles.add(new Tile("V", 4));

        tiles.add(new Tile("W", 4));
        tiles.add(new Tile("W", 4));

        tiles.add(new Tile("X", 8));

        tiles.add(new Tile("Y", 4));
        tiles.add(new Tile("Y", 4));

        tiles.add(new Tile("Z", 10));
    }

}
