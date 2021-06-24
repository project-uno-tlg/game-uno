package com.unomas.model.player;

import junit.framework.TestCase;
import org.junit.Before;

public class PlayerFactoryTest extends TestCase {

    Player hPlayer;
    Player cPlayer;

    @Override
    @Before
    public void setUp() {
        hPlayer  = new HumanPlayer("Jack");
        cPlayer  = new AIPlayer("computer1",true);
    }


    public void testCreateHumanPlayer() {
        assertEquals(hPlayer.getName(), PlayerFactory.createPlayer("Jack",false).getName());
    }
    public void testCreateAIPlayer() {
        assertEquals(cPlayer.getName(), PlayerFactory.createPlayer("computer1",true).getName());
    }
}