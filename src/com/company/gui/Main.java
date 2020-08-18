
package com.company.gui;

import com.company.Classes.*;

import com.company.Classes.PokerGames.*;
import com.company.actions.Action;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;


public class Main extends JFrame implements Client {
    




    private static final int STARTING_CASH = 1000;


    private FiveCards fiveCards;

    private Holdem holdem;

    private Omaha omaha;

    private SevenCards sevenCards;


    private final Map<String, Player> players;


    private final GridBagConstraints gc;

    private final BoardPanel boardPanel;

    private final ControlPanel controlPanel;

    private Map<String, PlayerPanel> playerPanels;

    private final Player Player1;

    private final Player Player2;

    private String dealerName;

    private String actorName;


    public Main() {




        super("Card Games");



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());

        gc = new GridBagConstraints();
        
        controlPanel = new ControlPanel();
        
        boardPanel = new BoardPanel(controlPanel);        
        addComponent(boardPanel, 1, 1, 1, 1);

        players = new LinkedHashMap<String, Player>();
        Player1 = new Player("Eddy", STARTING_CASH, this);
        Player2 = new Player("Francisco", STARTING_CASH, this);

        players.put("Eddy", Player1);
        players.put("Francisco", Player2);



        Scanner in = new Scanner(System.in);

        System.out.println("1\t Texas Hold'em");
        System.out.println("2\t Five Cards");
        System.out.println("3\t Omaha");
        System.out.println("4\t Seven Cards");

        System.out.println("Please enter your choice:");

        int choice=in.nextInt();

        switch (choice) {
            case 1: startHoldem();
                break;
            case 2: startFiveCards();
                break;
            case 3: startOmaha();
                break;
            case 4: startSevenCards();
                break;
            default: System.out.println("Invalid choice");
        }

    }

    public static void main(String[] args) {
        new Main();
    }


    public void startHoldem(){
        holdem = new Holdem();
        for (Player player : players.values()) {
            holdem.addPlayer(player);
        }
        playerPanels = new HashMap<String, PlayerPanel>();
        int i = 0;
        for (Player player : players.values()) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.put(player.getName(), panel);
            addComponent(panel, 1, 2, 1, 1);

            switch (i++) {
                case 0:

                    addComponent(panel, 1, 0, 1, 1);
                    break;
                case 1:


                    addComponent(panel, 2, 1, 1, 1);
                    break;

                default:
                    // Do nothing.
            }
        }

        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        holdem.run();

    }

    public void startFiveCards(){
        fiveCards = new FiveCards();
        for (Player player : players.values()) {
            fiveCards.addPlayer(player);
        }
        playerPanels = new HashMap<String, PlayerPanel>();
        int i = 0;
        for (Player player : players.values()) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.put(player.getName(), panel);
            addComponent(panel, 1, 2, 1, 1);
            switch (i++) {
                case 0:

                    addComponent(panel, 1, 0, 1, 1);
                    break;
                case 1:


                    addComponent(panel, 2, 1, 1, 1);
                    break;

                default:
                    // Do nothing.
            }

        }
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        fiveCards.run();
    }

    public void startOmaha(){
        omaha = new Omaha();
        for (Player player : players.values()) {
            omaha.addPlayer(player);
        }
        playerPanels = new HashMap<String, PlayerPanel>();
        int i = 0;
        for (Player player : players.values()) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.put(player.getName(), panel);
            addComponent(panel, 1, 2, 1, 1);
            switch (i++) {
                case 0:

                    addComponent(panel, 1, 0, 1, 1);
                    break;
                case 1:


                    addComponent(panel, 2, 1, 1, 1);
                    break;

                default:
                    // Do nothing.
            }

        }
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        omaha.run();
    }

    public void startSevenCards(){
        sevenCards = new SevenCards();
        for (Player player : players.values()) {
            sevenCards.addPlayer(player);
        }
        playerPanels = new HashMap<String, PlayerPanel>();
        int i = 0;
        for (Player player : players.values()) {
            PlayerPanel panel = new PlayerPanel();
            playerPanels.put(player.getName(), panel);
            addComponent(panel, 1, 2, 1, 1);
            switch (i++) {
                case 0:

                    addComponent(panel, 1, 0, 1, 1);
                    break;
                case 1:


                    addComponent(panel, 2, 1, 1, 1);
                    break;

                default:
                    // Do nothing.
            }

        }
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        sevenCards.run();
    }


    @Override
    public void messageReceived(String message) {
        boardPanel.setMessage(message);
        boardPanel.waitForUserInput();
    }

    @Override
    public void joinedTable(int bigBlind, List<Player> players) {

    }

    @Override
    public void handStarted(Player dealer) {
        setDealer(false);
        dealerName = dealer.getName();
        setDealer(true);
    }

    @Override
    public void actorRotated(Player actor) {
        setActorInTurn(false);
        actorName = actor.getName();
        setActorInTurn(true);
    }

    @Override
    public void boardUpdated(List<Card> cards, int bet, int pot) {
        boardPanel.update(cards, bet, pot);
    }

    @Override
    public void playerUpdated(Player player) {
        PlayerPanel playerPanel = playerPanels.get(player.getName());
        if (playerPanel != null) {
            playerPanel.update(player);
        }
    }

    @Override
    public void playerActed(Player player) {
        String name = player.getName();
        PlayerPanel playerPanel = playerPanels.get(name);
        if (playerPanel != null) {
            playerPanel.update(player);
            Action action = player.getAction();
            if (action != null) {
                boardPanel.setMessage(String.format("%s %s.", name, action.getVerb()));
                if (player.getClient() != this) {
                    boardPanel.waitForUserInput();
                }
            }
        } else {
            throw new IllegalStateException(
                    String.format("No PlayerPanel found for player '%s'", name));
        }
    }

    @Override
    public Action act(int minBet, int currentBet, Set<Action> allowedActions) {
        boardPanel.setMessage("Please select an action:");
        return controlPanel.getUserInput(minBet, Player1.getCash(), allowedActions);
    }


    private void addComponent(Component component, int x, int y, int width, int height) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0.0;
        gc.weighty = 0.0;
        getContentPane().add(component, gc);
    }

    private void setActorInTurn(boolean isInTurn) {
        if (actorName != null) {
            PlayerPanel playerPanel = playerPanels.get(actorName);
            if (playerPanel != null) {
                playerPanel.setInTurn(isInTurn);
            }
        }
    }


    private void setDealer(boolean isDealer) {
        if (dealerName != null) {
            PlayerPanel playerPanel = playerPanels.get(dealerName);
            if (playerPanel != null) {
                playerPanel.setDealer(isDealer);
            }
        }
    }

}
