package com.unomas.controller;


import com.apps.util.Prompter;
import com.unomas.model.player.Player;
import com.unomas.model.player.PlayerFactory;
import com.unomas.util.ScreenPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static final int MAX_PLAYER_COUNT = 5;

    private int playerCount;
    private List<Player> players = new ArrayList<>();
    private Prompter prompter = new Prompter(new Scanner(System.in));


    // Constructor
    public Game() {
    }

    // Getters and Setters

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        if (playerCount <= 0){
            quit();
        }
        if (playerCount > MAX_PLAYER_COUNT) {
            System.out.println("Sorry only 6 players allowed. Setting up a game with 6 players...");
            this.playerCount = 5;
        } else {
            this.playerCount = playerCount;
        }
    }


    // Business Methods
    private void quit() {
        prompter.info("Game Over GoodBye.");
        System.exit(0);
    }


    public void start() {
        ScreenPrinter.welcome();
        setPlayerCount(Integer.parseInt(prompter.prompt("How many computer players (1-6) \n"+"Entering 0 will exit the game : ",
                "\\d+", "That is not a valid number!")));
        players.add(createRealPlayer(prompter.prompt("Type your name: ")));
        createAI(getPlayerCount());
        Dealer.getInstance(players).init();
    }

    private void createAI(int count) {
        int index = 1;
        List<Player> computerPlayers = new ArrayList<>();
        while (count > 0) {
            players.add(PlayerFactory.createPlayer("computer" + index, true));
            count--;
            index++;
        }
    }

    private Player createRealPlayer(String name) {
        if(name.equals("quit")){
            quit();
        }
        return PlayerFactory.createPlayer(name, false);
    }
}