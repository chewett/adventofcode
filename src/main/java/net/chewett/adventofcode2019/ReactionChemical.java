package net.chewett.adventofcode2019;

import java.util.Objects;

public class ReactionChemical {

    private String name;

    public ReactionChemical(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReactionChemical that = (ReactionChemical) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
