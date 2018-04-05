/*
// File:             Dealer.java
// Created:          2018/04/05
// Author:           [author]
// Description:      Describe function here.
*/

import java.util.Random;

public class Dealer {
  public static int[] deck = new int[52];
  public static void main (String[] args){
    for(int i = 0; i < deck.length; i++){
      deck[i] = i + 1;
    }
    suffleDeck(deck);
  }

  public static void suffleDeck(int[] deck) {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int change = i + random.nextInt(deck.length - 1) + 1;
            swap(deck, i, change);
        }

        for(int i = 0; i < deck.length; i++){
          System.out.println(deck[i]);
        }
    }

    private static void swap(int[] deck, int i, int change) {
        int helper = deck[i];
        deck[i] = deck[change];
        deck[change] = helper;
    }

}
