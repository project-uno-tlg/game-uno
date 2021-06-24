package com.unomas.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cardsInDeck = new ArrayList<>();

    private Deck(){};

    public static Deck getInstance(){
        Deck deck = new Deck();
        deck.generateDeck();
        return deck;
    }

    // get a fresh deck of cards ready to play
    public void generateDeck(){

        generateRegularCards();
        generateReverseCards();
        generateSkipCards();
        generateDrawTwoCards();
        generateWildCards();
        generateWildDrawFourCards();

        // shuffle the cards;
        Collections.shuffle(cardsInDeck);
    }


    public Card drawOneCardFromDeck(){
        Card card = cardsInDeck.get(cardsInDeck.size()-1);
        cardsInDeck.remove(cardsInDeck.size()-1);
        return card;
    }

    public List<Card> getAllCardsInDeck() {
        return Collections.unmodifiableList(cardsInDeck);
    }

    public int getCardsCountInDeck(){
        return cardsInDeck.size();
    }

    private void generateRegularCards(){
        // game has 76 regular cards
        // add two sets of regular 1-9 cards to deck
        for ( int i = 0; i<2; i++){
            for (Card.CardColor color : Card.CardColor.values()){
                for (int j = 1; j<=9; j++){
                    Card card = Card.getInstance(color, j);
                    cardsInDeck.add(card);
                }
            }
        }
        // add fou4 0 number cards to the deck
        for ( Card.CardColor color : Card.CardColor.values()){
            Card card = Card.getInstance(color, 0);
            cardsInDeck.add(card);
        }
    }

    private void generateReverseCards(){
        // game has 8 reverse cards, 2 for each color;
        for (int i = 0; i<2; i++){
            for (Card.CardColor color : Card.CardColor.values()){
                Card card = Card.getInstance(color, "REVERSE");
                cardsInDeck.add(card);
            }
        }
    }

    private void generateSkipCards() {
        // game has 8 skip cards, 2 for each color;
        for (int i = 0; i<2; i++){
            for (Card.CardColor color : Card.CardColor.values()){
                Card card = Card.getInstance(color, "SKIP");
                cardsInDeck.add(card);
            }
        }
    }

    private void generateDrawTwoCards() {
        // game has 8 draw two cards, 2 for each color;
        for (int i = 0; i<2; i++){
            for (Card.CardColor color : Card.CardColor.values()){
                Card card = Card.getInstance(color, "+2");
                cardsInDeck.add(card);
            }
        }
    }

    private void generateWildCards() {
        // game has 4 wild cards;
        for (int i = 0; i<4; i++){
            Card card = Card.getInstance("WILD");
            cardsInDeck.add(card);
        }
    }

    private void generateWildDrawFourCards() {
        // game has 4 wild +4 cards;
        for (int i = 0; i<4; i++){
            Card card = Card.getInstance("WILD+4");
            cardsInDeck.add(card);
        }
    }

}