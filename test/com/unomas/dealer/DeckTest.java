package com.unomas.dealer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeckTest {

    Deck testDeck;

    @Before
    public void init(){
        testDeck = Deck.getInstance();
    }


    @Test
    public void generateDeck_shouldCreateDeck_withEightyCards(){
        assertEquals( 80, testDeck.getAllCardsInDeck().size());
    }

    @Test
    public void generateDeck_shouldCreateDeck_with_twoSetOfAnyCards(){
        int testNumber = 8;
        Card.CardColor color = Card.CardColor.BLUE;
        int counter = 0;
        for (Card card : testDeck.getAllCardsInDeck()){
            if (card.getColor() == color && card.getNumber() == testNumber){
                counter += 1;
            }
        }
        assertEquals(2, counter);
    }

    @Test
    public void drawOneCardFromDeck_shouldReturnOneCard_andReduceCardInDeckQty(){
        Card card = testDeck.drawOneCardFromDeck();
        assertEquals(79, testDeck.getAllCardsInDeck().size());
    }

}