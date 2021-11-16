package com.example.scrabblegameframework.ScrabbleFramework;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.Test;

public class ScrabbleGameStateTest extends TestCase {

    //Harrison Winters
    public void testSelect() {
        ScrabbleGameState testState = new ScrabbleGameState(2);

        testState.getPlayer(0).selectDeck(3);
        boolean test1 = testState.select(0, 3);

        assertEquals(true, test1);


        testState.select(0, 5);
        boolean test2 = testState.getPlayer(0).isSelected(5);
        assertEquals(true, test2);


        testState.select(0, 6);
        boolean test3 = testState.getPlayer(0).isSelected(1);
        assertEquals(false, test3);

    }

    public void testDrawRandLetter() {
    }

    public void testDrawLetter() {
    }

    public void testPlaceLetter() {

    }

    public void testClear() {
    }

    //Jason Katayama
    public void testExchangeLetters() {
        ScrabbleGameState gameState = new ScrabbleGameState(2);
        Tile testB = gameState.getPlayer(0).getTile(1);
        System.out.println(testB);
        gameState.select(0,1);
        gameState.exchangeLetters(0);
        Tile testA = gameState.getPlayer(0).getTile(1);
        System.out.println(testA);
        assertTrue("not the same",!(testA.equals(testB)));
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