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
    public boolean active = true;
    public static int acePoints;                    //How many points the Ace is being counted as (1/11), used for calculating if a hand is soft or hard.

    Hand(ArrayList<Card> cards) {
        this.hand = cards;
    }

    public static int getPoints(Hand inputtedHand){
        int points = 0;

       // for (Card card : inputtedHand.hand){
          for(int i=0; i<inputtedHand.hand.size(); i++){
            if(!inputtedHand.hand.get(i).name.equals("Ace")){
                if((!inputtedHand.hand.get(i).name.equals("King")) && (!inputtedHand.hand.get(i).name.equals("Queen")) && (!inputtedHand.hand.get(i).name.equals("Jack")))
                    points += Integer.parseInt(inputtedHand.hand.get(i).name);
                else
                    points += 10;
            }
            else{
                Hand tempHand = copyHandWithoutAces(copyHand(inputtedHand));

                if(Hand.getPoints(tempHand) <= 10) {
                    points += 11;
                    acePoints = 11;
                }
                else {
                    points += 1;
                    acePoints = 1;
                }

            }
        }

        return points;
    }

    public static Hand copyHand(Hand inputtedHand){
        ArrayList<Card> tempList = new ArrayList<>();
        for(int i = 0; i < inputtedHand.hand.size(); i++){
            tempList.add(Card.copyCard(inputtedHand.hand.get(i)));
        }

        Hand tempHand = new Hand(tempList);

        return tempHand;
    }

    public static Hand copyHandWithoutAces(Hand inputtedHand){
        ArrayList<Card> tempList = new ArrayList<>();
        for(int i = 0; i < inputtedHand.hand.size(); i++){
            tempList.add(Card.copyCard(inputtedHand.hand.get(i)));
        }

        for(int i = 0; i < tempList.size(); i++){
            if(tempList.get(i).name.equals("Ace"))
                tempList.remove(tempList.get(i));
        }

        Hand tempHand = new Hand(tempList);

        return tempHand;
    }

    public static String printFullHand(Hand inputtedHand){
        String result = "";
        for (Card card : inputtedHand.hand){
            result += card.suit.symbol;
            result += card.name;
            result += ", ";
        }

        return result;
    }

    public static Hand clearHand(Hand inputtedHand){
        ArrayList<Card> tempList = new ArrayList<>();
        for(int i = 0; i < inputtedHand.hand.size(); i++){
            tempList.add(Card.copyCard(inputtedHand.hand.get(i)));
        }

        inputtedHand.hand.removeAll(tempList);

        return inputtedHand;
    }
}