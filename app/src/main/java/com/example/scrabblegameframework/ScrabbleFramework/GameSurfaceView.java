package com.example.scrabblegameframework.ScrabbleFramework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.scrabblegameframework.R;

import java.util.Random;

/**
 * GameSurfaceView: Surface view that contains all game elements
 *
 * @author Cory Marleau
 * @version September 26 2021
 */

public class GameSurfaceView extends SurfaceView{
    private int gameSize;
    private ScrabbleGameState state;

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //set default gameSize
        gameSize = 15;
        //enable drawing
        setWillNotDraw(false);
        //create and shuffle the board
        //createBoard();
        state = new ScrabbleGameState(2);
    }
    /**
     * Draws all tiles on the GameSurfaceView
     *
     * @param canvas canvas object that will be drawn upon
     */
    protected void onDraw(Canvas canvas) {
        //find the height and width of each piece
        int height = this.getHeight() / gameSize;
        int width = this.getWidth() / gameSize;

        //loop through the array[][] of Tiles and draw their position based
        // on height and width of the canvas
        int counter = 1;
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                state.getBoard().getBoardSpace(i, j).setPos(width / 2 + (i * width), height / 2 + (j * height));
                state.getBoard().getBoardSpace(i, j).setHeight(height);
                state.getBoard().getBoardSpace(i, j).setWidth(width);
                //tiles[j][i].initValue = ;
                /*if (tiles[i][j].initValue == tiles[i][j].getNum()){
                    tiles[i][j].select();
                } else {
                    tiles[i][j].deselect();
                }*/
                state.getBoard().getBoardSpace(i, j).draw(canvas);
                if(i == 7 && j == 7){
                    Paint center = new Paint();
                    center.setColor(0xFFFFFFFF);
                    canvas.drawCircle(state.getBoard().getBoardSpace(i, j).getCx(), state.getBoard().getBoardSpace(i, j).getCy(), state.getBoard().getBoardSpace(i, j).getWidth(), center);
                }
                counter++;
            }
        }
    }


    /**
     * Allows the program to interpret touch input
     *
     * @param view surfaceView that received the touch event, in this case GameSurfaceView
     * @param event touch event that stores all information related to the touch
     *
     * @return if the touch event was successful or not
     */
    /*
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            //get the coordinates of the touch input on the screen
            float x = event.getX();
            float y = event.getY();

            //find height and width of each tile
            int height = this.getHeight() / gameSize;
            int width = this.getWidth() / gameSize;

            //used to convert x,y coordinates to an array index
            int coordX = 0;
            int coordY = 0;

            //convert x,y coordinates to array indices
            for (int i = 1; i <= gameSize; i++) {
                if (x > width * i) {
                    coordX = i;
                }
            }
            for (int i = 1; i <= gameSize; i++) {
                if (y > height * i) {
                    coordY = i;
                }
            }

            //Call the swap tiles method if the touched
            //tile would result in a legal move
            if (verifyMove(coordX, coordY)){
                placeTile(tiles[coordX][coordY]);
            }
            //update the onDraw method
            invalidate();
            return true;
        }
        return false;
    }*/
    /**
     * Verifies if the move can be made
     *
     * @param x x index of the selected tile to be moved
     * @param y y index of the selected tile to be moved
     *
     * @return if the move is allowed
     */
    /*
    public boolean verifyMove(int x, int y){
        //above
        if (y>0){
            if (tiles[x][y-1].isEmpty){
                return true;
            }
        }
        //below
        if (y<gameSize - 1){
            if (tiles[x][y+1].isEmpty){
                return true;
            }
        }
        //left
        if (x > 0) {
            if (tiles[x -1][y].isEmpty){
                return true;
            }
        }
        //right
        if (x < gameSize - 1){
            if (tiles[x + 1][y].isEmpty){
                return true;
            }
        }

        return false;
    }*/
    /**
     * This method creates all the tile objects and stores them in a 2d array
     * It sets the last tile as the empty space on the board
     */
    /*
    public void createBoard() {
        tiles = new BoardSpace[gameSize][gameSize];

        //create (gameSize * gameSize) tiles for game
        int counter = 1;
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[j][i] = new BoardSpace(0, 0, 0, 0, counter);
                counter++;
            }
        }
        //tiles[gameSize - 1][gameSize - 1].setEmpty();
        //update the onDraw method
        invalidate();
    }*/

    /**
     * This method swaps the empty tile with the selected tile
     *
     * @param selectedTile Tile object that will be switched with the empty tile
     */
    public void placeTile(BoardSpace selectedTile) {
        selectedTile.select();
        //selectedTile.setNum(0);

    }

    public void setState(ScrabbleGameState s){
        state = s;
    }
}