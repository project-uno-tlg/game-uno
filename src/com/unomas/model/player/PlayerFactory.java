package com.unomas.model.player;

public class PlayerFactory {

    private PlayerFactory(){};

    public static Player createPlayer(String name,  boolean isAI){
        if (isAI){
            return new AIPlayer(name, isAI);
        }else {
            return new HumanPlayer(name);
        }


    }
}