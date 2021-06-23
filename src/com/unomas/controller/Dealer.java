package com.unomas.controller;

import com.unomas.model.cards.Card;
import com.unomas.model.cards.Deck;
import com.unomas.util.ScreenPrinter;
import com.unomas.model.player.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dealer {
    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex;
    private Card cardToMatch;
    private static Dealer dealerBot;

    private Dealer(List<Player> players){
        this.players = players;
    }

    public static Dealer getInstance() {
        return dealerBot;
    }

    public static Dealer getInstance(List<Player> players){
        if(dealerBot == null) {
            dealerBot = new Dealer(players);
        }
        return dealerBot;
    }


    public void init() {
        deck = Deck.getInstance();
        initDistributeCards();
        cardToMatch = deck.drawOneCardFromDeck();
        currentPlayerIndex = randomPlayer(players.size());
        startGame();
    }

    private void startGame() {

        while ( true){


            Player currentPlayer = players.get(currentPlayerIndex);

            if (currentPlayer.isAI() ) {
                // make computer player pause 3 sec before move
                try {
                    TimeUnit.SECONDS.sleep(2);
                }catch (InterruptedException ignored){}

            } else {
                // tell human player what card to match
                ScreenPrinter.matchCard(cardToMatch.getColor().toString(), cardToMatch.getNumber());
                ScreenPrinter.showCardsInHand(currentPlayer.getCardsInHand());
            }

            Card cardPlayed = currentPlayer.playCard();


            // when player has no matching card to play
            if (cardPlayed == null){

                Card newCard = deck.drawOneCardFromDeck();
                int cardLeftInDeck = deck.getCardsCountInDeck();
                if (cardLeftInDeck == 0){
                    ScreenPrinter.gameOverDeckOutOfCard();
                    System.exit(0);
                }
                // when the new drawing card is a match
                if (currentPlayer.checkCard(newCard)){
                    ScreenPrinter.drawCard(currentPlayer.getName());
                    ScreenPrinter.playsCard(currentPlayer.getName(), newCard.getColor().toString(),
                            newCard.getNumber(), currentPlayer.getCardsInHand().size());
                    cardToMatch = newCard;
                }
                // when it's not a match
                else {
                    currentPlayer.addCard(newCard);
                    ScreenPrinter.drawCard(currentPlayer.getName());
                }
            }
            // if player want quit the game by playing quit card.
            else if (cardPlayed.wannaQuit()){
                ScreenPrinter.gameOverPlayerQuit();
                System.exit(0);
            }
            // when player has matching card to play
            else {
                int cardLeftInHand = currentPlayer.getCardsInHand().size();
                ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed.getColor().toString(),
                        cardPlayed.getNumber(), cardLeftInHand);
                cardToMatch = cardPlayed;

                // winning condition
                if (cardLeftInHand == 0){
                    ScreenPrinter.gameOverWithWinner(currentPlayer.getName());
                    return;
                }

            }
            // in the end, update move to the next player.
            updateCurrentPlayer();
        }
    }

    public Card getCardToMatch(){
        return cardToMatch;
    }

    private void distributeCard(Player player, Card card){
        player.addCard(card);
    }

    public static int randomPlayer(int max){
        return (int) (Math.random() * max);
    }

    private void updateCurrentPlayer(){
        if (currentPlayerIndex + 1 >= players.size()){
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex++;
        }
    }

    private void initDistributeCards(){
        for (int i = 0; i < 7; i++){
            for (Player player:players){
                distributeCard(player, deck.drawOneCardFromDeck());
            }
        }
    }
}