package com.unomas.dealer;

import com.unomas.game.ScreenPrinter;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DealerBot {
    private List<Player> players;
    private Deck deck;
    private int currentPlayerIndex;
    private Card cardToMatch;

    private DealerBot(List<Player> players){
        this.players = players;
    }

    public static DealerBot getInstance(List<Player> players){
        return new DealerBot(players);
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
                    return;
                }
                // when the new drawing card is a match
                if (currentPlayer.checkCard(newCard)){
                    ScreenPrinter.playsCard(currentPlayer.getName(), newCard.getColor().toString(), newCard.getNumber());
                    cardToMatch = newCard;
                }
                // when it's not a match
                else {
                    currentPlayer.addCard(newCard);
                }
            }
            // when player has matching card to play
            else {
                int cardLeftInHand = currentPlayer.getCardsInHand().size();
                // winning condition
                if (cardLeftInHand == 0){
                    ScreenPrinter.gameOverWithWinner(currentPlayer.getName());
                    return;
                }
                ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed.getColor().toString(), cardPlayed.getNumber());
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
        player.cardsInHand.add(card);
    }

    public static int randomPlayer(int max){
        return (int) (Math.random() * (max  + 1));
    }

    private void updateCurrentPlayer(){
        currentPlayerIndex = currentPlayerIndex + 1 >= players.size() ?
                0 : currentPlayerIndex++;
    }

    private void initDistributeCards(){
        for (int i = 0; i < 7; i++){
            for (Player player:players){
                distributeCard(player, deck.drawOneCardFromDeck());
            }
        }
    }





    // remove it later on
    // for testing purpose now
    public static class Player{
        String name;
        List<Card> cardsInHand;
        public Player(String name, List<Card> cardsInHand){
            this.name = name;
            this.cardsInHand = cardsInHand;
        }

        public boolean checkCard (Card card){
            return true;
        }
        public Card playCard(){
            return null;
        }
        public List<Card> getCardsInHand(){
            return cardsInHand;
        }
        public String getName(){
            return name;
        }
        public void addCard(Card card){
            cardsInHand.add(card);
        }
        public boolean isAI(){
            return true;
        }

    }
}