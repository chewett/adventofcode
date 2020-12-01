package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.Moon;

import java.util.ArrayList;
import java.util.List;

public class Day12PartTwo {

    public static String getXString(List<Moon> moons) {
        String s = "";
        for(Moon m : moons) {
            s += "," + m.getPos().getX() + "," + m.getVelocity().getX() + ",";
        }
        return s;
    }

    public static String getYString(List<Moon> moons) {
        String s = "";
        for(Moon m : moons) {
            s += "," + m.getPos().getY() + "," + m.getVelocity().getY() + ",";
        }
        return s;
    }

    public static String getZString(List<Moon> moons) {
        String s = "";
        for(Moon m : moons) {
            s += "," + m.getPos().getZ() + "," + m.getVelocity().getZ() + ",";
        }
        return s;
    }

    public static List<Moon> getStartMoons() {
        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(0, 6, 1));
        moons.add(new Moon(4, 4, 19));
        moons.add(new Moon(-11, 1,  8));
        moons.add(new Moon(2, 19, 15));

        return moons;
    }

    public static void printMoons(List<Moon> moons) {
        int totalEnergy = 0;
        for(Moon m : moons) {
            System.out.println("pos=<x=" + m.getPos().getX() + ", y=" +  m.getPos().getY()+", z=" + m.getPos().getZ() + ">,"+
                    " vel=<x=" + m.getVelocity().getX() + ", y=" + m.getVelocity().getY()  + ", z=" + m.getVelocity().getZ()  + ">");
            totalEnergy += m.getTotalEnergy();
        }
        System.out.println("Total energy: " + totalEnergy);
    }

    public static void main(String[] args) {
        int[][] perms = new int[][] {
                new int[] { 0, 1 },
                new int[] { 0, 2 },
                new int[] { 0, 3 },
                new int[] { 1, 2 },
                new int[] { 1, 3 },
                new int[] { 2, 3 }
        };


        List<Moon> moons = Day12PartTwo.getStartMoons();


        String startString = Day12PartTwo.getXString(moons);
        String curString = "";
        long xSteps = 0;
        while(xSteps < 10000000) {
            for(int moonPerm = 0; moonPerm < perms.length; moonPerm++) {
                int[] moonPair = perms[moonPerm];
                moons.get(moonPair[0]).applyGravityX(moons.get(moonPair[1]));
            }
            for(Moon m : moons) {
                m.applyVelocityX();
            }
            xSteps++;

            curString = Day12PartTwo.getXString(moons);
            if(startString.equals(curString)) {
                System.out.println("After " + xSteps + " steps");
                break;
            }
        }

        startString = Day12PartTwo.getYString(moons);
        curString = "";
        long ySteps = 0;
        while(ySteps < 1000000000) {
            for(int moonPerm = 0; moonPerm < perms.length; moonPerm++) {
                int[] moonPair = perms[moonPerm];
                moons.get(moonPair[0]).applyGravityY(moons.get(moonPair[1]));
            }
            for(Moon m : moons) {
                m.applyVelocityY();
            }
            ySteps++;

            curString = Day12PartTwo.getYString(moons);
            if(startString.equals(curString)) {
                System.out.println("After " + ySteps + " steps");
                break;
            }
        }

        startString = Day12PartTwo.getZString(moons);
        long zSteps = 0;
        while(zSteps < 1000000000) {
            for(int moonPerm = 0; moonPerm < perms.length; moonPerm++) {
                int[] moonPair = perms[moonPerm];
                moons.get(moonPair[0]).applyGravityZ(moons.get(moonPair[1]));
            }
            for(Moon m : moons) {
                m.applyVelocityZ();
            }
            zSteps++;

            curString = Day12PartTwo.getZString(moons);
            if(startString.equals(curString)) {
                System.out.println("After " + zSteps + " steps");
                break;
            }
        }


        long x = xSteps;
        long y = ySteps;
        long z = zSteps;

        while(!(x == y && y == z)) {
            if(x < y && x < z) {
                x += xSteps;
            }else if(y < x && y < z) {
                y += ySteps;
            }else if(z < y && z < x) {
                z += zSteps;
            }else if(x == z) {
                x += xSteps;
                z += zSteps;
            }else if(z == y) {
                y += ySteps;
                z += zSteps;
            }else if(x == y) {
                x += xSteps;
                y += ySteps;
            }
        }

        System.out.println("States until combination: " + x);






    }
}
