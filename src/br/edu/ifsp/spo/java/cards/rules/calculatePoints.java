package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.core.Player;
import br.edu.ifsp.spo.java.cards.core.PlayerIA;

public class calculatePoints {
    public void winner(Player player1, Player player2, int scorePlayer1, int scorePlayer2) {
        if (scorePlayer1 > 21) {
            player1.points -= 5;
            player2.points += scorePlayer2;
        } else if (scorePlayer2 > 21) {
            player2.points -= 5;
            player1.points +=scorePlayer1;
        } else if (scorePlayer1 == 21 || scorePlayer2 == 21) {
            player1.points += (scorePlayer1 == 21) ? 30 : -5;
            player2.points += (scorePlayer2 == 21) ? 30 : -5;
        } else if (scorePlayer1 > scorePlayer2) {
            player1.points += scorePlayer1 - scorePlayer2;
        } else {
            player2.points += scorePlayer2 - scorePlayer1;
        }
    }

    public void draw(Player player1, Player player2, int scorePlayer1, int scorePlayer2) {
        if (scorePlayer1 > 21 && scorePlayer2 > 21) {//os dois estouraram
            player1.points -= scorePlayer1 - 21;
            player2.points -= scorePlayer2 - 21;
        } else if (scorePlayer1==21 && scorePlayer2==21){//
            player1.points += 21;
            player2.points += 21;
        } else {//empate
            player1.points += 10;
            player2.points += 10;
        }
    }
}

