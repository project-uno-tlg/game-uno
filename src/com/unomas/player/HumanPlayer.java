package com.unomas.player;

import com.apps.util.Prompter;
import com.unomas.dealer.Card;
import com.unomas.dealer.DealerBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public Card playCard() {

        Card cardToMatch = DealerBot.getInstance().getCardToMatch();
        boolean hasValidCard;
        boolean isUserCardInHand;

        Card playedCard = null;        //initialize the card to be played that is prompted by the user
        Prompter prompter = new Prompter(new Scanner(System.in));
        String matchCard = "Match this card.";
        String yourCards = "You have " + cardsInHand.size() + " cards in hand";
        String infoText = "It is your turn. What card do you want to play?";
        String promptColorText = "Please enter the color of the card you want to play.";
        String promptNumberText = "Please enter the number of the card you want to play.";
        String retryText = "That is not a valid input. Please try again.";
//        String noValidCards = "There are no valid cards in your hand. You must draw a card.";

        String colorInput;
        String numberInput;

            //while loop
            //checkCard to make sure player has any valid cards
            //if yes
            //prompt user what card they want to play
            //convert string
            //check if this is in their hand
            //check if this is a hasValidCard
            //if not valid try again message
            //else remove card and break out of the loop
            //if not

            while (true) {
                prompter.info(matchCard + cardToMatch);
                prompter.info(yourCards + cardsInHand);

                hasValidCard = checkCard(cardToMatch);
                if (hasValidCard) {

                    prompter.info(infoText);        //prompts the user that it is their turn and asks them to play a card
                    colorInput = prompter.prompt(promptColorText).toUpperCase();      //asks which color
                    numberInput = prompter.prompt(promptNumberText);    //asks which number

                    if (colorInput.equalsIgnoreCase("quit") || numberInput.equalsIgnoreCase("quit")) {
                        playedCard = Card.getQuitCard();
                        break;
                    }
                    int userInputNumber;
                    Card.CardColor cardColor;
                    try {
                        cardColor = Card.CardColor.valueOf(colorInput);      //converts the input string color to an enum
                        userInputNumber = Integer.parseInt(numberInput);
                    }catch (IllegalArgumentException ignored){
                        prompter.info(retryText);
                        continue;
                    }
                           //converts the input string number to a int

                    boolean tracker = false;
                    for (Card card : cardsInHand) {
                        isUserCardInHand = checkIfCardInHand(card, userInputNumber, cardColor);
                        if (isUserCardInHand) {
                            playedCard = card;
                            cardsInHand.remove(card);
                            tracker = true;
                            break;
                        }
                    }

                    if (tracker) {
                        break;
                    }

                    prompter.info(retryText);
                }
                else {          //if player does not have a hasValidCard have the dealer distribute a card to that player
//                    prompter.info(noValidCards);
                    playedCard = null;
                    break;
                }
            }
        return playedCard;
    }

    public boolean checkIfCardInHand(Card card, int userInputNumber, Card.CardColor cardColor) {
        boolean isUserCardInHand = false;
        if (userInputNumber == card.getNumber() && cardColor == card.getColor()) {
            isUserCardInHand = true;
        }
        return isUserCardInHand;
    }
}