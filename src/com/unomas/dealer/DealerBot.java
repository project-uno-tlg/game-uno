package com.unomas.dealer;

import java.util.Collection;
import java.util.List;

class DealerBot {
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
            // try catch for win?
            Card cardPlayed = currentPlayer.playCard();
            if (cardPlayed == null){
                // try catch when deck is out of card?
                Card newCard = deck.drawOneCardFromDeck();
                if (currentPlayer.checkCard(newCard)){
                    cardToMatch = newCard;
                    updateCurrentPlayer();
                } else {
                    updateCurrentPlayer();
                }
            } else {
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
    static class Player{
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
    }
}