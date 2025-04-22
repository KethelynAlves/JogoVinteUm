package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.items.Card;

import java.util.List;

public class ScoreAceEleven implements Score {
    @Override
    public int calculateScore(List<Card> cards) {
        int score=0;

        for (Card card : cards){

            score += card.getRank().getRankValue();
            if (card.getRank().getRankName()=="Ás"){
                score += 10;
            }
        }
        return score;
    }
}
