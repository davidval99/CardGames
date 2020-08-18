

package com.company.gui;

import com.company.Classes.Card;


import javax.swing.*;
import java.net.URL;


public abstract class ImageManager {
    
    private static final String IMAGE_PATH_FORMAT = "/images/card_%s.png";

    

    public static ImageIcon getCardImage(Card card) {

        int sequenceNr = card.getSuit() * Card.NO_OF_RANKS + card.getRank();
        String sequenceNrString = String.valueOf(sequenceNr);
        if (sequenceNrString.length() == 1) {
            sequenceNrString = "0" + sequenceNrString;
        }
        String path = String.format(IMAGE_PATH_FORMAT, sequenceNrString);
        return getIcon(path);
    }
    

    public static ImageIcon getIcon(String path) {
        URL url = ImageManager.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        } else {
            throw new RuntimeException("Resource file not found: " + path);
        }
    }
    
}
