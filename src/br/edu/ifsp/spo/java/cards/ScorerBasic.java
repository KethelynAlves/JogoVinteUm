package br.edu.ifsp.spo.java.cards;

import java.util.List;

public class ScorerBasic implements Scorer {
    @Override
    public int calculateScore(List<Card> hand) {
        int scorer=0;

        for (Card card : hand){
            scorer += card.getRank().getRankValue();
        }
        return scorer;
    }
}
