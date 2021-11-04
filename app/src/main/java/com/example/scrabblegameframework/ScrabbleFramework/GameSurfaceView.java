package com.example.scrabblegameframework.ScrabbleFramework;

import android.content.Context;
import android.graphics.Canvas;
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

public class GameSurfaceView extends SurfaceView implements View.OnTouchListener, Button.OnClickListener {
    private int gameSize;
    private PuzzleTile tiles[][];
    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //set default gameSize
        gameSize = 4;
        //enable drawing
        setWillNotDraw(false);
        //create and shuffle the board
        createBoard();
        shuffle(tiles);
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
                tiles[i][j].setPos(width / 2 + (i * width), height / 2 + (j * height));
                tiles[i][j].setHeight(height);
                tiles[i][j].setWidth(width);
                tiles[j][i].initValue = counter;
                if (tiles[i][j].initValue == tiles[i][j].getNum()){
                    tiles[i][j].select();
                } else {
                    tiles[i][j].deselect();
                }
                tiles[i][j].draw(canvas);
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
                swapTiles(tiles[coordX][coordY]);
            }
            //update the onDraw method
            invalidate();
            return true;
        }
        return false;
    }
    /**
     * Verifies if the move can be made
     *
     * @param x x index of the selected tile to be moved
     * @param y y index of the selected tile to be moved
     *
     * @return if the move is allowed
     */
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
    }
    /**
     * onClick determined behavior for the buttons
     *
     * @param view button that received the touch event
     */
    @Override
    public void onClick(View view) {
       /* if (view.getId() == R.id.sizeUpButton) {
            if (gameSize < 9) {
                gameSize = gameSize + 1;
                createBoard();
                shuffle(tiles);
            }
        }
        if (view.getId() == R.id.sizeDownButton) {
            if (gameSize > 3) {
                gameSize = gameSize - 1;
                createBoard();
                shuffle(tiles);
            }
        }
        if (view.getId() == R.id.shuffleButton){
            shuffle(tiles);
        }*/
        invalidate();
    }
    /**
     * This method creates all the tile objects and stores them in a 2d array
     * It sets the last tile as the empty space on the board
     */
    public void createBoard() {
        tiles = new PuzzleTile[gameSize][gameSize];

        //create (gameSize * gameSize) tiles for game
        int counter = 1;
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[j][i] = new PuzzleTile(0, 0, 0, 0, counter);
                counter++;
            }
        }
        tiles[gameSize - 1][gameSize - 1].setEmpty();
        //update the onDraw method
        invalidate();
    }
    /**
     * This method swaps the empty tile with the selected tile
     *
     * @param selectedTile Tile object that will be switched with the empty tile
     */
    public void swapTiles(PuzzleTile selectedTile) {
        //find the empty tile
        PuzzleTile emptyTile = null;
        for (int i = 0; i < gameSize; i++){
            for (int j = 0; j < gameSize; j++){
                if (tiles[i][j].isEmpty){
                    emptyTile = tiles[i][j];
                }
            }
        }
        //swap the number and setEmpty between the empty tile and the selectedTile
        int num = selectedTile.getNum();
        selectedTile.setNum(emptyTile.getNum());
        emptyTile.setNum(num);

        selectedTile.setEmpty();
        emptyTile.setEmpty();

    }
    /**
     * Shuffles the Tile[][]
     * used from: https://stackoverflow.com/questions/20190110/2d-int-array-shuffle
     *
     * @param a Tile[][] that is to be shuffled
     */
    void shuffle(PuzzleTile[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                PuzzleTile temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }
    }
}