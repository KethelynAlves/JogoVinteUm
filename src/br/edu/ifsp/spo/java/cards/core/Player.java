package br.edu.ifsp.spo.java.cards.core;

import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.rules.Score;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public String name;
    public List<Card> hand;
    public int points;

    public Player(String name){
        this.hand = new ArrayList<>();
        this.name = name;
        this.points = 0;
    }

    @Override
    public String toString() {
        var result = "Player: " + this.getName();
        result += "\n- Mão atual: ";
        for (var card : this.hand) {
            result += "\n-- " + card.toString();
        }
        return result;
    }

    public void receiveCard(Card card){
        this.hand.add(card);
    }
    public String getName(){return this.name;}
    public List<Card> getHand() {return this.hand;}

    public List<Card> discardHand(){
        var discrdedCards = new ArrayList<>(this.hand);
        this.hand.clear();
        return discrdedCards;
    }
}