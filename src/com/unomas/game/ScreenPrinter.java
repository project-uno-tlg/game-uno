package com.unomas.game;

import com.unomas.dealer.Card;
import com.unomas.player.Player;

public class ScreenPrinter {

    public static void gameOverWithWinner(String winner){
        System.out.println("The Game is Over! Winner is " + winner);
    }

    public static void gameOverDeckOutOfCard(){
        System.out.println("Game Over! We're out of card");
    }

    public static void playsCard(String name, String color, int number, int leftInHand){
        System.out.println(name + " played a " + color + " " + number + ", has " + leftInHand + " cards left" );
    }

    public static void gameOverPlayerQuit(){
        System.out.println("Game Over. Player quited game");
    }

    public static void drawCard(String name){
        System.out.println(name + " just draw one card.");
    }


}