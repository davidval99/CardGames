

package com.company.Classes;

import com.company.actions.Action;


import java.util.List;


public class Player {


    private final String name;


    private final Client client;


    public  HandPoker handPoker;


    private int cash;
    

    private boolean hasCards;


    private int bet;


    private Action action;


    public Player(String name, int cash, Client client) {
        this.name = name;
        this.cash = cash;
        this.client = client;

        handPoker = new HandPoker();

        resetHand();
    }


    public Client getClient() {
        return client;
    }


    public void resetHand() {
        hasCards = false;
        handPoker.removeAllCards();
        resetBet();
    }


    public void resetBet() {
        bet = 0;
        action = (hasCards() && cash == 0) ? Action.ALL_IN : null;
    }


    public void setCards(List<Card> cards) {
        handPoker.removeAllCards();
        if (cards != null) {

            handPoker.addCards(cards);
            hasCards = true;
            System.out.format("[CHEAT] %s's cards:\t%s\n", name, handPoker);
        }
    }
    public void addCards(List<Card> cards) {

        handPoker.addCards(cards);
        hasCards = true;
        System.out.format("[CHEAT] %s's cards:\t%s\n", name, handPoker);
    }



    public boolean hasCards() {
        return hasCards;
    }


    public String getName() {
        return name;
    }


    public int getCash() {
        return cash;
    }


    public int getBet() {
        return bet;
    }
    

    public void setBet(int bet) {
        this.bet = bet;
    }


    public Action getAction() {
        return action;
    }
    

    public void setAction(Action action) {
        this.action = action;
    }


    public boolean isAllIn() {
        return hasCards() && (cash == 0);
    }


    public Card[] getCards() {
        return handPoker.getCards();
    }


    public void postSmallBlind(int blind) {
        action = Action.SMALL_BLIND;
        cash -= blind;
        bet += blind;
    }


    public void postBigBlind(int blind) {
        action = Action.BIG_BLIND;
        cash -= blind;
        bet += blind;
    }
    

    public void payCash(int amount) {
        if (amount > cash) {
            throw new IllegalStateException("Player asked to pay more cash than he owns!");
        }
        cash -= amount;
    }
    

    public void win(int amount) {
        cash += amount;
    }


    public Player publicClone() {
        Player clone = new Player(name, cash, null);
        clone.hasCards = hasCards;
        clone.bet = bet;
        clone.action = action;
        return clone;
    }


    @Override
    public String toString() {
        return name;
    }

}
