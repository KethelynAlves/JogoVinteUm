package br.edu.ifsp.spo.java.cards;

public class Game {
    private Deck deck;
    private Player player1;
    private Player player2;
    private Score score = new Score();


    public void starGame(){
        Player j1 = new Player("Ada Lovelace");
        Player j2 = new Player("Adam Handles");
        int[] result = new int[2];

        this.deck = new Deck();
        Card card = this.deck.drawCard();

        player1 = new Player(deck);
        player1.receiveCard(deck);
        result[0] = score.calcValue(player1.hand);

        player2 = new  Player(deck);
        player2.receiveCard(deck);
        result[1] = score.calcValue(player2.hand);
        System.out.println("-Player "+ j1.getName() +" tem as cartas: " + player1.hand +" : Pontos " + result[0]);
        System.out.println("-Player "+ j2.getName() +" tem as cartas: " + player2.hand +" : Pontos " + result[1]);

        System.out.println("\n\n\n\n\n\n\nVencedor é...");
        if (result[0]>result[1] && result[0]<=21){
            System.out.println(j1.getName()+"!");
        } else if (result[0]>21){
            System.out.println(j1.getName() + " estourou a mesa.\n" + j2.getName()+ " venceu!");
        } else if(result[1]>result[0] && result[1]<=21){
            System.out.println(j2.getName());
        } else if (result[1]>21) {
            System.out.println(j2.getName() + " estourou a mesa.\n" + j1.getName()+ " venceu!");
        }
        if (result[0]==result[1]){
            System.out.println("Empate.");
        }


        System.out.println("\n\n\nSobraram: "+ deck.remainingCards() +" cartas.");


    }
}
