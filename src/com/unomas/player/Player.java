package com.unomas.player;

import java.awt.*;
import java.util.Collection;

public abstract class Player {

    //FIELDS
    Collection<Card> cardsInHand;
    String name;
    boolean isAI;           //is this needed????
    boolean isInTurn;       //same here

    //CONSTRUCTORS
    public Player() {
        //no arg constructor
    }

    public Player(Collection<Card> cardsInHand, String name, boolean isAI, boolean isInTurn) {
        setCardsInHand(cardsInHand);
        setName(name);
        setAI(isAI);
        setInTurn(isInTurn);
    }

    //BEHAVIORAL METHODS
    public abstract Card playCard(cardToMatch);

    public abstract boolean checkCard(Card);


    //ACCESSOR METHODS

    public Collection<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(Collection<Card> cardsInHand) {
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

    public boolean isInTurn() {
        return isInTurn;
    }

    public void setInTurn(boolean inTurn) {
        isInTurn = inTurn;
    }
}