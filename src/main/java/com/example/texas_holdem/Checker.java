package com.example.texas_holdem;

import com.example.texas_holdem.model.PorkerHand;
import com.example.texas_holdem.model.Suit;
import com.example.texas_holdem.model.Trump;

import java.util.*;

class Checker {
    private List<Integer> numList = new ArrayList<>();
    private Set<Suit> suitList = new HashSet<>();
    private int maxNum = 0;

    Checker(List<Trump> trumpList) {
        init();
    }

    /**
     * カードの初期化を行う。
     */
    private void init() {
        // フラッシュ・ストレート用にスートと数字で分ける
        for (Trump trump : trumpList) {
            numList.add(trump.getNumber());
            suitList.add(trump.getSuit());
            if (trump.getNumber() > maxNum) {
                maxNum = trump.getNumber();
            }
        }
        // 数字を降順で並べる
        Collections.sort(numList);
    }

    /**
     * 手札のカードで役を作れるか調べる。
     */
    public HandsWithMaxNum check(){
        boolean isStraight = isStraight();
        boolean isFlash = isFlash();
        if (isFlash && isStraight) {
            return new HandsWithMaxNum(PorkerHand.STRAIGHT_FLASH, maxNum);
        }
        else if (isFlash) {
            return new HandsWithMaxNum(PorkerHand.FLASH, maxNum);
        }
        else if (isStraight) {
            return new HandsWithMaxNum(PorkerHand.STRAIGHT, maxNum);
        }
        else {
            int i = getNumOfType();
            if (i == 5) {
                return new HandsWithMaxNum(PorkerHand.NO_PAIR, maxNum);
            }
            else if (i == 2) {
                if (getUsedNum() == 4) {
                    return new HandsWithMaxNum(PorkerHand.FOUR_CARD, maxNum);
                } else {
                    return new HandsWithMaxNum(PorkerHand.FULL_HOUSE, maxNum);
                }
            }
            else if (i == 3) {
                if (getUsedNum() == 4) {
                    return new HandsWithMaxNum(PorkerHand.TWO_PAIR, maxNum);
                } else {
                    return new HandsWithMaxNum(PorkerHand.THREE_CARD, maxNum);
                }
            }
            else {
                return new HandsWithMaxNum(PorkerHand.ONE_PAIR, maxNum);
            }
        }
    }

    private boolean isStraight() {
        /**
         * ストレートかどうか確認する
         */
        for (int i = 0; i < numList.size()-1; i++) {
            if (numList.get(i + 1) - numList.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isFlash() {
        return suitList.size() == 1;
    }

    private int getNumOfType() {
        Set numSet = new HashSet();
        for (Integer i : numList) {
            numSet.add(i);
        }
        return numSet.size();
    }


    private int getUsedNum() {
        int count = 0;
        for (Integer i : numList) {
            List<Integer> tempList = numList;
            tempList.remove(i);
            if (tempList.contains(i)) {
                count++;
            }
        }
        return count;
    }

    class HandsWithMaxNum{
        PorkerHand hands;
        int max;

        HandsWithMaxNum(PorkerHand hands, int max) {
            this.hands = hands;
            this.max = max;
        }

        PorkerHand getHands() {
            return hands;
        }

        int getMax() {
            return max;
        }
    }
}
