package com.unomas.model.player;

import com.apps.util.Prompter;
import com.unomas.model.cards.Card;
import com.unomas.controller.Dealer;

import java.util.Scanner;

class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Card playCard() {

        Card cardToMatch = Dealer.getInstance().getCardToMatch();
        boolean hasValidCard;
        Card playedCard = null;        //initialize the card to be played that is prompted by the user
        Prompter prompter = new Prompter(new Scanner(System.in));
        String infoText = "What card do you want to play?";
        String promptText = "Enter the number next to the card you want to play.";
        String retryText = "That is not a valid input. Please try again.";

        String cardInput;

            while (true) {

                hasValidCard = checkCard(cardToMatch);

                if (hasValidCard) {

                    prompter.info(infoText);        //prompts the user that it is their turn and asks them to play a card
                    cardInput = prompter.prompt(promptText);

                    if ("quit".equalsIgnoreCase(cardInput)) {
                        playedCard = Card.getQuitCard();
                        break;
                    }

                    int userCardInput;
                    try {
                        userCardInput = Integer.parseInt(cardInput);
                    }catch (IllegalArgumentException ignored){
                        prompter.info(retryText);
                        continue;
                    }

                    Card promptedCard = cardsInHand.get(userCardInput - 1);
                    boolean doesPromptedCardMatchCardToMatch = checkPromptedCard(cardToMatch, promptedCard);
                    if (doesPromptedCardMatchCardToMatch) {
                        playedCard = promptedCard;
                        cardsInHand.remove(promptedCard);
                        break;
                    }

                    prompter.info(retryText);
                }
                else {          //if player does not have a hasValidCard have the dealer distribute a card to that player
                    playedCard = null;
                    break;
                }
            }
        return playedCard;
    }


    public boolean checkPromptedCard(Card cardToMatch, Card promptedCard) {
        boolean doesPromptedCardMatch = false;
        if(promptedCard.getNumber() == cardToMatch.getNumber() ||
                promptedCard.getColor() == cardToMatch.getColor() ||
                promptedCard.getAction().equalsIgnoreCase(cardToMatch.getAction()) &&
                        !"null".equalsIgnoreCase(promptedCard.getAction())
        ) {
            doesPromptedCardMatch = true;
        }
        return  doesPromptedCardMatch;
    }

}