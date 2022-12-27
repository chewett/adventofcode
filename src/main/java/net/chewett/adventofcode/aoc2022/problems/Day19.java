package net.chewett.adventofcode.aoc2022.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 19: Not Enough Minerals ---
 * Your scans show that the lava did indeed form obsidian!
 *
 * The wind has changed direction enough to stop sending lava droplets toward you, so you and the elephants exit the
 * cave. As you do, you notice a collection of geodes around the pond. Perhaps you could use the obsidian to create some
 * geode-cracking robots and break them open?
 *
 * To collect the obsidian from the bottom of the pond, you'll need waterproof obsidian-collecting robots. Fortunately,
 * there is an abundant amount of clay nearby that you can use to make them waterproof.
 *
 * In order to harvest the clay, you'll need special-purpose clay-collecting robots. To make any type of robot, you'll
 * need ore, which is also plentiful but in the opposite direction from the clay.
 *
 * Collecting ore requires ore-collecting robots with big drills. Fortunately, you have exactly one ore-collecting robot
 * in your pack that you can use to kickstart the whole operation.
 *
 * Each robot can collect 1 of its resource type per minute. It also takes one minute for the robot factory (also
 * conveniently from your pack) to construct any type of robot, although it consumes the necessary resources available
 * when construction begins.
 *
 * The robot factory has many blueprints (your puzzle input) you can choose from, but once you've configured it with a
 * blueprint, you can't change it. You'll need to work out which blueprint is best.
 *
 * For example:
 *
 * Blueprint 1:
 *   Each ore robot costs 4 ore.
 *   Each clay robot costs 2 ore.
 *   Each obsidian robot costs 3 ore and 14 clay.
 *   Each geode robot costs 2 ore and 7 obsidian.
 *
 * Blueprint 2:
 *   Each ore robot costs 2 ore.
 *   Each clay robot costs 3 ore.
 *   Each obsidian robot costs 3 ore and 8 clay.
 *   Each geode robot costs 3 ore and 12 obsidian.
 * (Blueprints have been line-wrapped here for legibility. The robot factory's actual assortment of blueprints are
 * provided one blueprint per line.)
 *
 * The elephants are starting to look hungry, so you shouldn't take too long; you need to figure out which blueprint
 *
 * would maximize the number of opened geodes after 24 minutes by figuring out which robots to build and when to build
 * them.
 *
 * Using blueprint 1 in the example above, the largest number of geodes you could open in 24 minutes is 9. One way to
 * achieve that is:
 *
 * == Minute 1 ==
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 *
 * == Minute 2 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 *
 * == Minute 3 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * The new clay-collecting robot is ready; you now have 1 of them.
 *
 * == Minute 4 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 1 clay-collecting robot collects 1 clay; you now have 1 clay.
 *
 * == Minute 5 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * 1 clay-collecting robot collects 1 clay; you now have 2 clay.
 * The new clay-collecting robot is ready; you now have 2 of them.
 *
 * == Minute 6 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 2 clay-collecting robots collect 2 clay; you now have 4 clay.
 *
 * == Minute 7 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * 2 clay-collecting robots collect 2 clay; you now have 6 clay.
 * The new clay-collecting robot is ready; you now have 3 of them.
 *
 * == Minute 8 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 9 clay.
 *
 * == Minute 9 ==
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 12 clay.
 *
 * == Minute 10 ==
 * 1 ore-collecting robot collects 1 ore; you now have 4 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 15 clay.
 *
 * == Minute 11 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 4 clay.
 * The new obsidian-collecting robot is ready; you now have 1 of them.
 *
 * == Minute 12 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 7 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 1 obsidian.
 * The new clay-collecting robot is ready; you now have 4 of them.
 *
 * == Minute 13 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 11 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 2 obsidian.
 *
 * == Minute 14 ==
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 15 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 3 obsidian.
 *
 * == Minute 15 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 5 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 4 obsidian.
 * The new obsidian-collecting robot is ready; you now have 2 of them.
 *
 * == Minute 16 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 9 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 6 obsidian.
 *
 * == Minute 17 ==
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 13 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 8 obsidian.
 *
 * == Minute 18 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 17 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 3 obsidian.
 * The new geode-cracking robot is ready; you now have 1 of them.
 *
 * == Minute 19 ==
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 21 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 5 obsidian.
 * 1 geode-cracking robot cracks 1 geode; you now have 1 open geode.
 *
 * == Minute 20 ==
 * 1 ore-collecting robot collects 1 ore; you now have 4 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 25 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 7 obsidian.
 * 1 geode-cracking robot cracks 1 geode; you now have 2 open geodes.
 *
 * == Minute 21 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 29 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 2 obsidian.
 * 1 geode-cracking robot cracks 1 geode; you now have 3 open geodes.
 * The new geode-cracking robot is ready; you now have 2 of them.
 *
 * == Minute 22 ==
 * 1 ore-collecting robot collects 1 ore; you now have 4 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 33 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 4 obsidian.
 * 2 geode-cracking robots crack 2 geodes; you now have 5 open geodes.
 *
 * == Minute 23 ==
 * 1 ore-collecting robot collects 1 ore; you now have 5 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 37 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 6 obsidian.
 * 2 geode-cracking robots crack 2 geodes; you now have 7 open geodes.
 *
 * == Minute 24 ==
 * 1 ore-collecting robot collects 1 ore; you now have 6 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 41 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 8 obsidian.
 * 2 geode-cracking robots crack 2 geodes; you now have 9 open geodes.
 * However, by using blueprint 2 in the example above, you could do even better: the largest number of geodes you could
 * open in 24 minutes is 12.
 *
 * Determine the quality level of each blueprint by multiplying that blueprint's ID number with the largest number of
 * geodes that can be opened in 24 minutes using that blueprint. In this example, the first blueprint has ID 1 and can
 * open 9 geodes, so its quality level is 9. The second blueprint has ID 2 and can open 12 geodes, so its quality level
 * is 24. Finally, if you add up the quality levels of all of the blueprints in the list, you get 33.
 *
 * Determine the quality level of each blueprint using the largest number of geodes it could produce in 24 minutes. What
 * do you get if you add up the quality level of all of the blueprints in your list?
 *
 * Your puzzle answer was 1599.
 *
 * --- Part Two ---
 * While you were choosing the best blueprint, the elephants found some food on their own, so you're not in as much of
 * a hurry; you figure you probably have 32 minutes before the wind changes direction again and you'll need to get out
 * of range of the erupting volcano.
 *
 * Unfortunately, one of the elephants ate most of your blueprint list! Now, only the first three blueprints in your
 * list are intact.
 *
 * In 32 minutes, the largest number of geodes blueprint 1 (from the example above) can open is 56. One way to achieve
 * that is:
 *
 * == Minute 1 ==
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 *
 * == Minute 2 ==
 * 1 ore-collecting robot collects 1 ore; you now have 2 ore.
 *
 * == Minute 3 ==
 * 1 ore-collecting robot collects 1 ore; you now have 3 ore.
 *
 * == Minute 4 ==
 * 1 ore-collecting robot collects 1 ore; you now have 4 ore.
 *
 * == Minute 5 ==
 * Spend 4 ore to start building an ore-collecting robot.
 * 1 ore-collecting robot collects 1 ore; you now have 1 ore.
 * The new ore-collecting robot is ready; you now have 2 of them.
 *
 * == Minute 6 ==
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 *
 * == Minute 7 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * The new clay-collecting robot is ready; you now have 1 of them.
 *
 * == Minute 8 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 1 clay-collecting robot collects 1 clay; you now have 1 clay.
 * The new clay-collecting robot is ready; you now have 2 of them.
 *
 * == Minute 9 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 2 clay-collecting robots collect 2 clay; you now have 3 clay.
 * The new clay-collecting robot is ready; you now have 3 of them.
 *
 * == Minute 10 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 3 clay-collecting robots collect 3 clay; you now have 6 clay.
 * The new clay-collecting robot is ready; you now have 4 of them.
 *
 * == Minute 11 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 4 clay-collecting robots collect 4 clay; you now have 10 clay.
 * The new clay-collecting robot is ready; you now have 5 of them.
 *
 * == Minute 12 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 5 clay-collecting robots collect 5 clay; you now have 15 clay.
 * The new clay-collecting robot is ready; you now have 6 of them.
 *
 * == Minute 13 ==
 * Spend 2 ore to start building a clay-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 6 clay-collecting robots collect 6 clay; you now have 21 clay.
 * The new clay-collecting robot is ready; you now have 7 of them.
 *
 * == Minute 14 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 14 clay.
 * The new obsidian-collecting robot is ready; you now have 1 of them.
 *
 * == Minute 15 ==
 * 2 ore-collecting robots collect 2 ore; you now have 4 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 21 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 1 obsidian.
 *
 * == Minute 16 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 14 clay.
 * 1 obsidian-collecting robot collects 1 obsidian; you now have 2 obsidian.
 * The new obsidian-collecting robot is ready; you now have 2 of them.
 *
 * == Minute 17 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 7 clay.
 * 2 obsidian-collecting robots collect 2 obsidian; you now have 4 obsidian.
 * The new obsidian-collecting robot is ready; you now have 3 of them.
 *
 * == Minute 18 ==
 * 2 ore-collecting robots collect 2 ore; you now have 4 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 14 clay.
 * 3 obsidian-collecting robots collect 3 obsidian; you now have 7 obsidian.
 *
 * == Minute 19 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 7 clay.
 * 3 obsidian-collecting robots collect 3 obsidian; you now have 10 obsidian.
 * The new obsidian-collecting robot is ready; you now have 4 of them.
 *
 * == Minute 20 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 3 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 14 clay.
 * 4 obsidian-collecting robots collect 4 obsidian; you now have 7 obsidian.
 * The new geode-cracking robot is ready; you now have 1 of them.
 *
 * == Minute 21 ==
 * Spend 3 ore and 14 clay to start building an obsidian-collecting robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 7 clay.
 * 4 obsidian-collecting robots collect 4 obsidian; you now have 11 obsidian.
 * 1 geode-cracking robot cracks 1 geode; you now have 1 open geode.
 * The new obsidian-collecting robot is ready; you now have 5 of them.
 *
 * == Minute 22 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 14 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 9 obsidian.
 * 1 geode-cracking robot cracks 1 geode; you now have 2 open geodes.
 * The new geode-cracking robot is ready; you now have 2 of them.
 *
 * == Minute 23 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 21 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 7 obsidian.
 * 2 geode-cracking robots crack 2 geodes; you now have 4 open geodes.
 * The new geode-cracking robot is ready; you now have 3 of them.
 *
 * == Minute 24 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 2 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 28 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 5 obsidian.
 * 3 geode-cracking robots crack 3 geodes; you now have 7 open geodes.
 * The new geode-cracking robot is ready; you now have 4 of them.
 *
 * == Minute 25 ==
 * 2 ore-collecting robots collect 2 ore; you now have 4 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 35 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 10 obsidian.
 * 4 geode-cracking robots crack 4 geodes; you now have 11 open geodes.
 *
 * == Minute 26 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 4 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 42 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 8 obsidian.
 * 4 geode-cracking robots crack 4 geodes; you now have 15 open geodes.
 * The new geode-cracking robot is ready; you now have 5 of them.
 *
 * == Minute 27 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 4 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 49 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 6 obsidian.
 * 5 geode-cracking robots crack 5 geodes; you now have 20 open geodes.
 * The new geode-cracking robot is ready; you now have 6 of them.
 *
 * == Minute 28 ==
 * 2 ore-collecting robots collect 2 ore; you now have 6 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 56 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 11 obsidian.
 * 6 geode-cracking robots crack 6 geodes; you now have 26 open geodes.
 *
 * == Minute 29 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 6 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 63 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 9 obsidian.
 * 6 geode-cracking robots crack 6 geodes; you now have 32 open geodes.
 * The new geode-cracking robot is ready; you now have 7 of them.
 *
 * == Minute 30 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 6 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 70 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 7 obsidian.
 * 7 geode-cracking robots crack 7 geodes; you now have 39 open geodes.
 * The new geode-cracking robot is ready; you now have 8 of them.
 *
 * == Minute 31 ==
 * Spend 2 ore and 7 obsidian to start building a geode-cracking robot.
 * 2 ore-collecting robots collect 2 ore; you now have 6 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 77 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 5 obsidian.
 * 8 geode-cracking robots crack 8 geodes; you now have 47 open geodes.
 * The new geode-cracking robot is ready; you now have 9 of them.
 *
 * == Minute 32 ==
 * 2 ore-collecting robots collect 2 ore; you now have 8 ore.
 * 7 clay-collecting robots collect 7 clay; you now have 84 clay.
 * 5 obsidian-collecting robots collect 5 obsidian; you now have 10 obsidian.
 * 9 geode-cracking robots crack 9 geodes; you now have 56 open geodes.
 * However, blueprint 2 from the example above is still better; using it, the largest number of geodes you could open
 * in 32 minutes is 62.
 *
 * You no longer have enough blueprints to worry about quality levels. Instead, for each of the first three blueprints,
 * determine the largest number of geodes you could open; then, multiply these three values together.
 *
 * Don't worry about quality levels; instead, just determine the largest number of geodes you could open using each of
 * the first three blueprints. What do you get if you multiply these numbers together?
 */
public class Day19 {

    /**
     * A simple formula to work out how many geodes could be produced if you buy one maker every minute until the end
     * However this is very optimistic as it assumes you can buy a new geode robot every turn
     * @param maxMinutes Max minutes left to mine
     * @param minute Current minute
     * @param geodeCount Current geodes
     * @param geodeRobots Current geode robot count
     * @return Number of geodes that could potentially be mined
     */
    private int guessHowManyGeodes(int maxMinutes, int minute, int geodeCount, int geodeRobots) {
        int minutesLeft = 1 + maxMinutes - minute;
        int cumulativeTotal = 0;
        for (int i = 0; i < minutesLeft; i++) {
            cumulativeTotal += i + 1;
        }

        return geodeCount + (minutesLeft * geodeRobots) + cumulativeTotal;
    }

    /**
     * Runs the geode simulation and either return the quality level or the number of geodes generated
     * @param blueprint Blueprint string
     * @param maxMinutes Max minute of minutes to run the blueprints for
     * @param returnQualityMetric If passed in as true then this will return the quality metric, or geode count
     * @return Either the quality metric or geode count
     */
    public int runGeodeSimulation(String blueprint, int maxMinutes, boolean returnQualityMetric) {
        String[] parts = blueprint.split("(Blueprint |: Each ore robot costs | ore. Each clay robot costs | ore. Each obsidian robot costs | ore and | clay. Each geode robot costs | obsidian.)");
        int blueprintId = Integer.parseInt(parts[1]);
        int oreRobotCost = Integer.parseInt(parts[2]);
        int clayRobotCost = Integer.parseInt(parts[3]);
        int obsidianRobotOreCost = Integer.parseInt(parts[4]);
        int obsidianRobotClayCost = Integer.parseInt(parts[5]);
        int geodeRobotOreCost = Integer.parseInt(parts[6]);
        int geodeRobotObsidianCost = Integer.parseInt(parts[7]);

        // We only buy one type of robot for the simulation so the max useful ore robots are the highest cost
        int maxNumberOfUsefulOreRobots = Math.max(oreRobotCost, Math.max(clayRobotCost, Math.max(obsidianRobotOreCost, geodeRobotOreCost)));
        int maxNumberOfUsefulClayRobots = obsidianRobotClayCost;

        int mostGeodes = 0;

        Stack<int[]> states = new Stack<>();

        //STORAGE IS MINUTE, ORE, CLAY, OBSIDIAN, GEODE, ORE ROBOT, CLAY ROBOT, OBSIDIAN ROBOT, GEODE BOT
        states.add(new int[] {0, 0, 0, 0, 0, 1, 0, 0, 0});
        while(states.size() > 0) {
            int[] state = states.pop();

            int nextMinute = state[0] + 1;
            int ore = state[1];
            int clay = state[2];
            int obsidian = state[3];
            int geode = state[4];
            int oreRobot = state[5];
            int clayRobot = state[6];
            int obsidianRobot = state[7];
            int geodeRobot = state[8];

            if(this.guessHowManyGeodes(maxMinutes, nextMinute, geode, geodeRobot) < mostGeodes) {
                continue;
            }

            if(nextMinute > maxMinutes) {
                mostGeodes = Math.max(mostGeodes, geode);
                continue;
            }

            int oreGain = oreRobot;
            int clayGain = clayRobot;
            int obsidianGain = obsidianRobot;
            int geodeGain = geodeRobot;

            //Do nothing and just wait, we just gain stuff!

            int[] newState1 = new int[]{ nextMinute, ore + oreGain,  clay + clayGain, obsidian + obsidianGain, geode + geodeGain,
                    oreRobot, clayRobot, obsidianRobot, geodeRobot};
            states.add(newState1);


            //Just buy a ore robot
            if(ore >= oreRobotCost && oreRobot < maxNumberOfUsefulOreRobots) {
                int[] newState = new int[] { nextMinute, ore + oreGain - oreRobotCost, clay + clayGain, obsidian + obsidianGain, geode + geodeGain, oreRobot+1, clayRobot, obsidianRobot, geodeRobot };
                states.add(newState);

            }

            //Just buy a clay robot
            if(ore >= clayRobotCost && clayRobot < maxNumberOfUsefulClayRobots) {
                int[] newState = new int[]{ nextMinute, ore + oreGain - clayRobotCost,  clay + clayGain, obsidian + obsidianGain, geode + geodeGain, oreRobot, clayRobot+1, obsidianRobot, geodeRobot};
                states.add(newState);
            }

            //Just buy an obsidian robot
            if(ore >= obsidianRobotOreCost && clay >= obsidianRobotClayCost) {
                int[] newState = new int[]{ nextMinute, ore + oreGain - obsidianRobotOreCost,  clay + clayGain - obsidianRobotClayCost,obsidian + obsidianGain, geode + geodeGain, oreRobot, clayRobot, obsidianRobot+1, geodeRobot};
                states.add(newState);
            }

            //Just buy a geode robot
            if(ore >= geodeRobotOreCost && obsidian >= geodeRobotObsidianCost) {
                int[] newState = new int[]{ nextMinute, ore + oreGain - geodeRobotOreCost,  clay + clayGain, obsidian + obsidianGain - geodeRobotObsidianCost, geode + geodeGain, oreRobot, clayRobot, obsidianRobot, geodeRobot+1};
                states.add(newState);
            }
        }

        return (returnQualityMetric ? blueprintId : 1) * mostGeodes;
    }


    /**
     * Add up the quality levels of all of the blueprints
     * @param input List of blueprints
     * @return The sum of all the quality levels
     */
    public long solvePartOne(List<String> input) {

        int blueprintTotals = 0;
        for(String blueprint : input) {
            blueprintTotals += this.runGeodeSimulation(blueprint, 24, true);
        }

        return blueprintTotals;
    }

    /**
     * Work out the largest number of geodes could be generated from the first three blueprints and multiply it together
     * @param input List of blueprints (we only process up to three)
     * @return Multiplication of the maximum number of geodes that could be produced
     */
    public long solvePartTwo(List<String> input) {

        int mult = 1;
        for(int i = 0; i < 3 && i < input.size(); i++) {
            mult *= this.runGeodeSimulation(input.get(i), 32, false);
        }

        return mult;

    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 19);

        Day19 d = new Day19();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of all quality levels is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The multiplication of the remaining three blueprints is " + partTwo);

    }


}
