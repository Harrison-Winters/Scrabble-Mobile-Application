package com.example.tttgameframework;

public class Tile {
    //define variables
    private String letter;
    private int isInDeck;
    private boolean isOnBoard;
    private int points;

    public Tile(String l, int p) {
        letter = l;
        points = p;

        isInDeck = 0;
        isOnBoard = false;
    }

    public Tile(Tile tile) {
        this.letter = tile.letter;
        this.points = tile.points;

        this.isInDeck = tile.isInDeck;
        this.isOnBoard = tile.isOnBoard;
    }

    public String getName() {
        return letter;
    }
}
