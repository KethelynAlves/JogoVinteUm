package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.core.Player;
import br.edu.ifsp.spo.java.cards.core.PlayerAction;
import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.rules.Score;

import java.util.List;

public interface GameUi {
    String requestPlayerName(int playerNumber);

    Score requestGameMode();

    void renderGameStart();

    void renderStartTurn(String playerName);

    void renderHand(List<Card> hand, int score);

    PlayerAction requestAction();

    void renderBusted(String name);

    void renderEndTurn(String name);

    void renderBlackjack(String name);

    void renderWinner(Player winner);

    void renderPoints(int playerScore);

    int selectRounds();

    void renderPoints2(Player player1, Player player2, int pontos1, int pontos2);

    void renderWinner2(Player player);
}
