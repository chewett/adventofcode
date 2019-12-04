package net.chewett.adventofcode2019.wiring;

import java.util.Objects;

public class WirePoint {

    public int x;
    public int y;

    public WirePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getFromOrigin() {
        return Math.abs(this.x) + Math.abs(this.y);
    }

    public String getMapString() {
        return this.x + "," + this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WirePoint wirePoint = (WirePoint) o;
        return x == wirePoint.x &&
                y == wirePoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
