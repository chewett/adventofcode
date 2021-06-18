package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 18: Operation Order ---
 * As you look out the window and notice a heavily-forested continent slowly appear over the horizon, you are
 * interrupted by the child sitting next to you. They're curious if you could help them with their math homework.
 *
 * Unfortunately, it seems like this "math" follows different rules than you remember.
 *
 * The homework (your puzzle input) consists of a series of expressions that consist of addition (+), multiplication (*),
 * and parentheses ((...)). Just like normal math, parentheses indicate that the expression inside must be evaluated
 * before it can be used by the surrounding expression. Addition still finds the sum of the numbers on both sides of the
 * operator, and multiplication still finds the product.
 *
 * However, the rules of operator precedence have changed. Rather than evaluating multiplication before addition, the
 * operators have the same precedence, and are evaluated left-to-right regardless of the order in which they appear.
 *
 * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are as follows:
 *
 * 1 + 2 * 3 + 4 * 5 + 6
 *   3   * 3 + 4 * 5 + 6
 *       9   + 4 * 5 + 6
 *          13   * 5 + 6
 *              65   + 6
 *                  71
 * Parentheses can override this order; for example, here is what happens if parentheses are added to form
 * 1 + (2 * 3) + (4 * (5 + 6)):
 *
 * 1 + (2 * 3) + (4 * (5 + 6))
 * 1 +    6    + (4 * (5 + 6))
 *      7      + (4 * (5 + 6))
 *      7      + (4 *   11   )
 *      7      +     44
 *             51
 * Here are a few more examples:
 *
 * 2 * 3 + (4 * 5) becomes 26.
 * 5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 437.
 * 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 12240.
 * ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 13632.
 * Before you can help with the homework, you need to understand it yourself. Evaluate the expression on each line of
 * the homework; what is the sum of the resulting values?
 *
 * --- Part Two ---
 * You manage to answer the child's questions and they finish part 1 of their homework, but get stuck when they reach
 * the next section: advanced math.
 *
 * Now, addition and multiplication have different precedence levels, but they're not the ones you're familiar with.
 * Instead, addition is evaluated before multiplication.
 *
 * For example, the steps to evaluate the expression 1 + 2 * 3 + 4 * 5 + 6 are now as follows:
 *
 * 1 + 2 * 3 + 4 * 5 + 6
 *   3   * 3 + 4 * 5 + 6
 *   3   *   7   * 5 + 6
 *   3   *   7   *  11
 *      21       *  11
 *          231
 * Here are the other examples from above:
 *
 * 1 + (2 * 3) + (4 * (5 + 6)) still becomes 51.
 * 2 * 3 + (4 * 5) becomes 46.
 * 5 + (8 * 3 + 9 + 3 * 4 * 3) becomes 1445.
 * 5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)) becomes 669060.
 * ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2 becomes 23340.
 * What do you get if you add up the results of evaluating the homework problems using these new rules?
 */
public class Day18 {

    /**
     * Given a simple string this will solve it and return the sum of the parts
     *
     * A simple string is one without any brackets or need to consider precedence of operators.
     * @param simple The simple string to solve
     * @return The value of the solved sum
     */
    private long solveSimpleString(String simple) {
        //Use a queue to nicely pop items off it until we are finished
        Queue<String> partsToSolve = new LinkedList<>();
        String[] partsOfSimpleProblem = simple.split(" ");
        partsToSolve.addAll(Arrays.asList(partsOfSimpleProblem));

        //Take the first value as the current sum
        long curSum = Long.parseLong(partsToSolve.remove());
        //Then loop over popping an operand and a value off each time and performing the operation
        while(partsToSolve.size() > 0) {
            String operation = partsToSolve.remove();
            long additionalNumber = Long.parseLong(partsToSolve.remove());

            if(operation.equals("+")) {
                curSum += additionalNumber;
            }else if(operation.equals("*")) {
                curSum *= additionalNumber;
            }else{
                throw new RuntimeException("Unknown operation " + operation);
            }
        }

        return curSum;
    }

    /**
     * Given a problem this will resolve it with the precedence rules by adding brackets around all the bits that
     * need precedence
     * @param problem The problem to resolve
     * @return The value of the problem once solved
     */
    private long resolveBracketsWithPrecedence(String problem) {
        String[] alteredProblem = problem.split(" ");
        List<String> parts = new ArrayList<>();
        //First we go around and add in brackets around the additional operation to force that to be run first
        for(int i = 0; i < alteredProblem.length; i++) {
            if(i < (alteredProblem.length-1)) {
                if(alteredProblem[i+1].equals("+")) {
                    parts.add("(");
                }
            }

            parts.add(alteredProblem[i]);
            if(i > 0) {
                if(alteredProblem[i-1].equals("+")) {
                    parts.add(")");
                }
            }
        }

        //Once we have added the brackets to force the order of operations, we then can solve it using the standard solver
        String resolvedProblem = "";
        for(String s : parts) {
            resolvedProblem += s + " ";
        }

        resolvedProblem = resolvedProblem.replace("( ", "(").replace(" )", ")").trim();

        //We can now solve this ignoring precedence
        return this.solveProblem(resolvedProblem, false);
    }

    /**
     * Helper function to solve a problem, if precedence is set to false it will operate left to right, otherwise it
     * will treat additional as more important than multiplication
     * @param prob The string representing the problem to solve
     * @param precedence Whether precedence should be taken into account
     * @return The value of the solved sum
     */
    private long solveProblem(String prob, boolean precedence) {

        //Resolve brackets and handle them first
        boolean foundBracket = true;
        while(foundBracket) {
            foundBracket = false;
            //Leys try and find a bracket
            for (int charIndex = 0; charIndex < prob.length(); charIndex++) {
                if(foundBracket) { break; }
                char charAtPos = prob.charAt(charIndex);
                if (charAtPos == '(') {
                    //We have found a bracket, time to find an "end" bracket
                    int startingBracket = charIndex;
                    for (int bracketSearchIndex = charIndex+1; bracketSearchIndex < prob.length(); bracketSearchIndex++) {
                        if(foundBracket) { break; }
                        char possibleEndBracketChar = prob.charAt(bracketSearchIndex);
                        //Oh no, nesting brackets, ok reset our position!
                        if(possibleEndBracketChar == '(') {
                            startingBracket = bracketSearchIndex;
                        }else if(possibleEndBracketChar == ')') {
                            //Yay we have found the end, now lets solve it
                            long solutionForBracketPiece;
                            if(precedence) {
                                solutionForBracketPiece = this.resolveBracketsWithPrecedence(prob.substring(startingBracket+1, bracketSearchIndex));
                            }else{
                                solutionForBracketPiece = this.solveSimpleString(prob.substring(startingBracket+1, bracketSearchIndex).trim());
                            }

                            //Recreate the problem by adding in the "sub problem" replacing the part of the original problem
                            prob = prob.substring(0, startingBracket) + solutionForBracketPiece + prob.substring(bracketSearchIndex+1);
                            foundBracket = true;
                        }
                    }
                }
            }
        }

        //At this point all the brackets have been solved, so we can just solve the "simple" part.
        if(precedence) {
            return this.resolveBracketsWithPrecedence(prob);
        }else{
            return this.solveSimpleString(prob);
        }

    }

    /**
     * Solves the list of problems and sums them together by just solving them left to right
     * @param problems List of problems to solve
     * @return The sum of the answers of each problem
     */
    public long solvePartOne(List<String> problems) {
        long totalSum = 0;
        for(String s : problems) {
            totalSum += this.solveProblem(s, false);
        }

        return totalSum;
    }

    /**
     * Solves the list of problems and sums them together by just solving them with precedence
     * @param problems List of problems to solve
     * @return The sum of the answers of each problem
     */
    public long solvePartTwo(List<String> problems) {
        long totalSum = 0;
        for(String s : problems) {
            totalSum += this.solveProblem(s, true);
        }

        return totalSum;
    }

    public static void main(String[] args) {
        List<String> problems = ProblemLoader.loadProblemIntoStringArray(2020, 18);
        Day18 d = new Day18();
        long p1 = d.solvePartOne(problems);
        System.out.println("After solving the set of equations the total sum is " + p1);
        long p2 = d.solvePartTwo(problems);
        System.out.println("After solving the equations with bracket precedence the sum is " + p2);
    }

}
