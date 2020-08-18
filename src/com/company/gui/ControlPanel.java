

package com.company.gui;

import com.company.actions.*;
import com.company.actions.Action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;


public class ControlPanel extends JPanel implements ActionListener {
    

    private final JButton checkButton;
    

    private final JButton callButton;
    

    private final JButton betButton;
    

    private final JButton raiseButton;
    

    private final JButton foldButton;

    private final JButton changeButton;
    

    private final JButton continueButton;
    

    private final AmountPanel amountPanel;


    private final Object monitor = new Object();
    

    private Action selectedAction;


    

    public ControlPanel() {

        setBackground(UIConstants.TABLE_COLOR);
        continueButton = createActionButton(Action.CONTINUE);
        checkButton = createActionButton(Action.CHECK);
        callButton = createActionButton(Action.CALL);
        betButton = createActionButton(Action.BET);
        raiseButton = createActionButton(Action.RAISE);
        foldButton = createActionButton(Action.FOLD);
        changeButton = createActionButton(Action.CHANGE);
        amountPanel = new AmountPanel();
    }
    

    public void waitForUserInput() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                removeAll();
                add(continueButton);
                repaint();
            }
        });
        Set<Action> allowedActions = new HashSet<Action>();
        allowedActions.add(Action.CONTINUE);
        getUserInput(0, 0, allowedActions);
    }
    

    public Action getUserInput(int minBet, int cash, final Set<Action> allowedActions) {
        selectedAction = null;
        while (selectedAction == null) {
            // Show the buttons for the allowed actions.
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    removeAll();
                    if (allowedActions.contains(Action.CONTINUE)) {
                        add(continueButton);
                    } else {
                        if (allowedActions.contains(Action.CHECK)) {
                            add(checkButton);
                        }
                        if (allowedActions.contains(Action.CALL)) {
                            add(callButton);
                        }
                        if (allowedActions.contains(Action.BET)) {
                            add(betButton);
                        }
                        if (allowedActions.contains(Action.RAISE)) {

                            add(raiseButton);
                        }
                        if (allowedActions.contains(Action.FOLD)) {
                            add(foldButton);
                        }
                        if (allowedActions.contains(Action.CHANGE)) {
                            add(foldButton);
                        }

                    }
                    repaint();
                }
            });
            

            synchronized (monitor) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {

                }
            }
            

            if ((selectedAction == Action.BET || selectedAction == Action.RAISE)) {

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        removeAll();
                        add(amountPanel);

                        repaint();
                    }
                });
                selectedAction = amountPanel.show(selectedAction, minBet, cash);
                if (selectedAction == Action.BET) {
                    selectedAction = new BetAction(amountPanel.getAmount());
                } else if (selectedAction == Action.RAISE) {
                    selectedAction = new RaiseAction(amountPanel.getAmount());

                } else {

                    selectedAction = null;
                }
            }
        }
        
        return selectedAction;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == continueButton) {
            selectedAction = Action.CONTINUE;
        } else if (source == checkButton) {
            selectedAction = Action.CHECK;
        } else if (source == callButton) {
            selectedAction = Action.CALL;
        } else if (source == betButton) {
            selectedAction = Action.BET;
        } else if (source == raiseButton) {

            selectedAction = Action.RAISE;


        } else if(source == Action.CHANGE) {
            selectedAction = Action.CHANGE;
        }

        else {
            selectedAction = Action.FOLD;
        }
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
    

    private JButton createActionButton(Action action) {
        String label = action.getName();
        JButton button = new JButton(label);
        button.setMnemonic(label.charAt(0));
        button.setSize(100, 30);
        button.addActionListener(this);
        return button;
    }

}
