package br.edu.ifsp.spo.java.cards.core;

import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.items.Deck;

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