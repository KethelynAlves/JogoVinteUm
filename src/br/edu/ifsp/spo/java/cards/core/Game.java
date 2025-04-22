package br.edu.ifsp.spo.java.cards.core;


import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.items.Rank;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreAceEleven;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;
import br.edu.ifsp.spo.java.cards.ui.TerminalGameUi;

import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score;
    private final TerminalGameUi terminal = new TerminalGameUi();
    private final ArrayList<String> playerNames = new ArrayList<>();

    public void starGame(){
        for (int i = 0; i<2; i++) {
            String name = terminal.requestPlayerName(i+1);
            playerNames.add(name);
        }
        int mode = terminal.requestGameMode();
        if (mode == 1){
            score = new ScoreBasic();
        } else {
            score = new ScoreAceEleven();
        }
        this.deck = new Deck();
        player1 = new Player(deck);
        player1.receiveCard(deck);
        int score1 = score.calculateScore(player1.hand);

        player2 = new  Player(deck);
        player2.receiveCard(deck);
        int score2 = score.calculateScore(player2.hand);
        System.out.println("-Player "+ playerNames.get(0) +" tem as cartas: " + player1.hand +" : Pontos " + score1);
        System.out.println("-Player "+ playerNames.get(1) +" tem as cartas: " + player2.hand +" : Pontos " + score2);

        System.out.println("\n\n\nVencedor é...");
        if (score1>score2 && score1<=21){
            System.out.println(playerNames.get(0)+"!");
        } else if (score1>21){
            System.out.println(playerNames.get(0) + " estourou a mesa.\n" + playerNames.get(1) + " venceu!");
        } else if(score2>score1 && score2<=21){
            System.out.println(playerNames.get(1));
        } else if (score2>21) {
            System.out.println(playerNames.get(1) + " estourou a mesa.\n" + playerNames.get(0) + " venceu!");
        }
        if (score1==score2){
            System.out.println("Empate.");
        }
        System.out.println("\nSobraram: "+ deck.remainingCards() +" cartas.");

    }
}
