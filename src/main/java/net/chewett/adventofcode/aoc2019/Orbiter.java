package net.chewett.adventofcode.aoc2019;

import java.util.ArrayList;
import java.util.List;

public class Orbiter {

    private String name;
    private List<Orbiter> thingsThatOrbitThis = new ArrayList<>();
    private Orbiter parent = null;

    public Orbiter(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addOrbit(Orbiter o) {
        if(!this.thingsThatOrbitThis.contains(o)) {
            this.thingsThatOrbitThis.add(o);
            o.addParent(this);
        }
    }

    public void addParent(Orbiter o) {
        this.parent = o;
    }

    public Orbiter getParent() {
        return this.parent;
    }

    public List<Orbiter> getThingsThatOrbitThis() {
        return this.thingsThatOrbitThis;
    }

    public int calculateOrbits() {
        return calculateOrbits(0);
    }

    public int calculateOrbits(int level) {
        int total = 0;
        for(Orbiter o : this.thingsThatOrbitThis) {
            total += o.calculateOrbits(level + 1);
        }

        return level + total;
    }

}
