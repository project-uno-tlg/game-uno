package com.unomas.util;

import com.unomas.model.cards.Card;

import java.util.List;

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

    private ScreenPrinter(){};

    public static void welcome(){
        System.out.println("Welcome to \n" + ANSI_GREEN + uno + ANSI_WHITE);
    }

    public static void gameOverWithWinner(String winner){
        System.out.println("The Game is Over! Winner is " + winner);
    }

    public static void gameOverDeckOutOfCard(){
        System.out.println("Game Over! We're out of card");
    }

    public static void playsCard(String name, Card card, int leftInHand){
        String cardColor = card.getColor().toString();
        String textColor = convertCardColor(cardColor);
        String cardValue = (card.getAction().equalsIgnoreCase("null")) ? String.valueOf(card.getNumber()) :
                card.getAction();

        System.out.println( name + " played a " + textColor + cardColor + " " + cardValue + ANSI_WHITE + ", has " + leftInHand + " " +
                "cards " +
                "left" );
    }

    public static void matchCard(Card card){

        String cardColor = card.getColor().toString();
        String textColor = convertCardColor(cardColor);
        String cardValue = (card.getAction().equalsIgnoreCase("null")) ? String.valueOf(card.getNumber()) :
                card.getAction();

        System.out.println("The Card you need to match is: " + textColor + cardColor + " " + cardValue + ANSI_WHITE);
    }

    public static void gameOverPlayerQuit(){
        System.out.println("Game Over. Player quited game");
    }

    public static void drawCard(String name){
        System.out.println(name + " just draw one card.");
    }

    public static void showCardsInHand(List<Card> cardsInHand){
        StringBuilder message = new StringBuilder();
        message.append("You Have ").append(cardsInHand.size()).append(" cards: ");
        for (int i = 0; i<cardsInHand.size(); i++){
            String cardColor = cardsInHand.get(i).getColor().toString();
            String textColor = convertCardColor(cardColor);
            message.append("\n [ " + (i+1) + " ]  "  + textColor + cardColor + " " + cardsInHand.get(i).getNumber() + ANSI_WHITE);
        }
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