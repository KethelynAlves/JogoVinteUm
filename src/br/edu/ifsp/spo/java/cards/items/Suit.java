package br.edu.ifsp.spo.java.cards.items;

public enum Suit {
    HEARTS("Copas"), DIAMONDS("Ouros"), CLUBS("Paus"), SPADES("Espadas");


    private String suitName;

    public String getSuitName() {
        return suitName;
    }

    private Suit(String suitName){
        this.suitName = suitName;
    }

    @Override
    public String toString(){
        return this.suitName;
    }

}
