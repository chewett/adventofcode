package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ChineseRemainderTheorem;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 13: Shuttle Search ---
 * Your ferry can make it safely to a nearby port, but it won't get much further. When you call to book another ship,
 * you discover that no ships embark from that port to your vacation island. You'll need to get from the port to the
 * nearest airport.
 *
 * Fortunately, a shuttle bus service is available to bring you from the sea port to the airport! Each bus has an ID
 * number that also indicates how often the bus leaves for the airport.
 *
 * Bus schedules are defined based on a timestamp that measures the number of minutes since some fixed reference point
 * in the past. At timestamp 0, every bus simultaneously departed from the sea port. After that, each bus travels to the
 * airport, then various other locations, and finally returns to the sea port to repeat its journey forever.
 *
 * The time this loop takes a particular bus is also its ID number: the bus with ID 5 departs from the sea port at
 * timestamps 0, 5, 10, 15, and so on. The bus with ID 11 departs at 0, 11, 22, 33, and so on. If you are there when the
 * bus departs, you can ride that bus to the airport!
 *
 * Your notes (your puzzle input) consist of two lines. The first line is your estimate of the earliest timestamp you
 * could depart on a bus. The second line lists the bus IDs that are in service according to the shuttle company;
 * entries that show x must be out of service, so you decide to ignore them.
 *
 * To save time once you arrive, your goal is to figure out the earliest bus you can take to the airport. (There will be
 * exactly one such bus.)
 *
 * For example, suppose you have the following notes:
 *
 * 939
 * 7,13,x,x,59,x,31,19
 * Here, the earliest timestamp you could depart is 939, and the bus IDs in service are 7, 13, 59, 31, and 19. Near
 * timestamp 939, these bus IDs depart at the times marked D:
 *
 * time   bus 7   bus 13  bus 59  bus 31  bus 19
 * 929      .       .       .       .       .
 * 930      .       .       .       D       .
 * 931      D       .       .       .       D
 * 932      .       .       .       .       .
 * 933      .       .       .       .       .
 * 934      .       .       .       .       .
 * 935      .       .       .       .       .
 * 936      .       D       .       .       .
 * 937      .       .       .       .       .
 * 938      D       .       .       .       .
 * 939      .       .       .       .       .
 * 940      .       .       .       .       .
 * 941      .       .       .       .       .
 * 942      .       .       .       .       .
 * 943      .       .       .       .       .
 * 944      .       .       D       .       .
 * 945      D       .       .       .       .
 * 946      .       .       .       .       .
 * 947      .       .       .       .       .
 * 948      .       .       .       .       .
 * 949      .       D       .       .       .
 * The earliest bus you could take is bus ID 59. It doesn't depart until timestamp 944, so you would need to wait 944 -
 * 939 = 5 minutes before it departs. Multiplying the bus ID by the number of minutes you'd need to wait gives 295.
 *
 * What is the ID of the earliest bus you can take to the airport multiplied by the number of minutes you'll need to
 * wait for that bus?
 *
 * --- Part Two ---
 * The shuttle company is running a contest: one gold coin for anyone that can find the earliest timestamp such that
 * the first bus ID departs at that time and each subsequent listed bus ID departs at that subsequent minute. (The first
 * line in your input is no longer relevant.)
 *
 * For example, suppose you have the same list of bus IDs as above:
 *
 * 7,13,x,x,59,x,31,19
 * An x in the schedule means there are no constraints on what bus IDs must depart at that time.
 *
 * This means you are looking for the earliest timestamp (called t) such that:
 *
 * Bus ID 7 departs at timestamp t.
 * Bus ID 13 departs one minute after timestamp t.
 * There are no requirements or restrictions on departures at two or three minutes after timestamp t.
 * Bus ID 59 departs four minutes after timestamp t.
 * There are no requirements or restrictions on departures at five minutes after timestamp t.
 * Bus ID 31 departs six minutes after timestamp t.
 * Bus ID 19 departs seven minutes after timestamp t.
 * The only bus departures that matter are the listed bus IDs at their specific offsets from t. Those bus IDs can depart
 * at other times, and other bus IDs can depart at those times. For example, in the list above, because bus ID 19 must
 * depart seven minutes after the timestamp at which bus ID 7 departs, bus ID 7 will always also be departing with bus
 * ID 19 at seven minutes after timestamp t.
 *
 * In this example, the earliest timestamp at which this occurs is 1068781:
 *
 * time     bus 7   bus 13  bus 59  bus 31  bus 19
 * 1068773    .       .       .       .       .
 * 1068774    D       .       .       .       .
 * 1068775    .       .       .       .       .
 * 1068776    .       .       .       .       .
 * 1068777    .       .       .       .       .
 * 1068778    .       .       .       .       .
 * 1068779    .       .       .       .       .
 * 1068780    .       .       .       .       .
 * 1068781    D       .       .       .       .
 * 1068782    .       D       .       .       .
 * 1068783    .       .       .       .       .
 * 1068784    .       .       .       .       .
 * 1068785    .       .       D       .       .
 * 1068786    .       .       .       .       .
 * 1068787    .       .       .       D       .
 * 1068788    D       .       .       .       D
 * 1068789    .       .       .       .       .
 * 1068790    .       .       .       .       .
 * 1068791    .       .       .       .       .
 * 1068792    .       .       .       .       .
 * 1068793    .       .       .       .       .
 * 1068794    .       .       .       .       .
 * 1068795    D       D       .       .       .
 * 1068796    .       .       .       .       .
 * 1068797    .       .       .       .       .
 * In the above example, bus ID 7 departs at timestamp 1068788 (seven minutes after t). This is fine; the only
 * requirement on that minute is that bus ID 19 departs then, and it does.
 *
 * Here are some other examples:
 *
 * The earliest timestamp that matches the list 17,x,13,19 is 3417.
 * 67,7,59,61 first occurs at timestamp 754018.
 * 67,x,7,59,61 first occurs at timestamp 779210.
 * 67,7,x,59,61 first occurs at timestamp 1261476.
 * 1789,37,47,1889 first occurs at timestamp 1202161486.
 * However, with so many bus IDs in your list, surely the actual earliest timestamp will be larger than 100000000000000!
 *
 * What is the earliest timestamp such that all of the listed bus IDs depart at offsets matching their positions in the
 * list?
 */
public class Day13 {

    /**
     * Given the set of bus notes try to find the earliest bus I can take and multiply that by the amount of
     * time that I need to wait before catching it
     * @param notes List of bus departure times
     * @return The earliest bus ID multiplied by the time I need to wait before catching it
     */
    public int solvePartOne(List<String> notes) {
        int startingMinute = Integer.parseInt(notes.get(0));
        String[] busses = notes.get(1).split(",");
        List<Integer> bussesList = new ArrayList<>();
        for(String b : busses) {
            if(!b.equals("x")) {
                bussesList.add(Integer.parseInt(b));
            }
        }

        //Use Integer max value here so we don't have to have custom "first bus" logic.
        int closestTimeToStart = Integer.MAX_VALUE;
        int bestBus = 0;
        //Loop over them checking the earliest time that
        for(int b : bussesList) {
            int count = 0;
            //Count up to the first stop after the starting minute
            while(count < startingMinute) {
                count += b;
            }

            //If we have a time lower than the closest then this is currently the best bus!
            if(count < closestTimeToStart) {
                closestTimeToStart = count;
                bestBus = b;
            }

        }

        //Perform the required calculation to get out value
        return bestBus * (closestTimeToStart - startingMinute);
    }

    /**
     * This problem actually boils down to using the Chinese Remeinder Theorem to determine which number has the
     * remainders all offset properly in the question. Therefore the bulk of this answer is just formatting the data and
     * inputting it into the CRT function
     * @param notes Bus timings
     * @return The time at which each bus will arrive one after each other
     */
    public long solvePartTwo(List<String> notes) {
        String[] busIds = notes.get(1).split(",");
        List<Integer> busIdsList = new ArrayList<>();
        List<Integer> remainders = new ArrayList<>();
        //Reformat the data for the CRT, creating two arrays of values (Bus ID's) and remainders
        int offsetId = 0;
        for(String busIdString : busIds) {
            if(!busIdString.equals("x")) {
                int busIdInt = Integer.parseInt(busIdString);

                remainders.add((busIdInt - offsetId) % busIdInt);
                busIdsList.add(busIdInt);
            }
            offsetId++;
        }

        return ChineseRemainderTheorem.solve(busIdsList, remainders);
    }

    /**
     * This is a solution that does work, but would take an age. I am leaving it here so that I can think of possible
     * improvements however using the Chinese remainder Theorem is much more elegant.
     * @param notes
     * @return
     */
    public long solvePartTwoBruteforce(List<String> notes) {
        String[] busIds = notes.get(1).split(",");
        Map<Integer, Integer> busOffsets = new HashMap<>();
        int offsetId = 0;
        int highestBusId = 0;
        int secondHighestBusId = 0;
        for(String busIdString : busIds) {
            if(!busIdString.equals("x")) {
                int busId = Integer.parseInt(busIdString);
                if(busId > highestBusId) {
                    if(highestBusId > secondHighestBusId) {
                        secondHighestBusId = highestBusId;
                    }
                    highestBusId = busId;
                }else{
                    if(busId > secondHighestBusId) {
                        secondHighestBusId = busId;
                    }
                }
                busOffsets.put(busId, offsetId);
            }
            offsetId++;
        }

        long offsetToMove = busOffsets.get(highestBusId);

        boolean foundFitting = false;
        long currentTime = 0;
        while(!foundFitting) {
            currentTime += offsetToMove;

            boolean foundFittingThisTime = true;
            for(Map.Entry<Integer, Integer> e : busOffsets.entrySet()) {
                long thisBusTime = currentTime + e.getValue();
                if((thisBusTime % e.getKey()) != 0) {
                    foundFittingThisTime = false;
                }
            }

            if(foundFittingThisTime) {
                foundFitting = true;
            }
        }

        return currentTime;
    }

    public static void main(String[] args) {
        Day13 d = new Day13();
        List<String> notes = ProblemLoader.loadProblemIntoStringArray(2020, 13);

        int p1 = d.solvePartOne(notes);
        System.out.println("The earliest bus ID multiplied by the time I need to wait is "+ p1);
        long p2 = d.solvePartTwo(notes);
        System.out.println("The earliest time that all buses arrive at their given offsets is " + p2);
    }

}
