package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.aoc2022.Day16State;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 16: Proboscidea Volcanium ---
 * The sensors have led you to the origin of the distress signal: yet another handheld device, just like the one the
 * Elves gave you. However, you don't see any Elves around; instead, the device is surrounded by elephants! They must
 * have gotten lost in these tunnels, and one of the elephants apparently figured out how to turn on the distress signal.
 *
 * The ground rumbles again, much stronger this time. What kind of cave is this, exactly? You scan the cave with your
 * handheld device; it reports mostly igneous rock, some ash, pockets of pressurized gas, magma... this isn't just a
 * cave, it's a volcano!
 *
 * You need to get the elephants out of here, quickly. Your device estimates that you have 30 minutes before the
 * volcano erupts, so you don't have time to go back out the way you came in.
 *
 * You scan the cave for other options and discover a network of pipes and pressure-release valves. You aren't sure how
 * such a system got into a volcano, but you don't have time to complain; your device produces a report (your puzzle
 * input) of each valve's flow rate if it were opened (in pressure per minute) and the tunnels you could use to move
 * between the valves.
 *
 * There's even a valve in the room you and the elephants are currently standing in labeled AA. You estimate it will
 * take you one minute to open a single valve and one minute to follow any tunnel from one valve to another. What is
 * the most pressure you could release?
 *
 * For example, suppose you had the following scan output:
 *
 * Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
 * Valve BB has flow rate=13; tunnels lead to valves CC, AA
 * Valve CC has flow rate=2; tunnels lead to valves DD, BB
 * Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
 * Valve EE has flow rate=3; tunnels lead to valves FF, DD
 * Valve FF has flow rate=0; tunnels lead to valves EE, GG
 * Valve GG has flow rate=0; tunnels lead to valves FF, HH
 * Valve HH has flow rate=22; tunnel leads to valve GG
 * Valve II has flow rate=0; tunnels lead to valves AA, JJ
 * Valve JJ has flow rate=21; tunnel leads to valve II
 * All of the valves begin closed. You start at valve AA, but it must be damaged or jammed or something: its flow rate
 * is 0, so there's no point in opening it. However, you could spend one minute moving to valve BB and another minute
 * opening it; doing so would release pressure during the remaining 28 minutes at a flow rate of 13, a total eventual
 * pressure release of 28 * 13 = 364. Then, you could spend your third minute moving to valve CC and your fourth minute
 * opening it, providing an additional 26 minutes of eventual pressure release at a flow rate of 2, or 52 total pressure
 * released by valve CC.
 *
 * Making your way through the tunnels like this, you could probably open many or all of the valves by the time 30
 * minutes have elapsed. However, you need to release as much pressure as possible, so you'll need to be methodical.
 * Instead, consider this approach:
 *
 * == Minute 1 ==
 * No valves are open.
 * You move to valve DD.
 *
 * == Minute 2 ==
 * No valves are open.
 * You open valve DD.
 *
 * == Minute 3 ==
 * Valve DD is open, releasing 20 pressure.
 * You move to valve CC.
 *
 * == Minute 4 ==
 * Valve DD is open, releasing 20 pressure.
 * You move to valve BB.
 *
 * == Minute 5 ==
 * Valve DD is open, releasing 20 pressure.
 * You open valve BB.
 *
 * == Minute 6 ==
 * Valves BB and DD are open, releasing 33 pressure.
 * You move to valve AA.
 *
 * == Minute 7 ==
 * Valves BB and DD are open, releasing 33 pressure.
 * You move to valve II.
 *
 * == Minute 8 ==
 * Valves BB and DD are open, releasing 33 pressure.
 * You move to valve JJ.
 *
 * == Minute 9 ==
 * Valves BB and DD are open, releasing 33 pressure.
 * You open valve JJ.
 *
 * == Minute 10 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve II.
 *
 * == Minute 11 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve AA.
 *
 * == Minute 12 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve DD.
 *
 * == Minute 13 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve EE.
 *
 * == Minute 14 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve FF.
 *
 * == Minute 15 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve GG.
 *
 * == Minute 16 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You move to valve HH.
 *
 * == Minute 17 ==
 * Valves BB, DD, and JJ are open, releasing 54 pressure.
 * You open valve HH.
 *
 * == Minute 18 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You move to valve GG.
 *
 * == Minute 19 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You move to valve FF.
 *
 * == Minute 20 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You move to valve EE.
 *
 * == Minute 21 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You open valve EE.
 *
 * == Minute 22 ==
 * Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
 * You move to valve DD.
 *
 * == Minute 23 ==
 * Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
 * You move to valve CC.
 *
 * == Minute 24 ==
 * Valves BB, DD, EE, HH, and JJ are open, releasing 79 pressure.
 * You open valve CC.
 *
 * == Minute 25 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * == Minute 26 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * == Minute 27 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * == Minute 28 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * == Minute 29 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * == Minute 30 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 * This approach lets you release the most pressure possible in 30 minutes with this valve layout, 1651.
 *
 * Work out the steps to release the most pressure in 30 minutes. What is the most pressure you can release?
 *
 * --- Part Two ---
 * You're worried that even with an optimal approach, the pressure released won't be enough. What if you got one of the
 * elephants to help you?
 *
 * It would take you 4 minutes to teach an elephant how to open the right valves in the right order, leaving you with
 * only 26 minutes to actually execute your plan. Would having two of you working together be better, even if it means
 * having less time? (Assume that you teach the elephant before opening any valves yourself, giving you both the same
 * full 26 minutes.)
 *
 * In the example above, you could teach the elephant to help you as follows:
 *
 * == Minute 1 ==
 * No valves are open.
 * You move to valve II.
 * The elephant moves to valve DD.
 *
 * == Minute 2 ==
 * No valves are open.
 * You move to valve JJ.
 * The elephant opens valve DD.
 *
 * == Minute 3 ==
 * Valve DD is open, releasing 20 pressure.
 * You open valve JJ.
 * The elephant moves to valve EE.
 *
 * == Minute 4 ==
 * Valves DD and JJ are open, releasing 41 pressure.
 * You move to valve II.
 * The elephant moves to valve FF.
 *
 * == Minute 5 ==
 * Valves DD and JJ are open, releasing 41 pressure.
 * You move to valve AA.
 * The elephant moves to valve GG.
 *
 * == Minute 6 ==
 * Valves DD and JJ are open, releasing 41 pressure.
 * You move to valve BB.
 * The elephant moves to valve HH.
 *
 * == Minute 7 ==
 * Valves DD and JJ are open, releasing 41 pressure.
 * You open valve BB.
 * The elephant opens valve HH.
 *
 * == Minute 8 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You move to valve CC.
 * The elephant moves to valve GG.
 *
 * == Minute 9 ==
 * Valves BB, DD, HH, and JJ are open, releasing 76 pressure.
 * You open valve CC.
 * The elephant moves to valve FF.
 *
 * == Minute 10 ==
 * Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
 * The elephant moves to valve EE.
 *
 * == Minute 11 ==
 * Valves BB, CC, DD, HH, and JJ are open, releasing 78 pressure.
 * The elephant opens valve EE.
 *
 * (At this point, all valves are open.)
 *
 * == Minute 12 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * ...
 *
 * == Minute 20 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 *
 * ...
 *
 * == Minute 26 ==
 * Valves BB, CC, DD, EE, HH, and JJ are open, releasing 81 pressure.
 * With the elephant helping, after 26 minutes, the best you could do would release a total of 1707 pressure.
 *
 * With you and an elephant working together for 26 minutes, what is the most pressure you could release?
 */
public class Day16 {

    /**
     * Cost memoization code mapping from one cave to another
     */
    private Map<String, Map<String, Integer>> costs = new HashMap<>();


    /**
     * Work out the time cost that it will take to go from one cave to another
     * @param start Starting cave
     * @param end Ending cave
     * @param tunnels Mappings between tunnels and caves
     * @return Time cost to move between two caves
     */
    private int costToLocationFromLocation(String start, String end, Map<String, List<String>> tunnels) {
        //Simple memoization step
        if(costs.containsKey(start) && costs.get(start).containsKey(end)) {
            return costs.get(start).get(end);
        }

        //Keep track of the locations we need to check, start with the start
        Queue<String> locationsToCheck = new LinkedList<>();
        Queue<Integer> costsToMove = new LinkedList<>();
        locationsToCheck.add(start);
        costsToMove.add(0);
        boolean foundThing = false;
        while(!foundThing) {
            //Pop the next location and cost
            String locToCheck = locationsToCheck.poll();
            int newCost = costsToMove.poll() + 1;

            for(String newLoc : tunnels.get(locToCheck)) {
                //If we find that this leads to the end, we have found our path!
                if(newLoc.equals(end)) {
                    if(!costs.containsKey(start)) {
                        costs.put(start, new HashMap<>());
                    }
                    costs.get(start).put(end, newCost);
                    return newCost;
                }else{
                    //havne't found it... add this to the location to check its tunnels of
                    locationsToCheck.add(newLoc);
                    costsToMove.add(newCost);
                }
            }
        }

        //Broken :(
        return -1;
    }

    /**
     * Take the input and then process it into the various parts and adds it to the arrays that are passed in
     * @param input Input string to parse and process
     * @param flowRates Flow rate map to store the data into
     * @param tunnels List of tunnels to store the data into
     * @param locationsOfInterestToMoveTo Locations of interest to
     */
    private void processInput(List<String> input, Map<String, Integer> flowRates, Map<String, List<String>> tunnels, List<String> locationsOfInterestToMoveTo) {
        for(String str : input) {
            String[] strParts = str.split("(Valve | has flow rate=|; tunnels lead to valves |; tunnel leads to valve )");
            int flowRate = Integer.parseInt(strParts[2]);
            flowRates.put(strParts[1], flowRate);
            if(flowRate != 0) {
                locationsOfInterestToMoveTo.add(strParts[1]);
            }

            String[] tunnelEnds = strParts[3].split(", ");
            tunnels.put(strParts[1], Arrays.asList(tunnelEnds));
        }
    }

    /**
     * Work out the maximum pressure I can reduce by opening vents in 30 minutes
     * @param input Cave, tunnel, and pressure data
     * @return Maximum amount of pressure I can reduce in 30 minutes
     */
    public long solvePartOne(List<String> input) {

        Map<String, Integer> flowRates = new HashMap<>();
        Map<String, List<String>> tunnels = new HashMap<>();
        List<String> locationsOfInterestToMoveTo = new ArrayList<>();
        this.processInput(input, flowRates, tunnels, locationsOfInterestToMoveTo);


        Queue<Day16State> states = new LinkedList<>();
        states.add(new Day16State("AA", new ArrayList<>(), 0, 0));
        int maxNumber = 0;

        while(states.size() > 0) {
            Day16State state = states.poll();

            List<String> statesToMoveTo = state.getAllPossibleStates(locationsOfInterestToMoveTo);
            for(String newState : statesToMoveTo) {
                int moveCost = this.costToLocationFromLocation(state.getLocation(), newState, tunnels);
                int newMinute = state.getMinute() + moveCost + 1;
                //Must be less than 30 otherwise you open it and stop :(
                if(newMinute < 30) {
                    maxNumber = Math.max(maxNumber, state.getScore());

                    int newScore = state.getScore() + (flowRates.get(newState) * (30-newMinute));
                    List<String> newOpenValves = new ArrayList<>(state.getOpenValves());
                    newOpenValves.add(newState);
                    states.add(new Day16State(newState, newOpenValves, newScore, newMinute));
                }else{
                    maxNumber = Math.max(maxNumber, state.getScore());
                }
            }
            if(statesToMoveTo.size() == 0) {
                maxNumber = Math.max(maxNumber, state.getScore());
            }

        }

        return maxNumber;
    }

    /**
     * This will take a while, there are much faster ways to solve this but it worked for me but I will optimise this later
     *
     * This does similar to part one but instead of just reducing pressure myself I also use an elephant, which after
     * training gives me 26 minutes
     *
     * @param input Cave, tunnel, and pressure data
     * @return Maximum amount of pressure the elephant and I can reduce in 26 minutes
     */
    public long solvePartTwo(List<String> input) {
        Map<String, Integer> flowRates = new HashMap<>();
        Map<String, List<String>> tunnels = new HashMap<>();
        List<String> locationsOfInterestToMoveTo = new ArrayList<>();
        this.processInput(input, flowRates, tunnels, locationsOfInterestToMoveTo);


        Stack<Day16State> states = new Stack<>();
        //Both person and elephant!
        states.add(new Day16State("AA", new ArrayList<>(), 0, 0));
        states.add(new Day16State("AA", new ArrayList<>(), 0, 0));
        int maxNumber = 0;

        int count = 0;
        while(states.size() > 0) {
            Day16State personState = states.pop();
            Day16State elephantState = states.pop();

            //Print some progress because this is LARGE!
            count++;
            if(count > 1000000) {
                System.out.println(maxNumber + " " + states.size() + " " + personState.getMinute() + " " + elephantState.getMinute());
                count = 0;
            }


            List<String> statesToMoveTo = personState.getAllPossibleStates(locationsOfInterestToMoveTo);
            boolean moved = false;

            for(String newState : statesToMoveTo) {
                int personMoveCost = this.costToLocationFromLocation(personState.getLocation(), newState, tunnels);
                int personNewMinute = personState.getMinute() + personMoveCost + 1;
                //Must be less than 26 otherwise you open it and stop :(
                if(personNewMinute < 26) {
                    int newScore = personState.getScore() + (flowRates.get(newState) * (26-personNewMinute));
                    List<String> newOpenValves = new ArrayList<>(personState.getOpenValves());
                    newOpenValves.add(newState);
                    states.add(new Day16State(newState, newOpenValves, newScore, personNewMinute));
                    states.add(new Day16State(elephantState.getLocation(), newOpenValves, elephantState.getScore(), elephantState.getMinute()));
                    moved = true;
                }

                int elephantMoveCost = this.costToLocationFromLocation(elephantState.getLocation(), newState, tunnels);
                int elephantNewMinute = elephantState.getMinute() + elephantMoveCost + 1;
                //Must be less than 26 otherwise you open it and stop :(
                if(elephantNewMinute < 26) {
                    int newScore = elephantState.getScore() + (flowRates.get(newState) * (26-elephantNewMinute));
                    List<String> newOpenValves = new ArrayList<>(elephantState.getOpenValves());
                    newOpenValves.add(newState);
                    states.add(new Day16State(personState.getLocation(), newOpenValves, personState.getScore(), personState.getMinute()));
                    states.add(new Day16State(newState, newOpenValves, newScore, elephantNewMinute));
                    moved = true;
                }
                if(!moved) {
                    maxNumber = Math.max(maxNumber, personState.getScore() + elephantState.getScore());
                }
            }
            if(statesToMoveTo.size() == 0) {
                maxNumber = Math.max(maxNumber, personState.getScore() + elephantState.getScore());
            }
        }

        return maxNumber;

    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 16);

        Day16 d = new Day16();
        long partOne = d.solvePartOne(input);
        System.out.println("The most pressure I can relieve is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("THe most pressure we can relieve working together with the elephant is "+ partTwo);
    }


}
