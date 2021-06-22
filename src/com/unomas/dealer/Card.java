package com.unomas.dealer;

public class Card {

    private CardColor color;
    private int number;
    private boolean wannaQuit = false;

    private Card(CardColor color, int number){
        setColor(color);
        setNumber(number);
    }

    private Card(){
        this.wannaQuit = true;
    }

    public static Card getInstance(CardColor color, int number){
        return new Card(color, number);
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
        return "Card{" +
                "color=" + color +
                ", number=" + number +
                '}';
    }

    public enum CardColor{
        RED,
        YELLOW,
        BLUE,
        GREEN;
    }
}