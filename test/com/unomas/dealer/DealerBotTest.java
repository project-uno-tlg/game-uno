package com.unomas.dealer;

import org.junit.Before;
import org.junit.Test;
import com.unomas.dealer.DealerBot.Player;

import java.util.Arrays;
import java.util.List;

public class DealerBotTest {
    // update this later
    List<Player> players;
    DealerBot dealerBot;

    @Before
    public void init(){
        players = Arrays.asList(
                new Player("testA", Arrays.asList(
                        Card.getInstance(Card.CardColor.BLUE, 9),
                        Card.getInstance(Card.CardColor.YELLOW, 7)
                )),
                new Player("testB", Arrays.asList(
                        Card.getInstance(Card.CardColor.BLUE, 9),
                        Card.getInstance(Card.CardColor.YELLOW, 6)
                )),
                new Player("testC", Arrays.asList(
                        Card.getInstance(Card.CardColor.RED, 9),
                        Card.getInstance(Card.CardColor.YELLOW, 7)
                )));
        dealerBot = DealerBot.getInstance(players);
    }

    @Test
    public void randomPlayer_returnsCorrectNumberInRange(){

    }
}