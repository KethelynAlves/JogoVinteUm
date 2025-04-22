package br.edu.ifsp.spo.java.cards.core;


import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.items.Rank;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreAceEleven;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;
import br.edu.ifsp.spo.java.cards.ui.GameUi;
import br.edu.ifsp.spo.java.cards.ui.TerminalGameUi;

import java.util.ArrayList;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score;
    //private final TerminalGameUi terminal = new TerminalGameUi();
    //private final ArrayList<String> playerNames = new ArrayList<>();
    private GameUi ui;

    public Game(GameUi gameUi){
        this.ui = gameUi;
        this.initialize();
    }

    private void initialize(){
        /*for (int i = 0; i<2; i++) {
            String name = ui.requestPlayerName(i+1);
            playerNames.add(name);
        }*/

        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new Player(ui.requestPlayerName(2));

        this.score = new ScoreBasic();
        this.score = ui.requestGameMode();

        this.deck = new Deck();

        this.player1.receiveCard(this.deck.drawCard());
        this.player1.receiveCard(this.deck.drawCard());

        int score1 = score.calculateScore(player1.hand);

        this.player2.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());

        int score2 = score.calculateScore(player2.hand);
        System.out.println(this.player1.toString() +"\nPontos " + score1); //mão do jogador e pontuação atual
        System.out.println(this.player2.toString() +"\nPontos " + score2);

        System.out.println("\n\nVencedor é...");
        if (score1>score2 && score1<=21){
            System.out.println(this.player1+"!");
        } else if (score1>21){
            System.out.println(this.player1+ " estourou a mesa.\n" + this.player2 + " venceu!");
        } else if(score2>score1 && score2<=21){
            System.out.println(this.player2);
        } else if (score2>21) {
            System.out.println(this.player2 + " estourou a mesa.\n" + this.player1 + " venceu!");
        }
        if (score1==score2){
            System.out.println("Empate.");
        }

        System.out.println("\nSobraram: "+ deck.remainingCards() +" cartas.");
    }

    public void Play(){

    }

    @Override
    public String toString(){
        var result = "Game - 21!";

        result += "\n\nPlayers:\n";
        result += "\n" + this.player1.toString();
        result += "\nPontuação do jogador 1: " + this.score.calculateScore(this.player1.hand);
        result += "\n" + this.player2.toString();
        result += "\n\nRemaining cards:" + this.deck.remainingCards();
        result += "\nPontuação do jogador 2: " + this.score.calculateScore(this.player2.hand);

        return result;
    }
}
/**/