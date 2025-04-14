package br.edu.ifsp.spo.java.cards;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score = new Score();

    public void starGame(){
        Player j1 = new Player("Ada Lovelace");
        Player j2 = new Player("Adam Handles");

        this.deck = new Deck();
        Card card = this.deck.drawCard();

        player1 = new Player(deck);
        player1.receiveCard(deck);

        player2 = new  Player(deck);
        player2.receiveCard(deck);
        System.out.println("-Player "+ j1.getName() +" tem as cartas: " + player1.hand +" : " + score.calcValue(player1.hand));
        System.out.println("-Player "+ j2.getName() +" tem as cartas: " + player2.hand +" : " + score.calcValue(player2.hand));

        System.out.println("\nVencedor é...");

        System.out.println("\nSobraram: "+ deck.remainingCards() +" cartas.");


    }
}
