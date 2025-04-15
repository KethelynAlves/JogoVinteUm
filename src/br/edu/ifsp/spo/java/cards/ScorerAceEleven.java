package br.edu.ifsp.spo.java.cards;

import java.util.List;

public class ScorerAceEleven implements Scorer{
    @Override
    public int calculateScore(List<Card> cards) {
        int scorer=0;

        for (Card card : cards){

            scorer += card.getRank().getRankValue();
            if (card.getRank().getRankName()=="Ás"){
                scorer += 10;
            }
        }
        return scorer;
    }
}
