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
        boolean isUserCardInHand;

        Card playedCard = null;        //initialize the card to be played that is prompted by the user
        Prompter prompter = new Prompter(new Scanner(System.in));
//        String matchCard = "Match this card.";
//        String yourCards = "You have " + cardsInHand.size() + " cards in hand";
        String infoText = "What card do you want to play?";
        String promptText = "Enter the number next to the card you want to play.";
        String retryText = "That is not a valid input. Please try again.";

        //String promptColorText = "Please enter the color of the card you want to play.";
        //String promptNumberText = "Please enter the number of the card you want to play.";

//        String noValidCards = "There are no valid cards in your hand. You must draw a card.";


        String cardInput;

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
//                prompter.info(matchCard + cardToMatch);
//                prompter.info(yourCards + cardsInHand);

                hasValidCard = checkCard(cardToMatch);
                if (hasValidCard) {

                    prompter.info(infoText);        //prompts the user that it is their turn and asks them to play a card
                    cardInput = prompter.prompt(promptText);


                    //colorInput = prompter.prompt(promptColorText).toUpperCase();      //asks which color
                    //numberInput = prompter.prompt(promptNumberText);    //asks which number

                    if (cardInput.equalsIgnoreCase("quit")) { //colorInput.equalsIgnoreCase("quit") || numberInput.equalsIgnoreCase("quit")
                        playedCard = Card.getQuitCard();
                        break;
                    }

                    int userCardInput;

                    //int userInputNumber;
                    //Card.CardColor cardColor;
                    try {
                        userCardInput = Integer.parseInt(cardInput);

                        //cardColor = Card.CardColor.valueOf(colorInput);      //converts the input string color to an enum
                        //userInputNumber = Integer.parseInt(numberInput);
                    }catch (IllegalArgumentException ignored){
                        prompter.info(retryText);
                        continue;
                    }
                           //converts the input string number to a int

                    boolean tracker = false;
                    for (Card card : cardsInHand) {
                        //isUserCardInHand = checkIfCardInHand(card, userInputNumber, cardColor);     //change parameter to card and userCardInput
                        Card promptedCard = cardsInHand.get(userCardInput - 1);
                        //potentially not needed
                        isUserCardInHand = checkIfCardInHand(card, promptedCard);

                        boolean doesPromptedCardMatchCardToMatch = checkPromptedCard(cardToMatch, promptedCard);
                        if (doesPromptedCardMatchCardToMatch) {
                            playedCard = promptedCard;
                            cardsInHand.remove(promptedCard);
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


    //might not even need to checkIfCardInHand
    public boolean checkIfCardInHand(Card card, Card promptedCard) {    //Card card, int userInputNumber, Card.CardColor cardColor
        boolean isUserCardInHand = false;
        //if (userInputNumber == card.getNumber() && cardColor == card.getColor()) {
            //isUserCardInHand = true;
        //}
        if (promptedCard.getNumber() == card.getNumber() && promptedCard.getColor() == card.getColor()) {
            isUserCardInHand = true;
        }

        return isUserCardInHand;
    }

    public boolean checkPromptedCard(Card cardToMatch, Card promptedCard) {
        boolean doesPromptedCardMatch = false;
        if(promptedCard.getNumber() == cardToMatch.getNumber() || promptedCard.getColor() == cardToMatch.getColor()) {
            doesPromptedCardMatch = true;
        }
        return  doesPromptedCardMatch;
    }

}