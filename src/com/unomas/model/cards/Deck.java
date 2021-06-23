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

        // shuffle the cards;
        Collections.shuffle(cardsInDeck);

//        setCardsInDeck(deck);
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

    private void setCardsInDeck(List<Card> cards){
        cardsInDeck = cards;
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

}