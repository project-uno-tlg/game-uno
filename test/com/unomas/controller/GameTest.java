package com.unomas.controller;

import com.unomas.model.player.Player;
import junit.framework.TestCase;

import java.util.List;

public class GameTest extends TestCase {
    Game game = new Game();
    List<Player> playerList;

//    public void testSetPlayerCount() { // You will have to change setPlayerCount() to return an int to test.
//        assertEquals(4, game.setPlayerCount(4));
//    }
//
//    public void testSetPlayerCount_WithNumberGreaterThan6() {
//        assertEquals(5, game.setPlayerCount(100));
//    }
//
//    public void testSetPlayerCount_WithNumberZeroOrLessThanZero() {
//        assertEquals(System.exit(0), game.setPlayerCount(0));
//    }
//
//    public void testStart_IfPlayerListIsFilled() {
//        playerList = game.start();
//        for (Player plyr: playerList) {
//            System.out.println(plyr);
//        }
//    }
//
//    public void testStart_IfPlayerListHasComputerPlayers() {
//        playerList = game.start();
//        for (Player plyr: playerList) {
//            if (plyr.getName().startsWith("comp")){
//                System.out.println(plyr);
//            }
//        }
//    }
//
//    public void testStart_IfPlayerListHasHumanPlayer() {
//        playerList = game.start();
//        for (Player plyr: playerList) {
//            if (!plyr.getName().startsWith("comp")){
//                System.out.println(plyr);
//            }
//        }
//    }
}