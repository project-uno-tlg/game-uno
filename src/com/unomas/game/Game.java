package com.unomas.game;

import java.time.LocalDateTime;
import java.util.Collection;

class Game {
    public static final int MAX_PLAYER_COUNT = 6;

    private int	playerCount;
    private LocalDateTime date = LocalDateTime.now();
    //private Player players;
   private Prompter prompt;
    //private DealerBot dealerBot;


    // Constructor
    public Game(int playerCount, LocalDateTime date) {
        setPlayerCount(playerCount);
        setDate(date);
    }

    // Getters and Setters

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        if (playerCount > MAX_PLAYER_COUNT){
            prompt.info("Sorry only 6 players allowed");
        }else {
            this.playerCount = playerCount;
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Business Methods
    public void quit(){
        if (playerCount == 1){
            prompt.info("Game Over.");
            prompt.info("Winner: ");
            prompt.info("Hit any key to close prompt.");
            System.exit(0);
        }

    }
    public void start(){
        prompt.info("How many players: ");
    }

//    private Collection<Player> createAI(int count){
//        return;
//    }

//    private Player createRealPlayer(String name){
//
//    }

}