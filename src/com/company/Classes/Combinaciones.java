package com.company.Classes;

import java.util.ArrayList;
import java.util.List;

public class Combinaciones {


public List<Card[]> combinar(Card[] input, int k) {

        //4c2 = 6 [mano1,mano2,mano3.....mano6]

        //5c3 = 10 [mano1,mano2,mano3.....mano10]

        //60


    List<Card[]> subsets = new ArrayList();

    int[] s = new int[k];                  // aqui van los indices apuntando a los elemenots del array de entrada

    if (k <= input.length) {
        // first index sequence: 0, 1, 2, ...

        for (int i = 0; (s[i] = i) < k - 1; i++) ;
        subsets.add(getSubset(input, s));
        for (; ; ) {
            int i;
            // find position of item that can be incremented
            for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--) ;
            if (i < 0) {
                break;
            }
            s[i]++;                    // increment this item
            for (++i; i < k; i++) {    // fill up remaining items
                s[i] = s[i - 1] + 1;
            }
            subsets.add(getSubset(input, s));
        }
    }



    return subsets;

}

    // generate actual subset by index sequence
    Card[] getSubset(Card[] input, int[] subset) {

        Card[] result = new Card[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];

        return result;
    }


}