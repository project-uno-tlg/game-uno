package com.unomas.client;

import com.unomas.game.Game;

public class UnoClient {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.start();
    }
}