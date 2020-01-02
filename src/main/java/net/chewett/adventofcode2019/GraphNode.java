package net.chewett.adventofcode2019;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    private int cost;
    private List<GraphNode> linkedNodes = new ArrayList<>();
    private int x;
    private int y;

    public GraphNode(int cost, int x, int y) {
        this.cost = cost;
        this.x = x;
        this.y = y;
    }

    public void linkNode(GraphNode gn) {
        this.linkedNodes.add(gn);
    }


    public int getCost() {
        return cost;
    }

    public List<GraphNode> getLinkedNodes() {
        return linkedNodes;
    }

    public List<Point> getAdjacentPoints() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(this.x + 1, this.y));
        points.add(new Point(this.x - 1, this.y));
        points.add(new Point(this.x, this.y + 1));
        points.add(new Point(this.x, this.y - 1));

        return points;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
