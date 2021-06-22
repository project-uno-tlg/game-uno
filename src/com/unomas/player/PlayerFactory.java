package com.unomas.game;

import com.unomas.player.Player;

class PlayerFactory {

    public static Player createPlayer(String name){
        String indicator = name.substring(0,8);
        if (name.equals(indicator)){
            return new AIplayer(indicator);
        }else {
            return new HumanPlayer(indicator);
        }


    }
}