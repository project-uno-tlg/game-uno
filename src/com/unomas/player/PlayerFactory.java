package com.unomas.player;

import com.unomas.player.Player;

public class PlayerFactory {

    public static Player createPlayer(String name,  boolean isAI){
        if (isAI){
            return new AIPlayer(name, isAI);
        }else {
            return new HumanPlayer(name);
        }


    }
}