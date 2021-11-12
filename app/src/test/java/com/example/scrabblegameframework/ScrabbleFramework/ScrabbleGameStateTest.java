package com.example.scrabblegameframework.ScrabbleFramework;

import junit.framework.TestCase;

public class ScrabbleGameStateTest extends TestCase {

    public void testSelect() {

    }

    public void testDrawRandLetter() {
    }

    public void testDrawLetter() {
    }

    public void testPlaceLetter() {
    }

    public void testClear() {
    }

    public void testExchangeLetters() {
    }

    public void testDisplayRules() {
    }

    public void testEndTurn() {
        ScrabbleGameState testState = new ScrabbleGameState(2);
        testState.endTurn(testState.getCurrPlayerTurn());
        assertEquals(1, testState.getCurrPlayerTurn());
    }

    public void testTestToString() {
    }

    public void testTestEquals() {
    }

    public void testGetPlayerTurn() {
        ScrabbleGameState testState = new ScrabbleGameState(2);
        int turn = testState.getCurrPlayerTurn();
        assertEquals(0, turn);
    }

    public void testGetPlayer() {
    }

    public void testGetSelected() {
    }

    public void testGetBoard() {
    }
}