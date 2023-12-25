package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;
import com.microsoft.z3.*;


/**
 * --- Day 24: Never Tell Me The Odds ---
 * It seems like something is going wrong with the snow-making process. Instead of forming snow, the water that's been
 * absorbed into the air seems to be forming hail!
 *
 * Maybe there's something you can do to break up the hailstones?
 *
 * Due to strong, probably-magical winds, the hailstones are all flying through the air in perfectly linear
 * trajectories. You make a note of each hailstone's position and velocity (your puzzle input). For example:
 *
 * 19, 13, 30 @ -2,  1, -2
 * 18, 19, 22 @ -1, -1, -2
 * 20, 25, 34 @ -2, -2, -4
 * 12, 31, 28 @ -1, -2, -1
 * 20, 19, 15 @  1, -5, -3
 * Each line of text corresponds to the position and velocity of a single hailstone. The positions indicate where the
 * hailstones are right now (at time 0). The velocities are constant and indicate exactly how far each hailstone will
 * move in one nanosecond.
 *
 * Each line of text uses the format px py pz @ vx vy vz. For instance, the hailstone specified by
 * 20, 19, 15 @ 1, -5, -3 has initial X position 20, Y position 19, Z position 15, X velocity 1, Y velocity -5, and Z velocity -3. After one nanosecond, the hailstone would be at 21, 14, 12.
 *
 * Perhaps you won't have to do anything. How likely are the hailstones to collide with each other and smash into tiny
 * ice crystals?
 *
 * To estimate this, consider only the X and Y axes; ignore the Z axis. Looking forward in time, how many of the
 * hailstones' paths will intersect within a test area? (The hailstones themselves don't have to collide, just test for
 * intersections between the paths they will trace.)
 *
 * In this example, look for intersections that happen with an X and Y position each at least 7 and at most 27; in your
 * actual data, you'll need to check a much larger test area. Comparing all pairs of hailstones' future paths produces
 * the following results:
 *
 * Hailstone A: 19, 13, 30 @ -2, 1, -2
 * Hailstone B: 18, 19, 22 @ -1, -1, -2
 * Hailstones' paths will cross inside the test area (at x=14.333, y=15.333).
 *
 * Hailstone A: 19, 13, 30 @ -2, 1, -2
 * Hailstone B: 20, 25, 34 @ -2, -2, -4
 * Hailstones' paths will cross inside the test area (at x=11.667, y=16.667).
 *
 * Hailstone A: 19, 13, 30 @ -2, 1, -2
 * Hailstone B: 12, 31, 28 @ -1, -2, -1
 * Hailstones' paths will cross outside the test area (at x=6.2, y=19.4).
 *
 * Hailstone A: 19, 13, 30 @ -2, 1, -2
 * Hailstone B: 20, 19, 15 @ 1, -5, -3
 * Hailstones' paths crossed in the past for hailstone A.
 *
 * Hailstone A: 18, 19, 22 @ -1, -1, -2
 * Hailstone B: 20, 25, 34 @ -2, -2, -4
 * Hailstones' paths are parallel; they never intersect.
 *
 * Hailstone A: 18, 19, 22 @ -1, -1, -2
 * Hailstone B: 12, 31, 28 @ -1, -2, -1
 * Hailstones' paths will cross outside the test area (at x=-6, y=-5).
 *
 * Hailstone A: 18, 19, 22 @ -1, -1, -2
 * Hailstone B: 20, 19, 15 @ 1, -5, -3
 * Hailstones' paths crossed in the past for both hailstones.
 *
 * Hailstone A: 20, 25, 34 @ -2, -2, -4
 * Hailstone B: 12, 31, 28 @ -1, -2, -1
 * Hailstones' paths will cross outside the test area (at x=-2, y=3).
 *
 * Hailstone A: 20, 25, 34 @ -2, -2, -4
 * Hailstone B: 20, 19, 15 @ 1, -5, -3
 * Hailstones' paths crossed in the past for hailstone B.
 *
 * Hailstone A: 12, 31, 28 @ -1, -2, -1
 * Hailstone B: 20, 19, 15 @ 1, -5, -3
 * Hailstones' paths crossed in the past for both hailstones.
 * So, in this example, 2 hailstones' future paths cross inside the boundaries of the test area.
 *
 * However, you'll need to search a much larger test area if you want to see if any hailstones might collide. Look for
 * intersections that happen with an X and Y position each at least 200000000000000 and at most 400000000000000.
 * Disregard the Z axis entirely.
 *
 * Considering only the X and Y axes, check all pairs of hailstones' future paths for intersections. How many of these
 * intersections occur within the test area?
 *
 * --- Part Two ---
 * Upon further analysis, it doesn't seem like any hailstones will naturally collide. It's up to you to fix that!
 *
 * You find a rock on the ground nearby. While it seems extremely unlikely, if you throw it just right, you should be
 * able to hit every hailstone in a single throw!
 *
 * You can use the probably-magical winds to reach any integer position you like and to propel the rock at any integer
 * velocity. Now including the Z axis in your calculations, if you throw the rock at time 0, where do you need to be so
 * that the rock perfectly collides with every hailstone? Due to probably-magical inertia, the rock won't slow down or
 * change direction when it collides with a hailstone.
 *
 * In the example above, you can achieve this by moving to position 24, 13, 10 and throwing the rock at velocity
 * -3, 1, 2. If you do this, you will hit every hailstone as follows:
 *
 * Hailstone: 19, 13, 30 @ -2, 1, -2
 * Collision time: 5
 * Collision position: 9, 18, 20
 *
 * Hailstone: 18, 19, 22 @ -1, -1, -2
 * Collision time: 3
 * Collision position: 15, 16, 16
 *
 * Hailstone: 20, 25, 34 @ -2, -2, -4
 * Collision time: 4
 * Collision position: 12, 17, 18
 *
 * Hailstone: 12, 31, 28 @ -1, -2, -1
 * Collision time: 6
 * Collision position: 6, 19, 22
 *
 * Hailstone: 20, 19, 15 @ 1, -5, -3
 * Collision time: 1
 * Collision position: 21, 14, 12
 * Above, each hailstone is identified by its initial position and its velocity. Then, the time and position of that
 * hailstone's collision with your rock are given.
 *
 * After 1 nanosecond, the rock has exactly the same position as one of the hailstones, obliterating it into ice dust!
 * Another hailstone is smashed to bits two nanoseconds after that. After a total of 6 nanoseconds, all of the
 * hailstones have been destroyed.
 *
 * So, at time 0, the rock needs to be at X position 24, Y position 13, and Z position 10. Adding these three
 * coordinates together produces 47. (Don't add any coordinates from the rock's velocity.)
 *
 * Determine the exact position and velocity the rock needs to have at time 0 so that it perfectly collides with every
 * hailstone. What do you get if you add up the X, Y, and Z coordinates of that initial position?
 */
public class Day24 {


    /**
     * Gets the gradient (m) value of a line given the x and y movement values
     * @param xMove X movement value in one time step
     * @param yMove Y movement value in one time step
     * @return Gradient of the line
     */
    public double getGradient(long xMove, long yMove) {
        // diff in y / diff in x
        // 1.0 to get float division
        return (1.0*yMove) / xMove;

    }

    /**
     * Given the gradient, an x and y value this will calculate the C value
     * @param m Gradient of the line
     * @param x An x value on the line
     * @param y A y value on the line
     * @return The C intercept value
     */
    public double getC(double m, long x, long y) {
        // y = mx + c
        // y - mx = c

        return y - (m * x);

    }

    /**
     * Work out the X position that the lines cross each other by solving it together
     * @param m1 Gradient of line one
     * @param m2 Gradient of line two
     * @param c1 Y intercept of line one
     * @param c2 Y intercept of line two
     * @return The X position that both lines cross
     */
    public double findXCrossing(double m1, double m2, double c1, double c2) {
        // m1*x + c1 = y = m2*x + c2
        // m1*x + c1 = y = m2*x + c2
        // m1*x - m2*x = c2 - c1
        // x * (m1 - m2) = c2 - c1
        // x = (c2 - c1) / (m1 - m2);

        double left = m1 - m2;
        double right = c2 - c1;

        return right / left;
    }

    /**
     * Finds all the intersection points between the pairs of lines and work out the number of times their insection
     * is in the range
     * @param input List of lines
     * @return Number of times the lines intersect in the specific range
     */
    public long solvePartOne(List<String> input) {
        List<long[]> hailstones = new ArrayList<>();
        List<long[]> velocities = new ArrayList<>();

        for(String line : input) {
            String[] parts = line.replace(" ", "").split("([,@])");
            hailstones.add(
                new long[]{
                        Long.parseLong(parts[0]),
                        Long.parseLong(parts[1]),
                        Long.parseLong(parts[2])
                }
            );
            velocities.add(
                new long[]{
                        Long.parseLong(parts[3]),
                        Long.parseLong(parts[4]),
                        Long.parseLong(parts[5])
                }
            );
        }

        long testArea1 = 200000000000000L; long testArea2 = 400000000000000L;
        if(input.size() == 5) { //Test area for test input is much smaller so split it on the size of the input
            testArea1 = 7;
            testArea2 = 27;
        }

        //Loop over all pairs
        int insideArea = 0;
        for(int hail1Index = 0; hail1Index < hailstones.size(); hail1Index++) {
            for(int hail2Index = hail1Index+1; hail2Index < hailstones.size(); hail2Index++) {
                long[] hail1 = hailstones.get(hail1Index);
                long[] hail2 = hailstones.get(hail2Index);
                long[] velocity1 = velocities.get(hail1Index);
                long[] velocity2 = velocities.get(hail2Index);
                //Work out the equations of both lines
                double grad1 = this.getGradient(velocity1[0], velocity1[1]);
                double grad2 = this.getGradient(velocity2[0], velocity2[1]);
                double c1 = this.getC(grad1, hail1[0], hail1[1]);
                double c2 = this.getC(grad2, hail2[0], hail2[1]);

                //Work out the x and y crossing point
                double xCrossing = this.findXCrossing(grad1, grad2, c1, c2);
                double yCrossing = xCrossing * grad1 + c1;

                //Now we check to see if these are before/after the starting points

                //check to make sure it's in the future and not the past
                double differenceInX = xCrossing - hail1[0];
                double differenceInY = yCrossing - hail1[1];
                boolean xMovingInPositiveDirection = velocity1[0] > 0;
                boolean yMovingInPositiveDirection = velocity1[1] > 0;
                if(differenceInX > 0 != xMovingInPositiveDirection || (differenceInY > 0 != yMovingInPositiveDirection)) {
                    continue;
                }

                //Now test the second piece of hail
                differenceInX = xCrossing - hail2[0];
                differenceInY = yCrossing - hail2[1];
                xMovingInPositiveDirection = velocity2[0] > 0;
                yMovingInPositiveDirection = velocity2[1] > 0;
                if(differenceInX > 0 != xMovingInPositiveDirection || differenceInY > 0 != yMovingInPositiveDirection) {
                    continue;
                }

                //Then increment if it sits within the test area
                if(xCrossing >= testArea1 && xCrossing <= testArea2 && yCrossing >= testArea1 && yCrossing <= testArea2) {
                    insideArea++;
                }
            }
        }

        return insideArea;
    }

    /**
     * Works out the X, Y, Z positions that you need to start at to hit all of the points.
     * Originally I solved this with numpy but I wanted to solve it in Java#
     * Z3 seemed to be a really nice way of solving this by providing the equations
     * @param input List of lines
     * @return Sum of the X, Y, Z position you need to start at to properly hit every hailstone
     */
    public long solvePartTwo(List<String> input) {
        HashMap<String, String> cfg = new HashMap<>();
        cfg.put("model", "true");
        Context ctx = new Context(cfg);

        Solver solver = ctx.mkSolver();

        //Set up some variables/constants to use
        IntExpr zero = ctx.mkInt(0);
        IntExpr rx = ctx.mkIntConst("rx");
        IntExpr ry = ctx.mkIntConst("ry");
        IntExpr rz = ctx.mkIntConst("rz");
        IntExpr rvx = ctx.mkIntConst("rvx");
        IntExpr rvy = ctx.mkIntConst("rvy");
        IntExpr rvz = ctx.mkIntConst("rvz");

        //We only need to match three hailstones to work out the X/Y/Z positions to start with
        //Essentially we add all the various equations to the solver and ask it really nicely to solve it
        for(int i = 0; i < 3; i++) {
            String[] parts = input.get(i).replace(" ", "").split("([,@])");

            IntExpr curTime = ctx.mkIntConst("t_" + i);

            IntNum xPos = ctx.mkInt(Long.parseLong(parts[0]));
            IntNum yPos = ctx.mkInt(Long.parseLong(parts[1]));
            IntNum zPos = ctx.mkInt(Long.parseLong(parts[2]));
            IntNum xVel = ctx.mkInt(Long.parseLong(parts[3]));
            IntNum yVel = ctx.mkInt(Long.parseLong(parts[4]));
            IntNum zVel = ctx.mkInt(Long.parseLong(parts[5]));

            // add t_ >= 0 to ensure that its always in the future
            solver.add(ctx.mkGe(curTime, zero));
            // add rx + curTime * rvx == xPos + (curTime * xVel) to solve us meeting the X axis
            solver.add(ctx.mkEq(
                ctx.mkAdd(rx, ctx.mkMul(curTime, rvx)),
                ctx.mkAdd(xPos, ctx.mkMul(curTime, xVel))
            ));
            // add ry + curTime * rvy == yPos + (curTime * yVel) to solve us meeting the Y axis
            solver.add(ctx.mkEq(
                ctx.mkAdd(ry, ctx.mkMul(curTime, rvy)),
                ctx.mkAdd(yPos, ctx.mkMul(curTime, yVel))
            ));
            // add rz + curTime * rvz == zPos + (curTime * zVel) to solve us meeting the Z axis
            solver.add(ctx.mkEq(
                ctx.mkAdd(rz, ctx.mkMul(curTime, rvz)),
                ctx.mkAdd(zPos, ctx.mkMul(curTime, zVel))
            ));
        }

        //Now lets run the solver and check to see if it is satisfiable
        Status s = solver.check();
        if(s != Status.SATISFIABLE) {
            throw new RuntimeException("Something is wrong with this and it is not sat");
        }

        //It all worked! Lets get the values now :)
        Model m = solver.getModel();
        IntNum rxValue = (IntNum)m.eval(rx, true);
        IntNum ryValue = (IntNum)m.eval(ry, true);
        IntNum rzValue = (IntNum)m.eval(rz, true);

        //Work out the final value now we have worked out X, Y, Z
        return rxValue.getInt64() + ryValue.getInt64() + rzValue.getInt64();
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 24);

        Day24 d = new Day24();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of intersections occurring in the region is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("Tne sum of the X, Y, and Z coordinates of the starting position is " + partTwo);
    }
}


