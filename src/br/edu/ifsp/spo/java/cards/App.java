package br.edu.ifsp.spo.java.cards;

import br.edu.ifsp.spo.java.cards.core.Game;
import br.edu.ifsp.spo.java.cards.ui.GameUi;

public class App {

    public static void main(String[] args) {
        Game vinteUm = new Game();
        System.out.println("Iniciando Game");
        vinteUm.Play();
    }
}
