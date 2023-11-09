package net.chewett.adventofcode.aoc2021;

import net.chewett.adventofcode.aoc2020.problems.Day23;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day23State implements Comparable<Day23State> {

    private static int maxStates = 15;

    private char[] states = new char[15];
    private int energy;

    public Day23State(String state, int stateEnergy) {
        for(int i = 0; i < state.length(); i++) {
            this.states[i] = state.charAt(i);
        }
        this.energy = stateEnergy;
    }

    public String getState() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.states);
        return sb.toString();
    }

    public int getEnergy() {
        return this.energy;
    }

    @Override
    public int compareTo(Day23State o) {
        return this.getEnergy() - o.getEnergy();
    }

    public void printThis() {
        System.out.println("");
        System.out.println("Energy level: " + this.getEnergy());
        System.out.println("#############");
        System.out.println("#"+this.states[0]+this.states[1]+"."+this.states[4]+"."+this.states[7]+"."+this.states[10]+"."+this.states[13]+this.states[14]+"#");
        System.out.println("###"+this.states[2]+"#"+this.states[5]+"#"+this.states[8]+"#"+this.states[11]+"###");
        System.out.println("  #"+this.states[3]+"#"+this.states[6]+"#"+this.states[9]+"#"+this.states[12]+"#");
        System.out.println("  #########");

    }

    public Day23State createNewStateSwappingValue(int firstState, int secondState) {
        int mult = 1;
        if(this.states[firstState] == 'B') {
            mult = 10;
        }else if(this.states[firstState] == 'C') {
            mult = 100;
        }else if(this.states[firstState] == 'D') {
            mult = 1000;
        }

        int newEnergy = this.energy + (this.getEnergyForMove(firstState, secondState) * mult);

        StringBuilder stringState = new StringBuilder(this.getState());
        stringState.setCharAt(secondState, this.states[firstState]);
        stringState.setCharAt(firstState, '0');

        return new Day23State(stringState.toString(), newEnergy);
    }

    public List<Day23State> getNewStates() {
        List<Day23State> newStates = new ArrayList<>();
        for(int stateToCheck = 0; stateToCheck < Day23State.maxStates; stateToCheck++) {
            if(this.states[stateToCheck] != '0') {
                List<Integer> statesToMoveTo = this.getStatesToMoveToForState(stateToCheck);
                for(int newState : statesToMoveTo) {
                    newStates.add(this.createNewStateSwappingValue(stateToCheck, newState));
                }
            }
        }

        return newStates;
    }

    public boolean isAllFree(int[] listOfInts) {
        boolean allFree = true;
        for(int i : listOfInts) {
            allFree = allFree && this.states[i] == '0';
        }

        return allFree;
    }

    public boolean isFinalState() {
        return this.getState().equals("00AA0BB0CC0DD00");
    }

    public List<Integer> getStatesToMoveToForState(int currentState) {
        List<Integer> newStates = new ArrayList<>();
        char myValue = this.states[currentState];

        //If the current state is in the hallway, we move into our home positon
        if(currentState == 0) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{1, 2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{1, 2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{1, 4, 5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{1, 4, 5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{1, 4, 7, 8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{1, 4, 7, 8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{1, 4, 7, 10, 11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{1, 4, 10, 11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }
        }else if(currentState == 1) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{4, 5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{4, 5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{4, 7, 8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{4, 7, 8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{4, 7, 10, 11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{4, 7, 10, 11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 4) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{7, 8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{7, 8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{7, 10, 11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{7, 10, 11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 7) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{4, 2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{4, 2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{10, 11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{10, 11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 10) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{7, 4, 2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{7, 4, 2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{7, 5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{7, 5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 13) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{10, 7, 4, 2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{10, 7, 4, 2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{10, 7, 5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{10, 7, 5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{10, 8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{10, 8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 14) {
            if (myValue == 'A') {
                if (this.isAllFree(new int[]{13, 10, 7, 4, 2, 3})) {
                    newStates.add(3);
                } else if (this.isAllFree(new int[]{13, 10, 7, 4, 2}) && this.states[3] == 'A') {
                    newStates.add(2);
                }
            } else if (myValue == 'B') {
                if (this.isAllFree(new int[]{13, 10, 7, 5, 6})) {
                    newStates.add(6);
                } else if (this.isAllFree(new int[]{13, 10, 7, 5}) && this.states[6] == 'B') {
                    newStates.add(5);
                }
            } else if (myValue == 'C') {
                if (this.isAllFree(new int[]{13, 10, 8, 9})) {
                    newStates.add(9);
                } else if (this.isAllFree(new int[]{13, 10, 8}) && this.states[9] == 'C') {
                    newStates.add(8);
                }

            } else if (myValue == 'D') {
                if (this.isAllFree(new int[]{13, 11, 12})) {
                    newStates.add(12);
                } else if (this.isAllFree(new int[]{13, 11}) && this.states[12] == 'D') {
                    newStates.add(11);
                }

            } else {
                throw new RuntimeException("SOMETHING IS VERY WRONG");
            }

        }else if(currentState == 3) {
            if(this.isAllFree(new int[] {2,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {2, 1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {2,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {2,4,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {2,4,7,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {2,4,7,10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {2,4,7,10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 2) {
            if(this.isAllFree(new int[] {1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {4,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {4,7,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {4,7,10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {4,7,10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 6) {
            if(this.isAllFree(new int[] {5,4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {5,4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {5,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {5,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {5,7,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {5,7,10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {5,7,10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 5) {
            if(this.isAllFree(new int[] {4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {7,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {7,10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {7,10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 9) {
            if(this.isAllFree(new int[] {8,7,4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {8,7,5,4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {8,7,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {8,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {8,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {8,10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {8,10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 8) {
            if(this.isAllFree(new int[] {7,4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {7,5,4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {7,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {10,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {10,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 12) {
            if(this.isAllFree(new int[] {11,10,7,4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {11,10,7,5,4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {11,10,7,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {11,10,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {11,10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {11,13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {11,13,14})) {
                newStates.add(14);
            }

        }else if(currentState == 11) {
            if(this.isAllFree(new int[] {10,7,4,1,0})) {
                newStates.add(0);
            }
            if(this.isAllFree(new int[] {10,7,5,4,1}) && this.states[0] != '0') {
                newStates.add(1);
            }
            if(this.isAllFree(new int[] {10,7,4})) {
                newStates.add(4);
            }
            if(this.isAllFree(new int[] {10,7})) {
                newStates.add(7);
            }
            if(this.isAllFree(new int[] {10})) {
                newStates.add(10);
            }
            if(this.isAllFree(new int[] {13}) && this.states[14] != '0') {
                newStates.add(13);
            }
            if(this.isAllFree(new int[] {13,14})) {
                newStates.add(14);
            }

        }else{
            throw new RuntimeException("Bad stuff?");
        }

        return newStates;
    }

    public int getEnergyForMove(int start, int end) {
        int additionalVal = 0;

        if(start == 0) {
            start = 1;
            additionalVal++;
        }
        if(start == 3) {
            start = 2;
            additionalVal++;
        }
        if(start == 6) {
            start = 5;
            additionalVal++;
        }
        if(start == 9) {
            start = 8;
            additionalVal++;
        }
        if(start == 12) {
            start = 11;
            additionalVal++;
        }
        if(start == 14) {
            start = 13;
            additionalVal++;
        }

        if(end == 0) {
            end = 1;
            additionalVal++;
        }
        if(end == 3) {
            end = 2;
            additionalVal++;
        }
        if(end == 6) {
            end = 5;
            additionalVal++;
        }
        if(end == 9) {
            end = 8;
            additionalVal++;
        }
        if(end == 12) {
            end = 11;
            additionalVal++;
        }
        if(end == 14) {
            end = 13;
            additionalVal++;
        }


        if(start == 1) {
            if(end == 2) {
                return additionalVal + 2;
            }else if(end == 5) {
                return additionalVal + 4;
            }else if(end == 8) {
                return additionalVal + 6;
            }else if(end == 11) {
                return additionalVal + 8;
            }else{
                throw new RuntimeException("Found a bad state...");
            }
        }else if(start == 4) {
            if(end == 2) {
                return additionalVal + 2;
            }else if(end == 5) {
                return additionalVal + 2;
            }else if(end == 8) {
                return additionalVal + 4;
            }else if(end == 11) {
                return additionalVal + 6;
            }else{
                throw new RuntimeException("Found a bad state...");
            }
        }else if(start == 7) {
            if(end == 2) {
                return additionalVal + 4;
            }else if(end == 5) {
                return additionalVal + 2;
            }else if(end == 8) {
                return additionalVal + 2;
            }else if(end == 11) {
                return additionalVal + 4;
            }else{
                throw new RuntimeException("Found a bad state...");
            }
        }else if(start == 10) {
            if(end == 2) {
                return additionalVal + 6;
            }else if(end == 5) {
                return additionalVal + 4;
            }else if(end == 8) {
                return additionalVal + 2;
            }else if(end == 11) {
                return additionalVal + 2;
            }else{
                throw new RuntimeException("Found a bad state...");
            }
        }else if(start == 13) {
            if(end == 2) {
                return additionalVal + 8;
            }else if(end == 5) {
                return additionalVal + 6;
            }else if(end == 8) {
                return additionalVal + 4;
            }else if(end == 11) {
                return additionalVal + 2;
            }else{
                throw new RuntimeException("Found a bad state...");
            }
        }else if(start == 2) {
            if(end == 1) {
                return additionalVal + 2;
            }else if(end == 4) {
                return additionalVal + 2;
            }else if(end == 7) {
                return additionalVal + 4;
            }else if(end == 10) {
                return additionalVal + 6;
            }else if(end == 13) {
                return additionalVal + 8;
            }else{
                throw new RuntimeException("Found a bad state...");
            }

        }else if(start == 5) {
            if(end == 1) {
                return additionalVal + 4;
            }else if(end == 4) {
                return additionalVal + 2;
            }else if(end == 7) {
                return additionalVal + 2;
            }else if(end == 10) {
                return additionalVal + 4;
            }else if(end == 13) {
                return additionalVal + 6;
            }else{
                throw new RuntimeException("Found a bad state...");
            }

        }else if(start == 8) {
            if(end == 1) {
                return additionalVal + 6;
            }else if(end == 4) {
                return additionalVal + 4;
            }else if(end == 7) {
                return additionalVal + 2;
            }else if(end == 10) {
                return additionalVal + 2;
            }else if(end == 13) {
                return additionalVal + 4;
            }else{
                throw new RuntimeException("Found a bad state...");
            }

        }else if(start == 11) {
            if(end == 1) {
                return additionalVal + 8;
            }else if(end == 4) {
                return additionalVal + 6;
            }else if(end == 7) {
                return additionalVal + 4;
            }else if(end == 10) {
                return additionalVal + 2;
            }else if(end == 13) {
                return additionalVal + 2;
            }else{
                throw new RuntimeException("Found a bad state...");
            }

        }else{
            throw new RuntimeException("Found bad state type 2...");
        }
    }


}
