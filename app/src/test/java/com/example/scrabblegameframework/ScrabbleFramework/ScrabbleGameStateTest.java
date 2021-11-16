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
    //Cory Marleau
    public void testPlaceLetter() {
        Board b = new Board();
        Tile t = new Tile("b", 4);
        b.addToBoard(t, 7, 7);

        assertTrue(b.getBoardSpace(7,7).getTile().getLetter() == "b");
    }

    public void testClear() {
    }

    //Jason Katayama
    //Verifies if the exchange works with one letter being exchanged
    public void testExchangeLetters() {
        ScrabbleGameState gameState = new ScrabbleGameState(2);
        Tile testB = gameState.getPlayer(0).getTile(1);
        Tile testB2 = gameState.getPlayer(0).getTile(1);

        assertTrue("Same tiles",testB2.equals(testB));

        gameState.select(0,1);
        gameState.exchangeLetters(0);
        Tile testA = gameState.getPlayer(0).getTile(1);

        assertTrue("not the same",!(testA.equals(testB)));
    }

    //Jason Katayama
    //Verifies if the exchange works with half letters being exchanged
    public void testExchangeLettersHalf() {
        ScrabbleGameState gameState = new ScrabbleGameState(2);
        Tile[] test = new Tile[4];
        test[0] = gameState.getPlayer(0).getTile(0);
        test[1] = gameState.getPlayer(0).getTile(1);
        test[2] = gameState.getPlayer(0).getTile(2);
        test[3] = gameState.getPlayer(0).getTile(3);


        gameState.select(0,0);
        gameState.select(0,1);
        gameState.select(0,2);
        gameState.select(0,3);
        gameState.exchangeLetters(0);

        Tile[] afterTest = new Tile[4];
        afterTest[0] = gameState.getPlayer(0).getTile(0);
        afterTest[1] = gameState.getPlayer(0).getTile(1);
        afterTest[2] = gameState.getPlayer(0).getTile(2);
        afterTest[3] = gameState.getPlayer(0).getTile(3);

        //Testing to see if the array of the tiles has been changed
        assertTrue("Tiles are not the same", !(test.equals(afterTest)));
    }

    //Jason Katayama
    //Verifies if the exchange works with all letters being exchanged
    public void testExchangeLettersFull() {
        ScrabbleGameState gameState = new ScrabbleGameState(2);
        Tile[] test = new Tile[7];
        test[0] = gameState.getPlayer(0).getTile(0);
        test[1] = gameState.getPlayer(0).getTile(1);
        test[2] = gameState.getPlayer(0).getTile(2);
        test[3] = gameState.getPlayer(0).getTile(3);
        test[4] = gameState.getPlayer(0).getTile(4);
        test[5] = gameState.getPlayer(0).getTile(5);
        test[6] = gameState.getPlayer(0).getTile(6);

        gameState.select(0,0);
        gameState.select(0,1);
        gameState.select(0,2);
        gameState.select(0,3);
        gameState.select(0,4);
        gameState.select(0,5);
        gameState.select(0,6);
        gameState.exchangeLetters(0);

        Tile[] afterTest = new Tile[7];
        afterTest[0] = gameState.getPlayer(0).getTile(0);
        afterTest[1] = gameState.getPlayer(0).getTile(1);
        afterTest[2] = gameState.getPlayer(0).getTile(2);
        afterTest[3] = gameState.getPlayer(0).getTile(3);
        afterTest[4] = gameState.getPlayer(0).getTile(4);
        afterTest[5] = gameState.getPlayer(0).getTile(5);
        afterTest[6] = gameState.getPlayer(0).getTile(6);

        assertTrue("Tiles are not the same", !(test.equals(afterTest)));
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
    //Cory Marleau
    public void test_Tile_Equals(){
        Tile tile = new Tile("b", 4);
        Tile anotherTile = new Tile("b", 4);
        assertTrue(tile.equals(anotherTile));
    }
    //Cory Marleau
    public void test_set_Tile(){
        Tile tile = new Tile("b", 4);
        assertTrue(tile.getLetter() == "b");
        assertTrue(tile.getPoints() == 4);
    }

}