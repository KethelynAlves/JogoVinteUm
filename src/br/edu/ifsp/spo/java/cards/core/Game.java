package br.edu.ifsp.spo.java.cards.core;


import br.edu.ifsp.spo.java.cards.items.Deck;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;
import br.edu.ifsp.spo.java.cards.rules.calculatePoints;
import br.edu.ifsp.spo.java.cards.ui.GameUi;

import java.util.Optional;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score;
    private final GameUi ui;
    private calculatePoints calculatePoints;
    int rodadas=0;
    public Game(GameUi gameUi){
        this.ui = gameUi;
        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new PlayerIA();
        this.initialize();
    }

    private void initialize(){
        this.calculatePoints = new calculatePoints();
        rodadas = ui.selectRounds();

        this.score = new ScoreBasic();
        this.score = ui.requestGameMode();

        this.deck = new Deck();

        this.player1.receiveCard(this.deck.drawCard());
        this.player1.receiveCard(this.deck.drawCard());

        this.player2.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }


    public void Play() {
        do{
            Optional<Player> winner = Optional.empty();

            while (winner.isEmpty()) {
                ui.renderGameStart();
                executeTurn(player1);
                executeTurn(player2);
                winner = this.resolveWinner();

                if (winner.isPresent()) {
                    ui.renderWinner(winner.get());
                    ui.renderHand(winner.get().getHand(), score.calculateScore(winner.get().getHand()));
                } else {
                   this.restart();
                }
                this.ui.renderPoints2(this.player1, this.player2);
                System.out.println("Rodadas restantes: "+(rodadas-1));
            }
            this.restart();
            rodadas--;

        } while(rodadas>0);

        this.ui.renderPoints2(this.player1,this.player2);
        if (this.player1.points>this.player2.points){
            this.ui.renderWinner2(this.player1);
        } else {
            this.ui.renderWinner2(this.player2);
        }
    }


    public void restart(){
        deck.addToDiscartPile(this.player1.discardHand());
        deck.addToDiscartPile(this.player2.discardHand());

        this.player1.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());

        this.player1.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }

    public void executeTurn(Player player) {
        ui.renderStartTurn(player.getName());

        PlayerAction action;

        do {
            var currentScore = this.score.calculateScore(player.getHand());
            if (player instanceof PlayerIA ia) {
                action = ia.makeDecision(currentScore);
                if (action == PlayerAction.HIT)
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
        var isDraw = (scorePlayer1>21 && scorePlayer2>21) || (scorePlayer1==scorePlayer2);//se for empate
            if(!isDraw){
                Optional<Player> winner;
                if (scorePlayer1>21)
                    winner = Optional.of(this.player2);//verifica se estorou
                else if (scorePlayer2>21)
                    winner = Optional.of(this.player1);//verifica se estourou
                else
                    winner = Optional.of(scorePlayer1>scorePlayer2? this.player1 : this.player2);//verifica quem tem mais
                this.calculatePoints.winner(this.player1, this.player2, scorePlayer1, scorePlayer2);
                return winner;
            } else {
                this.calculatePoints.draw(this.player1, this.player2, scorePlayer1, scorePlayer2);
                return Optional.empty();
            }
    }
}
