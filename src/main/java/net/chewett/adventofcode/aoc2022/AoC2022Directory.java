package net.chewett.adventofcode.aoc2022;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple data structure to hold directories and files, alongside helper methods to calculate the size
 * of the tree and also cache this
 */
public class AoC2022Directory {

    private final String name;
    private final AoC2022Directory parent;
    private final Map<String, Integer> files = new HashMap<>();
    private final Map<String, AoC2022Directory> dirs = new HashMap<>();

    /** Used for performance reasons to cache the size once we have generated the tree */
    int cachedSize = -1;


    public AoC2022Directory(String name, AoC2022Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public Map<String, AoC2022Directory> getDirs() {
        return dirs;
    }

    public String getName() {
        return name;
    }

    public void cacheSize() {
        //Cache all the children sizes, then calculate the size of this directory
        for(Map.Entry<String, AoC2022Directory> f : this.dirs.entrySet()) {
            f.getValue().cacheSize();
        }

        this.cachedSize = this.getSize();
    }

    public int getSize() {
        //Use the cache if it has been set, -1 represents it hasn't been set
        if(this.cachedSize != -1) {
            return this.cachedSize;
        }

        int total = 0;
        for(Map.Entry<String, Integer> e : this.files.entrySet()) {
            total += e.getValue();
        }
        for(Map.Entry<String, AoC2022Directory> f : this.dirs.entrySet()) {
            total += f.getValue().getSize();
        }

        return total;
    }

    public void addFile(String name, int size) {
        this.files.put(name, size);
    }

    public void addDir(AoC2022Directory d) {
        this.dirs.put(d.getName(), d);
    }

    public AoC2022Directory getDir(String name) {
        return this.dirs.get(name);
    }

    public AoC2022Directory getParent() {
        return parent;
    }
}
