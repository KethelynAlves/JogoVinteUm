package br.edu.ifsp.spo.java.cards;

import java.util.List;

public class Score {

    public int calcValue(List<Card> hand){
        int score=0;

        for (Card card : hand){
            score += card.getRank().getRankValue();
        }
        return score;
    }

}
