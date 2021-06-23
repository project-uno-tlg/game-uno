package com.unomas.model.player;

import com.unomas.model.cards.Card;
import com.unomas.controller.Dealer;

import java.util.ArrayList;
import java.util.List;

class AIPlayer extends Player {

    public AIPlayer(String name, boolean isAI) {
        super(name, isAI);
    }

    @Override
    public Card playCard() {
        List<Card> playableCards = new ArrayList<>();
        int maxIndex = 0;
        Card playedCard;
        boolean hasValidCard;

        Card cardToMatch = Dealer.getInstance().getCardToMatch();
        hasValidCard = checkCard(cardToMatch);

        if (hasValidCard) {
            for (Card card : cardsInHand) {
                if (card.getColor() == cardToMatch.getColor() || card.getNumber() == cardToMatch.getNumber()) {
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