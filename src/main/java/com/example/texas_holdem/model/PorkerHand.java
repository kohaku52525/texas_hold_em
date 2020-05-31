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


    private final String name;
    private final int power;
    private int num;

    PorkerHand(String name, int power) {
        this.name = name;
        this.power = power;
    }

    /**
     * 役の名前を取得する
     *
     * @return 役の名前
     */
    public String getName() {
        return name;
    }

    /**
     * 役の強さを取得する
     *
     * @return 役の強さ
     */
    public int getPower() {
        return power;
    }

}
