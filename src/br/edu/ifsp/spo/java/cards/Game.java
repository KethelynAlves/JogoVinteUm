package br.edu.ifsp.spo.java.cards;

import java.util.Scanner;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private ScorerBasic scorer = new ScorerBasic();

    public void starGame(){
        var scanner = new Scanner(System.in);
        System.out.println("Insira nome do jagador 1: ");
        var player1name = scanner.nextLine();
        System.out.println("Insira nome do jagador 2: ");
        var player2name = scanner.nextLine();

        int[] result = new int[2];

        this.deck = new Deck();
        Card card = this.deck.drawCard();

        player1 = new Player(deck);
        player1.receiveCard(deck);
        result[0] = scorer.calculateScore(player1.hand);

        player2 = new  Player(deck);
        player2.receiveCard(deck);
        result[1] = scorer.calculateScore(player2.hand);
        System.out.println("-Player "+ player1name +" tem as cartas: " + player1.hand +" : Pontos " + result[0]);
        System.out.println("-Player "+ player2name +" tem as cartas: " + player2.hand +" : Pontos " + result[1]);

        System.out.println("\n\n\nVencedor é...");
        if (result[0]>result[1] && result[0]<=21){
            System.out.println(player1name+"!");
        } else if (result[0]>21){
            System.out.println(player1name + " estourou a mesa.\n" + player2name+ " venceu!");
        } else if(result[1]>result[0] && result[1]<=21){
            System.out.println(player2name);
        } else if (result[1]>21) {
            System.out.println(player2name + " estourou a mesa.\n" + player1name+ " venceu!");
        }
        if (result[0]==result[1]){
            System.out.println("Empate.");
        }

        System.out.println("\nSobraram: "+ deck.remainingCards() +" cartas.");


    }
}
