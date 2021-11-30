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
                if (    i==0 && j==0 ||
                        i==7 && j==0 ||
                        i==14 && j==0 ||
                        i==0 && j==7 ||
                        i== 14 && j==7||
                        i==0 && j==14||
                        i==7 && j==14||
                        i==14 && j==14
                ){
                    //triple word score
                    board[i][j].setMultiplier(4);
                }
                if (    i==1 && j==1 ||
                        i==2 && j==2 ||
                        i==3 && j==3 ||
                        i==4 && j==4 ||
                        i==1 && j==13 ||
                        i==2 && j==12 ||
                        i==3 && j==11 ||
                        i==4 && j==10 ||
                        i==13 && j==1 ||
                        i==12 && j==2 ||
                        i==11 && j==3 ||
                        i==10 && j==4 ||
                        i==10 && j==10 ||
                        i==11 && j==11 ||
                        i==12 && j==12 ||
                        i==13 && j==13
                ){
                    //double word score
                    board[i][j].setMultiplier(3);
                }
                if (    i==5 && j==1 ||
                        i==5 && j==9 ||
                        i==5 && j==5 ||
                        i==9 && j==9 ||
                        i==9 && j==1 ||
                        i==9 && j==5 ||
                        i==1 && j==9 ||
                        i==1 && j==5 ||
                        i==9 && j==13||
                        i == 5 && j==13||
                        i==13 && j ==5||
                        i==13 && j==9){
                    //triple letter score
                    board[i][j].setMultiplier(2);
                }
                if ( i==0 && j==3 ||
                        i==0 && j==11 ||
                        i==2 && j==6 ||
                        i==2 && j==8 ||
                        i==3 && j==0 ||
                        i==11 && j==0 ||
                        i==6 && j==2 ||
                        i==8 && j==2 ||
                        i==14 && j==3 ||
                        i==14 && j==11||
                        i==12 && j==6||
                        i==12 && j==8||
                        i==11 && j==7||
                        i==3 && j==14||
                        i==11 && j==14 ||
                        i==6 && j==12 ||
                        i==8 && j==12 ||
                        i==7 && j==11
                ) {
                    //double letter score
                    board[i][j].setMultiplier(1);
                }
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
                if(!(b.board[i][q].equals(board[i][q]))){
                    return false;
                }
            }
        }
        return true;
    }

    public BoardSpace getBoardSpace(int row, int col){
        return board[row][col];
    }
}
