/*
// File:             SinglePlayer.java
// Created:          2018/04/05
// Author:           danIv
// Description:      The single player game.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Scanner;

public class SinglePlayer {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        String name = getName(reader);
        double money = getMoney(reader);

        if (money == -1) {
            System.exit(10); //10 = Bad money
        }

        boolean playing = true;
        boolean playStillGoing = true;
        boolean won = false;

        while (playing) {
            Dealer.resetDeck(Dealer.deck);
            Dealer.initDeck(Dealer.deck);
            Dealer.shuffleDeck(Dealer.deck);
            double wager;
            int playPoints;

            Hand playerHand = new Hand(new ArrayList<Card>(Dealer.dealStartingCards(Dealer.deck)));

            wager = getWager(reader, 1.00);

            System.out.println("Dealing cards...\n");

            //Wait
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {

            }

            System.out.println("You have recieved a " + playerHand.hand.get(0).suit.symbol + playerHand.hand.get(0).cardNumber + " and a " + playerHand.hand.get(1).suit.symbol + playerHand.hand.get(1).cardNumber);
            System.out.println(Hand.getPoints(playerHand));

            if(Hand.getPoints(playerHand) == 21){
                //Blackjack!
                System.out.println("That's a blackjack! The dealer now must reveal his cards");
                playStillGoing = false;
                //See if you won
                //Blackjack wins 3:2, so they win 1.5 * wager
            }

            while (playStillGoing) {
                String choice = getChoice(reader);

                if (choice.equals("H")) {
                    Dealer.dealCard(playerHand.hand);
                    System.out.println("You have recieved a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).cardNumber);
                }
                else if (choice.equals("D")) {
                    Dealer.dealCard(playerHand.hand);
                    wager *= 2;
                    System.out.println("Upping your bid to $" + wager);
                    System.out.println("You have recieved a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).cardNumber);
                    playStillGoing = false;
                }
                //    else if(choice.equals("St"))
                //      //End the turn of the player
                //    else if(choice.equals("Sp"))
                //      //Split the player's hand into two hands
                //    else

                playPoints = Hand.getPoints(playerHand);
                System.out.println(playerHand.hand.get(playerHand.hand.size() - 1).cardNumber);
                System.out.println(playPoints);

                if(playPoints > 21){
                    won = false;
                    money -= wager;
                    System.out.println("You busted! You lose your bet!\nYou have $" + money + " left.");
                    break;
                }
            }

            while(true) {
                System.out.println("Would you like to play another hand? Y/N");
                String answer = reader.nextLine();
                if (answer.equals("Y") || answer.equals("N")) {
                    if(answer.equals("Y")){
                        playing = true;
                        break;
                    }
                    else{
                        playing = false;
                        break;
                    }
                }

                System.out.println("I'm sorry, but '" + answer + "' is not a valid input.");
            }
        }
    }

    public static double getMoney(Scanner reader) {
        double money = 0;

        while (!false) {
            System.out.println("How much money do you have? You must have at least 1 dollar to play.");
            money = reader.nextDouble();
            reader.nextLine();

            if (money >= 1.00) {
                break;
            }

            System.out.println("You do not have enough money to begin. Please get more money and try again.");
            return -1;
        }

        return money;
    }

    public static String getName(Scanner reader) {
        String name = "";

        while (!false) {
            System.out.println("Welcome to the table, what is your name?");
            name = reader.nextLine();

            if (name.length() >= 3) {
                break;
            }

            System.out.println("Sorry, but '" + name + "' is not a valid name.");
        }

        return name;
    }

    public static double getWager(Scanner reader, double currentWager) {
        double wager = 0;
        while (!false) {
            System.out.println("What would you like to wager?");
            wager = reader.nextDouble();
            reader.nextLine();

            if (wager >= currentWager) {
                break;
            }

            System.out.println("Sorry, but $" + wager + " is not a valid wager.\nPlease either match or raise $" + currentWager + ".");
        }

        return wager;

    }

    public static String getChoice(Scanner reader) {
        String choice;
        while (true) {
            System.out.println("(H)it, (St)and, (Sp)lit, (D)ouble, (Su)rrender");
            choice = reader.nextLine();
            boolean accepted = false;

            String[] acceptedAnswers = {"H", "St", "Sp", "D", "Su"};
            for (int i = 0; i < acceptedAnswers.length; i++) {
                if (choice.equals(acceptedAnswers[i])) {
                    accepted = true;
                    break;
                }
            }

            if (accepted)
                break;

            System.out.println("Sorry, but '" + choice + "' is not an accepted input.\n");
        }

        return choice;
    }
}
