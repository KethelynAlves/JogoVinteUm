package br.edu.ifsp.spo.java.cards.core;


import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;
import br.edu.ifsp.spo.java.cards.rules.calculatePoints;
import br.edu.ifsp.spo.java.cards.ui.GameUi;

import java.util.Optional;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score;
    private final GameUi ui;

    private calculatePoints calculatePoints;

    public Game(GameUi gameUi){
        this.ui = gameUi;
        this.initialize();
        //this.calculatePoints = new calculatePoints();
    }

    private void initialize(){

        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new PlayerIA();

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
        Optional<Player> winner = Optional.empty();

        while (winner.isEmpty()){
            ui.renderGameStart();

            executeTurn(player1);
            executeTurn(player2);

            winner = this.resolveWinner();

            if (winner.isPresent()){//apresenta o resultado
                ui.renderWinner(winner.get());
            } else { //ciclo reiniciado
                this.restart();
            }

        }
    }


//    public void PlayMatch(){
//        var scanner = new Scanner(System.in);
//        System.out.println("Quantas rodadas quer ter?");
//        int numRodadas = scanner.nextInt();
//
//        for (int i = 0; i<numRodadas; i++){
//            System.out.println("Esta é a "+i+1+"° rodada");
//            this.initialize();
//            this.Play();
//
//            int winner = this.checkWinner();
//            if (winner == 0){
//                player1.points+=(player1.points-21);
//                player2.points+=(player2.points-21);
//            }
//        }
//    }

    public void restart(){
        deck.addToDiscartPile(this.player1.discardHand());
        deck.addToDiscartPile(this.player2.discardHand());

        this.player1.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());

        this.player1.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }

//    private int checkWinner() {
//        int score1 = score.calculateScore(player1.hand);
//        int score2 = score.calculateScore(player2.hand);
//
//        if (score1>21 && score2>21){
//            return 0;
//        } else if (score1==score2){
//            return 1;
//        } else if (score1>score2 && score1<=21){
//            return 2;
//        } else if(score2>score1 && score2<=21){
//            return 3;
//        }
//        return 0;
//    }

    public void executeTurn(Player player) {
        ui.renderStartTurn(player.getName());

        PlayerAction action = PlayerAction.PASS;

        do {
            var currentScore = this.score.calculateScore(player.getHand());
            if (player instanceof PlayerIA) {
                var ia = (PlayerIA) player;
                action = ia.makeDecision(currentScore);
                if (action == PlayerAction.HIT) {
                    ia.receiveCard(this.deck.drawCard());
                } else {
                    ui.renderHand(player.getHand(), currentScore);
                    action = getAction(currentScore);

                    switch (action) {
                        case HIT -> player.receiveCard(this.deck.drawCard());
                        case BUST -> ui.renderBusted(player.getName());
                        case BLACKJACK -> ui.renderBlackjack(player.getName());
                    }
                }
            }
        } while (action == PlayerAction.HIT);
        ui.renderEndTurn(player.getName());
    }

    private PlayerAction getAction(int score){
        if (score < 21)
            return ui.requestAction();
        else if (score > 21)
            return PlayerAction.BUST;
        else
            return PlayerAction.BLACKJACK;
    }

    private Optional<Player> resolveWinner(){
        var scorePlayer1 = this.score.calculateScore(this.player1.getHand());
        var scorePlayer2 = this.score.calculateScore(this.player2.getHand());
        var isDraw = (scorePlayer1>21 && scorePlayer2>21) || (scorePlayer1==scorePlayer2);
                if(!isDraw){
                    Optional<Player> winner = Optional.empty();
                    if (scorePlayer1>21)
                        winner = Optional.of(this.player2);
                     else if (scorePlayer2>21)
                        winner = Optional.of(this.player1);
                    else
                        winner = Optional.of(scorePlayer1>scorePlayer2? this.player1 : this.player2);

                    return winner;
                }
                else
                    return Optional.empty();
    }

    @Override
    public String toString() {
        var result = "Game - 21!";

        result += "\n\nPlayers:\n";
        result += "\n" + this.player1.toString();
        result += "\nPontuação do jogador 1: " + this.score.calculateScore(this.player1.getHand());
        result += "\n" + this.player2.toString();
        result += "\n\nRemaining cards:" + this.deck.remainingCards();
        result += "\nPontuação do jogador 2: " + this.score.calculateScore(this.player2.getHand());

        return result;
    }
}
