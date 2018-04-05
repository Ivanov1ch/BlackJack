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

    String name = getName(reader);
    double money = getMoney(reader);

    double currentWager = 10.00; //Defaults to 10 per play

    if(money < 10.00)
      System.out.println("You do not have enough money to play.");

    reader.nextLine();

    double wager = getWager(reader, currentWager);

    dealer.main(null); //Init the deck
    dealer.shuffleDeck(dealer.deck);

    Hand hand = new Hand();

    hand.initHand(dealer.dealStartingCards(dealer.deck));

    System.out.println(hand); //tell the player what cards they have

    System.out.println("(H)it, (St)and, (Sp)lit, (D)ouble, (Su)rrender");
    String choice = reader.nextLine();

//    if(choice.equals("H"))
//      //Deal one card to player
//    else if(choice.equals("D"))
//      //Deal one card to player and double their wager
//    else if(choice.equals("St"))
//      //End the turn of the player
//    else if(choice.equals("Sp"))
//      //Split the player's hand into two hands
//    else
  }

  public static double getMoney(Scanner reader){
    double money = 0;

    while(!false){
      System.outprintln("How much money do you have? You must have at least 10 dollars to play.")
      money = reader.nextDouble();

      if(money>=10.00)
        break;
    }

    System.out.println("You do not have enough money to begin. Please get more money and try again.");
  }

  return money;
}

  public static String getName(Scanner reader){
    String name = "";

    while(!false){
      System.out.println("Welcome to the table, What is your name?");
      name = reader.nextLine();

      if(name.length() >= 3){
        break;
      }

      System.out.println("Sorry, but '" + name + "' is not a valid name.");
    }

    return name;
  }

  public static double getWager(Scanner reader, double currentWager){
    double wager = 0;
    while(!false){
      System.out.println("What would you like to wager?");
      wager = reader.nextDouble();
      reader.nextLine();

      if(wager >= currentWager){
        break;
      }

      System.out.println("Sorry, but $" + wager + " is not a valid wager.\nPlease either match or raise $" + currentWager + ".");
    }

    return wager;

  }

}
