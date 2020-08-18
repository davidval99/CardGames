
package com.company.Classes.PokerGames;


import com.company.Classes.*;

public class SevenCards extends Holdem {


    public SevenCards() {
        super();
    }

    public void run() {
        for (Player player : players) {
            player.getClient().joinedTable(bigBlind, players);
        }
        dealerPosition = -1;
        actorPosition = -1;
        while (true) {
            int noOfActivePlayers = 0;
            for (Player player : players) {
                if (player.getCash() >= bigBlind) {
                    noOfActivePlayers++;
                }
            }
            if (noOfActivePlayers > 1) {
                playHand();
            } else {
                break;
            }
        }

        // Game over.
        board.clear();
        pots.clear();
        bet = 0;
        notifyBoardUpdated();
        for (Player player : players) {
            player.resetHand();
        }
        notifyPlayersUpdated(false);
        notifyMessage("Game over.");
    }

    public void playHand() {
        resetHand();

        // Small blind.
        if (activePlayers.size() > 2) {
            rotateActor();
        }
        postSmallBlind();

        // Big blind.
        rotateActor();
        postBigBlind();

        // Pre-Flop.
        dealHoleCards(2);
        doBettingRound();

        // Flop.
        if (activePlayers.size() > 1) {
            bet = 0;
            addCards(3);
            //dealCommunityCards("Flop", 3);
            doBettingRound();
            doBettingRound();

            // Turn.
            if (activePlayers.size() > 1) {
                bet = 0;
                addCards(1);
                //dealCommunityCards("Turn", 1);
                minBet = 2 * bigBlind;
                doBettingRound();

                // River.
                if (activePlayers.size() > 1) {
                    bet = 0;
                    addCards(1);
                    //dealCommunityCards("River", 1);
                    doBettingRound();

                    // Showdown.
                    if (activePlayers.size() > 1) {
                        bet = 0;
                        doShowdown();
                    }
                }
            }
        }
    }

    public void addCards(int numeroCartas) {
        for (Player player : activePlayers) {
            player.addCards(deck.deal(numeroCartas));
        }
        System.out.println();
        notifyPlayersUpdated(false);
        notifyMessage("%s deals the hole cards.", dealer);
    }



}
