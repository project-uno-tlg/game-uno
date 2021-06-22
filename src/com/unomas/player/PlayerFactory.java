package com.unomas.player;

import com.unomas.player.Player;

public class PlayerFactory {

    public static Player createPlayer(String name){
        String indicator = name.substring(0,8);
        if (name.equals(indicator)){
            return new AIPlayer();
        }else {
            return new HumanPlayer();
        }


    }
}