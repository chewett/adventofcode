package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.aoc2023.Seed;
import net.chewett.adventofcode.aoc2023.Translation;
import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 5: If You Give A Seed A Fertilizer ---
 * You take the boat and find the gardener right where you were told he would be: managing a giant "garden" that looks
 * more to you like a farm.
 *
 * "A water source? Island Island is the water source!" You point out that Snow Island isn't receiving any water.
 *
 * "Oh, we had to stop the water because we ran out of sand to filter it with! Can't make snow with dirty water. Don't
 * worry, I'm sure we'll get more sand soon; we only turned off the water a few days... weeks... oh no." His face sinks
 * into a look of horrified realization.
 *
 * "I've been so busy making sure everyone here has food that I completely forgot to check why we stopped getting more
 * sand! There's a ferry leaving soon that is headed over in that direction - it's much faster than your boat. Could
 * you please go check it out?"
 *
 * You barely have time to agree to this request when he brings up another. "While you wait for the ferry, maybe you
 * can help us with our food production problem. The latest Island Island Almanac just arrived and we're having trouble
 * making sense of it."
 *
 * The almanac (your puzzle input) lists all of the seeds that need to be planted. It also lists what type of soil to
 * use with each kind of seed, what type of fertilizer to use with each kind of soil, what type of water to use with
 * each kind of fertilizer, and so on. Every type of seed, soil, fertilizer and so on is identified with a number,
 * but numbers are reused by each category - that is, soil 123 and fertilizer 123 aren't necessarily related to each
 * other.
 *
 * For example:
 *
 * seeds: 79 14 55 13
 *
 * seed-to-soil map:
 * 50 98 2
 * 52 50 48
 *
 * soil-to-fertilizer map:
 * 0 15 37
 * 37 52 2
 * 39 0 15
 *
 * fertilizer-to-water map:
 * 49 53 8
 * 0 11 42
 * 42 0 7
 * 57 7 4
 *
 * water-to-light map:
 * 88 18 7
 * 18 25 70
 *
 * light-to-temperature map:
 * 45 77 23
 * 81 45 19
 * 68 64 13
 *
 * temperature-to-humidity map:
 * 0 69 1
 * 1 0 69
 *
 * humidity-to-location map:
 * 60 56 37
 * 56 93 4
 * The almanac starts by listing which seeds need to be planted: seeds 79, 14, 55, and 13.
 *
 * The rest of the almanac contains a list of maps which describe how to convert numbers from a source category into
 * numbers in a destination category. That is, the section that starts with seed-to-soil map: describes how to convert
 * a seed number (the source) to a soil number (the destination). This lets the gardener and his team know which soil
 * to use with which seeds, which water to use with which fertilizer, and so on.
 *
 * Rather than list every source number and its corresponding destination number one by one, the maps describe entire
 * ranges of numbers that can be converted. Each line within a map contains three numbers: the destination range start,
 * the source range start, and the range length.
 *
 * Consider again the example seed-to-soil map:
 *
 * 50 98 2
 * 52 50 48
 * The first line has a destination range start of 50, a source range start of 98, and a range length of 2. This line
 * means that the source range starts at 98 and contains two values: 98 and 99. The destination range is the same
 * length, but it starts at 50, so its two values are 50 and 51. With this information, you know that seed number 98
 * corresponds to soil number 50 and that seed number 99 corresponds to soil number 51.
 *
 * The second line means that the source range starts at 50 and contains 48 values: 50, 51, ..., 96, 97. This
 * corresponds to a destination range starting at 52 and also containing 48 values: 52, 53, ..., 98, 99. So, seed
 * number 53 corresponds to soil number 55.
 *
 * Any source numbers that aren't mapped correspond to the same destination number. So, seed number 10 corresponds to
 * soil number 10.
 *
 * So, the entire list of seed numbers and their corresponding soil numbers looks like this:
 *
 * seed  soil
 * 0     0
 * 1     1
 * ...   ...
 * 48    48
 * 49    49
 * 50    52
 * 51    53
 * ...   ...
 * 96    98
 * 97    99
 * 98    50
 * 99    51
 * With this map, you can look up the soil number required for each initial seed number:
 *
 * Seed number 79 corresponds to soil number 81.
 * Seed number 14 corresponds to soil number 14.
 * Seed number 55 corresponds to soil number 57.
 * Seed number 13 corresponds to soil number 13.
 * The gardener and his team want to get started as soon as possible, so they'd like to know the closest location that
 * needs a seed. Using these maps, find the lowest location number that corresponds to any of the initial seeds. To do
 * this, you'll need to convert each seed number through other categories until you can find its corresponding location
 * number. In this example, the corresponding types are:
 *
 * Seed 79, soil 81, fertilizer 81, water 81, light 74, temperature 78, humidity 78, location 82.
 * Seed 14, soil 14, fertilizer 53, water 49, light 42, temperature 42, humidity 43, location 43.
 * Seed 55, soil 57, fertilizer 57, water 53, light 46, temperature 82, humidity 82, location 86.
 * Seed 13, soil 13, fertilizer 52, water 41, light 34, temperature 34, humidity 35, location 35.
 * So, the lowest location number in this example is 35.
 *
 * What is the lowest location number that corresponds to any of the initial seed numbers?
 *
 * --- Part Two ---
 * Everyone will starve if you only plant such a small number of seeds. Re-reading the almanac, it looks like the
 * seeds: line actually describes ranges of seed numbers.
 *
 * The values on the initial seeds: line come in pairs. Within each pair, the first value is the start of the range
 * and the second value is the length of the range. So, in the first line of the example above:
 *
 * seeds: 79 14 55 13
 * This line describes two ranges of seed numbers to be planted in the garden. The first range starts with seed
 * number 79 and contains 14 values: 79, 80, ..., 91, 92. The second range starts with seed number 55 and contains
 * 13 values: 55, 56, ..., 66, 67.
 *
 * Now, rather than considering four seed numbers, you need to consider a total of 27 seed numbers.
 *
 * In the above example, the lowest location number can be obtained from seed number 82, which corresponds to soil 84,
 * fertilizer 84, water 84, light 77, temperature 45, humidity 46, and location 46. So, the lowest location number is 46.
 *
 * Consider all of the initial seed numbers listed in the ranges on the first line of the almanac. What is the lowest
 * location number that corresponds to any of the initial seed numbers?
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

        //Run the translations each time
        seeds = this.runMaths(seeds, seedToSoil);
        seeds = this.runMaths(seeds, soilToFertiliser);
        seeds = this.runMaths(seeds, fertiliserToWater);
        seeds = this.runMaths(seeds, waterToLight);
        seeds = this.runMaths(seeds, lightToTemp);
        seeds = this.runMaths(seeds, tempToHumidity);
        seeds = this.runMaths(seeds, humidityToLocation);

        //And then find the result!
        long minSeed = Long.MAX_VALUE;
        for(Seed s : seeds) {
            minSeed = Math.min(s.getStart(), minSeed);
        }

        return minSeed;
    }

    /**
     * Do the replacement maths for each of the range mappings
     * @param seeds List of initial starting positions
     * @param translations Translation data to move the starting positions
     * @return New locations after the translations
     */
    public List<Seed> runMaths(List<Seed> seeds, List<Translation> translations) {
        //Copy the data, not sure I need to but Java is a right pain sometimes
        List<Seed> currentSeeds = new ArrayList<>();
        for(Seed s : seeds) {
            currentSeeds.add(s.copy());
        }
        List<Translation> currentTranslations = new ArrayList<>();
        for(Translation t : translations) {
            currentTranslations.add(t.copy());
        }

        //This loops through the seeds and then translation indexes until it finds a split it needs to perform
        //Then it splits the seeds + translations, breaks out of the loops, and starts again
        //This will eventually end up with no more splits needed and makes the next checks easy
        boolean hasSplit = true;
        while(hasSplit) {
            hasSplit = false;

            List<Seed> foundNewSeeds = null;
            List<Translation> foundNewTranslations = null;

            //Loop over the seeds
            int seedIndex;
            int translationIndex = 0;
            for(seedIndex = 0; seedIndex < currentSeeds.size(); seedIndex++) {
                Seed curSeed = currentSeeds.get(seedIndex);

                //And the translations
                for(translationIndex = 0; translationIndex < currentTranslations.size(); translationIndex++) {
                    Translation curTranslation = currentTranslations.get(translationIndex);

                    //If there is an overlap then we split
                    if(curSeed.doesTranslationOverlap(curTranslation)) {
                        hasSplit = true;
                        //Perform the split here
                        foundNewSeeds = curSeed.getNewSeeds(curTranslation);
                        foundNewTranslations = curTranslation.getNewTranslations(curSeed);
                        break;
                    }
                }

                if(hasSplit) {
                    break;
                }
            }

            //If then it has split, we create the new arrays by adding everything "current" and then the split data
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

        //Once we get here, everything has been split into matching regions between seeds and translations

        List<Seed> finalSeeds = new ArrayList<>();
        for(Seed seed : currentSeeds) {
            boolean foundTranslation = false;
            for (Translation t : currentTranslations) {
                //If there is a matching "start" region then we just perform the translation
                if(t.getSourceStart() == seed.getStart()) {
                    finalSeeds.add(new Seed(t.getDestination(), seed.getRange()));
                    foundTranslation = true;
                }
            }

            //If we didn't find the translation then we just continue as normal
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
        System.out.println("The lowest location number that corresponds to the initial seed numbers is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The newest lowest location number with the new seed planting data is " + partTwo);
    }
}


