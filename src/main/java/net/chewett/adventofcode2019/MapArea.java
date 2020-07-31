package net.chewett.adventofcode2019;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * WALL = #
 * FLOOR = .
 * OXYGEN = O
 *
 * NORTH - 1
 * SOUTH - 2
 * WEST - 3
 * EAST - 4
 */
public class MapArea {

    private Map<Point, Character> mapData = new HashMap<>();

    public MapArea() {

    }

    public Map<Point, Character> getMapData() {
        return mapData;
    }

    public void setMapData(int x, int y, char letter) {
        this.mapData.put(new Point(x, y), letter);
    }

    public char getMapData(int x, int y) {
        return this.mapData.getOrDefault(new Point(x, y), '?');
    }



    public int getDirectionToMoveToPoint(int startX, int startY, int endX, int endY) {
        //System.out.println("Getting direction to move in for " + startX+","+startY+" to " + endX + "," + endY);
        Map<Point, GraphNode> allNodes = new HashMap<>();
        GraphNode gn = new GraphNode(0, startX, startY);
        GraphNode endNode = new GraphNode(999999, endX, endY);
        allNodes.put(new Point(startX, startY), gn);
        allNodes.put(new Point(endX, endY), endNode);

        Queue<GraphNode> nodesToVisit = new LinkedList<>();

        GraphNode currentNode = gn;
        while(currentNode != null) {
            List<Point> adjacentNodes = currentNode.getAdjacentPoints();
            for(Point p : adjacentNodes) {
                if(!allNodes.containsKey(p) && this.getMapData(p.x, p.y) == '.') {
                    GraphNode newNode = new GraphNode(currentNode.getCost() + 1, p.x, p.y);
                    newNode.linkNode(currentNode);
                    currentNode.linkNode(newNode);
                    nodesToVisit.add(newNode);
                    allNodes.put(new Point(newNode.getX(), newNode.getY()), newNode);
                }
            }

            if(nodesToVisit.size() > 0) {
                currentNode = nodesToVisit.remove();
            }else{
                currentNode = null;
            }
        }

        //Now traverse the array to find the lowest.
        boolean foundEndPoint = false;
        currentNode = endNode;
        GraphNode finalPoint = null;
        while(!foundEndPoint) {
            int minAdjacentPoints = 9999999;
            GraphNode foundMin = null;
            List<Point> adjacentNodes = currentNode.getAdjacentPoints();
            for(Point p : adjacentNodes) {
                if(allNodes.containsKey(p)) {
                    if(allNodes.get(p).getCost() < minAdjacentPoints) {
                        minAdjacentPoints = allNodes.get(p).getCost();
                        foundMin = allNodes.get(p);
                    }
                }
            }

            if(minAdjacentPoints == 0) {
                finalPoint = currentNode;
                foundEndPoint = true;
            }else{
                currentNode = foundMin;
            }
        }

        if(finalPoint.getX() > startX) {
            return 4;
        }else if(finalPoint.getX() < startX) {
            return 3;
        }else if(finalPoint.getY() > startY) {
            return 2;
        }else if(finalPoint.getY() < startY) {
            return 1;
        }

        return 0;
    }

    public int calculateCostBetweenPoints(int startX, int startY, int endX, int endY) {
        System.out.println("Getting direction to move in for " + startX+","+startY+" to " + endX + "," + endY);
        Map<Point, GraphNode> allNodes = new HashMap<>();
        GraphNode gn = new GraphNode(0, startX, startY);
        GraphNode endNode = new GraphNode(999999, endX, endY);
        allNodes.put(new Point(startX, startY), gn);
        allNodes.put(new Point(endX, endY), endNode);

        Queue<GraphNode> nodesToVisit = new LinkedList<>();

        GraphNode currentNode = gn;
        while(currentNode != null) {
            List<Point> adjacentNodes = currentNode.getAdjacentPoints();
            for(Point p : adjacentNodes) {
                if(!allNodes.containsKey(p) && (this.getMapData(p.x, p.y) == '.' || this.getMapData(p.x, p.y) == 'O')) {
                    GraphNode newNode = new GraphNode(currentNode.getCost() + 1, p.x, p.y);
                    newNode.linkNode(currentNode);
                    currentNode.linkNode(newNode);
                    nodesToVisit.add(newNode);
                    allNodes.put(new Point(newNode.getX(), newNode.getY()), newNode);
                }
            }

            if(nodesToVisit.size() > 0) {
                currentNode = nodesToVisit.remove();
            }else{
                currentNode = null;
            }
        }

        //Now traverse the array to find the lowest.
        boolean foundEndPoint = false;
        currentNode = endNode;

        int minAdjacentPoints = 9999999;
        GraphNode foundMin = null;
        List<Point> adjacentNodes = currentNode.getAdjacentPoints();
        for(Point p : adjacentNodes) {
            if(allNodes.containsKey(p)) {
                if(allNodes.get(p).getCost() < minAdjacentPoints) {
                    minAdjacentPoints = allNodes.get(p).getCost();
                    foundMin = allNodes.get(p);
                }
            }
        }

        return minAdjacentPoints + 1;
    }

    public List<Point> findAdjacentUnexploredPoints(int x, int y) {
        GraphNode currentNode = new GraphNode(0, x, y);
        List<Point> newPoints = currentNode.getAdjacentPoints();
        List<Point> newAdjacentPoints = new ArrayList<>();
        for(Point p : newPoints) {
            char val = this.getMapData(p.x, p.y);
            if(val == '?') {
                newAdjacentPoints.add(p);
            }
        }

        return newAdjacentPoints;
    }

    public Point getPointInDirection(Point p, int direction) {
        int x = p.x;
        int y = p.y;
        if(direction == 1) {
            y--;
        }else if(direction == 2) {
            y++;
        }else if(direction == 3) {
            x--;
        }else{
            x++;
        }

        return new Point(x, y);
    }

    public void draw(int curX, int curY) {
        int minX = 99999;
        int minY = 99999;
        int maxX = -99999;
        int maxY = -99999;

        for(Map.Entry<Point, Character> e : this.mapData.entrySet()) {
            minX = Math.min(minX, e.getKey().x);
            minY = Math.min(minY, e.getKey().y);
            maxX = Math.max(maxX, e.getKey().x);
            maxY = Math.max(maxY, e.getKey().y);
        }

        for(int y = minY; y <= maxY; y++) {
            String lineToPrint = "";
            for(int x = minX; x <= maxX; x++) {
                if(x == curX && y == curY) {
                    lineToPrint += "M";
                }else{
                    lineToPrint += this.getMapData(x, y);
                }
            }
            System.out.println(lineToPrint);
        }


    }

}
