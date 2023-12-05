package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 *
 */
public class Day5 {

    private List<Long> createMapping(String line) {
        String[] splitLine = line.split(" ");
        List<Long> newList = new ArrayList<>();
        for(String str : splitLine) {
            newList.add(Long.parseLong(str));
        }
        return newList;
    }

    private long translate(List<List<Long>> translation, long translateVal) {
        for(List<Long> transValueFunc : translation) {
            long destinationMin = transValueFunc.get(0);
            long sourceMin = transValueFunc.get(1);
            long range = transValueFunc.get(2);
            long diff = destinationMin - sourceMin;

            long sourceMax = sourceMin + range - 1;

            if(translateVal >= sourceMin && translateVal <= sourceMax) {
                return translateVal + diff;
            }
        }

        return translateVal;
    }

    public List<Pair<Long>> newRanges(long sourceMin, long sourceMax, long rangeMin, long rangeMax) {
        List<Pair<Long>> finalRanges = new ArrayList<>();

        long intersectionA = Math.max(sourceMin, rangeMin);
        long intersectionB = Math.min(sourceMax, rangeMax);

        if(sourceMin < intersectionA) {
            finalRanges.add(new Pair<Long>(sourceMin, intersectionA-1));
        }
        finalRanges.add(new Pair<Long>(intersectionA, intersectionB));
        if(intersectionB < sourceMax) {
            finalRanges.add(new Pair<Long>(intersectionB + 1, sourceMax));
        }

        return finalRanges;
    }

    public long solvePartOne(List<String> input) {
        List<Long> seeds = new ArrayList<>();
        List<List<Long>> seedToSoil = new ArrayList<>();
        List<List<Long>> soilToFertiliser = new ArrayList<>();
        List<List<Long>> fertiliserToWater = new ArrayList<>();
        List<List<Long>> waterToLight = new ArrayList<>();
        List<List<Long>> lightToTemp = new ArrayList<>();
        List<List<Long>> tempToHumidity = new ArrayList<>();
        List<List<Long>> humidityToLocation = new ArrayList<>();

        int curLine = 0;
        String[] seedsStr = input.get(curLine).split("seeds: ")[1].split(" ");
        for(String str : seedsStr) {
            seeds.add(Long.parseLong(str));
        }

        curLine = 3;
        String curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            seedToSoil.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            soilToFertiliser.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            fertiliserToWater.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            waterToLight.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            lightToTemp.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            tempToHumidity.add(this.createMapping(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            humidityToLocation.add(this.createMapping(curLineString));
            curLine += 1;
            if(curLine >= input.size()) {
                curLineString = "";
            }else {
                curLineString = input.get(curLine);
            }
        }

        List<Long> endSeeds = new ArrayList<>();
        for(Long seed : seeds) {
            long finalSeedVal = seed;
            finalSeedVal = this.translate(seedToSoil, finalSeedVal);
            finalSeedVal = this.translate(soilToFertiliser, finalSeedVal);
            finalSeedVal = this.translate(fertiliserToWater, finalSeedVal);
            finalSeedVal = this.translate(waterToLight, finalSeedVal);
            finalSeedVal = this.translate(lightToTemp, finalSeedVal);
            finalSeedVal = this.translate(tempToHumidity, finalSeedVal);
            finalSeedVal = this.translate(humidityToLocation, finalSeedVal);

            endSeeds.add(finalSeedVal);
        }

        long minVal = Long.MAX_VALUE;
        for(long l : endSeeds) {
            minVal = Math.min(minVal, l);
        }

        return minVal;
    }

    private Translation createMapping2(String line) {
        String[] splitLine = line.split(" ");
        return new Translation(
                Long.parseLong(splitLine[0]),
                Long.parseLong(splitLine[1]),
                Long.parseLong(splitLine[2])
        );
    }

    public long solvePartTwo(List<String> input) {
        List<Seed> seeds = new ArrayList<>();
        List<Translation> seedToSoil = new ArrayList<>();
        List<Translation> soilToFertiliser = new ArrayList<>();
        List<Translation> fertiliserToWater = new ArrayList<>();
        List<Translation> waterToLight = new ArrayList<>();
        List<Translation> lightToTemp = new ArrayList<>();
        List<Translation> tempToHumidity = new ArrayList<>();
        List<Translation> humidityToLocation = new ArrayList<>();

        int curLine = 0;
        String[] seedsStr = input.get(curLine).split("seeds: ")[1].split(" ");
        int seedIndex = 0;
        while(seedIndex < seedsStr.length) {
            seeds.add(new Seed(Long.parseLong(seedsStr[seedIndex]), Long.parseLong(seedsStr[seedIndex+1])));
            seedIndex +=2;
        }

        curLine = 3;
        String curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            seedToSoil.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            soilToFertiliser.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            fertiliserToWater.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            waterToLight.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            lightToTemp.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            tempToHumidity.add(this.createMapping2(curLineString));
            curLine += 1;
            curLineString = input.get(curLine);
        }

        curLine += 2;
        curLineString = input.get(curLine);
        while(!curLineString.equals("")) {
            humidityToLocation.add(this.createMapping2(curLineString));
            curLine += 1;
            if(curLine >= input.size()) {
                curLineString = "";
            }else {
                curLineString = input.get(curLine);
            }
        }


        seeds = this.runMaths(seeds, seedToSoil);
        seeds = this.runMaths(seeds, soilToFertiliser);
        seeds = this.runMaths(seeds, fertiliserToWater);
        seeds = this.runMaths(seeds, waterToLight);
        seeds = this.runMaths(seeds, lightToTemp);
        seeds = this.runMaths(seeds, tempToHumidity);
        seeds = this.runMaths(seeds, humidityToLocation);

        long minSeed = Long.MAX_VALUE;
        for(Seed s : seeds) {
            minSeed = Math.min(s.getStart(), minSeed);
        }

        return minSeed;
    }

    public List<Seed> runMaths(List<Seed> seeds, List<Translation> translations) {

        List<Seed> currentSeeds = new ArrayList<>();
        for(Seed s : seeds) {
            currentSeeds.add(s.copy());
        }
        List<Translation> currentTranslations = new ArrayList<>();
        for(Translation t : translations) {
            currentTranslations.add(t.copy());
        }

        boolean hasSplit = true;
        while(hasSplit) {
            hasSplit = false;

            List<Seed> foundNewSeeds = null;
            List<Translation> foundNewTranslations = null;

            int seedIndex;
            int translationIndex = 0;
            for(seedIndex = 0; seedIndex < currentSeeds.size(); seedIndex++) {
                Seed curSeed = currentSeeds.get(seedIndex);

                for(translationIndex = 0; translationIndex < currentTranslations.size(); translationIndex++) {
                    Translation curTranslation = currentTranslations.get(translationIndex);

                    if(curSeed.doesTranslationOverlap(curTranslation)) {
                        hasSplit = true;
                        foundNewSeeds = curSeed.getNewSeeds(curTranslation);
                        foundNewTranslations = curTranslation.getNewTranslations(curSeed);
                        break;
                    }
                }

                if(hasSplit) {
                    break;
                }
            }

            if(hasSplit) {
                List<Seed> newSeedsToReplace = new ArrayList<>(foundNewSeeds);
                for(int seedIndexToAdd = 0; seedIndexToAdd < currentSeeds.size(); seedIndexToAdd++) {
                    if(seedIndexToAdd != seedIndex) {
                        newSeedsToReplace.add(currentSeeds.get(seedIndexToAdd));
                    }
                }

                List<Translation> newTranslationsToReplace = new ArrayList<>(foundNewTranslations);
                for(int translationIndexToAdd = 0; translationIndexToAdd < currentTranslations.size(); translationIndexToAdd++) {
                    if(translationIndexToAdd != translationIndex) {
                        newTranslationsToReplace.add(currentTranslations.get(translationIndexToAdd));
                    }
                }

                currentSeeds = newSeedsToReplace;
                currentTranslations = newTranslationsToReplace;
            }

        }


        List<Seed> finalSeeds = new ArrayList<>();
        for(Seed seed : currentSeeds) {
            boolean foundTranslation = false;
            for (Translation t : currentTranslations) {
                if(t.getSourceStart() == seed.getStart()) {
                    finalSeeds.add(new Seed(t.getDestination(), seed.getRange()));
                    foundTranslation = true;
                }
            }

            if(!foundTranslation) {
                finalSeeds.add(seed);
            }
        }

        return finalSeeds;

    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 5);

        Day5 d = new Day5();
        long partOne = d.solvePartOne(input);
        System.out.println("" + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("" + partTwo);
    }
}


