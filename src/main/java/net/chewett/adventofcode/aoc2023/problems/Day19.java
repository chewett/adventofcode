package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.*;

/**
 * --- Day 19: Aplenty ---
 * The Elves of Gear Island are thankful for your help and send you on your way. They even have a hang glider that
 * someone stole from Desert Island; since you're already going that direction, it would help them a lot if you would
 * use it to get down there and return it to them.
 *
 * As you reach the bottom of the relentless avalanche of machine parts, you discover that they're already forming a
 * formidable heap. Don't worry, though - a group of Elves is already here organizing the parts, and they have a system.
 *
 * To start, each part is rated in each of four categories:
 *
 * x: Extremely cool looking
 * m: Musical (it makes a noise when you hit it)
 * a: Aerodynamic
 * s: Shiny
 * Then, each part is sent through a series of workflows that will ultimately accept or reject the part. Each workflow
 * has a name and contains a list of rules; each rule specifies a condition and where to send the part if the condition
 * is true. The first rule that matches the part being considered is applied immediately, and the part moves on to the
 * destination described by the rule. (The last rule in each workflow has no condition and always applies if reached.)
 *
 * Consider the workflow ex{x>10:one,m<20:two,a>30:R,A}. This workflow is named ex and contains four rules. If workflow
 * ex were considering a specific part, it would perform the following steps in order:
 *
 * Rule "x>10:one": If the part's x is more than 10, send the part to the workflow named one.
 * Rule "m<20:two": Otherwise, if the part's m is less than 20, send the part to the workflow named two.
 * Rule "a>30:R": Otherwise, if the part's a is more than 30, the part is immediately rejected (R).
 * Rule "A": Otherwise, because no other rules matched the part, the part is immediately accepted (A).
 * If a part is sent to another workflow, it immediately switches to the start of that workflow instead and never
 * returns. If a part is accepted (sent to A) or rejected (sent to R), the part immediately stops any further processing.
 *
 * The system works, but it's not keeping up with the torrent of weird metal shapes. The Elves ask if you can help
 * sort a few parts and give you the list of workflows and some part ratings (your puzzle input). For example:
 *
 * px{a<2006:qkq,m>2090:A,rfg}
 * pv{a>1716:R,A}
 * lnx{m>1548:A,A}
 * rfg{s<537:gd,x>2440:R,A}
 * qs{s>3448:A,lnx}
 * qkq{x<1416:A,crn}
 * crn{x>2662:A,R}
 * in{s<1351:px,qqz}
 * qqz{s>2770:qs,m<1801:hdj,R}
 * gd{a>3333:R,R}
 * hdj{m>838:A,pv}
 *
 * {x=787,m=2655,a=1222,s=2876}
 * {x=1679,m=44,a=2067,s=496}
 * {x=2036,m=264,a=79,s=2244}
 * {x=2461,m=1339,a=466,s=291}
 * {x=2127,m=1623,a=2188,s=1013}
 * The workflows are listed first, followed by a blank line, then the ratings of the parts the Elves would like you to
 * sort. All parts begin in the workflow named in. In this example, the five listed parts go through the following
 * workflows:
 *
 * {x=787,m=2655,a=1222,s=2876}: in -> qqz -> qs -> lnx -> A
 * {x=1679,m=44,a=2067,s=496}: in -> px -> rfg -> gd -> R
 * {x=2036,m=264,a=79,s=2244}: in -> qqz -> hdj -> pv -> A
 * {x=2461,m=1339,a=466,s=291}: in -> px -> qkq -> crn -> R
 * {x=2127,m=1623,a=2188,s=1013}: in -> px -> rfg -> A
 * Ultimately, three parts are accepted. Adding up the x, m, a, and s rating for each of the accepted parts gives 7540
 * for the part with x=787, 4623 for the part with x=2036, and 6951 for the part with x=2127. Adding all of the ratings
 * for all of the accepted parts gives the sum total of 19114.
 *
 * Sort through all of the parts you've been given; what do you get if you add together all of the rating numbers for
 * all of the parts that ultimately get accepted?
 *
 * --- Part Two ---
 * Even with your help, the sorting process still isn't fast enough.
 *
 * One of the Elves comes up with a new plan: rather than sort parts individually through all of these workflows,
 * maybe you can figure out in advance which combinations of ratings will be accepted or rejected.
 *
 * Each of the four ratings (x, m, a, s) can have an integer value ranging from a minimum of 1 to a maximum of 4000.
 * Of all possible distinct combinations of ratings, your job is to figure out which ones will be accepted.
 *
 * In the above example, there are 167409079868000 distinct combinations of ratings that will be accepted.
 *
 * Consider only your list of workflows; the list of part ratings that the Elves wanted you to sort is no longer
 * relevant. How many distinct combinations of ratings will be accepted by the Elves' workflows?
 */
public class Day19 {

    /**
     * Parse all of the parts and work out the sum of the value of the accepted ones
     * @param input List of rules and parts
     * @return Sum of the total value of all parts
     */
    public long solvePartOne(List<String> input) {
        Map<String, List<String>> workflows = new HashMap<>();

        int lineNo = 0;
        while(true) {
            String line = input.get(lineNo);
            if(line.isEmpty()) {
                break;
            }
            String[] parts = line.split("[{,}]");
            List<String> rules = new ArrayList<>();
            for(int i = 1; i < parts.length; i++) {
                rules.add(parts[i]);
            }
            workflows.put(parts[0], rules);

            lineNo++;
        }
        lineNo++;

        List<String> parts = new ArrayList<>();
        while(lineNo < input.size()) {
            parts.add(input.get(lineNo));
            lineNo++;
        }

        //Once we have the parts and rules stripped out we can just go through checking each one
        long total = 0;
        for(String p : parts) {
            String[] splitPats = p.split("(\\{x=|,m=|,a=|,s=|})");
            long x = Long.parseLong(splitPats[1]);
            long m = Long.parseLong(splitPats[2]);
            long a = Long.parseLong(splitPats[3]);
            long s = Long.parseLong(splitPats[4]);

            boolean rejectedOrAccepted = false;
            String workflow = "in";
            //Loop over the part until we reject or accept it
            while(!rejectedOrAccepted) {
                List<String> rulesToRun = workflows.get(workflow);
                //Loop over each rule until we find the end or jump to a new rule
                for(String rule : rulesToRun) {
                    //base case, accept it, add the values
                    if(rule.equals("A")) {
                        total += x + m + a + s;
                        rejectedOrAccepted = true;
                        break;
                    //base case, reject it, do nothing with the values
                    }else if(rule.equals("R")) {
                        rejectedOrAccepted = true;
                        break;
                    //Recursive/iterative case, Work out whether we go into a new work item or continue this list of rules
                    }else if(rule.contains(":")) {
                        //Handle the :
                        String[] mathParts = rule.split("[:><]");
                        char operation = rule.charAt(1);
                        char valToOperate = rule.charAt(0);
                        long comparisonValue = Long.parseLong(mathParts[1]);
                        String doValue = mathParts[2];

                        //Parse out the value we are comparing
                        long valToOperateLong;
                        if(valToOperate == 'x') {
                            valToOperateLong = x;
                        }else if(valToOperate == 'm') {
                            valToOperateLong = m;
                        }else if(valToOperate == 'a') {
                            valToOperateLong = a;
                        }else if(valToOperate == 's') {
                            valToOperateLong = s;
                        }else{
                            throw new RuntimeException("Error");
                        }

                        //Do the comparison
                        boolean opIsTrue;
                        if(operation == '>') {
                            opIsTrue = valToOperateLong > comparisonValue;
                        }else if(operation == '<') {
                            opIsTrue = valToOperateLong < comparisonValue;
                        }else{
                            throw new RuntimeException("Error");
                        }

                        //If its true then we do whatever it says
                        if(opIsTrue) {
                            //base case again, accept
                            if(doValue.equals("A")) {
                                total += x + m + a + s;
                                rejectedOrAccepted = true;
                                break;
                            //base case again, reject
                            }else if(doValue.equals("R")) {
                                rejectedOrAccepted = true;
                                break;
                            }else{
                                //Move to new workflow if it's not rejected or accepted
                                workflow = doValue;
                                break;
                            }
                        }
                    }else{
                        //Move to new workflow if it's not rejected or accepted and there is no comparison to run (done above)
                        workflow = rule;
                        break;
                    }
                }
            }
        }

        return total;
    }

    /**
     * Given a list of workflows, a range, and a workflow to apply return a list of all ranges that are acceptable
     *
     * This is a recursive function to slowly work out all valid ranges
     *
     * @param workflows List of workflows you can apply
     * @param range A range that you want to apply the workflow to
     * @param workflowInput The name of the workflow to apply
     * @return List of ranges that are valid
     */
    public List<int[]> findRangesForWorkflow(Map<String, List<String>> workflows, int[] range, String workflowInput) {
        //Keep track of all valid ranges
        List<int[]> newRanges = new ArrayList<>();

        //Track the current range as we will slowly split this and handle the split recursively
        int[] currentRange = range;
        List<String>workflowStr = workflows.get(workflowInput);
        for(String operation : workflowStr) {
            //Base case, accept the current range
            if(operation.equals("A")) {
                newRanges.add(currentRange);

            //base case, reject, do nothing with current range
            }else if(operation.equals("R")) {
                //Nothing is here intentionally!

            }else if(operation.contains(":")) {
                //Split the range into two parts based on the condition being true and false
                int[][] splitRanges = this.splitRange(operation, currentRange);
                //Work out the workflow to move onto for the condition being true case
                String newWorkflowToMoveTo = operation.split(":")[1];

                //base case again, just accept this range
                if(newWorkflowToMoveTo.equals("A")) {
                    newRanges.add(splitRanges[0]);

                //base case again, just reject this range
                }else if(newWorkflowToMoveTo.equals("R")) {
                    //Nothing is here intentionally!

                //Recursive case, Work out the ranges for the workflow we move to and add them here
                }else {
                    newRanges.addAll(this.findRangesForWorkflow(workflows, splitRanges[0], newWorkflowToMoveTo));
                }

                //Move the current range to the range not accepted
                currentRange = splitRanges[1];
            }else{
                //Nothing else specified, just move to the named workflow
                newRanges.addAll(findRangesForWorkflow(workflows, currentRange, operation));
            }
        }

        return newRanges;
    }

    /**
     * Given a rule and a range we return the two ranges representing "meeting" the rule and not meeting it
     *
     * Note: I think this input has been crafted so inputs are always split nicely rather than one side
     * sometimes not having any values. This doesn't handle that case so if you wanted to expand it you would
     * need to take into account that sometimes only one range is valid after splitting
     *
     * @param rule Rule to analyse and use for splitting
     * @param range Current range needing splitting
     * @return in[][] representing two ranges that have been split from the first
     */
    public int[][] splitRange(String rule, int[] range) {
        //Variables to make indexing nicer
        int xMin = range[0];
        int xMax = range[1];
        int mMin = range[2];
        int mMax = range[3];
        int aMin = range[4];
        int aMax = range[5];
        int sMin = range[6];
        int sMax = range[7];

        //Do similar like we did to part 1
        String[] mathParts = rule.split("[:><]");
        char operation = rule.charAt(1);
        char valToOperate = rule.charAt(0);
        int comparisonValue = Integer.parseInt(mathParts[1]);

        //Work out current min+max to create the values with less if statements
        int curMin;
        int curMax;
        if(valToOperate == 'x') {
            curMin = xMin;
            curMax = xMax;
        }else if(valToOperate == 'm') {
            curMin = mMin;
            curMax = mMax;
        }else if(valToOperate == 'a') {
            curMin = aMin;
            curMax = aMax;
        }else if(valToOperate == 's') {
            curMin = sMin;
            curMax = sMax;
        }else{
            throw new RuntimeException("Error");
        }

        //Create our new min and maxes
        int[] splitNums;
        if(operation == '>') {
            splitNums = new int[] {comparisonValue+1, curMax, curMin, comparisonValue};
        }else if(operation == '<') {
            splitNums = new int[] {curMin, comparisonValue-1, comparisonValue, curMax};
        }else{
            throw new RuntimeException("Error");
        }

        //pre-create our return values
        int[][] returnValues = new int[][] {
                {xMin, xMax, mMin, mMax, aMin, aMax, sMin, sMax},
                {xMin, xMax, mMin, mMax, aMin, aMax, sMin, sMax},
        };
        //And then augment them with the new values we worked above
        if(valToOperate == 'x') {
            returnValues[0][0] = splitNums[0];
            returnValues[0][1] = splitNums[1];
            returnValues[1][0] = splitNums[2];
            returnValues[1][1] = splitNums[3];
        }else if(valToOperate == 'm') {
            returnValues[0][2] = splitNums[0];
            returnValues[0][3] = splitNums[1];
            returnValues[1][2] = splitNums[2];
            returnValues[1][3] = splitNums[3];
        }else if(valToOperate == 'a') {
            returnValues[0][4] = splitNums[0];
            returnValues[0][5] = splitNums[1];
            returnValues[1][4] = splitNums[2];
            returnValues[1][5] = splitNums[3];
        }else if(valToOperate == 's') {
            returnValues[0][6] = splitNums[0];
            returnValues[0][7] = splitNums[1];
            returnValues[1][6] = splitNums[2];
            returnValues[1][7] = splitNums[3];
        }else{
            throw new RuntimeException("Error");
        }

        return returnValues;
    }


    /**
     * Works out all possible combinations of parts that are acceptable by the algorithm
     * @param input List of workflows
     * @return The total number of possible combinations of parts
     */
    public long solvePartTwo(List<String> input) {
        Map<String, List<String>> workflows = new HashMap<>();

        int lineNo = 0;
        while(true) {
            String line = input.get(lineNo);
            if(line.isEmpty()) {
                break;
            }
            String[] parts = line.split("[{,}]");
            List<String> rules = new ArrayList<>();
            for(int i = 1; i < parts.length; i++) {
                rules.add(parts[i]);
            }
            workflows.put(parts[0], rules);

            lineNo++;
        }

        int[] startRange = new int[] {1, 4000, 1, 4000, 1, 4000, 1, 4000};

        //Get all the ranges
        List<int[]> allRanges = this.findRangesForWorkflow(workflows, startRange, "in");

        //Then work out their totals
        long total = 0;
        for(int[] finalRange : allRanges) {
            //Plus one because fence panels and fence posts
            total += ((finalRange[1] - finalRange[0])+1L) * ((finalRange[3] - finalRange[2])+1L)
             * ((finalRange[5] - finalRange[4])+1L) * ((finalRange[7] - finalRange[6])+1L);
        }

        return total;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 19);

        Day19 d = new Day19();
        long partOne = d.solvePartOne(input);
        System.out.println("The number of accepted parts are " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("All possible parts which might be accepted are " + partTwo);
    }
}


