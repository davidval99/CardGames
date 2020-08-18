package com.company.Classes;

import java.util.ArrayList;
import java.util.List;

public class Combinaciones {


public List<Card[]> combinar(Card[] input, int k) {




    List<Card[]> subsets = new ArrayList();

    int[] s = new int[k];

    if (k <= input.length) {


        for (int i = 0; (s[i] = i) < k - 1; i++) ;
        subsets.add(getSubset(input, s));
        for (; ; ) {
            int i;

            for (i = k - 1; i >= 0 && s[i] == input.length - k + i; i--) ;
            if (i < 0) {
                break;
            }
            s[i]++;
            for (++i; i < k; i++) {
                s[i] = s[i - 1] + 1;
            }
            subsets.add(getSubset(input, s));
        }
    }



    return subsets;

}


    Card[] getSubset(Card[] input, int[] subset) {

        Card[] result = new Card[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];

        return result;
    }


}