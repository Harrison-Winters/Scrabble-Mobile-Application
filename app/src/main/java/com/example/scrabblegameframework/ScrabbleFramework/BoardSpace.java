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
    private Paint tileColor;
    private Paint letterColor;

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
        multiplier = mult;
        border = 10;

        //set up Paint Colors
        tileColor = new Paint();
        tileColor.setARGB(255, 255, 211, 181);
        letterColor = new Paint();
        letterColor.setARGB(255, 0,0,0);
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

        tileColor = new Paint(other.tileColor);
        letterColor = new Paint(other.letterColor);
    }

    /**
     * draw - draws each space onto the board
     * @param canvas canvas to draw
     */
    public void draw(Canvas canvas){
        if (tile != null){
            canvas.drawRect((cx - width/2) + border,
                    (cy - height/2) + border,
                    (cx + width/2) - border,
                    (cy + height/2) - border,
                    tileColor);
        }
    }

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
        if(!(space.tile.equals(tile))){
            return false;
        }
        if(space.multiplier != multiplier){
            return false;
        }
        return true;
    }
}