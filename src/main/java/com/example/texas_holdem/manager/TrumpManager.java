package com.example.texas_holdem.manager;

import com.example.texas_holdem.model.Suit;
import com.example.texas_holdem.model.Trump;

import java.util.List;

/**
 * トランプの操作に関する処理をまとめる
 */
public class TrumpManager {

    /**
     * トランプの初期化を行う
     */
    public void initTrump(List<Trump> trumpList) {
        for (int i = 1; i < 14; i++) {
            trumpList.add(new Trump(i, Suit.CLUB));
            trumpList.add(new Trump(i, Suit.HEART));
            trumpList.add(new Trump(i, Suit.DIA));
            trumpList.add(new Trump(i, Suit.SPADE));
        }
    }

}
