package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.items.Card;

import java.util.List;

public class ScoreBasic implements Score {
    @Override
    public int calculateScore(List<Card> hand) {
        int score=0;

        for (Card card : hand){
            score += card.getRank().getRankValue();
        }
        return score;
    }
}
