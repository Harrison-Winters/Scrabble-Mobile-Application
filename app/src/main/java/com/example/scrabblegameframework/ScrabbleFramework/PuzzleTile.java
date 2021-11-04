package com.example.scrabblegameframework.ScrabbleFramework;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Tile: Tile Object to be drawn on the GameSurfaceView
 *
 * @author Cory Marleau
 * @version September 26 2021
 */
public class PuzzleTile {
    //define variables
    private float cx, cy;
    private float height, width;
    private int value;
    private int border;
    private Paint primaryColor;
    private Paint secondaryColor;

    public boolean isEmpty;
    public boolean isSelected;
    public int initValue;

    public PuzzleTile(float x, float y, float h, float w, int num){
        //initialize variables
        cx = x;
        cy = y;
        height = h;
        width = w;
        value = num;
        border = 10;
        isSelected = false;
        isEmpty = false;
        initValue = 0;

        //set up the paint color
        primaryColor = new Paint();
        primaryColor.setARGB(255,105,7,0);
        secondaryColor = new Paint();
        secondaryColor.setARGB(255,130,130,130);
        secondaryColor.setTextSize(100);

    }
    /**
     * draws the tile object
     *
     * @param canvas canvas that the tile will be drawn upon
     */
    public void draw(Canvas canvas) {
        canvas.drawRect((cx - width/2), (cy - height/2), (cx + width/2), (cy + height/2), secondaryColor);
        canvas.drawRect((cx - width/2) + border, (cy - height/2) + border, (cx + width/2) - border, (cy + height/2) - border, primaryColor);
        canvas.drawText(""+ value, (cx - width/2) + border, (cy + height/2) - border, secondaryColor);
    }
    /**
     * Changes the color of the tile from red to yellow
     */
    public void select(){
        if (!isEmpty) {
            primaryColor.setARGB(255, 214, 168, 51);
            isSelected = true;
        }
    }
    /**
     * Changes the color of the tile from yellow to red
     * */
    public void deselect(){
        if (!isEmpty){
            primaryColor.setARGB(255, 105, 7, 0);
            isSelected = false;
        }
    }
    /**
     * Manually sets the position of the Tile
     * */
    public void setPos(float x, float y){
        cx = x;
        cy = y;
    }
    /**
     * Gets the value of the tile
     * */
    public int getNum(){
        return value;
    };
    /**
     * Sets the value of the tile
     * */
    public void setNum(int num){
        value = num;
    };
    /**
     * Sets the height of the tile
     * */
    public void setHeight(float h){
        height = h;
    };
    /**
     * Sets the width of the tile
     * */
    public void setWidth(float w){
        width = w;
    };
    /**
     * Toggles the color of the tile between its normal colors and black
     * */
    public void setEmpty() {
        if (!isEmpty) {
            primaryColor.setARGB(0, 0, 0, 0);
            secondaryColor.setARGB(0, 0, 0, 0);
            isEmpty = true;
        } else {
            primaryColor.setARGB(255,105,7,0);
            secondaryColor.setARGB(255,130,130,130);
            isEmpty = false;
        }
    }
}
