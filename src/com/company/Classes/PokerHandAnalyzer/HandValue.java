

package com.company.Classes.PokerHandAnalyzer;

import com.company.Classes.HandPoker;


public class HandValue implements Comparable<HandValue> {
    

    private final HandPoker handPoker;
    

    private final HandValueType type;
    

    private final int value;
    

    public HandValue(HandPoker handPoker) {

        this.handPoker = handPoker;
	HandEvaluator evaluator = new HandEvaluator(handPoker);
	type = evaluator.getType();
	value = evaluator.getValue();
    }

    public HandPoker getHandPoker() {
	return handPoker;
    }
    

    public HandValueType getType() {
	return type;
    }

    public String getDescription() {
	return type.getDescription();
    }
    

    public int getValue() {
	return value;
    }
    

    @Override
    public int hashCode() {
	return value;
    }
    

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof HandValue) {
	    return ((HandValue) obj).getValue() == value;
	} else {
	    return false;
	}
    }
    

    @Override
    public int compareTo(HandValue handValue) {
	if (value > handValue.getValue()) {
	    return -1;
	} else if (value < handValue.getValue()) {
	    return 1;
	} else {
	    return 0;
	}
    }


    @Override
    public String toString() {
        return String.format("%s (%d)", type.getDescription(), value);
    }

}
