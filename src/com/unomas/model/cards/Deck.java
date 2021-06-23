package com.unomas.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cardsInDeck;

    private Deck(){};

    public static Deck getInstance(){
        Deck deck = new Deck();
        deck.generateDeck();
        return deck;
    }

    // get a fresh deck of cards ready to play
    public void generateDeck(){
        List<Card> deck = new ArrayList<>();
        // get one set of cards
        for (Card.CardColor color : Card.CardColor.values()){
            for (int i = 0; i<=9; i++){
                Card card = Card.getInstance(color, i);
                deck.add(card);
            }
        }
        // double the set of cards
        deck.addAll(deck);
        // shuffle the cards;
        Collections.shuffle(deck);

        setCardsInDeck(deck);
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
}