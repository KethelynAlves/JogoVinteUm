package br.edu.ifsp.spo.java.cards.items;

public enum Rank {
    ACE("Ás", 1), TWO("Dois", 2), THREE("Três",3), FOUR("Quatro", 4), FIVE("Cinco", 5),
    SIX("Seis",6), SEVEN("Sete", 7), EIGHT("Oito", 8), NINE("Nove", 9),
    TEN("Dez", 10), JACK("Valete", 10),
    QUEEN("Rainha",10), KING("Rei", 10);

    private String rankName;
    private int rankValue;

    private Rank(String rankName, int rankValue){
        this.rankName = rankName;
        this.rankValue = rankValue;
    }

    public String getRankName(){
        return rankName;
    }

    public int getRankValue(){
        return rankValue;
    }

    @Override
    public String toString(){
        return this.rankName;
    }
}
