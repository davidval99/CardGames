
package com.company.Classes;

import com.company.actions.Action;

import java.util.List;
import java.util.Set;


public interface Client {
    

    void messageReceived(String message);

    void joinedTable(int bigBlind, List<Player> players);
    

    void handStarted(Player dealer);
    

    void actorRotated(Player actor);
    

    void playerUpdated(Player player);
    

    void boardUpdated(List<Card> cards, int bet, int pot);
    

    void playerActed(Player player);


    Action act(int minBet, int currentBet, Set<Action> allowedActions);

}
