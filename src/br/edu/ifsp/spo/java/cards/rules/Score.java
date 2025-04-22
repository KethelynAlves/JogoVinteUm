package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.items.Card;

import java.util.List;
//import br.edu.ifsp.spo.java.cards.items.Card;

public interface Score {
    int calculateScore(List<Card> hand);
}
