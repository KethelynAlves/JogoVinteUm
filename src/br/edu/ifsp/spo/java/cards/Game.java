package br.edu.ifsp.spo.java.cards;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;

    public void starGame(){
        this.deck = new Deck();
        Card card = this.deck.drawCard();

        player1 = new Player(deck);
        player1.receiveCard(deck);

        player2 = new  Player(deck);
        player2.receiveCard(deck);
        System.out.println("Player 1 tem as cartas: " + player1.hand);
        System.out.println("Player 2 tem as cartas: " + player2.hand);
    }


}
