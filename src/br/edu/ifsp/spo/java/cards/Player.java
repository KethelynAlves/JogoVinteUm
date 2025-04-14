package br.edu.ifsp.spo.java.cards;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<Card> hand;
    private String name;

    public String getName() {
        return this.name;
    }

    public Player(String name){
        this.name = name;
    }

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








/*
* private String name;
    private List<Card> hand;
    private int score;


    public Player(String name){

        this.hand = new ArrayList<>();
        this.score = 0;
    }
    public void addCard(Card card){
        hand.add(card);
        updateScore();
    }

    private void updateScore() {
        int sum = 0;
        sum = score + card.getRank();

        }

    }
* */