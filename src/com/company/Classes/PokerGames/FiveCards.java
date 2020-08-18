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
            player.getClient().joinedTable(tableType, bigBlind, players);
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
                System.out.println("Processing....");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.format("Primera" + "[CHEAT] %s's cards:\t%s\n", actor.getName(), actor.handPoker);


            Boolean[] changeCards = changeCardController.boolArray;
            HandPoker nuevaMano = actor.handPoker.DisposeCards(changeCards);

            actor.handPoker = null;
            actor.handPoker = nuevaMano;


            System.out.format("Segunda" + "[CHEAT] %s's cards:\t%s\n", actor.getName(), actor.handPoker);
            actor.addCards(deck.deal(changeCardController.NumberOfChangeCards));
            System.out.format("Tercera" + "[CHEAT] %s's cards:\t%s\n", actor.getName(), actor.handPoker);

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
