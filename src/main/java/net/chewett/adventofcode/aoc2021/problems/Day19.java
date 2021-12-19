package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Point3D;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day19 {

    private boolean getOffSetDirectionFromInts(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);

        List<Integer> xValuesDiffOrigin = new ArrayList<>();
        int prevValue = Integer.MIN_VALUE;
        for(int i : a) {
            if(prevValue != Integer.MIN_VALUE) {
                xValuesDiffOrigin.add(i - prevValue);
            }
            prevValue = i;
        }
        List<Integer> xValuesDiffComparison = new ArrayList<>();
        prevValue = Integer.MIN_VALUE;
        for(int i : b) {
            if(prevValue != Integer.MIN_VALUE) {
                xValuesDiffComparison.add(i - prevValue);
            }
            prevValue = i;
        }

        if(xValuesDiffOrigin.equals(xValuesDiffComparison)) {
            return true;
        }else{
            return false;
        }
    }

    private int getOffset(Set<Point3D> originSet, Set<Point3D> comparisonSet) {
        List<List<Point3D>> listOfAllTranslations = new ArrayList<>();
        for(int i = 0; i < 24; i++) {
            listOfAllTranslations.add(new ArrayList<>());
        }

        for(Point3D p : comparisonSet) {
            List<Point3D> allPoints = p.getAll90DegreeRotations();
            for(int pointIndex = 0; pointIndex < allPoints.size(); pointIndex++) {
                Point3D pointToAdd = allPoints.get(pointIndex);
                listOfAllTranslations.get(pointIndex).add(pointToAdd);
            }
        }

        for(int rotationIndex = 0; rotationIndex < listOfAllTranslations.size(); rotationIndex++) {
            List<Point3D> pointsToCheck = listOfAllTranslations.get(rotationIndex);
            List<Integer> xValuesOrigin = new ArrayList<>();
            for(Point3D p : originSet) {
                xValuesOrigin.add(p.getX());
            }
            List<Integer> xValuesComparison = new ArrayList<>();
            for(Point3D p : pointsToCheck) {
                xValuesComparison.add(p.getX());
            }

            List<Integer> yValuesOrigin = new ArrayList<>();
            for(Point3D p : originSet) {
                yValuesOrigin.add(p.getY());
            }
            List<Integer> yValuesComparison = new ArrayList<>();
            for(Point3D p : pointsToCheck) {
                yValuesComparison.add(p.getY());
            }

            List<Integer> zValuesOrigin = new ArrayList<>();
            for(Point3D p : originSet) {
                zValuesOrigin.add(p.getZ());
            }
            List<Integer> zValuesComparison = new ArrayList<>();
            for(Point3D p : pointsToCheck) {
                zValuesComparison.add(p.getZ());
            }

            if(this.getOffSetDirectionFromInts(xValuesOrigin, xValuesComparison)
                && this.getOffSetDirectionFromInts(yValuesOrigin, yValuesComparison)
                    && this.getOffSetDirectionFromInts(zValuesOrigin, zValuesComparison)
            ) {
                return rotationIndex;
            }
        }

        throw new RuntimeException("Failure, this should never happen");

    }


    public long solvePartOne(List<String> sensorReports) {
        List<Integer> scannersToFind = new ArrayList<>();
        Map<Integer, List<Point3D>> scanners = new HashMap<>();
        int currentSensorNumber = 0;
        for(String line : sensorReports) {
            if(line.contains("--- ")) {
                currentSensorNumber = Integer.parseInt(line.split(" ")[2]);
                scanners.put(currentSensorNumber, new ArrayList<>());
                scannersToFind.add(currentSensorNumber);
            }else if(!line.equals("")) {
                scanners.get(currentSensorNumber).add(new Point3D(line));
            }
        }

        Map<Integer, Map<Double, List<Point3D>>> knownDistances = new HashMap<>();
        for(Map.Entry<Integer, List<Point3D>> sensors : scanners.entrySet()) {
            knownDistances.put(sensors.getKey(), new HashMap<>());

            List<Point3D> seenSatelites = sensors.getValue();
            for(Point3D sat1 : seenSatelites) {
                for(Point3D sat2 : seenSatelites) {
                    double distance = sat1.getDistanceTo(sat2);
                    if(distance != 0) {

                        List<Point3D> sats = new ArrayList<>();
                        sats.add(sat1);
                        sats.add(sat2);
                        Collections.sort(sats);

                        if (knownDistances.get(sensors.getKey()).containsKey(distance)) {
                            if (!sats.equals(knownDistances.get(sensors.getKey()).get(distance))) {

                                System.out.println(sats);
                                System.out.println(knownDistances.get(sensors.getKey()).get(distance));
                                throw new RuntimeException(String.valueOf(distance));
                            }
                        }

                        knownDistances.get(sensors.getKey()).put(distance, sats);
                    }
                }
            }
        }

        Map<Integer, Map<Integer, Set<Point3D>>> allCommonPoints = new HashMap<>();
        for(int x = 0; x < scanners.size(); x++) {
            for(int y = x+1; y < scanners.size(); y++) {

                for(Map.Entry<Double, List<Point3D>> c1 : knownDistances.get(x).entrySet()) {
                    for(Map.Entry<Double, List<Point3D>> c2 : knownDistances.get(y).entrySet()) {
                        if(c1.getKey().equals(c2.getKey())) {
                            if(!allCommonPoints.containsKey(x)) {
                                allCommonPoints.put(x, new HashMap<>());
                            }
                            if(!allCommonPoints.containsKey(y)) {
                                allCommonPoints.put(y, new HashMap<>());
                            }
                            if(!allCommonPoints.get(x).containsKey(y)) {
                                allCommonPoints.get(x).put(y, new HashSet<>());
                            }
                            if(!allCommonPoints.get(y).containsKey(x)) {
                                allCommonPoints.get(y).put(x, new HashSet<>());
                            }

                            allCommonPoints.get(x).get(y).addAll(c1.getValue());
                            allCommonPoints.get(y).get(x).addAll(c2.getValue());

                            if(allCommonPoints.get(x).get(y).size() != allCommonPoints.get(y).get(x).size()) {
                                System.out.println(allCommonPoints.get(x).get(y));
                                System.out.println(allCommonPoints.get(y).get(x));
                                System.out.println(c1.getValue());
                                System.out.println(c2.getValue());
                                throw new RuntimeException();
                            }

                        }
                    }
                }
            }
        }




        //All known positions are relative to scanner 0 so we can remove that one
        scannersToFind.remove(Integer.valueOf(0));
        Map<Integer, Point3D> scannerLocations = new HashMap<>();
        scannerLocations.put(0, new Point3D(0,0,0));
        Map<Integer, Integer> scannerTranslation = new HashMap<>();
        scannerTranslation.put(0, 0);

        //scannersToFind.remove(Integer.valueOf(2));
        //scannersToFind.remove(Integer.valueOf(3));
        //scannersToFind.remove(Integer.valueOf(4));
        int foundScannerId = 0;
        while(foundScannerId != -1 && scannersToFind.size() > 0) {
            foundScannerId = -1;
            for(int scannerToFindIndex = 0; scannerToFindIndex < scannersToFind.size() && foundScannerId == -1; scannerToFindIndex++) {
                int scannerToFindId = scannersToFind.get(scannerToFindIndex);

                for(Map.Entry<Integer, Point3D> knownScannerLocation : scannerLocations.entrySet()) {
                    //You can only run this where 12 beacons align themselves together
                    if(allCommonPoints.get(scannerToFindId).get(knownScannerLocation.getKey()) != null &&
                            allCommonPoints.get(scannerToFindId).get(knownScannerLocation.getKey()).size() >= 12) {
                        int knownScannerId = knownScannerLocation.getKey();
                        System.out.println();
                        System.out.println("STARTING TO DETERMINE SCANNER: " + scannerToFindId + " WITH SCANNER: " + knownScannerId);


                        Set<Point3D> commonOriginPointsTranslated = new HashSet<>();
                        Set<Point3D> commonNewPoints = allCommonPoints.get(scannerToFindId).get(knownScannerId);
                        for(Point3D p : allCommonPoints.get(knownScannerId).get(scannerToFindId)) {
                            Point3D post = p.translateAccordingToRotationValue(scannerTranslation.get(knownScannerId));
                            commonOriginPointsTranslated.add(post);
                        }

                        System.out.println(commonOriginPointsTranslated);
                        System.out.println(commonNewPoints);

                        System.out.println("Real positions");
                        for(Point3D p : commonOriginPointsTranslated) {
                            System.out.println(p);
                        }

                        int directionOffsets = this.getOffset(commonOriginPointsTranslated, commonNewPoints);
                        System.out.println("OFFSET: " + directionOffsets);
                        List<Point3D> compPoints = new ArrayList<>(commonOriginPointsTranslated);

                        List<Point3D> translatedPoints = new ArrayList<>();
                        for(Point3D p : commonNewPoints) {
                            translatedPoints.add(p.translateAccordingToRotationValue(directionOffsets));
                        }

                        Collections.sort(compPoints);
                        Collections.sort(translatedPoints);
                        System.out.println("Difference:");
                        for(int i = 0; i < compPoints.size(); i++) {
                            System.out.println(compPoints.get(i).subtract(translatedPoints.get(i)));
                        }


                        Point3D foundScannerLocation = compPoints.get(0).subtract(translatedPoints.get(0)).add(knownScannerLocation.getValue());
                        scannerLocations.put(scannerToFindId, foundScannerLocation);
                        scannerTranslation.put(scannerToFindId, directionOffsets);

                        //TODO: remove this once I know it works once.
                        foundScannerId = scannerToFindId;
                        break;
                    }
                }
            }

            if(foundScannerId != -1) {
                scannersToFind.remove(Integer.valueOf(foundScannerId));
            }

        }

        for(Map.Entry<Integer, Point3D> res : scannerLocations.entrySet()) {
            System.out.println(res.getKey() + " - " + res.getValue().toString());
        }

        Set<Point3D> allKnownValues = new HashSet<>();
//
//        for(int scannerIndex = 0; scannerIndex < scanners.size(); scannerIndex++) {
//            int rotationValue = scannerTranslation.get(scannerIndex);
//            for(Point3D p : scanners.get(scannerIndex)) {
//                allKnownValues.add(p.translateAccordingToRotationValue(rotationValue).add(scannerLocations.get(scannerIndex)));
//            }
//        }


        return allKnownValues.size();
    }


    public static void main(String[] args) {
        List<String> sensors = ProblemLoader.loadProblemIntoStringArray(2021, 19);

        Day19 d = new Day19();
        System.out.println(d.solvePartOne(sensors));

    }

}
