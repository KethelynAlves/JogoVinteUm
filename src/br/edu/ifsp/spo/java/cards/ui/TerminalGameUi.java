package br.edu.ifsp.spo.java.cards.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalGameUi implements GameUi {

    @Override
    public String requestPlayerName(int playerNumber) {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o nome do jogador " + playerNumber + ": ");
        return scanner.nextLine();
    }

    @Override
    public int requestGameMode() {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o modo de jogo: \n1)Ace= 1 \n2)Ace = 11");
        return scanner.nextInt();
    }
}
