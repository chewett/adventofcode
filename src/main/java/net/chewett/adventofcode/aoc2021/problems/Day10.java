package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 10: Syntax Scoring ---
 * You ask the submarine to determine the best route out of the deep-sea cave, but it only replies:
 *
 * Syntax error in navigation subsystem on line: all of them
 * All of them?! The damage is worse than you thought. You bring up a copy of the navigation subsystem (your puzzle
 * input).
 *
 * The navigation subsystem syntax is made of several lines containing chunks. There are one or more chunks on each line,
 * and chunks contain zero or more other chunks. Adjacent chunks are not separated by any delimiter; if one chunk stops,
 * the next chunk (if any) can immediately start. Every chunk must open and close with one of four legal pairs of
 * matching characters:
 *
 * If a chunk opens with (, it must close with ).
 * If a chunk opens with [, it must close with ].
 * If a chunk opens with {, it must close with }.
 * If a chunk opens with <, it must close with >.
 * So, () is a legal chunk that contains no other chunks, as is []. More complex but valid chunks include
 * ([]), {()()()}, <([{}])>, [<>({}){}[([])<>]], and even (((((((((()))))))))).
 *
 * Some lines are incomplete, but others are corrupted. Find and discard the corrupted lines first.
 *
 * A corrupted line is one where a chunk closes with the wrong character - that is, where the characters it opens and
 * closes with do not form one of the four legal pairs listed above.
 *
 * Examples of corrupted chunks include (], {()()()>, (((()))}, and <([]){()}[{}]). Such a chunk can appear anywhere
 * within a line, and its presence causes the whole line to be considered corrupted.
 *
 * For example, consider the following navigation subsystem:
 *
 * [({(<(())[]>[[{[]{<()<>>
 * [(()[<>])]({[<{<<[]>>(
 * {([(<{}[<>[]}>{[]{[(<()>
 * (((({<>}<{<{<>}{[]{[]{}
 * [[<[([]))<([[{}[[()]]]
 * [{[{({}]{}}([{[{{{}}([]
 * {<[[]]>}<{[{[{[]{()[[[]
 * [<(<(<(<{}))><([]([]()
 * <{([([[(<>()){}]>(<<{{
 * <{([{{}}[<[[[<>{}]]]>[]]
 * Some of the lines aren't corrupted, just incomplete; you can ignore these lines for now. The remaining five lines are
 * corrupted:
 *
 * {([(<{}[<>[]}>{[]{[(<()> - Expected ], but found } instead.
 * [[<[([]))<([[{}[[()]]] - Expected ], but found ) instead.
 * [{[{({}]{}}([{[{{{}}([] - Expected ), but found ] instead.
 * [<(<(<(<{}))><([]([]() - Expected >, but found ) instead.
 * <{([([[(<>()){}]>(<<{{ - Expected ], but found > instead.
 * Stop at the first incorrect closing character on each corrupted line.
 *
 * Did you know that syntax checkers actually have contests to see who can get the high score for syntax errors in a
 * file? It's true! To calculate the syntax error score for a line, take the first illegal character on the line and
 * look it up in the following table:
 *
 * ): 3 points.
 * ]: 57 points.
 * }: 1197 points.
 * >: 25137 points.
 * In the above example, an illegal ) was found twice (2*3 = 6 points), an illegal ] was found once (57 points), an
 * illegal } was found once (1197 points), and an illegal > was found once (25137 points). So, the total syntax error
 * score for this file is 6+57+1197+25137 = 26397 points!
 *
 * Find the first illegal character in each corrupted line of the navigation subsystem. What is the total syntax error
 * score for those errors?
 *
 * --- Part Two ---
 * Now, discard the corrupted lines. The remaining lines are incomplete.
 *
 * Incomplete lines don't have any incorrect characters - instead, they're missing some closing characters at the end
 * of the line. To repair the navigation subsystem, you just need to figure out the sequence of closing characters that
 * complete all open chunks in the line.
 *
 * You can only use closing characters (), ], }, or >), and you must add them in the correct order so that only legal
 * pairs are formed and all chunks end up closed.
 *
 * In the example above, there are five incomplete lines:
 *
 * [({(<(())[]>[[{[]{<()<>> - Complete by adding }}]])})].
 * [(()[<>])]({[<{<<[]>>( - Complete by adding )}>]}).
 * (((({<>}<{<{<>}{[]{[]{} - Complete by adding }}>}>)))).
 * {<[[]]>}<{[{[{[]{()[[[] - Complete by adding ]]}}]}]}>.
 * <{([{{}}[<[[[<>{}]]]>[]] - Complete by adding ])}>.
 * Did you know that autocomplete tools also have contests? It's true! The score is determined by considering the
 * completion string character-by-character. Start with a total score of 0. Then, for each character, multiply the total
 * score by 5 and then increase the total score by the point value given for the character in the following table:
 *
 * ): 1 point.
 * ]: 2 points.
 * }: 3 points.
 * >: 4 points.
 * So, the last completion string above - ])}> - would be scored as follows:
 *
 * Start with a total score of 0.
 * Multiply the total score by 5 to get 0, then add the value of ] (2) to get a new total score of 2.
 * Multiply the total score by 5 to get 10, then add the value of ) (1) to get a new total score of 11.
 * Multiply the total score by 5 to get 55, then add the value of } (3) to get a new total score of 58.
 * Multiply the total score by 5 to get 290, then add the value of > (4) to get a new total score of 294.
 * The five lines' completion strings have total scores as follows:
 *
 * }}]])})] - 288957 total points.
 * )}>]}) - 5566 total points.
 * }}>}>)))) - 1480781 total points.
 * ]]}}]}]}> - 995444 total points.
 * ])}> - 294 total points.
 * Autocomplete tools are an odd bunch: the winner is found by sorting all of the scores and then taking the middle
 * score. (There will always be an odd number of scores to consider.) In this example, the middle score is 288957
 * because there are the same number of scores smaller and larger than it.
 *
 * Find the completion string for each incomplete line, score the completion strings, and sort the scores. What is the
 * middle score?
 */
public class Day10 {

    /**
     * Getter for a simple map which when you pass the closing character it returns the opening character
     * @return Map representing the closing -> opening character pairings
     */
    private Map<Character, Character> getClosingCharMap() {
        Map<Character, Character> closingCharMap = new HashMap<>();
        closingCharMap.put(')', '(');
        closingCharMap.put(']', '[');
        closingCharMap.put('}', '{');
        closingCharMap.put('>', '<');
        return closingCharMap;
    }


    /**
     * Goes through the list of code and determines the syntax error score for the corrupted lines
     * @param syntax List of code lines to syntax check
     * @return The syntax checking score
     */
    public long solvePartOne(List<String> syntax) {
        Map<Character, Character> closingCharMap = this.getClosingCharMap();
        Map<Character, Integer> syntaxErrorScore = new HashMap<>();
        syntaxErrorScore.put(')', 3);
        syntaxErrorScore.put(']', 57);
        syntaxErrorScore.put('}', 1197);
        syntaxErrorScore.put('>', 25137);

        int totalSyntaxErrorScore = 0;
        for(String str : syntax) {

            //Keep track of unclosed braces
            Stack<Character> unclosedBraces = new Stack<>();
            //Keep track if we have found a syntax issue
            boolean foundBadChar = false;

            for(int i = 0; i < str.length() && !foundBadChar; i++) {
                char curChar = str.charAt(i);

                //For any of the braces, pop the unclosed brace onto the stack
                if(curChar == '(' || curChar == '[' || curChar == '{' || curChar == '<') {
                    unclosedBraces.push(curChar);
                }else{
                    char expectedClosingChar = closingCharMap.get(curChar);
                    //If the top of the stack isn't the closing brace expected, or the stack is empty it's an error
                    if(unclosedBraces.size() == 0 || unclosedBraces.peek() != expectedClosingChar) {
                        totalSyntaxErrorScore += syntaxErrorScore.get(curChar);
                        foundBadChar = true;
                    }else{
                        unclosedBraces.pop();
                    }
                }
            }
        }

        return totalSyntaxErrorScore;
    }

    /**
     * Process the input to work out what needs to be done to close any incomplete lines
     * @param syntax List of code lines to syntax check
     * @return The winning score of all the lines
     */
    public long solvePartTwo(List<String> syntax) {
        Map<Character, Character> closingCharMap = this.getClosingCharMap();
        Map<Character, Character> openingCharMap = new HashMap<>();
        openingCharMap.put('(', ')');
        openingCharMap.put('[', ']');
        openingCharMap.put('{', '}');
        openingCharMap.put('<', '>');
        Map<Character, Integer> autocompleteCharScore = new HashMap<>();
        autocompleteCharScore.put(')', 1);
        autocompleteCharScore.put(']', 2);
        autocompleteCharScore.put('}', 3);
        autocompleteCharScore.put('>', 4);

        List<Long> autocompleteScores = new ArrayList<>();
        for(String str : syntax) {
            Stack<Character> unclosedBraces = new Stack<>();
            boolean foundBadChar = false;

            //Here we do a similar set of things as above to determine if there is a bad character
            for(int i = 0; i < str.length() && !foundBadChar; i++) {
                char curChar = str.charAt(i);

                if(curChar == '(' || curChar == '[' || curChar == '{' || curChar == '<') {
                    unclosedBraces.push(curChar);
                }else{
                    char expectedClosingChar = closingCharMap.get(curChar);
                    if(unclosedBraces.size() == 0 || unclosedBraces.peek() != expectedClosingChar) {
                        foundBadChar = true;
                    }else{
                        unclosedBraces.pop();
                    }
                }
            }

            //However if the code isn't bad, then we just unwind the expected closing brackets to get the score
            if(!foundBadChar && unclosedBraces.size() > 0) {
                long thisLinesScore = 0;
                while(unclosedBraces.size() > 0) {
                    char autocompleteChar = openingCharMap.get(unclosedBraces.pop());
                    thisLinesScore *= 5;
                    thisLinesScore += autocompleteCharScore.get(autocompleteChar);
                }
                autocompleteScores.add(thisLinesScore);
            }

        }

        //Sort the scores in order to find the middle value
        Collections.sort(autocompleteScores);

        return autocompleteScores.get(autocompleteScores.size() / 2);
    }

    public static void main(String[] args) {
        List<String> syntax = ProblemLoader.loadProblemIntoStringArray(2021, 10);

        Day10 d = new Day10();
        long partOne = d.solvePartOne(syntax);
        System.out.println("The total syntax error score is " + partOne);

        long partTwo = d.solvePartTwo(syntax);
        System.out.println("The winning score for the second part is " + partTwo);
    }

}
