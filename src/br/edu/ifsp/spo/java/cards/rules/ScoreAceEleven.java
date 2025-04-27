package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.items.Rank;

import java.util.List;

public class ScoreAceEleven implements Score {
    @Override
    public int calculateScore(List<Card> cards) {
        int score=0;

        for (Card card : cards){
            if (card.getRank()== Rank.ACE) {
                score += 11;
            }else{
                score += card.getRank().getRankValue();
            }
        }
        return score;
    }
}
