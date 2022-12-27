package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * --- Day 21: Monkey Math ---
 * The monkeys are back! You're worried they're going to try to steal your stuff again, but it seems like they're just
 * holding their ground and making various monkey noises at you.
 *
 * Eventually, one of the elephants realizes you don't speak monkey and comes over to interpret. As it turns out, they
 * overheard you talking about trying to find the grove; they can show you a shortcut if you answer their riddle.
 *
 * Each monkey is given a job: either to yell a specific number or to yell the result of a math operation. All of the
 * number-yelling monkeys know their number from the start; however, the math operation monkeys need to wait for two
 * other monkeys to yell a number, and those two other monkeys might also be waiting on other monkeys.
 *
 * Your job is to work out the number the monkey named root will yell before the monkeys figure it out themselves.
 *
 * For example:
 *
 * root: pppw + sjmn
 * dbpl: 5
 * cczh: sllz + lgvd
 * zczc: 2
 * ptdq: humn - dvpt
 * dvpt: 3
 * lfqf: 4
 * humn: 5
 * ljgn: 2
 * sjmn: drzm * dbpl
 * sllz: 4
 * pppw: cczh / lfqf
 * lgvd: ljgn * ptdq
 * drzm: hmdt - zczc
 * hmdt: 32
 * Each line contains the name of a monkey, a colon, and then the job of that monkey:
 *
 * A lone number means the monkey's job is simply to yell that number.
 * A job like aaaa + bbbb means the monkey waits for monkeys aaaa and bbbb to yell each of their numbers; the monkey
 * then yells the sum of those two numbers.
 * aaaa - bbbb means the monkey yells aaaa's number minus bbbb's number.
 * Job aaaa * bbbb will yell aaaa's number multiplied by bbbb's number.
 * Job aaaa / bbbb will yell aaaa's number divided by bbbb's number.
 * So, in the above example, monkey drzm has to wait for monkeys hmdt and zczc to yell their numbers. Fortunately, both
 * hmdt and zczc have jobs that involve simply yelling a single number, so they do this immediately: 32 and 2. Monkey
 * drzm can then yell its number by finding 32 minus 2: 30.
 *
 * Then, monkey sjmn has one of its numbers (30, from monkey drzm), and already has its other number, 5, from dbpl.
 * This allows it to yell its own number by finding 30 multiplied by 5: 150.
 *
 * This process continues until root yells a number: 152.
 *
 * However, your actual situation involves considerably more monkeys. What number will the monkey named root yell?
 *
 * --- Part Two ---
 * Due to some kind of monkey-elephant-human mistranslation, you seem to have misunderstood a few key details about
 * the riddle.
 *
 * First, you got the wrong job for the monkey named root; specifically, you got the wrong math operation. The correct
 * operation for monkey root should be =, which means that it still listens for two numbers (from the same two monkeys
 * as before), but now checks that the two numbers match.
 *
 * Second, you got the wrong monkey for the job starting with humn:. It isn't a monkey - it's you. Actually, you got
 * the job wrong, too: you need to figure out what number you need to yell so that root's equality check passes. (The
 * number that appears after humn: in your input is now irrelevant.)
 *
 * In the above example, the number you need to yell to pass root's equality test is 301. (This causes root to get the
 * same number, 150, from both of its monkeys.)
 *
 * What number do you yell to pass root's equality test?
 */
public class Day21 {

    /**
     * Attempt to work out what the number the monkey named root will yell
     * @param input List of monkey names and their values
     * @return The number the monkey named root with yell
     */
    public long solvePartOne(List<String> input) {
        Map<String, Long> calculatedValues = new HashMap<>();
        List<String> thingsToCalculate = new ArrayList<>(input);

        //Keep going until we have worked out what root will say
        while(!calculatedValues.containsKey("root")) {
            List<String> thingsLeft = new ArrayList<>();

            //Loop over every monkey we need to determine a value for
            for(String str : thingsToCalculate) {
                String[] parts = str.split(": ");
                String monkeyName = parts[0];
                String[] numberParts = parts[1].split(" ");

                //If the monkey is just shouting a value then we immediately know their value
                if(numberParts.length == 1) {
                    calculatedValues.put(monkeyName, Long.parseLong(numberParts[0]));
                }else{
                    //Otherwise the Monkey will be shouting a calculated value and we need to know two other monkey values
                    String monkeyOne = numberParts[0];
                    String operator = numberParts[1];
                    String monkeyTwo = numberParts[2];

                    //If we know both monkey values we can work out their value
                    if(calculatedValues.containsKey(monkeyOne) && calculatedValues.containsKey(monkeyTwo)) {
                        long finalVal = switch (operator) {
                            case "*" -> calculatedValues.get(monkeyOne) * calculatedValues.get(monkeyTwo);
                            case "/" -> calculatedValues.get(monkeyOne) / calculatedValues.get(monkeyTwo);
                            case "-" -> calculatedValues.get(monkeyOne) - calculatedValues.get(monkeyTwo);
                            case "+" -> calculatedValues.get(monkeyOne) + calculatedValues.get(monkeyTwo);
                            default -> throw new RuntimeException("Bad operator");
                        };

                        calculatedValues.put(monkeyName, finalVal);
                    }else{
                        thingsLeft.add(str);
                    }
                }
            }

            thingsToCalculate = thingsLeft;
        }

        //Once we have worked out root, return the value
        return calculatedValues.get("root");
    }

    /**
     * Work out what we have to shout out to equal the value that root will shout out
     * @param input List of monkey names and their values
     * @return The value that I need to shout out to make root equal the right value
     */
    public long solvePartTwo(List<String> input) {
        Map<String, Long> calculatedValues = new HashMap<>();
        List<String> thingsToCalculate = new ArrayList<>();

        for(String str : input) {
            if(!str.startsWith("humn:") && !str.startsWith("root:")) {
                thingsToCalculate.add(str);
            }else if(str.startsWith("root:")) {
                //We have to replace this root value with the correct one
                thingsToCalculate.add(str.replace("(\\+|-|\\*|/)", "="));
            }
        }

        //Keep going until we have found a change
        boolean changeFound = true;
        while(changeFound) {
            changeFound = false;
            List<String> thingsLeft = new ArrayList<>();

            for(String str : thingsToCalculate) {
                String[] parts = str.split(": ");
                String monkeyName = parts[0];
                String[] numberParts = parts[1].split(" ");

                //Again if there is a single value we can store it
                if(numberParts.length == 1) {
                    calculatedValues.put(monkeyName, Long.parseLong(numberParts[0]));
                    changeFound = true;
                }else{
                    String monkeyOne = numberParts[0];
                    String operator = numberParts[1];
                    String monkeyTwo = numberParts[2];

                    //Similarly as before if we have both values then we can save that value
                    if(calculatedValues.containsKey(monkeyOne) && calculatedValues.containsKey(monkeyTwo) && !operator.equals("=")) {
                        changeFound = true;
                        long finalVal = switch (operator) {
                            case "*" -> calculatedValues.get(monkeyOne) * calculatedValues.get(monkeyTwo);
                            case "/" -> calculatedValues.get(monkeyOne) / calculatedValues.get(monkeyTwo);
                            case "-" -> calculatedValues.get(monkeyOne) - calculatedValues.get(monkeyTwo);
                            case "+" -> calculatedValues.get(monkeyOne) + calculatedValues.get(monkeyTwo);
                            default -> throw new RuntimeException("Bad operator");
                        };

                        calculatedValues.put(monkeyName, finalVal);
                    }else{
                        thingsLeft.add(str);
                    }
                }
            }

            thingsToCalculate = thingsLeft;
        }

        //Convert all the strings into numbers
        String rootEquation = "";
        List<String> newThingsToCalculate = new ArrayList<>();
        for(String str : thingsToCalculate) {
            if(str.startsWith("root: ")) {
                rootEquation = str;
            }else{
                newThingsToCalculate.add(str);
            }
        }

        //Store a map of replacements and hardcode humn to humn
        Map<String, String> replacementEquations = new HashMap<>();
        replacementEquations.put("humn", "humn");

        for(Map.Entry<String, Long> e : calculatedValues.entrySet()) {
            replacementEquations.put(e.getKey(), ""+e.getValue());
        }

        boolean replaced = true;
        while(replaced) {
            replaced = false;

            List<String> newNewThingsToCompare = new ArrayList<>();

            for(String str : newThingsToCalculate) {
                String[] replacementSplit = str.split(":? ");
                if(replacementEquations.containsKey(replacementSplit[1]) &&
                        replacementEquations.containsKey(replacementSplit[3])) {

                    replacementEquations.put(replacementSplit[0], "("
                        + replacementEquations.get(replacementSplit[1]) + " "
                        + replacementSplit[2] + " "
                        + replacementEquations.get(replacementSplit[3]) + ")"
                    );
                    replaced = true;
                }else{
                    newNewThingsToCompare.add(str);
                }
            }
            newThingsToCalculate = newNewThingsToCompare;

        }

        //Time to combine the rules into a single string
        String[] rootParts = rootEquation.split(":? ");
        String leftRoot = rootParts[1];
        String rightRoot = rootParts[3];

        String comparisonString;

        long comparisonValue;
        if(calculatedValues.containsKey(leftRoot)) {
            comparisonValue = calculatedValues.get(leftRoot);
            comparisonString = rightRoot;
        }else{
            comparisonValue = calculatedValues.get(rightRoot);
            comparisonString = leftRoot;
        }

        String currentReplacement = replacementEquations.get(comparisonString);
        boolean reducing = true;
        while(reducing) {
            reducing = false;

            String lhs = "";
            char operator = ' ';
            String rhs = "";
            int bracketDepth = 0;
            for(int charIndex = 1; charIndex < currentReplacement.length() - 1; charIndex++) {
                char thisChar = currentReplacement.charAt(charIndex);
                if(thisChar == '(') {
                    bracketDepth++;
                }else if(thisChar == ')') {
                    bracketDepth--;
                }else if(thisChar == ' ' && bracketDepth == 0) {
                    operator = currentReplacement.charAt(charIndex + 1);
                    rhs = currentReplacement.substring(charIndex+3, currentReplacement.length()-1);
                    reducing = true;
                    break;
                }

                lhs += thisChar;
            }

            if(!reducing) {
                return comparisonValue;
            }

            if(lhs.contains("(") || lhs.equals("humn")) {

                //humn + 5
                if(operator == '+') {
                    comparisonValue -= Long.parseLong(rhs);
                //humn - 5
                }else if(operator == '-') {
                    comparisonValue += Long.parseLong(rhs);
                //humn * 5
                }else if(operator == '*') {
                    comparisonValue /= Long.parseLong(rhs);
                //humn / 5
                } else if (operator == '/') {
                    comparisonValue *= Long.parseLong(rhs);
                }else{
                    throw new RuntimeException("bad operator");
                }

                currentReplacement = lhs;

            }else{
                //5 + humn
                if(operator == '+') {
                    comparisonValue -= Long.parseLong(lhs);
                //5 - humn
                }else if(operator == '-') {
                    comparisonValue = Long.parseLong(lhs) - comparisonValue;
                //5 * humn
                }else if(operator == '*') {
                    comparisonValue /= Long.parseLong(lhs);
                // 5 / humn
                } else if (operator == '/') {
                    comparisonValue = Long.parseLong(lhs) / comparisonValue;
                }else{
                    throw new RuntimeException("bad operator");
                }

                currentReplacement = rhs;

            }
        }

       return -1;

    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2022, 21);

        Day21 d = new Day21();
        long partOne = d.solvePartOne(input);
        System.out.println("The monkey named root will shout out " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("I need to shout out "+ partTwo);

    }


}
