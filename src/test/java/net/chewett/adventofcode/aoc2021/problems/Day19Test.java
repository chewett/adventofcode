package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class Day19Test {


    /**
     * Function to get the example inputs
     * @return The example input
     */
    public List<String> getExampleInput() {
        List<String> arr = new ArrayList<>();
        arr.add("--- scanner 0 ---");
        arr.add("404,-588,-901");
        arr.add("528,-643,409");
        arr.add("-838,591,734");
        arr.add("390,-675,-793");
        arr.add("-537,-823,-458");
        arr.add("-485,-357,347");
        arr.add("-345,-311,381");
        arr.add("-661,-816,-575");
        arr.add("-876,649,763");
        arr.add("-618,-824,-621");
        arr.add("553,345,-567");
        arr.add("474,580,667");
        arr.add("-447,-329,318");
        arr.add("-584,868,-557");
        arr.add("544,-627,-890");
        arr.add("564,392,-477");
        arr.add("455,729,728");
        arr.add("-892,524,684");
        arr.add("-689,845,-530");
        arr.add("423,-701,434");
        arr.add("7,-33,-71");
        arr.add("630,319,-379");
        arr.add("443,580,662");
        arr.add("-789,900,-551");
        arr.add("459,-707,401");
        arr.add("");
        arr.add("--- scanner 1 ---");
        arr.add("686,422,578");
        arr.add("605,423,415");
        arr.add("515,917,-361");
        arr.add("-336,658,858");
        arr.add("95,138,22");
        arr.add("-476,619,847");
        arr.add("-340,-569,-846");
        arr.add("567,-361,727");
        arr.add("-460,603,-452");
        arr.add("669,-402,600");
        arr.add("729,430,532");
        arr.add("-500,-761,534");
        arr.add("-322,571,750");
        arr.add("-466,-666,-811");
        arr.add("-429,-592,574");
        arr.add("-355,545,-477");
        arr.add("703,-491,-529");
        arr.add("-328,-685,520");
        arr.add("413,935,-424");
        arr.add("-391,539,-444");
        arr.add("586,-435,557");
        arr.add("-364,-763,-893");
        arr.add("807,-499,-711");
        arr.add("755,-354,-619");
        arr.add("553,889,-390");
        arr.add("");
        arr.add("--- scanner 2 ---");
        arr.add("649,640,665");
        arr.add("682,-795,504");
        arr.add("-784,533,-524");
        arr.add("-644,584,-595");
        arr.add("-588,-843,648");
        arr.add("-30,6,44");
        arr.add("-674,560,763");
        arr.add("500,723,-460");
        arr.add("609,671,-379");
        arr.add("-555,-800,653");
        arr.add("-675,-892,-343");
        arr.add("697,-426,-610");
        arr.add("578,704,681");
        arr.add("493,664,-388");
        arr.add("-671,-858,530");
        arr.add("-667,343,800");
        arr.add("571,-461,-707");
        arr.add("-138,-166,112");
        arr.add("-889,563,-600");
        arr.add("646,-828,498");
        arr.add("640,759,510");
        arr.add("-630,509,768");
        arr.add("-681,-892,-333");
        arr.add("673,-379,-804");
        arr.add("-742,-814,-386");
        arr.add("577,-820,562");
        arr.add("");
        arr.add("--- scanner 3 ---");
        arr.add("-589,542,597");
        arr.add("605,-692,669");
        arr.add("-500,565,-823");
        arr.add("-660,373,557");
        arr.add("-458,-679,-417");
        arr.add("-488,449,543");
        arr.add("-626,468,-788");
        arr.add("338,-750,-386");
        arr.add("528,-832,-391");
        arr.add("562,-778,733");
        arr.add("-938,-730,414");
        arr.add("543,643,-506");
        arr.add("-524,371,-870");
        arr.add("407,773,750");
        arr.add("-104,29,83");
        arr.add("378,-903,-323");
        arr.add("-778,-728,485");
        arr.add("426,699,580");
        arr.add("-438,-605,-362");
        arr.add("-469,-447,-387");
        arr.add("509,732,623");
        arr.add("647,635,-688");
        arr.add("-868,-804,481");
        arr.add("614,-800,639");
        arr.add("595,780,-596");
        arr.add("");
        arr.add("--- scanner 4 ---");
        arr.add("727,592,562");
        arr.add("-293,-554,779");
        arr.add("441,611,-461");
        arr.add("-714,465,-776");
        arr.add("-743,427,-804");
        arr.add("-660,-479,-426");
        arr.add("832,-632,460");
        arr.add("927,-485,-438");
        arr.add("408,393,-506");
        arr.add("466,436,-512");
        arr.add("110,16,151");
        arr.add("-258,-428,682");
        arr.add("-393,719,612");
        arr.add("-211,-452,876");
        arr.add("808,-476,-593");
        arr.add("-575,615,604");
        arr.add("-485,667,467");
        arr.add("-680,325,-822");
        arr.add("-627,-443,-432");
        arr.add("872,-547,-609");
        arr.add("833,512,582");
        arr.add("807,604,487");
        arr.add("839,-516,451");
        arr.add("891,-625,532");
        arr.add("-652,-548,-490");
        arr.add("30,-46,-14");

        return arr;
    }

    /**
     * Test Day 19 part one with the example input (with the known result)
     */
    @Test
    public void testExamplePartOne() {
        Day19 d = new Day19();
        Assertions.assertEquals(79, d.solvePartOne(this.getExampleInput()));
    }

    /**
     * Test Day 19 part one with the real input (and the correct answer)
     */
    @Test
    public void testRealPartOne() {
        Day19 d = new Day19();
        Assertions.assertEquals(367, d.solvePartOne(ProblemLoader.loadProblemIntoStringArray(2021, 19)));
    }


    /**
     * Test Day 19 part two with the example input (with the known result)
     */
    @Test
    public void testExamplePartTwo() {
        Day19 d = new Day19();
        Assertions.assertEquals(3621, d.solvePartTwo(this.getExampleInput()));
    }

    /**
     * Test Day 19 part two with the real input (and the correct answer)
     */
    @Test
    public void testRealPartTwo() {
        Day19 d = new Day19();
        Assertions.assertEquals(11925, d.solvePartTwo(ProblemLoader.loadProblemIntoStringArray(2021, 19)));
    }



}
