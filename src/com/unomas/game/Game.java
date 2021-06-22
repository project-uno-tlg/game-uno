package com.unomas.game;


import com.apps.util.Prompter;
//import com.unomas.dealer.DealerBot;
import com.unomas.player.Player;
import com.unomas.player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Game{
    public static final int MAX_PLAYER_COUNT = 5;

    private int	playerCount;
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
        if (playerCount > MAX_PLAYER_COUNT){
            System.out.println("Sorry only 6 players allowed");
        }else {
            this.playerCount = playerCount;
        }
    }


    // Business Methods
    public void quit(){
        String exitText;
        if (playerCount == 1){
            prompter.info("Game Over.");
            prompter.info("Winner: "+ players.get(0));
            exitText = prompter.prompt("Type exit to exit game.");
            if(exitText.toLowerCase().equals("exit")){

                System.exit(0);
            }

        }
    }

    public void start(){
        setPlayerCount(Integer.parseInt(prompter.prompt("How many computer players: ",
                "\\d+", "That is not a valid number!")));
        players.add(createRealPlayer(prompter.prompt("Type your name: ")));
        players = createAI(getPlayerCount());
        //new DealerBot(players);
    }

    private List<Player> createAI(int count){
        List<Player> computerPlayers = new ArrayList<>();
        while (count > 0){
            computerPlayers.add(PlayerFactory.createPlayer("computer"+count));
            count--;
        }
       return computerPlayers;
   }

    private Player createRealPlayer(String name){
        Player humanPlayer = PlayerFactory.createPlayer(name);
        return humanPlayer;
    }




}