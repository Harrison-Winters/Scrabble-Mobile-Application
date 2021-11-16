package com.example.scrabblegameframework;

import org.junit.Test;

import static org.junit.Assert.*;

import android.graphics.Point;

import com.example.scrabblegameframework.ScrabbleFramework.ScrabbleGameState;
import com.example.scrabblegameframework.ScrabbleFramework.ScrabbleLocalGame;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void CopyState(){
        ScrabbleGameState gameState = new ScrabbleGameState(2);
        ScrabbleGameState copyState = new ScrabbleGameState(gameState);
        assertTrue("Copy Constructor did not produce equal States", gameState.equals(copyState));
    }

}