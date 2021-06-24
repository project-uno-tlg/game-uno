package com.unomas.model.player;

import com.unomas.model.cards.Card;
import com.unomas.controller.Dealer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AIPlayer extends Player {

    public AIPlayer(String name, boolean isAI) {
        super(name, isAI);
    }

    @Override
    public Card playCard() {
        List<Card> playableCards;
        int maxIndex = 0;
        Card playedCard;
        boolean hasValidCard;

        Card cardToMatch = Dealer.getInstance().getCardToMatch();
        hasValidCard = checkCard(cardToMatch);

        if (hasValidCard) {

            playableCards = cardsInHand.stream()
                    .filter(card -> card.getColor() == cardToMatch.getColor() ||
                            card.getNumber() == cardToMatch.getNumber() && card.getNumber() != -1 ||
                            card.getAction().equalsIgnoreCase(cardToMatch.getAction()) &&
                                    !"null".equalsIgnoreCase(cardToMatch.getAction()) ||
                                    "WILD".equalsIgnoreCase(cardToMatch.getAction()) ||
                                    "WILD".equalsIgnoreCase(card.getAction()) ||
                                    "WILD+4".equalsIgnoreCase(cardToMatch.getAction()) ||
                                    "WILD+4".equalsIgnoreCase(card.getAction())
                            )
                    .collect(Collectors.toList());

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