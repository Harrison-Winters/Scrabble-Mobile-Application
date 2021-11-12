package com.example.scrabblegameframework.ScrabbleFramework;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.Test;

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

    @Test
    public void test_Tile_Equals(){
        Tile tile = new Tile("b", 4);
        Tile anotherTile = new Tile("b", 4);
        assertTrue(tile.equals(anotherTile));
    }
    public void test_BoardSpace_Equals(){
        BoardSpace space = new BoardSpace(0,0,10,50, 3);
        BoardSpace anotherSpace = new BoardSpace(0,0,10,50,3);
        assertTrue(space.getTile() == anotherSpace.getTile());
    }


}