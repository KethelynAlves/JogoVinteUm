package br.edu.ifsp.spo.java.cards;

import java.util.List;
//import br.edu.ifsp.spo.java.cards.items.Card;

public interface Scorer {
    int calculateScore(List<Card> hand);
}
