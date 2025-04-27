package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.core.PlayerAction;
import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreAceEleven;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalGameUi implements GameUi {

    @Override
    public String requestPlayerName(int playerNumber) {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o nome do jogador " + playerNumber + ": ");
        return scanner.nextLine();
    }

    @Override
    public Score requestGameMode() {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o modo de jogo: \n1)Ace valendo '1' pontos\n2)Ace valendo '11' pontos");
        int mode = scanner.nextInt();
        //return scanner.nextInt();
        Score score;
        switch (mode) {
            case 1 -> score = new ScoreBasic();
            case 2 -> score = new ScoreAceEleven();
            default -> score = new ScoreBasic();
        }
        return score;
    }

    @Override
    public void renderHand(List<Card> cards, int score) {

    }

    @Override
    public PlayerAction requestAction() {
        return null;
    }

}
