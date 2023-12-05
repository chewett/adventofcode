package net.chewett.adventofcode.aoc2023;

import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.RangeFunctions;
import java.util.ArrayList;
import java.util.List;

public class Translation {

    private long destination;
    private long source;
    private long range;

    public Translation(long destination, long source, long range) {
        this.destination = destination;
        this.source = source;
        this.range = range;
    }

    public Translation copy() {
        return new Translation(this.destination, this.source, this.range);
    }

    public long getTranslationDiff() {
        return this.destination - this.source;
    }

    public long getSourceStart() {
        return this.source;
    }

    public long getDestination() {
        return this.destination;
    }

    public long getSourceEnd() {
        return this.source + this.range - 1;
    }

    public String toString() {
        return "Translation(" + this.source + ", " + this.getSourceEnd() + " => " +this.getTranslationDiff()+")";
    }

    public List<Translation> getNewTranslations(Seed s) {
        List<Translation> newTranslations = new ArrayList<>();

        List<Pair<Long>> newTranslationsPoint = RangeFunctions.getNewRanges(this.source, this.getSourceEnd(), s.getStart(), s.getEnd());

        for(Pair<Long> p : newTranslationsPoint) {
            newTranslations.add(new Translation(p.a + this.getTranslationDiff(), p.a, p.b - p.a + 1));
        }

        return newTranslations;
    }

}
