/*
// File:             Dealer.java
// Created:          2018/04/05
// Author:           [author]
// Description:      Describe function here.
*/

import java.util.Random;
import java.util.Stack;
import java.util.List;
import java.util.Arrays;

public class Dealer {

    public static Stack<Card> deck = new Stack<Card>();

    public static void shuffleDeck(Stack<Card> gameDeck) {

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {

            int indexToChange = random.nextInt(gameDeck.size());
            int indexToChangeTo = random.nextInt(gameDeck.size());

            swap(gameDeck, indexToChange, indexToChangeTo);

        }

    }

    public static void initDeck(Stack<Card> gameDeck) {
        final int NUM_SUITS = 4;
        final int CARDS_PER_SUIT = 13;
        final int DECKS_USED = 6;

        for(int dck = 1; dck <= DECKS_USED; dck++) {
            for (int suit = 0; suit < NUM_SUITS; suit++) {
                for (int card = 2; card <= CARDS_PER_SUIT; card++) {
                    gameDeck.add(Card.initCard(card, Card.Suit.values()[suit]));
                }
            }
        }


    }

    public static void swap(Stack<Card> gameDeck, int indexToChange, int indexToChangeTo) {

        Card temp = gameDeck.get(indexToChange);
        gameDeck.set(indexToChange, gameDeck.get(indexToChangeTo));
        gameDeck.set(indexToChangeTo, temp);

    }

    public static List<Card> dealStartingCards(Stack<Card> gameDeck) {
        Card cardOne = gameDeck.pop();
        Card cardTwo = gameDeck.pop();

        return Arrays.asList(cardOne, cardTwo);
    }

    public static void dealCard(List<Card> hand){
        hand.add(deck.pop());
    }

    public static void resetDeck(Stack<Card> gameDeck){
        gameDeck.removeAllElements();
    }

}