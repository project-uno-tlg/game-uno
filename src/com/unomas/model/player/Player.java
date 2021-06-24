package com.unomas.model.player;

import com.unomas.model.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player{

    //FIELDS
    public List<Card> cardsInHand = new ArrayList<>();
    public String name;
    public boolean isAI = false;

    //CONSTRUCTORS
    public Player(String name) {
        this.name = name;
    }

    public Player(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
    }

    //BEHAVIORAL METHODS
    public abstract Card playCard();

    public boolean checkCard(Card cardToMatch) {
        boolean hasValidCard = false;

        for(Card card : cardsInHand) {
            if (card.getColor() == cardToMatch.getColor() ||
                    card.getNumber() == cardToMatch.getNumber() && card.getNumber() != -1 ||
                    card.getAction().equalsIgnoreCase(cardToMatch.getAction()) &&
                            !"null".equalsIgnoreCase(cardToMatch.getAction()) ||
                    "WILD".equalsIgnoreCase(cardToMatch.getAction()) ||
                    "WILD".equalsIgnoreCase(card.getAction()) ||
                    "WILD+4".equalsIgnoreCase(cardToMatch.getAction()) ||
                    "WILD+4".equalsIgnoreCase(card.getAction())
            ){
                hasValidCard = true;
                break;
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