package net.chewett.adventofcode.aoc2019;

public class ReactionChemicalValue {

    private ReactionChemical r;
    private long value;

    public ReactionChemicalValue(String s) {
        String[] partsOfChemical = s.trim().split(" ");
        this.value = Integer.parseInt(partsOfChemical[0]);
        this.r = new ReactionChemical(partsOfChemical[1].trim());
    }

    public ReactionChemicalValue(ReactionChemical r, int value) {
        this.r = r;
        this.value = value;
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
