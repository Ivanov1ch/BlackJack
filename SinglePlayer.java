/*
// File:             SinglePlayer.java
// Created:          2018/04/05
// Author:           danIv
// Description:      The single player game.
*/
 
import java.util.Random;
import java.util.Scanner;
 
public class SinglePlayer {
  public static void main (String[] args){
    Random gen = new Random();
    Scanner reader = new Scanner(System.in);
    Dealer dealer = new Dealer();
 
    System.out.println("Welcome to the table, What is your name and how much money do you have you must have at least 10 dollars to begin?");
    String name = reader.nextLine();
    double money = reader.nextDouble();
 
    if(money<10.00)
      System.out.println("You do not have enough money.");
 
    reader.nextline();
 
    System.out.println("Enter your wager:");
    double wager = reader.nextDouble();
 
    dealer.main(null); //Init the deck
    dealer.shuffleDeck(dealer.deck);
 
    Hand hand = new Hand();
 
    hand.initHand(dealer.dealStartingCards(dealer.deck));
 
    System.out.println(hand); //tell the player what cards they have
 
    System.out.println("(H)it, (St)and, (Sp)lit, (D)ouble, (Su)rrender");
    String choice = reader.nextLine();
 
    if(choice.equals("H"))
      //Deal one card to player
    else if(choice.equals("D"))
      //Deal one card to player and double their wager
    else if(choice.equals("St"))
      //End the turn of the player
    else if(choice.equals("Sp"))
      //Split the player's hand into two hands
    else if(choice.)
  }
}