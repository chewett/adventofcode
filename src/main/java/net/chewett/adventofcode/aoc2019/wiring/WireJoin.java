package net.chewett.adventofcode.aoc2019.wiring;

import java.util.Objects;

public class WireJoin {

    public int x1;
    public int y1;
    public int x2;
    public int y2;

    public WireJoin(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public boolean containsPoint(WirePoint wp) {
        return (wp.x == x1 && wp.y == y1)
                || (wp.x == x2 && wp.y == y2);
    }

    public WirePoint getOtherPoint(WirePoint wp) {
        if(wp.x == x1 && wp.y == y1) {
            return new WirePoint(x2, y2);
        }else{
            return new WirePoint(x1, y1);
        }
    }

    public WirePoint[] getBothPoints() {
        return new WirePoint[] {
            new WirePoint(x1, y1),
            new WirePoint(x2, y2)
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WireJoin wireJoin = (WireJoin) o;
        return x1 == wireJoin.x1 &&
                y1 == wireJoin.y1 &&
                x2 == wireJoin.x2 &&
                y2 == wireJoin.y2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }
}
