package br.edu.ifsp.spo.java.cards.ui;

import java.util.Scanner;

public class TerminalGameUi implements GameUi {
    @Override
    public String requestPlayerName(int playerNumber) {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o nome do jogador"+ playerNumber +": ");
        return scanner.nextLine();
    }
}
