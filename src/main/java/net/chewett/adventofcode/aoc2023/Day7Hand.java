package net.chewett.adventofcode.aoc2023;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple class to hold a hand alongside a bid
 */
public class Day7Hand implements Comparable<Day7Hand> {

    // Value order for the standard hand
    public static char[] valOrder = {'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', '1'};

    // String representing the hand
    public String hand;
    // Bid value for the hand
    public long bid;

    //Various boolean values to represent what type of hand this is
    public boolean fiveOfAkind = false;
    public boolean fourOfAKind = false;
    public boolean fullHouse = false;
    public boolean threeOfAkind = false;
    public boolean twoPair = false;
    public boolean pair = false;
    public boolean jokerHand;

    //Mapping of card type to value
    private Map<Character, Integer> cardMapping = new HashMap();

    public Day7Hand(String hand) {
        this(hand, false);
    }

    public Day7Hand(String hand, boolean jokerHand) {
        this.jokerHand = jokerHand;
        String[] splitHand = hand.split(" ");
        this.hand = splitHand[0];
        this.bid = Long.parseLong(splitHand[1]);

        //Count how many of each card we have
        for(char c : this.hand.toCharArray()) {
            this.cardMapping.put(c, this.cardMapping.getOrDefault(c, 0) + 1);
        }

        //loop over each character and see how many of them there are
        for(char c : Day7Hand.valOrder) {
            //Ignore the joker if its a joker hand
            if(c == 'J' && this.jokerHand) {
                continue;
            }

            int cardCount  = this.cardMapping.getOrDefault(c, 0);
            if(cardCount == 5) {
                fiveOfAkind = true;
            }else if(cardCount == 4) {
                fourOfAKind = true;
            }else if(cardCount == 3) {
                threeOfAkind = true;
                if(pair) {
                    fullHouse = true;
                }
            }else if(cardCount == 2) {
                if(pair) {
                    twoPair = true;
                }else if(threeOfAkind) {
                    fullHouse = true;
                }
                pair = true;
            }
        }

        //If there is a joker hand then we could "upgrade" the hands with a joker
        if(this.jokerHand) {
            int jokerVal = this.cardMapping.getOrDefault('J', 0);
            if(jokerVal == 5 || jokerVal == 4) {
                fiveOfAkind = true;
            }else if(jokerVal == 3) {
                if(pair) {
                    fiveOfAkind = true;
                }else{
                    fourOfAKind = true;
                }
            }else if(jokerVal == 2) {
                if(threeOfAkind) {
                    fiveOfAkind = true;
                }else if(pair) {
                    fourOfAKind = true;
                }else{
                    threeOfAkind = true;
                }

            }else if(jokerVal == 1) {
                if(fourOfAKind) {
                    fiveOfAkind = true;
                }else if(threeOfAkind) {
                    fourOfAKind = true;
                }else if(twoPair) {
                    fullHouse = true;
                }else if(pair) {
                    threeOfAkind = true;
                }else{
                    pair = true;
                }
            }

        }

    }

    /**
     * Gets a simple value representing the "type" of hand
     * @return Value representing the type value
     */
    public int getValueOfHandType() {
        if(fiveOfAkind) {
            return 8;
        }else if(fourOfAKind) {
            return 7;
        }else if(fullHouse) {
            return 6;
        }else if(threeOfAkind) {
            return 5;
        }else if(twoPair) {
            return 4;
        }else if(pair) {
            return 3;
        }
        return 2;
    }

    /**
     * Return a simple value representing the order value
     * @return Value representing the order value
     */
    public long getOrderValue() {
        Map<Character, Integer> charValueMapping = new HashMap<>();
        charValueMapping.put('A', 15);
        charValueMapping.put( 'K', 14);
        charValueMapping.put( 'Q', 13);

        charValueMapping.put( 'T', 11);
        charValueMapping.put( '9', 10);
        charValueMapping.put( '8', 9);
        charValueMapping.put( '7', 8);
        charValueMapping.put( '6', 7);
        charValueMapping.put( '5', 6);
        charValueMapping.put( '4', 5);
        charValueMapping.put( '3', 4);
        charValueMapping.put( '2', 3);
        charValueMapping.put( '1', 2);
        //Handle the joker having a different value depending on the joker hand-ness
        if(!this.jokerHand) {
            charValueMapping.put( 'J', 12);
        }else{
            charValueMapping.put( 'J', 1);
        }

        //Work out the value using a simple power system
        long totalVal = 0;
        for(int i = 0; i < 5; i++) {
            char thisChar = this.hand.toCharArray()[i];
            int thisPosVal = charValueMapping.get(thisChar);
            long newVal = (long)(Math.pow(16, 5 - i) * thisPosVal);
            totalVal += newVal ;
        }

        return totalVal;
    }

    @Override
    public int compareTo(Day7Hand o) {
        //Simple comparator comparing "value of hand" and then ordering value
        if(this.getValueOfHandType() != o.getValueOfHandType()) {
            return Integer.compare(this.getValueOfHandType(), o.getValueOfHandType());
        }

        return Long.compare(this.getOrderValue(), o.getOrderValue());

    }

    public long getBid() {
        return this.bid;
    }

    public String toString() {
        return this.hand + " " + this.getValueOfHandType() + " " + this.getOrderValue() + " Bid: " + this.bid;
    }
}
