package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Card> hand;

    public void receiveCard(Deck deck){
        this.hand.add(deck.drawCard());
    }

    public Player(Deck deck){
        this.hand = new ArrayList<>();
        for (int i=0; i<1; i++){
            this.receiveCard(deck);
        }
    }

}