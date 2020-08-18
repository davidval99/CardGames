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

import java.util.*;


public class Omaha extends Holdem {


    public Omaha() {
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


        rotateActor();
        postBigBlind();


        dealHoleCards(4);
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
