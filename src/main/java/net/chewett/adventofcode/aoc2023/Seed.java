package net.chewett.adventofcode.aoc2023;

import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.RangeFunctions;
import java.util.ArrayList;
import java.util.List;

public class Seed {

    private long start;
    private long range;

    public Seed(long start, long range) {
        this.start = start;
        this.range = range;
    }

    public long getEnd() {
        return this.start + this.range - 1;
    }

    public long getStart() {
        return this.start;
    }

    public long getRange() {
        return this.range;
    }

    public Seed copy() {
        return new Seed(this.start, this.range);
    }

    public String toString() {
        return "Seed(" + this.start + ", " + this.getEnd() + ")";
    }

    public boolean doesTranslationOverlap(Translation t) {
        boolean sameStart = this.start == t.getSourceStart();
        boolean sameEnd = this.getEnd() == t.getSourceEnd();

        return RangeFunctions.isRangeOverlapping(
            this.start, this.getEnd(), t.getSourceStart(), t.getSourceEnd()
        ) && !(sameStart && sameEnd);
    }

    public List<Seed> getNewSeeds(Translation t) {
        List<Seed> newSeeds = new ArrayList<>();

        List<Pair<Long>> newSeedsPoint = RangeFunctions.getNewRanges(this.start, this.getEnd(), t.getSourceStart(), t.getSourceEnd());

        for(Pair<Long> p : newSeedsPoint) {
            newSeeds.add(new Seed(p.a, p.b - p.a + 1));
        }

        return newSeeds;
    }

}
