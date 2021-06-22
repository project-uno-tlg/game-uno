package com.unomas.dealer;

import com.unomas.game.ScreenPrinter;
import com.unomas.player.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DealerBot {
    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex;
    private Card cardToMatch;
    private static DealerBot dealerBot;

    private DealerBot(List<Player> players){
        this.players = players;
    }

    public static DealerBot getInstance() {
        return dealerBot;
    }

    public static DealerBot getInstance(List<Player> players){
        if(dealerBot == null) {
            dealerBot = new DealerBot(players);
        }
        return dealerBot;
    }


    public void init() throws InterruptedException {
        deck = Deck.getInstance();
        initDistributeCards();
        cardToMatch = deck.drawOneCardFromDeck();
        currentPlayerIndex = randomPlayer(players.size());
        startGame();
    }

    private void startGame() throws InterruptedException {

        while ( true){

            Player currentPlayer = players.get(currentPlayerIndex);

            if (currentPlayer.isAI() ) {
                // make computer player pause 3 sec before move
                TimeUnit.SECONDS.sleep(3);
            }

            Card cardPlayed = currentPlayer.playCard();


            // when player has no matching card to play
            if (cardPlayed == null){

                Card newCard = deck.drawOneCardFromDeck();
                int cardLeftInDeck = deck.getAllCardsInDeck().size();
                if (cardLeftInDeck == 0){
                    ScreenPrinter.gameOverDeckOutOfCard();
                    System.exit(0);
                }
                // when the new drawing card is a match
                if (currentPlayer.checkCard(newCard)){
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
                // winning condition
                if (cardLeftInHand == 0){
                    ScreenPrinter.gameOverWithWinner(currentPlayer.getName());
                    return;
                }
                ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed.getColor().toString(),
                        cardPlayed.getNumber(), currentPlayer.getCardsInHand().size());
                cardToMatch = cardPlayed;
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