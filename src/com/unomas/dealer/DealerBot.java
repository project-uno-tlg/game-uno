package com.unomas.dealer;

import java.util.Collection;
import java.util.List;

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


    public void init(){
        deck = Deck.getInstance();
        initDistributeCards();
        cardToMatch = deck.drawOneCardFromDeck();
        currentPlayerIndex = randomPlayer(players.size());
        startGame();
    }

    private void startGame(){
        boolean tracker = true;
        while ( tracker){
            Player currentPlayer = players.get(currentPlayerIndex);
            Card cardPlayed = currentPlayer.playCard();
            int cardLeftInHand = currentPlayer.getCardsInHand().size();
            // winning condition
            if (cardLeftInHand == 0){
                System.out.println("The game is over, winner is" + currentPlayer.getName());
                return;
            }
            // when player has no matching card to play
            if (cardPlayed == null){

                Card newCard = deck.drawOneCardFromDeck();
                int cardLeftInDeck = deck.getAllCardsInDeck().size();
                if (cardLeftInDeck == 0){
                    System.out.println("nobody win, we're out of cards");
                    return;
                }
                // when the new drawing card is a match
                if (currentPlayer.checkCard(newCard)){
                    System.out.println(currentPlayer.getName() + " played a " + newCard.getColor() + newCard.getNumber());
                    cardToMatch = newCard;
                    updateCurrentPlayer();
                }
                // when it's not a match
                else {
                    currentPlayer.addCard(newCard);
                    updateCurrentPlayer();
                }
            }
            // when player has matching card to play
            else {
                System.out.println(currentPlayer.getName() + " played a " + cardPlayed.getColor() + cardPlayed.getNumber());

                cardToMatch = cardPlayed;
                updateCurrentPlayer();
            }
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

    }
}