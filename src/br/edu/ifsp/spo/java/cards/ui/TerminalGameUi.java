package br.edu.ifsp.spo.java.cards.ui;

import br.edu.ifsp.spo.java.cards.core.Player;
import br.edu.ifsp.spo.java.cards.core.PlayerAction;
import br.edu.ifsp.spo.java.cards.items.Card;
import br.edu.ifsp.spo.java.cards.rules.Score;
import br.edu.ifsp.spo.java.cards.rules.ScoreAceEleven;
import br.edu.ifsp.spo.java.cards.rules.ScoreBasic;

import java.util.List;
import java.util.Scanner;

public class TerminalGameUi implements GameUi {
    public static final String ANSI_CLS = "\u001b[2J";     // Limpar tela
    public static final String ANSI_HOME = "\u001b[H";      // Mover cursor para 0,0 (topo esquerdo)
    private static void clearScreen() {
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush(); // Garante que os comandos sejam enviados imediatamente
    }

    @Override
    public String requestPlayerName(int playerNumber) {
        var scanner = new Scanner(System.in);
        System.out.println("Insira o nome do jogador " + playerNumber + ": ");
        return scanner.nextLine();
    }

    @Override
    public Score requestGameMode() {
        clearScreen();
        System.out.println("Insira o modo de jogo: \n1)Ace valendo '1' pontos\n2)Ace valendo '11' pontos");
        var scanner = new Scanner(System.in);
        int mode = scanner.nextInt();

        return switch (mode) {
            case 1 -> new ScoreBasic();
            case 2 -> new ScoreAceEleven();
            default -> new ScoreBasic();
        };
    }

    @Override
    public void renderGameStart() {
        clearScreen();
        System.out.println("\nComeçando a partida!");
    }

    @Override
    public void renderStartTurn(String playerName) {
        System.out.println("Agora é a vez de " + playerName);
    }

    @Override
    public void renderHand(List<Card> hand, int score) {
        System.out.println("Mão atual: ");
        for (Card card : hand){
            System.out.println(card);
        }
        System.out.println("Pontuação atual: "+score);
    }

    @Override
    public PlayerAction requestAction() {
        System.out.println("O que você deseja fazer?");
        System.out.println("1) Comprar uma carta");
        System.out.println("2) Manter a mão atual");
        var scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        return option == 1? PlayerAction.HIT : PlayerAction.PASS;
    }

    @Override
    public void renderBusted(String name) {
        System.out.println(name + " ESTOUROU!");
    }

    @Override
    public void renderEndTurn(String name) {
        System.out.println("Fim da vez de " + name);
    }

    @Override
    public void renderBlackjack(String name) {
        System.out.println(name + " CONSEGUIU 21!!!");

    }

    @Override
    public void renderWinner(Player winner) {
        System.out.println("\nO vencedor é:"+winner.getName());
    }

    @Override
    public void renderPoints(int playerScore) {
        System.out.println("Pontuação final: "+ playerScore);
    }

    @Override
    public int selectRounds() {
        var scanner = new Scanner(System.in);
        System.out.println("Quantas rodadas quer ter?");
        return scanner.nextInt();
    }

    @Override
    public void renderPoints2(Player player1, Player player2) {
        System.out.println(player1.getName() + ": " + player1.points);
        System.out.println(player2.getName() + ": " + player2.points);
    }

    @Override
    public void renderWinner2(Player player) {
        System.out.println("\nO jogador " + player.getName() +" venceu com "+player.points+" pontos.");
    }

}
