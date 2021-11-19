package com.example.scrabblegameframework.ScrabbleFramework;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.HashMap;

public class ScrabbleGameStateTest extends TestCase {

    //Harrison Winters
    public void testSelect() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState testState = new ScrabbleGameState(2, dictionary, 0,0);

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
    public void testCopy(){
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState gameState = new ScrabbleGameState(2,dictionary, 0,0);
        ScrabbleGameState copyState = new ScrabbleGameState(gameState);
        assertTrue("board is the same",gameState.equals(copyState));
    }

    //Jason Katayama
    public void testCopyAddOne(){
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState gameState = new ScrabbleGameState(2,dictionary,0,0);
        gameState.select(0,1);
        gameState.placeLetter(0,7,7);
        gameState.endTurn(0);
        ScrabbleGameState copyState = new ScrabbleGameState(gameState);
        assertTrue("board is the same",gameState.equals(copyState));
    }

    //Jason Katayama
    public void testCopyAdd(){
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState gameState = new ScrabbleGameState(2,dictionary, 0,0);
        gameState.select(0,1);
        gameState.placeLetter(0,7,7);
        gameState.select(0,2);
        gameState.placeLetter(0,7,8);
        gameState.endTurn(0);
        ScrabbleGameState copyState = new ScrabbleGameState(gameState);
        assertTrue("board is the same",gameState.equals(copyState));
    }

    //Jason and Kama
    public void testExchangedState(){
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState gameState = new ScrabbleGameState(2,dictionary, 0,0);
        gameState.select(0,1);
        gameState.select(0,4);
        gameState.select(0,6);
        gameState.exchangeLetters(0);
        ScrabbleGameState copyState = new ScrabbleGameState(gameState);
        assertTrue("board is the same",gameState.equals(copyState));
    }


    //Jason Katayama
    //Verifies if the exchange works with one letter being exchanged
    public void testExchangeLetters() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState gameState = new ScrabbleGameState(2, dictionary, 0,0);
        Tile testB = gameState.getPlayer(0).getTile(1);
        Tile testB2 = gameState.getPlayer(0).getTile(1);

        assertTrue("Same tiles",testB2.equals(testB));

        gameState.select(0,1);
        gameState.exchangeLetters(0);
        Tile testA = gameState.getPlayer(0).getTile(1);

        assertTrue("not the same",!(testA.equals(testB)));
    }


    public void testDisplayRules() {
    }

    public void testEndTurn() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState testState = new ScrabbleGameState(2, dictionary, 0,0);
        testState.endTurn(testState.getCurrPlayerTurn());
        assertEquals(1, testState.getCurrPlayerTurn());
    }

    public void testTestToString() {
    }

    public void testTestEquals() {
    }

    public void testGetPlayerTurn() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState testState = new ScrabbleGameState(2,dictionary, 0,0);
        int turn = testState.getCurrPlayerTurn();
        assertEquals(0, turn);
        testState.endTurn(turn);
        turn = testState.getCurrPlayerTurn();
        assertEquals(1, turn);
        testState.endTurn(turn);
        turn = testState.getCurrPlayerTurn();
        assertEquals(0, turn);
    }

    //Harrison Winters
    public void testGetPlayer() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        ScrabbleGameState testState = new ScrabbleGameState(2, dictionary, 0,0);
        Player player1 = testState.getPlayer(0);
        Player player1Test = testState.getPlayerList().get(0);
        boolean isEqual1 =  player1.equals(player1Test);
        assertTrue(isEqual1);



        Player player2 = testState.getPlayer(1);
        Player player2Test = testState.getPlayerList().get(1);
        boolean isEqual2 =  player2.equals(player2Test);
        assertTrue(isEqual2);
    }

    //Harrison Winters
    public void testGetSelected() {
        HashMap<String, Boolean> dictionary = new HashMap<>();
    ScrabbleGameState testState = new ScrabbleGameState(2, dictionary, 0,0);
    Player player1 = testState.getPlayer(0);

    player1.selectDeck(3);
    int idxSelected = player1.getSelected().get(0);

    assertEquals(3, idxSelected);


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