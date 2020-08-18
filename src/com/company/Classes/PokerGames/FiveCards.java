

package com.company.Classes.PokerGames;


import com.company.Classes.*;
import com.company.Classes.PokerHandAnalyzer.HandValue;
import com.company.actions.Action;
import com.company.actions.BetAction;
import com.company.actions.RaiseAction;
import com.company.gui.ChangeCardPanel;


public class FiveCards extends Poker {

    public FiveCards() {
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


        if (activePlayers.size() > 2) {
            rotateActor();
        }
        postSmallBlind();

        // Big blind.
        rotateActor();
        postBigBlind();

        // Pre-Flop.
        dealHoleCards(5);
        doBettingRound();

        doChangeCardsRound();

        // Flop.
        if (activePlayers.size() > 1) {
            bet = 0;

            doBettingRound();

            if (activePlayers.size() > 1) {

                if (activePlayers.size() > 1) {
                    bet = 0;
                    doShowdown();
                }
            }
        }
    }

    public void doChangeCardsRound() {

        int playersToAct = activePlayers.size();


        while (playersToAct > 0) {
            playersToAct--;
            rotateActor();

            ChangeCardPanel changeCardController = new ChangeCardPanel(actor.getName());

            while (!changeCardController.turnFinish) {

            }


            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            Boolean[] changeCards = changeCardController.boolArray;
            HandPoker nuevaMano = actor.handPoker.DisposeCards(changeCards);

            actor.handPoker = null;
            actor.handPoker = nuevaMano;



            actor.addCards(deck.deal(changeCardController.NumberOfChangeCards));
            System.out.format("%s's cards:\t%s\n", actor.getName(), actor.handPoker);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyPlayersUpdated(false);
            //notifyMessage("%s deals the hole cards.", dealer);

        }
    }



}
