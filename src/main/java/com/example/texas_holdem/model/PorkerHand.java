package com.example.texas_holdem.model;

public enum PorkerHand {
    NO_PAIR("ブタ", 0),
    ONE_PAIR("ワンペア", 1),
    TWO_PAIR("ツーペア", 2),
    THREE_CARD("スリーカード", 3),
    STRAIGHT("ストレート", 4),
    FLASH("フラッシュ", 5),
    FULL_HOUSE("フルハウス", 6),
    FOUR_CARD("フォーカード", 7),
    STRAIGHT_FLASH("ストレートフラッシュ", 8),
    LOYAL_STRAIGHT_FLASH("ロイヤルストレートフラッシュ", 9);


    private final String hand;
    private final int power;
    private int num;

    PorkerHand(String hand, int power) {
        this.hand = hand;
        this.power = power;
    }

    public String getHand() {
        return hand;
    }

    public int getPower() {
        return power;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
