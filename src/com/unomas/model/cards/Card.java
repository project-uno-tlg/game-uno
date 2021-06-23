package com.unomas.model.cards;

public class Card {

    private String action = "null";
    private CardColor color;
    private int number;
    private boolean wannaQuit = false;

    private Card(CardColor color, int number){
        setColor(color);
        setNumber(number);
    }

    private Card(CardColor color, String action){
        setColor(color);
        setAction(action);
    }


    private Card(){
        this.wannaQuit = true;
    }

    public static Card getInstance(CardColor color, int number){
        return new Card(color, number);
    }

    public static Card getInstance(CardColor color, String action){
        return new Card(color, action);
    }

    public static Card getQuitCard(){
        return new Card();
    }

    public boolean wannaQuit(){
        return wannaQuit;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {

        this.color = color;
    }

    public String getAction() {
        return action;
    }

    private void setAction(String action) {
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws IllegalArgumentException {
        if (number < 0 || number > 9){
            throw new IllegalArgumentException("Card number can only be 0-9");
        }
        this.number = number;
    }

    @Override
    public String toString() {
        return color + " " + number;
    }

    public enum CardColor{
        RED,
        YELLOW,
        BLUE,
        GREEN;
    }
}