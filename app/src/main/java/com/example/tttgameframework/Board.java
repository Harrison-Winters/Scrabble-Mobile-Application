/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */
package com.example.tttgameframework;

public class Board {
    private BoardSpace[][] board;

    public Board(){
        board = new BoardSpace[15][15];
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                board[i][j] = new BoardSpace(0,0,0,0,0);
            }
        }
    };

    public Board(Board b){
        board = new BoardSpace[15][15];
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                this.board[i][j] = b.board[i][j];
            }
        }
    }

    public void addToBoard(Tile tile, int i, int j){

    }
}
