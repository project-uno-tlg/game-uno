package com.unomas.player;

import com.apps.util.Prompter;
import com.unomas.dealer.Card;
import com.unomas.dealer.Deck;
import com.unomas.dealer.DealerBot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HumanPlayer extends Player {

    public HumanPlayer(List<Card> cardsInHand, String name, boolean isAI) {
        super(cardsInHand, name, isAI);
    }

    @Override
    public Card playCard(Card cardToMatch, boolean hasValidCard, Card newCard, Card userCard, boolean isUserCardInHand) {

        Card playedCard = null;        //initialize the card to be played that is prompted by the user
        int indicator = 0;
        Prompter prompter = new Prompter(new Scanner(System.in));
        String infoText = "It is your turn. What card do you want to play?";
        String promptColorText = "Please enter the color of the card you want to play.";
        String promptNumberText = "Please enter the number of the card you want to play.";
        String retryText = "That is not a valid input. Please try again.";

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

            while (indicator == 0) {
                checkCard(cardToMatch);
                if (hasValidCard) {

                    prompter.info(infoText);        //prompts the user that it is their turn and asks them to play a card
                    colorInput = prompter.prompt(promptColorText);      //asks which color
                    numberInput = prompter.prompt(promptNumberText);    //asks which number

                    Card.CardColor cardColor = Card.CardColor.valueOf(colorInput);    //converts the input string color to an enum
                    int userInputNumber = Integer.parseInt(numberInput);                //converts the input string number to a int

                    Card.getInstance(userCard.getColor(), userInputNumber);

                    if (cardsInHand.contains(userCard)) {
                        checkIfCardInHand(cardToMatch, userCard);
                        if (isUserCardInHand = true) {
                            playedCard = userCard;
                            cardsInHand.remove(userCard);
                            //output the card the human played
                            indicator++;
                        }
                    }
                    else if (colorInput.equalsIgnoreCase("quit") || numberInput.equalsIgnoreCase("quit")) {
                        Card.getQuitCard();
                        indicator++;        //possibly not needed depending on getQuitCard() method
                    }
                    else {
                        prompter.info(retryText);
                    }

                }
                else {          //if player does not have a hasValidCard have the dealer distribute a card to that player
                    playedCard = null;
                    addCard(newCard);
                }
            }


        return playedCard;
    }

    public boolean checkIfCardInHand(Card cardToMatch, Card userCard) {
        boolean isUserCardInHand = false;
        if (userCard.getNumber() == cardToMatch.getNumber() && userCard.getColor() == cardToMatch.getColor()) {
            isUserCardInHand = true;
        }
        return isUserCardInHand;
    }
}