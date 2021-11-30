/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */
package com.example.scrabblegameframework.ScrabbleFramework;

import android.graphics.Canvas;
import android.graphics.Paint;

public class BoardSpace {
    //define variables
    private float cx, cy;
    private float height, width;
    private Tile tile;
    private int multiplier;
    private int border;
    private int tileColor;
    private int letterColor;

    public boolean isEmpty;
    public Tile initValue;
    /**
     * BoardSpace - constructor for the BoardSpace object
     * @param x left edge location for space
     * @param y top edge location for space
     * @param h height of space
     * @param w width of space
     * @param mult multiplier value of space
     */
    public BoardSpace(float x, float y, float h, float w, int mult){
        cx = x;
        cy = y;
        height = h;
        width = w;
        tile = null;
        multiplier = 0;
        border = 1;
        initValue = null;

        //set up the paint color
        tileColor = 0xFF690700;
        letterColor = 0xFF828282;
    }

    /**
     * BoardSpace - copy constructor
     * @param other BoardSpace object to be copied
     */
    public BoardSpace (BoardSpace other){
        cx = other.cx;
        cy = other.cy;
        height = other.height;
        width = other.width;
        if(other.tile == null){
            tile = null;
        }
        else {
            tile = new Tile(other.tile);
        }
        multiplier = other.multiplier;
        border = other.border;

        tileColor = other.tileColor;
        letterColor = other.letterColor;
    }

    /**
     * draw - draws each space onto the board
     * @param canvas canvas to draw
     */
    public void draw(Canvas canvas){
        Paint toDraw = new Paint();
        toDraw.setColor(letterColor);
        canvas.drawRect((cx - width/2), (cy - height/2), (cx + width/2), (cy + height/2), toDraw);
        toDraw.setColor(tileColor);
        canvas.drawRect((cx - width/2) + border, (cy - height/2) + border, (cx + width/2) - border, (cy + height/2) - border, toDraw);
        if((cx > (7 * width)) && (cx < (8 * width)) && (cy > (7 * height) && cy < (8 * height))  && tile == null){
            Paint center = new Paint();
            center.setColor(0xFFFF160C);
            canvas.drawCircle(cx, cy, width/4, center);
        }
        if(tile != null) {
            toDraw.setColor(letterColor);
            toDraw.setTextSize(40);
            canvas.drawText("" + tile.getLetter(), (cx) + border - width /7 - 5, (cy) - border + width/ 7 + 5, toDraw);
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
     * getTile - gets the tile in the space
     * @return tile
     */
    public Tile getTile(){
        return tile;
    }
    /**
     * setTile - sets the tile of the space
     * @param tile to set to
     */
    public void setTile(Tile tile) {
        this.tile = tile;
    }
    public void setMultiplier(int m){
        multiplier = m;
    }
    public void select(){
        if (!isEmpty) {
            tileColor = 0xFFD6A833;
            letterColor = 0xFF000000;
            //255, 214, 168, 51
        }
    }
    /**
     * equals - overwrites equals method
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BoardSpace)) {
            return false;
        }
        BoardSpace space = (BoardSpace) object;
        if(!(space.tile == null)) {
            if (!(space.tile.equals(tile))) {
                return false;
            }
        }
        if(space.multiplier != multiplier){
            return false;
        }
        return true;
    }


    public void setColor(int tile, int letter){

        //Added by Harrison to fix board being black
        //tileColor.setColor(tile);
        //letterColor.setColor(letter);
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public int getMultiplier() {
        return multiplier;
    }
}