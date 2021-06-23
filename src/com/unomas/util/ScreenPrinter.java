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
        System.out.println();
        System.out.println("How to Play: " +
                "\n1. Enter how many computer players you want to play with." +
                "\n   You can only enter 1-6. If you enter more than 6 the system will enter 6 for you." +
                "\n   0 or less will exit the game.\n" +
                "\n" +
                "2. Dealer will shuffles the cards, and randomly pick a player to start with, \n" +
                "   and the send cards to the players one by one. ( 7 for each player) \n" +
                "\n" +
                "3. Then Dealer opens one card in deck as an indicator for the starter. \n" +
                "\n" +
                "4. The picked player’s turn started (Computer or Human).  Player must play a card that matches either a number or color.\n" +
                "   If player does not have it, the system will draw one from the deck for you and place it in your deck. \n" +
                "   If the drawn card is a match, " +"then the system will automatically play it; Or move to the next player. \n" +
                "\n" +
                "5. All players follow rule #4 and game keeps going and going. \n" +
                "\n" +
                "6. When any player is out of cards, that player wins the game.  \n" +
                "\n" +
                "7. In the end, someone wins the game, then the Dealer will ask whether you want to play again. \n" +
                "   If yes, then the game will start with the next player after who won the game. \n" +
                "\n" +
                "9. At any time, the human player can call “quit” to stop the game. \n");
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

    public static void drawCard(String name, String qty){
        System.out.println(name + " draws " + qty + " card(s).");
    }

    public static void showCardsInHand(List<Card> cardsInHand){
        StringBuilder message = new StringBuilder();
        message.append("You Have ").append(cardsInHand.size()).append(" cards: ");
        for (int i = 0; i<cardsInHand.size(); i++){
            String cardColor = cardsInHand.get(i).getColor().toString();
            String textColor = convertCardColor(cardColor);
            String cardValue = (cardsInHand.get(i).getAction().equalsIgnoreCase("null")) ?
                    String.valueOf(cardsInHand.get(i).getNumber()) : cardsInHand.get(i).getAction();
            message.append("\n [ " + (i+1) + " ]  "  + textColor + cardColor + " " + cardValue + ANSI_WHITE);
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