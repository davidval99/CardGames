// This file is part of the 'texasholdem' project, an open source
// Texas Hold'em poker application written in Java.
//
// Copyright 2009 Oscar Stigter
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.company.Classes;


public class Card implements Comparable<Card> {
    
    /** The number of ranks in a deck. */
    public static final int NO_OF_RANKS = 13;
    
    /** The number of suits in a deck. */
    public static final int NO_OF_SUITS = 4;
    
    // The ranks.
    public static final int ACE      = 12;
    public static final int KING     = 11;
    public static final int QUEEN    = 10;
    public static final int JACK     = 9;
    public static final int TEN      = 8;
    public static final int NINE     = 7;
    public static final int EIGHT    = 6;
    public static final int SEVEN    = 5;
    public static final int SIX      = 4;
    public static final int FIVE     = 3;
    public static final int FOUR     = 2;
    public static final int THREE    = 1;
    public static final int DEUCE    = 0;
    
    // The suits.
    public static final int SPADES   = 3;
    public static final int HEARTS   = 2;
    public static final int CLUBS    = 1;
    public static final int DIAMONDS = 0;
    
    /** The rank symbols. */
    public static final String[] RANK_SYMBOLS = {
        "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"
    };
    

    public static final char[] SUIT_SYMBOLS = { 'd', 'c', 'h', 's' };


    private final int rank;

    private final int suit;

    public Card(int rank, int suit) {

        this.rank = rank;
        this.suit = suit;
    }
    


    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int hashCode() {
        return (rank * NO_OF_SUITS + suit);
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            return ((Card) obj).hashCode() == hashCode();
        } else {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(Card card) {
        int thisValue = hashCode();
        int otherValue = card.hashCode();
        if (thisValue < otherValue) {
            return -1;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return RANK_SYMBOLS[rank] + SUIT_SYMBOLS[suit];
    }
    
}
