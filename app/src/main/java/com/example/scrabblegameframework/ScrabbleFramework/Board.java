/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */
package com.example.scrabblegameframework.ScrabbleFramework;

public class Board {
    private BoardSpace[][] board;

    /**
     * Board - constructor for the board object
     */
    public Board(){
        board = new BoardSpace[15][15];
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                board[i][j] = new BoardSpace(0,0,0,0,0);
            }
        }
    }

    /**
     * Board - copy constructor
     * @param b Board object to copy
     */
    public Board(Board b){
        board = new BoardSpace[15][15];
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                board[i][j] = new BoardSpace(b.board[i][j]);
            }
        }
    }

    /**
     * addToBoard - adds tile to the board
     * @param tile tile object to add
     * @param r row to add tile
     * @param c column to add tile
     */
    public void addToBoard(Tile tile, int r, int c){
        board[r][c].setTile(tile);
    }

    public String toString() {
        String toReturn = "";
        for(int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++){
                if (board[i][j].getTile() == null) {
                    toReturn = toReturn + "* ";
                } else {
                    toReturn = toReturn + board[i][j].getTile().getLetter();
                }
            }
            toReturn = toReturn + "\n";
        }
        return toReturn;
    }

    /**
     * isEmpty - checks if the board has any tiles on it
     * @return true if empty
     */
    public boolean isEmpty(){
        for (int i = 0; i < 15; i++){
            for (int q = 0; q < 15; q++){
                if(board[i][q].getTile() != null){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Board)) {
            return false;
        }
        Board b = (Board) object;
        for(int i = 0; i < 15; i++){
            for(int q = 0; q < 15; q++){
                if(b.board[i][q].equals(board[i][q])){
                    return false;
                }
            }
        }
        return true;
    }
}
