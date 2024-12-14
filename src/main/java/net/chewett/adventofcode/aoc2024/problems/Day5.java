package net.chewett.adventofcode.aoc2024.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 5: Print Queue ---
 * Satisfied with their search on Ceres, the squadron of scholars suggests subsequently scanning the stationery stacks
 * of sub-basement 17.
 *
 * The North Pole printing department is busier than ever this close to Christmas, and while The Historians continue
 * their search of this historically significant facility, an Elf operating a very familiar printer beckons you over.
 *
 * The Elf must recognize you, because they waste no time explaining that the new sleigh launch safety manual updates
 * won't print correctly. Failure to update the safety manuals would be dire indeed, so you offer your services.
 *
 * Safety protocols clearly indicate that new pages for the safety manuals must be printed in a very specific order.
 * The notation X|Y means that if both page number X and page number Y are to be produced as part of an update, page
 * number X must be printed at some point before page number Y.
 *
 * The Elf has for you both the page ordering rules and the pages to produce in each update (your puzzle input), but
 * can't figure out whether each update has the pages in the right order.
 *
 * For example:
 *
 * 47|53
 * 97|13
 * 97|61
 * 97|47
 * 75|29
 * 61|13
 * 75|53
 * 29|13
 * 97|29
 * 53|29
 * 61|53
 * 97|53
 * 61|29
 * 47|13
 * 75|47
 * 97|75
 * 47|61
 * 75|61
 * 47|29
 * 75|13
 * 53|13
 *
 * 75,47,61,53,29
 * 97,61,53,29,13
 * 75,29,13
 * 75,97,47,61,53
 * 61,13,29
 * 97,13,75,29,47
 * The first section specifies the page ordering rules, one per line. The first rule, 47|53, means that if an update
 * includes both page number 47 and page number 53, then page number 47 must be printed at some point before page
 * number 53. (47 doesn't necessarily need to be immediately before 53; other pages are allowed to be between them.)
 *
 * The second section specifies the page numbers of each update. Because most safety manuals are different, the pages
 * needed in the updates are different too. The first update, 75,47,61,53,29, means that the update consists of page
 * numbers 75, 47, 61, 53, and 29.
 *
 * To get the printers going as soon as possible, start by identifying which updates are already in the right order.
 *
 * In the above example, the first update (75,47,61,53,29) is in the right order:
 *
 * 75 is correctly first because there are rules that put each other page after it: 75|47, 75|61, 75|53, and 75|29.
 * 47 is correctly second because 75 must be before it (75|47) and every other page must be after it according to
 * 47|61, 47|53, and 47|29.
 * 61 is correctly in the middle because 75 and 47 are before it (75|61 and 47|61) and 53 and 29 are after it
 * (61|53 and 61|29).
 * 53 is correctly fourth because it is before page number 29 (53|29).
 * 29 is the only page left and so is correctly last.
 * Because the first update does not include some page numbers, the ordering rules involving those missing page
 * numbers are ignored.
 *
 * The second and third updates are also in the correct order according to the rules. Like the first update, they
 * also do not include every page number, and so only some of the ordering rules apply - within each update, the
 * ordering rules that involve missing page numbers are not used.
 *
 * The fourth update, 75,97,47,61,53, is not in the correct order: it would print 75 before 97, which violates the
 * rule 97|75.
 *
 * The fifth update, 61,13,29, is also not in the correct order, since it breaks the rule 29|13.
 *
 * The last update, 97,13,75,29,47, is not in the correct order due to breaking several rules.
 *
 * For some reason, the Elves also need to know the middle page number of each update being printed. Because you are
 * currently only printing the correctly-ordered updates, you will need to find the middle page number of each
 * correctly-ordered update. In the above example, the correctly-ordered updates are:
 *
 * 75,47,61,53,29
 * 97,61,53,29,13
 * 75,29,13
 * These have middle page numbers of 61, 53, and 29 respectively. Adding these page numbers together gives 143.
 *
 * Of course, you'll need to be careful: the actual list of page ordering rules is bigger and more complicated than
 * the above example.
 *
 * Determine which updates are already in the correct order. What do you get if you add up the middle page number from
 * those correctly-ordered updates?
 *
 * --- Part Two ---
 * While the Elves get to work printing the correctly-ordered updates, you have a little time to fix the rest of them.
 *
 * For each of the incorrectly-ordered updates, use the page ordering rules to put the page numbers in the right order.
 * For the above example, here are the three incorrectly-ordered updates and their correct orderings:
 *
 * 75,97,47,61,53 becomes 97,75,47,61,53.
 * 61,13,29 becomes 61,29,13.
 * 97,13,75,29,47 becomes 97,75,47,29,13.
 * After taking only the incorrectly-ordered updates and ordering them correctly, their middle page numbers are 47, 29,
 * and 47. Adding these together produces 123.
 *
 * Find the updates which are not in the correct order. What do you get if you add up the middle page numbers after
 * correctly ordering just those updates?
 */
public class Day5 {

    /**
     * Simple "dumb" method to check to see if the edit is in order
     * @param order List of page numbers to edit
     * @param rules Rules of which page edits this must occur before
     * @return A boolean representing whether the page edits are in order or not
     */
    private boolean isInOrder(List<Integer> order, Map<Integer, Set<Integer>> rules) {
        //Loop over each page edit (starting with the second element) and check all previous elements
        //Ensure any previous elements are not ones that this must be before
        //Somewhat of an expensive algorithm but this works fine, so it is an acceptable compromise
        for(int pageIndex = 1; pageIndex < order.size(); pageIndex++) {
            Set<Integer> mustBeBefore = rules.getOrDefault(order.get(pageIndex), Collections.emptySet());

            for(int checkingBackPages = pageIndex-1; checkingBackPages >= 0; checkingBackPages--) {
                if(mustBeBefore.contains(order.get(checkingBackPages))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Verifies which of the edits are in the right order and returns the sum of the middle pages
     * @param input Rules and edits
     * @return Sum of the middle pages
     */
    public long solvePartOne(List<String> input) {

        //Big chunk of code loading data...
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        List<List<Integer>> pageEdits = new ArrayList<>();

        boolean firstSection = true;
        for(String str : input) {
            if(str.isEmpty()) {
                firstSection = false;
            }else if(firstSection) {
                String[] parts = str.split("\\|");
                if(!rules.containsKey(Integer.parseInt(parts[0]))) {
                    rules.put(Integer.parseInt(parts[0]), new HashSet<>());
                }

                rules.get(Integer.parseInt(parts[0])).add(Integer.parseInt(parts[1]));
            }else{
                String[] parts = str.split(",");
                List<Integer> newPageEdit = new ArrayList<>();
                for(String part : parts) {
                    newPageEdit.add(Integer.parseInt(part));
                }
                pageEdits.add(newPageEdit);
            }
        }

        long rightOrderMiddleNumberSum = 0;
        for(List<Integer> order : pageEdits) {
            if(this.isInOrder(order, rules)) {
                //Find the midpoint by dividing the size by two and implicitly truncating
                rightOrderMiddleNumberSum += order.get(order.size() / 2);
            }
        }

        return rightOrderMiddleNumberSum;
    }

    /**
     * Verifies which of the edits are wrong and then reorders them so that they are right
     * and returns the sum of the middle pages
     * @param input Rules and edits
     * @return Sum of the middle pages
     */
    public long solvePartTwo(List<String> input) {

        //Big chunk of code loading data...
        Map<Integer, Set<Integer>> rules = new HashMap<>();
        List<List<Integer>> pageEdits = new ArrayList<>();

        boolean firstSection = true;
        for(String str : input) {
            if(str.isEmpty()) {
                firstSection = false;
            }else if(firstSection) {
                String[] parts = str.split("\\|");
                if(!rules.containsKey(Integer.parseInt(parts[0]))) {
                    rules.put(Integer.parseInt(parts[0]), new HashSet<>());
                }

                rules.get(Integer.parseInt(parts[0])).add(Integer.parseInt(parts[1]));
            }else{
                String[] parts = str.split(",");
                List<Integer> newPageEdit = new ArrayList<>();
                for(String part : parts) {
                    newPageEdit.add(Integer.parseInt(part));
                }
                pageEdits.add(newPageEdit);
            }
        }

        long wrongOderMiddleNumber = 0;
        for(List<Integer> order : pageEdits) {

            //Work out the bad orders
            boolean goodOrder = this.isInOrder(order, rules);
            if(!goodOrder) {
                boolean managedToFixIt = false;

                //Keep looping over it until it is good
                while(!managedToFixIt) {
                    managedToFixIt = true;
                    for(int pageIndex = 1; pageIndex < order.size() && managedToFixIt; pageIndex++) {
                        Set<Integer> mustBeBefore = rules.getOrDefault(order.get(pageIndex), Collections.emptySet());

                        for(int checkingBackPages = pageIndex-1; checkingBackPages >= 0 && managedToFixIt; checkingBackPages--) {
                            //If a page is found to not be placed correctly it will move that page in front of the page
                            //it should be placed before
                            if(mustBeBefore.contains(order.get(checkingBackPages))) {
                                LinkedList<Integer> newList = new LinkedList<>();
                                for(int i : order) {
                                    newList.add(i);
                                }

                                //Then once the new list is created, it will re-attempt the algorithm until it is good
                                //Somewhat inefficient but works fine for this purposes so it's a good compromise
                                newList.remove(pageIndex);
                                newList.add(checkingBackPages, order.get(pageIndex));
                                managedToFixIt = false;
                                order = newList;
                            }
                        }
                    }
                }

                wrongOderMiddleNumber += order.get(order.size() / 2);
            }
        }

        return wrongOderMiddleNumber;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2024, 5);

        Day5 d = new Day5();
        long partOne = d.solvePartOne(input);
        System.out.println("The sum of the middle numbers of correct edits is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The sum of the middle numbers of incorrect edits (once corrected) is " + partTwo);
    }
}


