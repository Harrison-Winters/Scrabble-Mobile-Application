package com.example.tttgameframework;

/*
 * @author Cory Marleau, Harrison Winters, Kamalii Silva, Jason Katayama
 *
 * */
import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.infoMessage.GameInfo;
import com.example.tttgameframework.GameFramework.players.GameHumanPlayer;
import com.example.tttgameframework.GameFramework.players.GamePlayer;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Tile> deck = new ArrayList<Tile>();
    private int score;

    public Player(String n) {
        name = n;
    }

    public void setDeck(Tile t) {
        if (deck.size() >= 7) {
            return;
        }
        deck.add(t);
    }

    public void removeFromDeck(Tile t) {
        deck.remove(t);

    }

    public ArrayList<Tile> getDeck() {
        return deck;
    }


    @Override
    public String toString() {
        String toReturn = new String("");
        for (int i = 0; i < deck.size(); i++) {
            toReturn = toReturn + "-" + deck.get(i).getName();
        }
        return toReturn;
    }
}


