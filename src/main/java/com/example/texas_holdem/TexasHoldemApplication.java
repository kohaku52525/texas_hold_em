package com.example.texas_holdem;

import com.example.texas_holdem.manager.TrumpManager;
import com.example.texas_holdem.model.Suit;
import com.example.texas_holdem.model.Trump;
import com.example.texas_holdem.model.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TexasHoldemApplication {

    public static void main(String[] args) {
        // Spring-Bootを動かすためのコード
        SpringApplication.run(TexasHoldemApplication.class, args);
        startPorker();
    }

    /**
     * ポーカを始める
     */
    private static void startPorker() {
        List<Trump> trumpList = new ArrayList<>();

        // トランプのカードの初期化を行う
        new TrumpManager().initTrump(trumpList);

        List<Trump> hand = new ArrayList<>();
        List<Trump> field = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 2; i++) {
            int randomIndex = rand.nextInt(trumpList.size());
            hand.add(trumpList.get(randomIndex));
            trumpList.remove(randomIndex);
        }

        for (int i = 0; i < 5; i++) {
            int randomIndex = rand.nextInt(trumpList.size());
            field.add(trumpList.get(randomIndex));
            trumpList.remove(randomIndex);
        }

        System.out.println(hand);
        System.out.println(field);

        List<Role> hands = new ArrayList<>();
        for (int i = 0; i < field.size(); i++) {
            for (int j = i + 1; j < field.size(); j++) {
                for (int k = j + 1; k < field.size(); k++) {
                    // フィールドの5枚から3枚選ぶ
                    List<Trump> checkHand = new ArrayList<>(hand);
                    checkHand.add(field.get(i));
                    checkHand.add(field.get(j));
                    checkHand.add(field.get(k));
                    Checker checker = new Checker(checkHand);
                    hands.add(checker.check());
                }
            }
        }

        Role maxHands = hands.get(0);
        for (int i = 1; i < hands.size(); i++) {
            if (maxHands.getHand().getPower() < hands.get(i).getHand().getPower()) {
                maxHands = hands.get(i);
            } else if (maxHands.getHand().getPower() == hands.get(i).getHand().getPower()) {
                if (maxHands.getLevel() > hands.get(i).getLevel()) {
                    maxHands = hands.get(i);
                }
            }
        }
        System.out.println(maxHands.getHand().getName());
    }

}
