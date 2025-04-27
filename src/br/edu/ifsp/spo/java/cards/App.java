package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.core.Game;
import br.edu.ifsp.spo.java.cards.ui.GameUi;
import br.edu.ifsp.spo.java.cards.ui.TerminalGameUi;

public class App {
    public static void main(String[] args) {
        System.out.println("Iniciando Game");
        var vinteUm = new Game(new TerminalGameUi());
        vinteUm.Play();
    }
}