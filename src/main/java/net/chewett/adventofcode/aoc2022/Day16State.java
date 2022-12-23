package net.chewett.adventofcode.aoc2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16State {

    private String location;
    private List<String> openValves = new ArrayList<>();

    private int score;

    private int minute;

    public Day16State(String location, List<String> openValves, int score, int minute) {
        this.location = location;
        this.openValves = openValves;
        this.score = score;
        this.minute = minute;
    }



    public List<String> getAllPossibleStates(List<String> locationsOfInterestToMoveTo) {

        List<String> locs = new ArrayList<>();
        for(String loc : locationsOfInterestToMoveTo) {
            if(!this.openValves.contains(loc)) {
                locs.add(loc);
            }
        }

        return locs;
    }


    public String getLocation() {
        return location;
    }

    public int getScore() {
        return score;
    }

    public int getMinute() {
        return minute;
    }

    public List<String> getOpenValves() {
        return openValves;
    }
}
