
package com.company.Classes;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Deck {
    

    private Card[] cards;

    private int nextCardIndex = 0;

    private Random random = new SecureRandom();

    public Deck() {
        cards = new Card[52];
        int index = 0;
        for (int suit = Card.NO_OF_SUITS - 1; suit >= 0; suit--) {
            for (int rank = Card.NO_OF_RANKS - 1; rank >= 0 ; rank--) {
                cards[index++] = new Card(rank, suit);
            }
        }
    }
    


    public void shuffle() {
        for (int oldIndex = 0; oldIndex < 52; oldIndex++) {
            int newIndex = random.nextInt(52);
            Card tempCard = cards[oldIndex];
            cards[oldIndex] = cards[newIndex];
            cards[newIndex] = tempCard;
        }
        nextCardIndex = 0;
    }
    

    public void reset() {
        nextCardIndex = 0;
    }
    

    public Card deal() {

        return cards[nextCardIndex++];
    }

    public List<Card> deal(int noOfCards) {

        List<Card> dealtCards = new ArrayList<Card>();
        for (int i = 0; i < noOfCards; i++) {
            dealtCards.add(cards[nextCardIndex++]);
        }
        return dealtCards;
    }

    public Card deal(int rank, int suit) {

        Card card = null;
        int index = -1;
        for (int i = nextCardIndex; i < 52; i++) {
            if ((cards[i].getRank() == rank) && (cards[i].getSuit() == suit)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            if (index != nextCardIndex) {
                Card nextCard = cards[nextCardIndex];
                cards[nextCardIndex] = cards[index];
                cards[index] = nextCard;
            }
            card = deal();
        }
        return card;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card);
            sb.append(' ');
        }
        return sb.toString().trim();
    }
    
}
