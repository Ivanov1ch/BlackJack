/*
// File:             Card.java
// Created:          2018/04/05
// Author:           danIv (Daniel Ivanovich)
// Description:      .
*/


public class Card {
  public int points;
  public int cardNumber;
  public Suit suit;
  
  public enum Suit
  {
      DIAMONDS("\u2660"),
      HEARTS("\u2660"),
      SPADES("\u2660"),
      CLUBS("\u2660");
      
      public final String symbol;
      
      Suit(String character)
      {
        this.symbol = character;
      }
  }
  
  public static Card initCard(int number, Suit newSuit){
  
    Card card = new Card();
    card.cardNumber = number;
    
    card.suit = newSuit;
  
    return card;
  }
}