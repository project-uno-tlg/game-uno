package com.unomas.util;

import com.unomas.model.cards.Card;
import com.unomas.model.player.Player;

import java.util.List;

public class ScreenPrinter {

    public static final String ANSI_RED    = "\u001B[91m";
    public static final String ANSI_GREEN  = "\u001B[92m";
    public static final String ANSI_YELLOW = "\u001B[93m";
    public static final String ANSI_BLUE   = "\u001B[94m";
    public static final String ANSI_WHITE  = "\u001B[97m";
    public static final String uno = "\n" +
            "       ██╗░░░██╗███╗░░██╗░█████╗░  ███╗░░░███╗░█████╗░░██████╗\n" +
            "       ██║░░░██║████╗░██║██╔══██╗  ████╗░████║██╔══██╗██╔════╝\n" +
            "       ██║░░░██║██╔██╗██║██║░░██║  ██╔████╔██║███████║╚█████╗░\n" +
            "       ██║░░░██║██║╚████║██║░░██║  ██║╚██╔╝██║██╔══██║░╚═══██╗\n" +
            "       ╚██████╔╝██║░╚███║╚█████╔╝  ██║░╚═╝░██║██║░░██║██████╔╝\n" +
            "       ░╚═════╝░╚═╝░░╚══╝░╚════╝░  ╚═╝░░░░░╚═╝╚═╝░░╚═╝╚═════╝░";

    public static final String gameOver = "\n" +
        "       ░██████╗░░█████╗░███╗░░░███╗███████╗  ░█████╗░██╗░░░██╗███████╗██████╗░\n" +
        "       ██╔════╝░██╔══██╗████╗░████║██╔════╝  ██╔══██╗██║░░░██║██╔════╝██╔══██╗\n" +
        "       ██║░░██╗░███████║██╔████╔██║█████╗░░  ██║░░██║╚██╗░██╔╝█████╗░░██████╔╝\n" +
        "       ██║░░╚██╗██╔══██║██║╚██╔╝██║██╔══╝░░  ██║░░██║░╚████╔╝░██╔══╝░░██╔══██╗\n" +
        "       ╚██████╔╝██║░░██║██║░╚═╝░██║███████╗  ╚█████╔╝░░╚██╔╝░░███████╗██║░░██║\n" +
        "       ░╚═════╝░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝  ░╚════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚═╝\n";

    public static final String winner = "\n" +

        "       █░█░█ █ █▄░█ █▄░█ █▀▀ █▀█   █ █▀\n" +
        "       ▀▄▀▄▀ █ █░▀█ █░▀█ ██▄ █▀▄   █ ▄█";

    private ScreenPrinter(){};

    public static void welcome(){
        // clear console magic only works for Mac / linux
        System.out.print("\033[H\033[2J");
        System.out.flush();


        System.out.println(ANSI_GREEN + uno + ANSI_WHITE + "\n");
        System.out.println("Welcome!! \n");
        System.out.println("How to Play: " +
                "\n1. Enter how many computer players you want to play with." +
                "\n   You can only enter 1-5. If you enter more than 5 the system will enter 5 for you." +
                "\n   0 or less will exit the game.\n" +
                "\n" +
                "2. Dealer will shuffles the cards, and randomly pick a player to start with, \n" +
                "   and the send cards to the players one by one. ( 7 for each player) \n" +
                "\n" +
                "3. Then Dealer opens one card in deck as an indicator for the starter. \n" +
                "\n" +
                "4. The picked player’s turn started (Computer or Human).  Player must play a card that matches " +
                "either a number or color, or play an allowed action card.\n" +
                "   If player could not play any card, the system will draw one from the deck for you and place it in" +
                " your deck. \n" +
                "   If the drawn card is a match, " +"then the system will automatically play it; Or move to the next player. \n" +
                "\n" +
                "5. All players follow rule #4 and game keeps going and going. \n" +
                "\n" +
                "6. When any player is out of cards, that player wins the game.  \n" +
                "\n" +
                "7. At any time, the human player can call “quit” to stop the game. \n");
    }


    public static void gameOverWithWinner(String player){
        System.out.println(gameOver + winner + "        " +player + "\n");
    }

    public static void gameOverDeckOutOfCard(){
        System.out.println(gameOver + "\n       Game Over! We're out of card");
    }

    public static void playsCard(String name, Card card, int leftInHand){

        String qtyColor = leftInHand < 2 ? ANSI_RED : ANSI_WHITE;

        if ("WILD".equalsIgnoreCase(card.getAction())){
            System.out.println("\n" +name + " played a " + ANSI_GREEN + "W" + ANSI_BLUE+ "I" +
                    ANSI_RED + "L" + ANSI_YELLOW + "D" + ANSI_WHITE + " card, has " + qtyColor + leftInHand +
                    ANSI_WHITE + " " + "cards left" );
        }
        else if ("WILD+4".equalsIgnoreCase(card.getAction())){
            System.out.println("\n" +name + " played a " + ANSI_GREEN + "W" + ANSI_BLUE+ "I" +
                    ANSI_RED + "L" + ANSI_YELLOW + "D +4" + ANSI_WHITE + " card, has " + qtyColor + leftInHand +
                    ANSI_WHITE + " " + "cards left " );
        }
        else {
            String cardColor = card.getColor().toString();
            String textColor = convertCardColor(cardColor);
            String cardValue = (card.getAction().equalsIgnoreCase("null")) ? String.valueOf(card.getNumber()) :
                    card.getAction();

            System.out.println( "\n" + name + " played a " + textColor + cardColor + " " + cardValue + ANSI_WHITE +
                    ", has " + qtyColor + leftInHand + ANSI_WHITE + " " + "cards left" );
        }
    }

    public static void matchCard(Card card){
        if ("WILD".equalsIgnoreCase(card.getAction())){
            System.out.println("\nThe Card need to match is: " + ANSI_GREEN + "W" + ANSI_BLUE+ "I" +
                    ANSI_RED + "L" + ANSI_YELLOW + "D" + ANSI_WHITE );
        }
        else if ("WILD+4".equalsIgnoreCase(card.getAction())) {
            System.out.println("\nThe Card need to match is: " + ANSI_GREEN + "W" + ANSI_BLUE + "I" +
                    ANSI_RED + "L" + ANSI_YELLOW + "D +4" + ANSI_WHITE);
        }
        else {

            String cardColor = card.getColor().toString();
            String textColor = convertCardColor(cardColor);
            String cardValue = (card.getAction().equalsIgnoreCase("null")) ? String.valueOf(card.getNumber()) :
                    card.getAction();

            System.out.println("\nThe Card need to match is: " + textColor + cardColor + " " + cardValue + ANSI_WHITE);
        }
    }

    public static void gameOverPlayerQuit(){
        System.out.println(gameOver + "\n       Game Over. Player quited game");
    }

    public static void skipPlayer(String name){
        System.out.println("\n" + name + " is skipped");
    }

    public static void drawCard(Player player, String qty){

        String name = player.getName();
        int cardsQty = player.getCardsInHand().size();
        String qtyColor = cardsQty < 2 ? ANSI_RED : ANSI_WHITE;

        System.out.println("\n" + name + " draws " + qty + " card(s), has " + qtyColor + cardsQty + ANSI_WHITE + " " +
                "card(s) left");
    }

    public static void showCardsInHand(List<Card> cardsInHand){
        StringBuilder message = new StringBuilder();
        message.append("You Have ").append(cardsInHand.size()).append(" cards: ");
        for (int i = 0; i<cardsInHand.size(); i++){
            if ("WILD".equalsIgnoreCase(cardsInHand.get(i).getAction())){
                message.append("\n [ " + (i+1) + " ]  "  + ANSI_GREEN + "W" + ANSI_BLUE+ "I" +
                        ANSI_RED + "L" + ANSI_YELLOW + "D" + ANSI_WHITE);
            } else if ("WILD+4".equalsIgnoreCase(cardsInHand.get(i).getAction())){
                message.append("\n [ " + (i+1) + " ]  "  + ANSI_GREEN + "W" + ANSI_BLUE+ "I" +
                        ANSI_RED + "L" + ANSI_YELLOW + "D +4" + ANSI_WHITE);
            }
            else {
                String cardColor = cardsInHand.get(i).getColor().toString();
                String textColor = convertCardColor(cardColor);
                String cardValue = (cardsInHand.get(i).getAction().equalsIgnoreCase("null")) ?
                        String.valueOf(cardsInHand.get(i).getNumber()) : cardsInHand.get(i).getAction();
                message.append("\n [ " + (i+1) + " ]  "  + textColor + cardColor + " " + cardValue + ANSI_WHITE);
            }
        }
        System.out.println("\n" + message + ANSI_WHITE);
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