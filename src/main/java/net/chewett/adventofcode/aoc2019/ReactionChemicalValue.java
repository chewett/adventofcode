package net.chewett.adventofcode.aoc2019;

/**
 * Data holder holding the details of a reaction chemical and the value of it for 2019 Day 14
 */
public class ReactionChemicalValue {

    private ReactionChemical r;
    private long value;

    public ReactionChemicalValue(String s) {
        String[] partsOfChemical = s.trim().split(" ");
        this.value = Integer.parseInt(partsOfChemical[0]);
        this.r = new ReactionChemical(partsOfChemical[1].trim());
    }

    public ReactionChemical getReactionChemical() {
        return r;
    }

    public String getReactionChemicalName() {
        return this.r.getName();
    }

    public long getValue() {
        return value;
    }
}
