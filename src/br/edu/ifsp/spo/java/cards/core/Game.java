package br.edu.ifsp.spo.java.cards.core;


import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;
import br.edu.ifsp.spo.java.cards.ui.GameUi;

import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score;
    private final GameUi ui;

    public Game(GameUi gameUi){
        this.ui = gameUi;
        this.initialize();
    }

    public void PlayMatch(){
        var scanner = new Scanner(System.in);
        System.out.println("Quantas rodadas quer ter?");
        int numRodadas = scanner.nextInt();



    }

    private void initialize(){

        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new Player(ui.requestPlayerName(2));

        this.score = new ScoreBasic();
        this.score = ui.requestGameMode();

        this.deck = new Deck();

        this.player1.receiveCard(this.deck.drawCard());
        this.player1.receiveCard(this.deck.drawCard());

        this.player2.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }

    public void Play(){
        System.out.println("\n\n");
        int score1 = score.calculateScore(player1.hand);
        int score2 = score.calculateScore(player2.hand);

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println(this.player1.toString() +"\nPontos " + score1); //mão do jogador e pontuação atual
            System.out.println("Deseja comprar uma carta player "+ player1.name+" (HIT or PASS)?");
            String resposta = scanner.nextLine().toUpperCase();
            PlayerAction acao = PlayerAction.valueOf(resposta);

            if (acao == PlayerAction.HIT){
                System.out.println("Você comprou uma carta.\n");
                this.player1.receiveCard(this.deck.drawCard());
                score1 = score.calculateScore(player1.hand);
            } else if (acao == PlayerAction.PASS){
                System.out.println("Você passou a vez.\n");
                break;
            }
        }

        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println(this.player2.toString() +"\nPontos " + score2);
            System.out.println("Deseja comprar uma carta player "+ player2.name+" (HIT or PASS)?");
            String resposta = scanner.nextLine().toUpperCase();
            PlayerAction acao = PlayerAction.valueOf(resposta);

            if (acao == PlayerAction.HIT){
                System.out.println("Você comprou uma carta.\n");
                this.player2.receiveCard(this.deck.drawCard());
                score2 = score.calculateScore(player2.hand);

            } else if (acao == PlayerAction.PASS){
                System.out.println("Você passou a vez.\n");
                break;
            }
        }

        System.out.println("\n\nResultado é...");

        if (score1>21 && score2>21){//ambos estouraram a mesa
            System.out.println("Ambos estouraram a mesa. Empate!");
        } else if (score1==score2){//iguais
            System.out.println("Empate.");
        } else if (score1>score2 && score1<=21){//maior que o oponente e menor que 21
            System.out.println(this.player1.name + " ganhou.");
        } else if(score2>score1 && score2<=21){//maior que o oponente e menor que 21
            System.out.println(this.player2.name + " ganhou.");
        }

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
