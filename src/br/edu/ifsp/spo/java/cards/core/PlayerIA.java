package br.edu.ifsp.spo.java.cards.core;

public class PlayerIA extends Player{
    public PlayerIA(){
        super("Hal-9000");
    }
    public PlayerAction makeDecision(int score){
        if(score >= 18){
            return PlayerAction.PASS;
        } else {
            return PlayerAction.HIT;
        }
    }
}
