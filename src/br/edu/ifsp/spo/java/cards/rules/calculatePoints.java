package br.edu.ifsp.spo.java.cards.rules;

import br.edu.ifsp.spo.java.cards.core.Player;
import br.edu.ifsp.spo.java.cards.core.PlayerIA;

public class calculatePoints {

    public void Winner(Player player1, Player player2, int scorePlayer1, int scorePlayerIA){
        if (scorePlayer1>21 && scorePlayerIA>21){
            player1.points -= player1.points-21;
            player2.points -= player2.points-21;
        } else if (scorePlayer1==scorePlayerIA){
            player1.points += 10;
            player2.points += 10;
        } else if (scorePlayer1>scorePlayerIA && scorePlayer1<=21){
            player1.points += 30;
            if (scorePlayerIA>21){
                player2.points -=5;
            }
        } else if(scorePlayerIA>scorePlayer1 && scorePlayerIA<=21){
            player2.points += 30;
            if (scorePlayer1>21){
                player1.points -=5;
            }
        } else if (scorePlayer1==21 && scorePlayerIA==21){
            player1.points += 21;
            player2.points += 21;
        }

    }
}