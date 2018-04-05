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

    dealStartingCards(deck);
  }

  public static void shuffleDeck(int[] deck){

    Random random = new Random();

    for(int i = 0; i < 10000; i++){

      int indexToChange = random.nextInt(deck.length - 1);
      int indexToChangeTo = random.nextInt(deck.length - 1);

      swap(deck, indexToChange, indexToChangeTo);

    }

  }

  public static void swap(int[] deck, int indexToChange, int indexToChangeTo) {

    int temp = deck[indexToChange];
    deck[indexToChange] = deck[indexToChangeTo];
    deck[indexToChangeTo] = temp;

  }
  
  public static int[] dealStartingCards(int[] deck){
    int cardOne = deck[0];
    int cardTwo = deck[1];
    
    removeCard(deck);
    removeCard(deck);
    
    int[] hand = {cardOne, cardTwo};
    return hand;
  }
  
  public static void removeCard(int[] deck){
    int removeIndex = 0;

    for(int i = removeIndex; i < deck.length -1; i++){
      deck[i] = deck[i + 1];
    }
    
    deck[deck.length - 1] = 0;
  }

}