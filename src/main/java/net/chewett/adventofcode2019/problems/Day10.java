package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.Asteroid;

import java.util.*;

public class Day10 {

    public static void main(String[] args) {
        String[] asteroidsOne = new String[] {
                ".#..#",
                ".....",
                "#####",
                "....#",
                "...##"
        };

        asteroidsOne = new String[] {
            "......#.#.",
            "#..#.#....",
            "..#######.",
            ".#.#.###..",
            ".#..#.....",
            "..#....#.#",
            "#..#....#.",
            ".##.#..###",
            "##...#..#.",
            ".#....####"
        };
        asteroidsOne = new String[] {
            "#.#...#.#.",
            ".###....#.",
            ".#....#...",
            "##.#.#.#.#",
            "....#.#.#.",
            ".##..###.#",
            "..#...##..",
            "..##....##",
            "......#...",
            ".####.###.",
        };
        asteroidsOne = new String[] {
            ".#..#..###",
            "####.###.#",
            "....###.#.",
            "..###.##.#",
            "##.##.#.#.",
            "....###..#",
            "..#.#..#.#",
            "#..#.#.###",
            ".##...##.#",
            ".....#.#..",
        };
        asteroidsOne = new String[] {
            ".#..##.###...#######",
            "##.############..##.",
            ".#.######.########.#",
            ".###.#######.####.#.",
            "#####.##.#.##.###.##",
            "..#####..#.#########",
            "####################",
            "#.####....###.#.#.##",
            "##.#################",
            "#####.##.###..####..",
            "..######..##.#######",
            "####.##.####...##..#",
            ".#####..#.######.###",
            "##...#.##########...",
            "#.##########.#######",
            ".####.#.###.###.#.##",
            "....##.##.###..#####",
            ".#.#.###########.###",
            "#.#.#.#####.####.###",
            "###.##.####.##.#..##",
        };

        asteroidsOne = new String[] {
            "##.##..#.####...#.#.####",
            "##.###..##.#######..##..",
            "..######.###.#.##.######",
            ".#######.####.##.#.###.#",
            "..#...##.#.....#####..##",
            "#..###.#...#..###.#..#..",
            "###..#.##.####.#..##..##",
            ".##.##....###.#..#....#.",
            "########..#####..#######",
            "##..#..##.#..##.#.#.#..#",
            "##.#.##.######.#####....",
            "###.##...#.##...#.######",
            "###...##.####..##..#####",
            "##.#...#.#.....######.##",
            ".#...####..####.##...##.",
            "#.#########..###..#.####",
            "#.##..###.#.######.#####",
            "##..##.##...####.#...##.",
            "###...###.##.####.#.##..",
            "####.#.....###..#.####.#",
            "##.####..##.#.##..##.#.#",
            "#####..#...####..##..#.#",
            ".##.##.##...###.##...###",
            "..###.########.#.###..#."
        };
        List<Asteroid> field = Asteroid.parseFromField(asteroidsOne);

        int maxAsteroidsFound = 0;
        Asteroid bestOneForStation = field.get(0);
        for(Asteroid a :field) {
            Set<Double> seenAsteroids = new HashSet<>();
            for(Asteroid a2: field) {
                if(a.equals(a2)) {
                    continue;
                }

                seenAsteroids.add(a.getAngleToAsteroid(a2));
            }
            if(maxAsteroidsFound < seenAsteroids.size()) {
                maxAsteroidsFound = Math.max(maxAsteroidsFound, seenAsteroids.size());
                bestOneForStation = a;
            }
        }


        Map<Double, Queue<Asteroid>> angleMapping = new HashMap<>();
        for(Asteroid a : field) {
            Set<Double> seenAsteroids = new HashSet<>();
            if(bestOneForStation.equals(a)) {
                continue;
            }

            double angle = bestOneForStation.getAngleToAsteroid(a);
            if(!angleMapping.containsKey(angle)) {
                angleMapping.put(angle, new LinkedList<>());
            }

            double distanceTo = bestOneForStation.getDistanceTo(a);
            a.storeDistanceToStation(distanceTo);
            angleMapping.get(angle).add(a);

        }

        Set<Double> allAngles = angleMapping.keySet();
        List<Double> allAnglesSort = new ArrayList<>(allAngles);

        Collections.sort(allAnglesSort);
        for(double d : allAnglesSort) {
            Queue<Asteroid> asteroidsToOrder = angleMapping.get(d);
            List<Asteroid> asteroidsToOrderList = new ArrayList<>(asteroidsToOrder);
            Collections.sort(asteroidsToOrderList);
            angleMapping.put(d, new LinkedList<>(asteroidsToOrderList));
        }


        int asteroidNo = 0;
        while(asteroidNo < 200) {
            for(double d : allAnglesSort) {
                if(angleMapping.get(d).size() > 0) {
                    asteroidNo++;
                    Asteroid a = angleMapping.get(d).remove();
                    if(asteroidNo == 200) {
                        System.out.println(a.getAsteroidNumber());

                        return;
                    }
                }


            }
        }



        System.out.println("Found max asteroids: " + maxAsteroidsFound);


    }

}
