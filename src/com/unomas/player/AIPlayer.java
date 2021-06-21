package com.unomas.player;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.Scanner;

class AIPlayer extends Player{

    @Override
    public Card playCard(cardToMatch) {
        Collection<Card> playableCards;

        checkCard();

        if(validCard == true) {
            //choose a random playable card to be placed down
            //set cardToMatch equals to the chosen card
            //output what card was played
            //remove played card from cardsInHand
            for(Card card : cardsInHand) {
                //TODO: update if qualification based on Peng's Card collection
                if () {//card color == cardToMatch color || card number == cardToMatch number
                    playableCards.add(card);
                }
            }

            cardsInHand.remove(playedCard);

        }
        else {          //if player does not have a validCard have the dealer distribute a card to that player
            distributeCard();
        }

        //go to next turn (updateCurrentPlayer)

        return null;
    }

    @Override
    public boolean checkCard(Card) {
        boolean validCard = false;
        Collection<Card> playableCards;

        for(Card card : cardsInHand) {
            if () {        //if card color == cardToMatch color || card number == cardToMatch number
                validCard = true;
                playableCards.add(card);
            }
        }
        return validCard;
    }
}