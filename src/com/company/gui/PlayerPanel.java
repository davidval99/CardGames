
package com.company.gui;

import com.company.Classes.Player;
import com.company.actions.Action;
import com.company.Classes.Card;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PlayerPanel extends JPanel {
    

    private static final Icon BUTTON_PRESENT_ICON =
            ImageManager.getIcon("/images/button_present.png");
    

    private static final Icon BUTTON_ABSENT_ICON =
            ImageManager.getIcon("/images/button_absent.png");
    
    private static final Icon CARD_PLACEHOLDER_ICON =
        ImageManager.getIcon("/images/card_placeholder.png");

    private static final Icon CARD_BACK_ICON =
            ImageManager.getIcon("/images/card_back.png");
    

    private static final Border BORDER = new EmptyBorder(10, 10, 10, 10);
    

    private JLabel nameLabel;
    

    private JLabel cashLabel;
    

    private JLabel actionLabel;
    

    private JLabel betLabel;


    private JLabel card1Label;


    private JLabel card2Label;

    private JLabel card3Label;

    private JLabel card4Label;

    private JLabel card5Label;

    private JLabel card6Label;

    private JLabel card7Label;



    private JLabel dealerButton;
    

    public PlayerPanel() {
        setBorder(BORDER);
        setBackground(UIConstants.TABLE_COLOR);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        nameLabel = new MyLabel();
        cashLabel = new MyLabel();
        actionLabel = new MyLabel();
        betLabel = new MyLabel();
        card1Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card2Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card3Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card4Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card5Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card6Label = new JLabel(CARD_PLACEHOLDER_ICON);
        card7Label = new JLabel(CARD_PLACEHOLDER_ICON);

        dealerButton = new JLabel(BUTTON_ABSENT_ICON);
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(dealerButton, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.insets = new Insets(1, 1, 1, 1);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        add(nameLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(cashLabel, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(actionLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;
        add(betLabel, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card1Label, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card2Label, gc);
        gc.gridx = 2;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card3Label, gc);
        gc.gridx = 3;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card4Label, gc);
        gc.gridx = 4;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card5Label, gc);
        gc.gridx = 5;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card6Label, gc);
        gc.gridx = 6;
        gc.gridy = 3;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weightx = 1.0;
        gc.weighty = 1.0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        add(card7Label, gc);



        setInTurn(false);
        setDealer(false);
    }
    

    public void update(Player player) {
        nameLabel.setText(player.getName());
        cashLabel.setText("$ " + player.getCash());
        int bet = player.getBet();
        if (bet == 0) {
            betLabel.setText(" ");
        } else {
            betLabel.setText("$ " + bet);
        }
        Action action = player.getAction();
        if (action != null) {
            actionLabel.setText(action.getName());
        } else {
            actionLabel.setText(" ");
        }
        if (player.hasCards()) {
            Card[] cards = player.getCards();
            if (cards.length == 2) {

                card1Label.setIcon(ImageManager.getCardImage(cards[0]));
                card2Label.setIcon(ImageManager.getCardImage(cards[1]));

            }
            if (cards.length == 4) {

                card1Label.setIcon(ImageManager.getCardImage(cards[0]));
                card2Label.setIcon(ImageManager.getCardImage(cards[1]));
                card3Label.setIcon(ImageManager.getCardImage(cards[2]));
                card4Label.setIcon(ImageManager.getCardImage(cards[3]));
            }
            else if (cards.length == 5) {

                card1Label.setIcon(ImageManager.getCardImage(cards[0]));
                card2Label.setIcon(ImageManager.getCardImage(cards[1]));
                card3Label.setIcon(ImageManager.getCardImage(cards[2]));
                card4Label.setIcon(ImageManager.getCardImage(cards[3]));
                card5Label.setIcon(ImageManager.getCardImage(cards[4]));
            }
            else if (cards.length == 6) {

                card1Label.setIcon(ImageManager.getCardImage(cards[0]));
                card2Label.setIcon(ImageManager.getCardImage(cards[1]));
                card3Label.setIcon(ImageManager.getCardImage(cards[2]));
                card4Label.setIcon(ImageManager.getCardImage(cards[3]));
                card5Label.setIcon(ImageManager.getCardImage(cards[4]));
                card6Label.setIcon(ImageManager.getCardImage(cards[5]));
            }
            if (cards.length == 7) {

                card1Label.setIcon(ImageManager.getCardImage(cards[0]));
                card2Label.setIcon(ImageManager.getCardImage(cards[1]));
                card3Label.setIcon(ImageManager.getCardImage(cards[2]));
                card4Label.setIcon(ImageManager.getCardImage(cards[3]));
                card5Label.setIcon(ImageManager.getCardImage(cards[4]));
                card6Label.setIcon(ImageManager.getCardImage(cards[5]));
                card7Label.setIcon(ImageManager.getCardImage(cards[6]));
            }


        } else {

            card1Label.setIcon(CARD_PLACEHOLDER_ICON);
            card2Label.setIcon(CARD_PLACEHOLDER_ICON);
            card3Label.setIcon(CARD_PLACEHOLDER_ICON);
            card4Label.setIcon(CARD_PLACEHOLDER_ICON);
            card5Label.setIcon(CARD_PLACEHOLDER_ICON);
            card6Label.setIcon(CARD_PLACEHOLDER_ICON);
            card7Label.setIcon(CARD_PLACEHOLDER_ICON);

        }
    }
    

    public void setDealer(boolean isDealer) {
        if (isDealer) {
            dealerButton.setIcon(BUTTON_PRESENT_ICON);
        } else {
            dealerButton.setIcon(BUTTON_ABSENT_ICON);
        }
    }
    

    public void setInTurn(boolean inTurn) {
        if (inTurn) {
            nameLabel.setForeground(Color.YELLOW);
        } else {
            nameLabel.setForeground(Color.GREEN);
        }
    }
    

    private static class MyLabel extends JLabel {

      MyLabel() {

            setBorder(UIConstants.LABEL_BORDER);
            setForeground(UIConstants.TEXT_COLOR);
            setHorizontalAlignment(JLabel.HORIZONTAL);
            setText(" ");
        }
        
    }
    
}
