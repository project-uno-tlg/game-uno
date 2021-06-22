package com.unomas.dealer;

public class Card {

    private CardColor color;
    private int number;
    private Card(CardColor color, int number){
        setColor(color);
        setNumber(number);
    }

    public static Card getInstance(CardColor color, int number){
        return new Card(color, number);
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

    enum CardColor{
        RED,
        YELLOW,
        BLUE,
        GREEN;
    }
}