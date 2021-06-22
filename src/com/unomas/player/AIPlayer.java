package com.unomas.player;

import com.unomas.dealer.Card;
import com.unomas.dealer.DealerBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AIPlayer extends Player {

    public AIPlayer(String name, boolean isAI) {
        super(name, isAI);
    }

    @Override
    public Card playCard() {
        List<Card> playableCards = new ArrayList<>();
        int maxIndex = 0;
        Card playedCard;        //initialize the card to be played by the AI
        boolean hasValidCard;

        Card cardToMatch = DealerBot.getInstance().getCardToMatch();
        hasValidCard = checkCard(cardToMatch);

        if (hasValidCard) { //assumes == true
            //choose a random playable card to be placed down
            //set cardToMatch equals to the chosen card
            //output what card was played
            //remove played card from cardsInHand
            for (Card card : cardsInHand) {
                if (card.getColor() == cardToMatch.getColor() || card.getNumber() == cardToMatch.getNumber()) { //card color == cardToMatch color || card number == cardToMatch number
                    playableCards.add(card);
                }
            }

            maxIndex = playableCards.size();
            playedCard = playableCards.get((int) (Math.random() * (maxIndex)));
            cardsInHand.remove(playedCard);
        }

        else {          //if player does not have a hasValidCard have the dealer distribute a card to that player
            playedCard = null;
        }

        return playedCard;
    }
}