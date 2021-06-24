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
    private Player currentPlayer;
    private int currentPlayerIndex;
    private Card cardToMatch;
    private static Dealer dealerBot;
    private boolean gameOver = false;
    private boolean isReversed = false;


    private Dealer(List<Player> players){
        this.players = players;
    }

    // let's do business
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
        currentPlayer = players.get(currentPlayerIndex);
        // clear console magic, only works for Mac / linux
        System.out.print("\033[H\033[2J");
        System.out.flush();
        ScreenPrinter.matchCard(cardToMatch);
        startGame();
    }

    private void startGame() {

        while ( !gameOver ){

            preparePlayer();

            Card cardPlayed = currentPlayer.playCard();

            processCard(cardPlayed, false);

            // in the end,  move to the next player.
            updateCurrentPlayer();

            if (gameOver) {System.exit(0);}
        }
    }

    private void processCard(Card cardPlayed, boolean drawFour) {

        if (cardPlayed == null) {
            processNullCard();
        }
        else if (cardPlayed.wannaQuit()){
            ScreenPrinter.gameOverPlayerQuit();
            gameOver = true;
        }
        else {
            switch (cardPlayed.getAction()){
                case "null":
                    processRegularCard(cardPlayed);
                    break;
                case "REVERSE":
                    processReverseCard(cardPlayed);
                    break;
                case "SKIP":
                    processSkipCard(cardPlayed);
                    break;
                case "+2":
                    processDrawTwoCard(cardPlayed);
                    break;
                case "WILD":
                    processWildCard(cardPlayed);
                    break;
                case "WILD+4":
                    processWildDrawFourCard(cardPlayed);
                    break;
            }
        }
        // check how many cards left in player's hand for winning condition, if 0, player wins
        checkCardCountInHand();
        // if this is called inside of a processDrawFourCard
        if (drawFour){ updateCurrentPlayer();}
    }

    private void processWildDrawFourCard(Card cardPlayed) {
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());

        int poorGuysIndex = getPoorGuysIndex();
        Player poorGuy = players.get(poorGuysIndex);

        for (int i=0; i<4; i++){
            Card newCard = deck.drawOneCardFromDeck();
            poorGuy.addCard(newCard);
        }
        ScreenPrinter.drawCard(poorGuy, "four");

        cardToMatch = cardPlayed;

        preparePlayer();

        Card newCardToPlay = currentPlayer.playCard();

        processCard(newCardToPlay, true);

        ScreenPrinter.skipPlayer(poorGuy.getName());
    }

    private void processWildCard(Card cardPlayed) {
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());
        cardToMatch = cardPlayed;
        preparePlayer();

        Card newCardToPlay = currentPlayer.playCard();

        processCard(newCardToPlay, false);
    }

    private void processDrawTwoCard(Card cardPlayed) {
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());
        // skip next player
        updateCurrentPlayer();

        Card newCard1 = deck.drawOneCardFromDeck();
        Card newCard2 = deck.drawOneCardFromDeck();
        currentPlayer.addCard(newCard1);
        currentPlayer.addCard(newCard2);
        ScreenPrinter.drawCard(currentPlayer, "two");
        cardToMatch = cardPlayed;
    }

    private void processSkipCard(Card cardPlayed) {
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());
        updateCurrentPlayer();
        cardToMatch = cardPlayed;
        ScreenPrinter.skipPlayer(currentPlayer.getName());
    }

    private void processNullCard() {
        // when player has no matching card to play
        Card newCard = deck.drawOneCardFromDeck();

        checkCardCountInDeck(); // do this every time draw card from deck. game is over when deck is out of cards
        // process the new drawing card is a match
        processNewDrawCard(newCard);
    }

    private void processNewDrawCard(Card newCard) {
        // if the new draw card is playable
        if (newCard.getColor() == cardToMatch.getColor() ||
                newCard.getNumber() == cardToMatch.getNumber() && newCard.getNumber() != -1 ||
                newCard.getAction().equalsIgnoreCase(cardToMatch.getAction()) &&
                        !newCard.getAction().equalsIgnoreCase("null") ||
                "WILD".equalsIgnoreCase(cardToMatch.getAction()) ||
                "WILD".equalsIgnoreCase(newCard.getAction()) ||
                "WILD+4".equalsIgnoreCase(cardToMatch.getAction()) ||
                "WILD+4".equalsIgnoreCase(newCard.getAction())
        ){
            ScreenPrinter.drawCard(currentPlayer, "one");
            processCard(newCard, false);
        }
        // when it's not a match
        else {
            currentPlayer.addCard(newCard);
            ScreenPrinter.drawCard(currentPlayer, "one");
        }
    }

    private void processReverseCard(Card cardPlayed) {
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());
        isReversed = !isReversed;
        cardToMatch = cardPlayed;
    }

    private void preparePlayer(){

        if (currentPlayer.isAI() ) {
            // make computer player pause 3 sec before move
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException ignored){}

        } else {
            // tell human player what card to match and show cards in hand
            ScreenPrinter.matchCard(cardToMatch);
            ScreenPrinter.showCardsInHand(currentPlayer.getCardsInHand());
        }
    }

    private void processRegularCard(Card cardPlayed){

        // when player has matching card to play
        ScreenPrinter.playsCard(currentPlayer.getName(), cardPlayed, currentPlayer.getCardsInHand().size());
        cardToMatch = cardPlayed;
    }

    private void checkCardCountInHand() {
        int cardLeftInHand = currentPlayer.getCardsInHand().size();
        if (cardLeftInHand == 0){
            ScreenPrinter.gameOverWithWinner(currentPlayer.getName());
            gameOver = true;
            System.exit(0);
        }
    }

    private void checkCardCountInDeck() {
        int cardLeftInDeck = deck.getCardsCountInDeck();
        if (cardLeftInDeck == 0){
            ScreenPrinter.gameOverDeckOutOfCard();
            gameOver = true;
            System.exit(0);
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
        if (!isReversed){
            // regular game sequence, index goes up
            if (currentPlayerIndex + 1 >= players.size()){
                currentPlayerIndex = 0;
            } else {
                currentPlayerIndex++;
            }
        } else {
            // in reversed game sequence, index goes down
            if (currentPlayerIndex == 0){
                currentPlayerIndex = players.size()-1;
            } else {
                currentPlayerIndex--;
            }
        }
        currentPlayer = players.get(currentPlayerIndex);
    }

    private int getPoorGuysIndex(){
        int poorGuysIndex;
        if (!isReversed){
            // regular game sequence, index goes up
            if (currentPlayerIndex + 1 >= players.size()){
                poorGuysIndex = 0;
            } else {
                poorGuysIndex = currentPlayerIndex+1;
            }
        } else {
            // in reversed game sequence, index goes down
            if (currentPlayerIndex == 0){
                poorGuysIndex = players.size()-1;
            } else {
                poorGuysIndex = currentPlayerIndex-1;
            }
        }
        return poorGuysIndex;
    }

    private void initDistributeCards(){
        for (int i = 0; i < 7; i++){
            for (Player player:players){
                distributeCard(player, deck.drawOneCardFromDeck());
            }
        }
    }
}