package net.chewett.adventofcode.aoc2019.problems;

import net.chewett.adventofcode.aoc2019.Moon;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

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


        List<Moon> moons = new ArrayList<>();
        moons.add(new Moon(0, 6, 1));
        moons.add(new Moon(4, 4, 19));
        moons.add(new Moon(-11, 1,  8));
        moons.add(new Moon(2, 19, 15));

        Day12.printMoons(moons);

        long steps = 0;
        while(steps < 1000) {
            for(int moonPerm = 0; moonPerm < perms.length; moonPerm++) {
                int[] moonPair = perms[moonPerm];
                moons.get(moonPair[0]).applyGravity(moons.get(moonPair[1]));
            }
            for(Moon m : moons) {
                m.applyVelocity();
            }
            steps++;

            System.out.println("After " + steps + " steps");
            Day12.printMoons(moons);
        }




    }
}
