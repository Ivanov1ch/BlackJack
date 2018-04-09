/*
// File:             SinglePlayer.java
// Created:          2018/04/05
// Author:           danIv
// Description:      The single player version of Blackjack.
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class SinglePlayer {
    public static double money;
    public static double wager;
    public static double insuranceWager;
    public static void runGame() {
        Scanner reader = new Scanner(System.in);

        String name = GameManager.getName(reader);

        money = GameManager.getMoney(reader);

        if (money == -1) {
            System.exit(10); //10 = Bad money
        }

        boolean playing = true;

        while (playing) {
            if(money < 0){
                System.out.println("You really screwed up now, didn't you? Now you owe us $" + (-1 * money) + ".\nExpect a bill.");
                playing = false;
                break;
            }
            else if(money < 1.00){
                System.out.println("You don't have enough money to keep playing!");
                playing = false;
                break;
            }
            Dealer.resetDeck(Dealer.deck);
            Dealer.initDeck(Dealer.deck);
            Dealer.shuffleDeck(Dealer.deck);

            boolean blackjack = false;
            boolean bust = false;
            boolean playStillGoing = true;

            Hand playerHand = new Hand(new ArrayList<Card>(Dealer.dealStartingCards(Dealer.deck)));
            Dealer.hand =  new Hand(new ArrayList<Card>(Dealer.dealStartingCards(Dealer.deck)));


            while(true) {
                wager = GameManager.getWager(reader, 1.00);
                if(wager <= money){
                    break;
                }

                System.out.println("You entered more money than you currently have! Please enter a valid wager.");
            }

            System.out.println("Dealing cards...\n");

            //Wait
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ie) {

            }

            System.out.println("You have recieved a " + playerHand.hand.get(0).suit.symbol + playerHand.hand.get(0).name + " and a " + playerHand.hand.get(1).suit.symbol + playerHand.hand.get(1).name);

            System.out.println("The dealer has recieved a " + Dealer.hand.hand.get(0).suit.symbol + Dealer.hand.hand.get(0).name + " and an unknown card.");

            if(Dealer.hand.hand.get(0).name.equals("Ace")){
                while(true) {
                    insuranceWager = GameManager.getInsuranceWager(reader, 1.00);
                    if(insuranceWager + wager <= money){
                        break;
                    }

                    System.out.println("you're entering more money than you have to bet! Please wager a value less than or equal to " + (money - wager));
                }
            }

            if(GameManager.checkForBlackjack(playerHand)){
                //Blackjack!
                System.out.println("That's a blackjack! The dealer now must reveal his cards");
                playStillGoing = false;
                blackjack = true;
            }

            boolean firstPlay = true;

            while (playStillGoing) {
                String choice = GameManager.getChoice(reader);

                if (choice.equals("H")) {
                    Dealer.dealCard(playerHand.hand);
                    System.out.println("You have recieved a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).name);
                }
                else if (choice.equals("D")) {
                    Dealer.dealCard(playerHand.hand);
                    wager *= 2;
                    System.out.println("Upping your bid to $" + wager);
                    System.out.println("You have recieved a " + playerHand.hand.get(playerHand.hand.size() - 1).suit.symbol + playerHand.hand.get(playerHand.hand.size() - 1).name);
                    playStillGoing = false;
                }
                else if(choice.equals("S")){
                    playStillGoing = false;
                    break;
                }
                else {
                    if(firstPlay){
                        wager *= 0.5;
                        playerHand.active = false;
                        firstPlay = false;
                        break;
                    }
                    else{
                        System.out.println("Sorry, but you can only Surrender on your first hand!");
                        continue;
                    }
                }

                System.out.println("Your current hand is: " + Hand.printFullHand(playerHand));

                firstPlay = false;

                bust = GameManager.checkForBust(playerHand);

                if(bust){
                    playStillGoing = false;
                }

            }

            Dealer.useTurn(Dealer.hand, Dealer.deck);

            getWinner(playerHand, Dealer.hand, blackjack, bust);

            System.out.println("\nYou have $" + money + " left.");

            /*****RESET******/
            Hand.clearHand(playerHand);
            Hand.clearHand(Dealer.hand);
            insuranceWager = 0;
            wager = 0;

            if(money < 0){
                System.out.println("\nYou really screwed up now, didn't you? Now you owe us $" + (-1 * money) + ".\nExpect a bill.\nGet out of here.\n");
                playing = false;
                break;
            }

            while(true) {
                System.out.println("Would you like to play another hand? Y/N");
                String answer = reader.nextLine().toUpperCase();
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

    public static void getWinner(Hand playerHand, Hand dealerHand, boolean playerBlackjack, boolean playerBust){
        if(playerBust){
            //The player goes first, so it checks if the player busted first.
            System.out.println("You busted! You lose your wager of $" + wager + ".");
            money -= wager;
            if(!GameManager.checkForBlackjack(dealerHand)){
                if(insuranceWager != 0.0){
                    //They took insurance
                    System.out.println("The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!");
                    money -= insuranceWager;
                }
            }
            else{
                if(insuranceWager != 0.0){
                    //They took insurance
                    insuranceWager *= 2;
                    System.out.println("However, you win back 2:1 ($" + insuranceWager + ") from your insurance!");
                    money += insuranceWager;
                }
            }
        }
        else if(GameManager.checkForBust(dealerHand)){
            System.out.println("The dealer busted! You win your wager of $" + wager + ".");
            money += wager;
            if(insuranceWager != 0.0){
                //They took insurance
                System.out.println("The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!");
                money -= insuranceWager;
            }
        }
        else{
            //Neither busted
            if(playerBlackjack && GameManager.checkForBlackjack(dealerHand)){
                //2 blackjacks == tie
                System.out.println("It's a push! You don't lose or gain anything!");
                if(insuranceWager != 0.0){
                    //They took insurance
                    insuranceWager *= 2;
                    System.out.println("However, you win back 2:1 ($" + insuranceWager + ") from your insurance!");
                    money += insuranceWager;
                }
            }
            else if(playerBlackjack){
                wager *= 1.5;
                System.out.println("You got a blackjack! You win 3:2 on your wager, or $" + wager + ".");
                if(insuranceWager != 0.0){
                    //They took insurance
                    System.out.println("The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!");
                    money -= insuranceWager;
                }
            }
            else if(GameManager.checkForBlackjack(dealerHand)){
                System.out.println("The dealer got a blackjack! You lose your $" + wager + " wager.");
                money -= wager;
                if(insuranceWager != 0.0){
                    //They took insurance
                    insuranceWager *= 2;
                    System.out.println("However, you win back 2:1 ($" + insuranceWager + ") from your insurance!");
                    money += insuranceWager;
                }
            }
            else{
                //No busts, no blackjacks
                if(insuranceWager != 0.0){
                    //They took insurance
                    System.out.println("The dealer didn't have a blackjack! You lose your $" + insuranceWager + " insurance!");
                    money -= insuranceWager;
                }

                if(Hand.getPoints(playerHand) == Hand.getPoints(dealerHand)){
                    System.out.println("It's a push! You don't lose or gain anything!");
                }
                else if(Hand.getPoints(playerHand) > Hand.getPoints(dealerHand)){
                    System.out.println("You win! You get $" + wager + ".");
                    money += wager;
                }
                else{
                    System.out.println("You lose! You lose $" + wager + ".");
                    money -= wager;
                }
            }
        }
    }

}