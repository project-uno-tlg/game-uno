package com.unomas.client;

import com.unomas.game.Game;

class UnoClient {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.start();
    }
}