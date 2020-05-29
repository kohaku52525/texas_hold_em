package com.example.texas_holdem.model;

public class Trump {
    /**
     * トランプのモデルクラス
     */

    /* トランプの数字 */
    private int number;

    /* トランプのスート */
    private Suit suit;

    public Trump(int number, Suit suit) {
        this.number = number;
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "{" +
                "number=" + number +
                ", suit=" + suit +
                '}';
    }
}
