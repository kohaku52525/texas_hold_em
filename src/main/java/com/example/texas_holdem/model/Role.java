package com.example.texas_holdem.model;

/**
 * ポーカの役と強さを管理する内部クラス
 */
public class Role{
    // ポーカーの役
    private PorkerHand hand;
    // 強さ
    private int level;

    public Role(PorkerHand hand, int level) {
        this.hand = hand;
        this.level = level;
    }

    /**
     * ポーカーの役の種類を取得する
     *
     * @return ポーカーの役の名前
     */
    public PorkerHand getHand() {
        return hand;
    }

    /**
     * ポーカーの強さを取得する。
     *
     * @return ポーカーの強さ
     */
    public int getLevel() {
        return level;
    }
}
