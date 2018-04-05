/*
// File:             Hand.java
// Created:          2018/04/05
// Author:           danIv (Daniel Ivanovich)
// Description:      The class for a hand of cards.
*/
import java.util.List;

public class Hand {
  public List<Card> hand;
  
  Hand(List<Card> cards){
    this.hand = cards;
  }
}