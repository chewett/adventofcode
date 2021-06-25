package net.chewett.adventofcode.aoc2020;

import java.util.*;

/**
 * A class to hold the data about a game of recursive combat, this needs to store a little more so I made it a class
 * to make it easier to keep track of everything and to make it easy to make it recursive
 */
public class RecursiveCombatGame {

    private Queue<Integer> p1;
    private Queue<Integer> p2;
    private boolean p1Won;
    Set<String> previousSeenConfigurations = new HashSet<>();

    public RecursiveCombatGame(Queue<Integer> p1, Queue<Integer> p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * Given a queue this will return a representation of this as a string
     * @param q Queue to convert to a string representation
     * @return The string representation of the queue
     */
    private String getConfigOfQueue(Queue<Integer> q) {
        List<Integer> tmpList = new ArrayList<>(q);
        StringBuilder tmpString = new StringBuilder();
        for(int i : tmpList) {
            tmpString.append(i).append(",");
        }
        return tmpString.toString();
    }

    /**
     * Plays a game of Recursive Combat until we have resolved this game
     * The winner and details of the final will be stored in this object to be withdrawn later
     * @return Returns true when player one won the match
     */
    public boolean play() {
        //Keep going until we have a winner
        while(this.p1.size() > 0 && this.p2.size() > 0) {
            boolean p1WonThisRound;

            //Store the current configuration fo cards
            String currentConfig = this.getConfigOfQueue(this.p1) + "|" + this.getConfigOfQueue(this.p2);
            //If we have seen this before, p1 wins, this stops loops!
            if(this.previousSeenConfigurations.contains(currentConfig)) {
                this.p1Won = true;
                return this.p1Won;
            }else{
                //Store this configuration and start checking!
                this.previousSeenConfigurations.add(currentConfig);
                int p1Card = this.p1.poll();
                int p2Card = this.p2.poll();

                //If the card numbers are less than the size in both cases, time to start a new recursive game!
                if(p1Card <= this.p1.size() && p2Card <= this.p2.size()) {
                    //Set up the new recursive game
                    Queue<Integer> newP1 = new LinkedList<>();
                    Queue<Integer> newP2 = new LinkedList<>();
                    List<Integer> listP1 = new ArrayList<>(this.p1);
                    List<Integer> listP2 = new ArrayList<>(this.p2);
                    for(int i = 0; i < p1Card; i++) {
                        newP1.add(listP1.get(i));
                    }
                    for(int i = 0; i < p2Card; i++) {
                        newP2.add(listP2.get(i));
                    }

                    //p1 winning is determined now by who wins the recursive game
                    RecursiveCombatGame rcg = new RecursiveCombatGame(newP1, newP2);
                    p1WonThisRound = rcg.play();
                }else{
                    //If one of the players doesn't have enough to recurse, we hit our second base case
                    if(p1Card > p2Card) {
                        p1WonThisRound = true;
                    }else{
                        p1WonThisRound = false;
                    }
                }

                //Standard winning criteria again, winner gets the cards
                if(p1WonThisRound) {
                    this.p1.add(p1Card);
                    this.p1.add(p2Card);
                }else{
                    this.p2.add(p2Card);
                    this.p2.add(p1Card);
                }
            }
        }

        this.p1Won = this.p1.size() > 0;

        return this.p1Won;
    }

    /**
     * Returns the final score of the game by the winning hand
     * @return Returns the final score
     */
    public long getFinalScore() {
        Queue<Integer> winningHand;
        if(this.p1Won) {
            winningHand = this.p1;
        }else{
            winningHand = this.p2;
        }

        long score = 0;
        long count = 0;
        int currentMultiplier = winningHand.size();
        while(winningHand.size() > 0) {
            count += currentMultiplier * winningHand.poll();
            currentMultiplier--;
        }

        return count;
    }




}
