package com.unomas.game;

import com.unomas.dealer.Card;
import com.unomas.player.Player;

import java.util.List;
import java.util.SortedMap;

public class ScreenPrinter {

    public static final String ANSI_RED    = "\u001B[91m";
    public static final String ANSI_GREEN  = "\u001B[92m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    public static final String ANSI_BLUE   = "\u001B[94m";
    public static final String ANSI_WHITE  = "\u001B[97m";
    public static final String uno = "\n" +
            "██╗░░░██╗███╗░░██╗░█████╗░  ███╗░░░███╗░█████╗░░██████╗\n" +
            "██║░░░██║████╗░██║██╔══██╗  ████╗░████║██╔══██╗██╔════╝\n" +
            "██║░░░██║██╔██╗██║██║░░██║  ██╔████╔██║███████║╚█████╗░\n" +
            "██║░░░██║██║╚████║██║░░██║  ██║╚██╔╝██║██╔══██║░╚═══██╗\n" +
            "╚██████╔╝██║░╚███║╚█████╔╝  ██║░╚═╝░██║██║░░██║██████╔╝\n" +
            "░╚═════╝░╚═╝░░╚══╝░╚════╝░  ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░";

    public static void welcome(){
        System.out.println("Welcome to \n" + ANSI_GREEN + uno + ANSI_WHITE);
    }

    public static void gameOverWithWinner(String winner){
        System.out.println("The Game is Over! Winner is " + winner);
    }

    public static void gameOverDeckOutOfCard(){
        System.out.println("Game Over! We're out of card");
    }

    public static void playsCard(String name, String color, int number, int leftInHand){
        String cardColor = convertCardColor(color);

        System.out.println( name + " played a " + cardColor + color + " " + number + ANSI_WHITE + ", has " + leftInHand + " cards " +
                "left" );
    }

    public static void matchCard(String color, int number){
        String cardColor = convertCardColor(color);
        System.out.println("The Card you need to match is: " + cardColor + color + " " + number + ANSI_WHITE);
    }

    public static void gameOverPlayerQuit(){
        System.out.println("Game Over. Player quited game");
    }

    public static void drawCard(String name){
        System.out.println(name + " just draw one card.");
    }

    public static void showCardsInHand(List<Card> cardsInHand){
        StringBuilder message = new StringBuilder();
        message.append("You Have ").append(cardsInHand.size()).append(" cards, ");
        cardsInHand.forEach(card -> {
                    String cardColor = card.getColor().toString();
                    String textColor = convertCardColor(cardColor);
                    message.append(textColor + cardColor + " " + card.getNumber() + ", " );
                });
        System.out.println(message + ANSI_WHITE);
    }


    private static String convertCardColor(String color){
        String cardColor;
        switch (color){
            case "RED":
                cardColor = ANSI_RED;
                break;
            case "GREEN":
                cardColor = ANSI_GREEN;
                break;
            case "BLUE":
                cardColor = ANSI_BLUE;
                break;
            case "YELLOW":
                cardColor = ANSI_YELLOW;
                break;
            default:
                cardColor = ANSI_WHITE;
        }

        return cardColor;
    }
}