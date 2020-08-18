
package com.company.Classes.PokerGames;


import com.company.Classes.*;

public class Holdem extends Poker {


    public Holdem() {
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


        if (activePlayers.size() > 2) {
            rotateActor();
        }
        postSmallBlind();


        rotateActor();
        postBigBlind();


        dealHoleCards(2);
        doBettingRound();


        if (activePlayers.size() > 1) {
            bet = 0;
            dealCommunityCards("Flop", 3);
            doBettingRound();


            if (activePlayers.size() > 1) {
                bet = 0;
                dealCommunityCards("Turn", 1);
                minBet = 2 * bigBlind;
                doBettingRound();


                if (activePlayers.size() > 1) {
                    bet = 0;
                    dealCommunityCards("River", 1);
                    doBettingRound();


                    if (activePlayers.size() > 1) {
                        bet = 0;
                        doShowdown();
                    }
                }
            }
        }
    }





}
