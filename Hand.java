/*
// File:             Hand.java
// Created:          2018/04/05
// Author:           danIv (Daniel Ivanovich)
// Description:      The class for a hand of cards.
*/

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> hand;

    Hand(ArrayList<Card> cards) {
        this.hand = cards;
    }

    public static int getPoints(Hand inputtedHand){
        List<Card> cards = inputtedHand.hand;
        int points = 0;

        for (Card card : cards){
            if(card.cardNumber != 13){
                if(card.cardNumber <= 9)
                    points += card.cardNumber;
                else
                    points += 10;
            }
            else{
                inputtedHand.hand.remove(card);

                if(Hand.getPoints(inputtedHand) <= 10)
                    points += 11;
                else
                    points += 1;

                inputtedHand.hand.add(card);
            }
        }

        return points;
    }
}