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

    public Game(GameUi gameUi){
        this.ui = gameUi;
        this.player1 = new Player(ui.requestPlayerName(1));
        this.player2 = new PlayerIA();
        //this.initialize();
        //this.calculatePoints = new calculatePoints();
    }

    private void initialize(){

        this.score = new ScoreBasic();
        this.score = ui.requestGameMode();

        this.deck = new Deck();

        this.player1.receiveCard(this.deck.drawCard());
        this.player1.receiveCard(this.deck.drawCard());

        this.player2.receiveCard(this.deck.drawCard());
        this.player2.receiveCard(this.deck.drawCard());
    }

    public void playMatch(){
        int rodadas = ui.selectRounds();
        int pontos1 = 0;
        int pontos2 = 0;
        this.initialize();
        for (int i = 0; i<rodadas; i++){
            System.out.println("\nQuantidade de rodadas restante: " + (rodadas-i-1));
            this.resolveWinner();
            Optional<Player> winner = this.Play();
            if(winner.isPresent()){
                if(winner.get() == player1){
                    pontos1++;
                } else if (winner.get() == player2){
                    pontos2++;
                } else {
                    System.out.println("Empate");
                }
            }
            this.restart();
        }
        System.out.println("\nPlacar: ");
        System.out.println(player1.getName() + ": "+ pontos1);
        System.out.println(player2.getName() + ": "+ pontos2);

        if (pontos1 > pontos2) {
            System.out.println(player1.getName() + " venceu o jogo!");
        } else if (pontos2 > pontos1) {
            System.out.println(player2.getName() + " venceu o jogo!");
        } else {
            System.out.println("O jogo terminou empatado!");
        }

    }

    public Optional<Player> Play(){
        Optional<Player> winner = Optional.empty();

        while (winner.isEmpty()){
            ui.renderGameStart();

            executeTurn(player1);
            executeTurn(player2);

            winner = this.resolveWinner();

            if (winner.isEmpty()){//ciclo reiniciado
                this.restart();
            } else { //apresenta o resultado
                ui.renderWinner(winner.get());
            }
        }
        return winner;
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
                    return winner;
                }
                else
                    return Optional.empty();
    }

}
