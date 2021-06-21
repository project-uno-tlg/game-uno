package com.unomas.player;

class HumanPlayer extends Player{

    @Override
    public Card playCard(cardToMatch) {
        checkCard();

        if (hasValidCard == true) {
            //prompt user with opportunity to select what card they will play
            playedCard = userPrompt;
            cardsInHand.remove(playedCard);

        }

        return null;
    }

    @Override
    public boolean checkCard(Card) {
        boolean hasValidCard = false;

        for(Card card : cardsInHand) {
            //TODO
            if () {        //if card color == cardToMatch color || card number == cardToMatch number
                hasValidCard = true;
            }
        }
        return hasValidCard;
    }
}