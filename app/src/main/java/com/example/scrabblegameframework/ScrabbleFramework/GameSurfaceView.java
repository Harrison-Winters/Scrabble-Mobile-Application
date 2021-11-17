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
    private int border;
    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //set default gameSize
        gameSize = 15;
        //enable drawing
        border = 1;
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
                BoardSpace boardSpace = state.getBoard().getBoardSpace(i, j);
                boardSpace.setPos(width / 2 + (i * width), height / 2 + (j * height));
                boardSpace.setHeight(height);
                boardSpace.setWidth(width);
                boardSpace.draw(canvas);
                //Draw the circle in the center of the board
                Paint wordScorePaint = new Paint();
                wordScorePaint.setColor(0xFFFF0000);
                wordScorePaint.setTextSize(40);
                Paint letterScorePaint = new Paint();
                letterScorePaint.setColor(0xFF0000FF);
                letterScorePaint.setTextSize(40);
                if (boardSpace.getTile() == null) {
                if(i == 7 && j == 7){
                    float circleSize;
                    if (boardSpace.getWidth() > boardSpace.getHeight()){
                        circleSize = boardSpace.getHeight();
                    } else {
                        circleSize = boardSpace.getWidth();
                    }

                        canvas.drawCircle(boardSpace.getCx(), boardSpace.getCy(), circleSize / 2, wordScorePaint);

                }
                if (    i==0 && j==0 ||
                        i==7 && j==0 ||
                        i==14 && j==0 ||
                        i==0 && j==7 ||
                        i== 14 && j==7||
                        i==0 && j==14||
                        i==7 && j==14||
                        i==14 && j==14
                ){
                    canvas.drawText("x3", (boardSpace.getCx()) + border - width /7, (boardSpace.getCy()) - border + width/ 7, wordScorePaint);
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
                    canvas.drawText("x2", (boardSpace.getCx()) + border - width /7, (boardSpace.getCy()) - border + width/ 7, wordScorePaint);
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
                    canvas.drawText("x3", (boardSpace.getCx()) + border - width /7, (boardSpace.getCy()) - border + width/ 7, letterScorePaint);
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
                    canvas.drawText("x2", (boardSpace.getCx()) + border - width /7, (boardSpace.getCy()) - border + width/ 7, letterScorePaint);
                }
                }
            }
        }
    }

    public void setState(ScrabbleGameState s){
        state = s;
    }
}