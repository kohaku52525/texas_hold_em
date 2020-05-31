package com.example.texas_holdem;

import com.example.texas_holdem.model.PorkerHand;
import com.example.texas_holdem.model.Role;
import com.example.texas_holdem.model.Suit;
import com.example.texas_holdem.model.Trump;

import java.util.*;

class Checker {
    private List<Integer> numList = new ArrayList<>();
    private Set<Suit> suitList = new HashSet<>();
    private int maxNum = 0;

    Checker(List<Trump> trumpList) {
        init(trumpList);
    }

    /**
     * カードの初期化を行う。
     */
    private void init(List<Trump> list) {
        // フラッシュ・ストレート用にスートと数字で分ける
        for (Trump trump : list) {
            numList.add(trump.getNumber());
            suitList.add(trump.getSuit());
            if (trump.getNumber() > maxNum) {
                maxNum = trump.getNumber();
            }
        }
        // 数字を昇順で並べる
        Collections.sort(numList);
    }

    /**
     * 手札のカードで役を作れるか調べる。
     */
    public Role check(){
        boolean isStraight = isStraight();
        boolean isFlash = isFlash();
        
        PorkerHand porkerHand;
        // ストレートフラッシュの場合
        if (isFlash && isStraight) {
            porkerHand = PorkerHand.STRAIGHT_FLASH;
        }
        // フラッシュの場合
        else if (isFlash) {
            porkerHand = PorkerHand.FLASH;
        }
        // ストレートの場合
        else if (isStraight) {
            porkerHand = PorkerHand.STRAIGHT;
        }
        // その他の役の場合
        else {
            switch (getNumOfType()) {
                case 5:
                    porkerHand = PorkerHand.NO_PAIR;
                    break;

                case 3:
                    porkerHand = getUsedNum() == 4 ? PorkerHand.TWO_PAIR : PorkerHand.THREE_CARD;
                    break;

                case 2:
                    porkerHand = getUsedNum() == 4 ? PorkerHand.FOUR_CARD : PorkerHand.FULL_HOUSE;
                    break;

                default:
                    porkerHand = PorkerHand.ONE_PAIR;
                    break;
            }
        }

        return new Role(porkerHand, maxNum);
    }

    /**
     * ストレートかどうか確認する
     * 
     * @return ストレートならtrue
     */
    private boolean isStraight() {
        for (int i = 0; i < numList.size()-1; i++) {
            if (numList.get(i + 1) - numList.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * フラッシュかどうか判断する
     * 
     * @return フラッシュならtrue
     */
    private boolean isFlash() {
        // 手札の役が1種類ならフラッシュなのでtrue
        return suitList.size() == 1;
    }

    /**
     * numListに存在する数字の種類の数を返す
     *
     * @return numListに存在する数字の種類の数
     */
    private int getNumOfType() {
        return numList.stream().distinct().toArray().length;
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

}
