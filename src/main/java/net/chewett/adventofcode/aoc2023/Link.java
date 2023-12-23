package net.chewett.adventofcode.aoc2023;

import java.awt.*;
import java.util.Objects;

/**
 * Represents a link between two points with an associated cost
 */
public class Link {

    private int cost;
    private Point firstPoint;
    private Point secondPoint;

    /**
     * The constructor forces an ordering for the points just to make things a bit nicer
     * @param f
     * @param s
     * @param cost
     */
    public Link(Point f, Point s, int cost) {
        //Enforce point ordering:
        if(f.getY() < s.getY()) {
            this.firstPoint = f;
            this.secondPoint = s;
        }else if(f.getY() > s.getY()) {
            this.firstPoint = s;
            this.secondPoint = f;
        }else{
            if(f.getX() < s.getX()) {
                this.firstPoint = f;
                this.secondPoint = s;
            }else{
                this.firstPoint = s;
                this.secondPoint = f;
            }
        }

        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    /**
     * Given another point this looks to merge the two links together on a common point
     * If there is not a common point this throws a Runtime Exception
     * @param otherLink Other link to merge into this one
     * @return New link representing the merged links
     */
    public Link merge(Link otherLink) {

        Point firstNewPoint;
        Point secondNewPoint;
        int newCost = this.cost + otherLink.cost;

        if(this.firstPoint.equals(otherLink.getFirstPoint())) {
            firstNewPoint = this.secondPoint;
            secondNewPoint = otherLink.getSecondPoint();
        }else if(this.firstPoint.equals(otherLink.getSecondPoint())) {
            firstNewPoint = this.secondPoint;
            secondNewPoint = otherLink.getFirstPoint();
        }else if(this.secondPoint.equals(otherLink.getFirstPoint())) {
            firstNewPoint = this.firstPoint;
            secondNewPoint = otherLink.getSecondPoint();
        }else if(this.secondPoint.equals(otherLink.getSecondPoint())) {
            firstNewPoint = this.firstPoint;
            secondNewPoint = otherLink.getFirstPoint();
        }else{
            throw new RuntimeException("These items cannot be merged due to not similar values");
        }

        return new Link(firstNewPoint, secondNewPoint, newCost);
    }

    /**
     * When given one point, this will return the other. This allows easier general handling.
     * If it can't find the point given it will throw a Runtime Exception
     * @param p Point to find and return the other point of
     * @return Other of the two pair of points given the passed in one
     */
    public Point getOtherPoint(Point p) {
        if(p.equals(this.firstPoint)) {
            return this.secondPoint;
        }else if(p.equals(this.secondPoint)){
            return this.firstPoint;
        }else{
            throw new RuntimeException("Cannot find point passed in");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return cost == link.cost && Objects.equals(firstPoint, link.firstPoint) && Objects.equals(secondPoint, link.secondPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, firstPoint, secondPoint);
    }
}
