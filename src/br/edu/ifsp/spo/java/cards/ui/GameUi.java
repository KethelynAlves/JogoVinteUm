package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.core.PlayerAction;
import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.rules.Score;

import java.util.List;

public interface GameUi {
    String requestPlayerName(int playerNumber);
    Score requestGameMode();
    void renderHand(List<Card> cards, int score);
    PlayerAction requestAction();
}
