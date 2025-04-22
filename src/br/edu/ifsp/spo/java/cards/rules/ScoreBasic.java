package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.items.Card;

import java.util.List;

public class ScoreBasic implements Score {
    @Override
    public int calculateScore(List<Card> hand) {
        int scorer=0;

        for (Card card : hand){
            scorer += card.getRank().getRankValue();
        }
        return scorer;
    }
}
