package com.unomas.player;

import com.unomas.dealer.Card;
import com.unomas.dealer.Deck;
import com.unomas.dealer.DealerBot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Player{

    //FIELDS
    List<Card> cardsInHand;
    String name;
    boolean isAI;


    //CONSTRUCTORS
    public Player() {
        //no arg constructor
    }

    public Player(List<Card> cardsInHand, String name, boolean isAI) {
        this.cardsInHand = cardsInHand;
        this.name = name;
        this.isAI = isAI;

    }

    //BEHAVIORAL METHODS
    public abstract Card playCard(Card cardToMatch, boolean hasValidCard, Card newCard, Card card, boolean isUserCardInHand);

    public boolean checkCard(Card cardToMatch) {
        boolean hasValidCard = false;

        for(Card card : cardsInHand) {
            if (card.getColor() == cardToMatch.getColor() || card.getNumber() == cardToMatch.getNumber()) {
                hasValidCard = true;
            }
        }
        return hasValidCard;
    }

    public void addCard(Card newCard) {
        cardsInHand.add(newCard);
    }

    //ACCESSOR METHODS
    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(List<Card> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }
}