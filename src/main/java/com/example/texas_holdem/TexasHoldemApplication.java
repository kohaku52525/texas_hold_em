package com.example.texas_holdem;

import com.example.texas_holdem.model.Suit;
import com.example.texas_holdem.model.Trump;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TexasHoldemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TexasHoldemApplication.class, args);
        List<Trump> trumpList = new ArrayList<>();
        List<Trump> hand = new ArrayList<>();
        List<Trump> field = new ArrayList<>();

        for (int i = 1; i < 14; i++) {
            trumpList.add(new Trump(i, Suit.CLUB));
            trumpList.add(new Trump(i, Suit.HEART));
            trumpList.add(new Trump(i, Suit.DIA));
            trumpList.add(new Trump(i, Suit.SPADE));
        }

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

        List<Checker.HandsWithMaxNum> hands = new ArrayList<>();
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

        Checker.HandsWithMaxNum maxHands = hands.get(0);
        for (int i = 1; i < hands.size(); i++) {
            if (maxHands.getHands().getPower() < hands.get(i).getHands().getPower()) {
                maxHands = hands.get(i);
            } else if (maxHands.getHands().getPower() == hands.get(i).getHands().getPower()) {
                if (maxHands.getMax() > hands.get(i).getMax()) {
                    maxHands = hands.get(i);
                }
            }
        }
        System.out.println(maxHands.getHands().getHand());
    }
}
