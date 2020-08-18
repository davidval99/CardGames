
package com.company.Classes;

import com.company.Interfaces.Mano;

import java.util.ArrayList;
import java.util.Collection;


public class HandPoker implements Mano {


    private static final int MAX_NO_OF_CARDS = 9;

    /**
     * The cards in this hand.
     */
    private Card[] cards = new Card[MAX_NO_OF_CARDS];

    /**
     * The current number of cards in this hand.
     */
    private int noOfCards = 0;

    /**
     * Constructor for an empty hand.
     */
    public HandPoker() {

    }


    public HandPoker(Card[] cards) {
        addCards(cards);
    }


    public HandPoker(Collection<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null array");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }


    /**
     * Returns the number of cards.
     *
     * @return The number of cards.
     */
    public int size() {
        return noOfCards;
    }

    /**
     * Adds a single card.
     * <p>
     * The card is inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     *
     * @param card The card to add.
     * @throws IllegalArgumentException If the card is null.
     */

    public void deleteCard(int index) {
        cards[index] = null;
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Null card");
        }

        int insertIndex = -1;
        for (int i = 0; i < noOfCards; i++) {
            if (card.compareTo(cards[i]) > 0) {
                insertIndex = i;
                break;
            }
        }
        if (insertIndex == -1) {
            // Could not insert anywhere, so append at the end.
            cards[noOfCards++] = card;
        } else {
            for (int i = noOfCards; i > insertIndex; i--) {
                cards[i] = cards[i - 1];
            }
            cards[insertIndex] = card;
            noOfCards++;
        }
    }

    /**
     * Adds multiple cards.
     * <p>
     * The cards are inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     *
     * @param cards The cards to add.
     */
    public void addCards(Card[] cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null array");
        }
        if (cards.length > MAX_NO_OF_CARDS) {
            throw new IllegalArgumentException("Too many cards");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }

    /**
     * Adds multiple cards.
     * <p>
     * The cards are inserted at such a position that the hand remains sorted
     * (highest ranking cards first).
     *
     * @param cards The cards to add.
     */
    public void addCards(Collection<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Null collection");
        }
        if (cards.size() > MAX_NO_OF_CARDS) {
            throw new IllegalArgumentException("Too many cards");
        }
        for (Card card : cards) {
            addCard(card);
        }
    }

    /**
     * Returns the cards.
     *
     * @return The cards.
     */
    public Card[] getCards() {
        Card[] dest = new Card[noOfCards];
        System.arraycopy(cards, 0, dest, 0, noOfCards);
        return dest;
    }

    /**
     * Removes all cards.
     */
    public void removeAllCards() {
        noOfCards = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noOfCards; i++) {
            sb.append(cards[i]);
            if (i < (noOfCards - 1)) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }


    public HandPoker DisposeCards(Boolean[] cards2copy) {

        ArrayList<Card> newCards = new ArrayList();

        int x = 0;
        int y = 0;

        for (int i = 0;i<5;i++){
            if (!cards2copy[i]) {
                newCards.add(this.cards[i]);

            }

        }

        /*
        while (y < 5) {
            if (!cards2copy[y]) {
                newCards.add(this.cards[x]);
                x++;
            }
            y++;
        }*/


        HandPoker newHand = new HandPoker();
        for (int i = 0; i < newCards.size(); i++) {
            newHand.addCard(newCards.get(i));
        }



        return newHand;
    }


}

