package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 19: Monster Messages ---
 * You land in an airport surrounded by dense forest. As you walk to your high-speed train, the Elves at the Mythical
 * Information Bureau contact you again. They think their satellite has collected an image of a sea monster!
 * Unfortunately, the connection to the satellite is having problems, and many of the messages sent back from the
 * satellite have been corrupted.
 *
 * They sent you a list of the rules valid messages should obey and a list of received messages they've collected so far
 * (your puzzle input).
 *
 * The rules for valid messages (the top part of your puzzle input) are numbered and build upon each other. For example:
 *
 * 0: 1 2
 * 1: "a"
 * 2: 1 3 | 3 1
 * 3: "b"
 * Some rules, like 3: "b", simply match a single character (in this case, b).
 *
 * The remaining rules list the sub-rules that must be followed; for example, the rule 0: 1 2 means that to match rule
 * 0, the text being checked must match rule 1, and the text after the part that matched rule 1 must then match rule 2.
 *
 * Some of the rules have multiple lists of sub-rules separated by a pipe (|). This means that at least one list of
 * sub-rules must match. (The ones that match might be different each time the rule is encountered.) For example, the
 * rule 2: 1 3 | 3 1 means that to match rule 2, the text being checked must match rule 1 followed by rule 3 or it must
 * match rule 3 followed by rule 1.
 *
 * Fortunately, there are no loops in the rules, so the list of possible matches will be finite. Since rule 1 matches a
 * and rule 3 matches b, rule 2 matches either ab or ba. Therefore, rule 0 matches aab or aba.
 *
 * Here's a more interesting example:
 *
 * 0: 4 1 5
 * 1: 2 3 | 3 2
 * 2: 4 4 | 5 5
 * 3: 4 5 | 5 4
 * 4: "a"
 * 5: "b"
 * Here, because rule 4 matches a and rule 5 matches b, rule 2 matches two letters that are the same (aa or bb), and
 * rule 3 matches two letters that are different (ab or ba).
 *
 * Since rule 1 matches rules 2 and 3 once each in either order, it must match two pairs of letters, one pair with
 * matching letters and one pair with different letters.
 * This leaves eight possibilities: aaab, aaba, bbab, bbba, abaa, abbb, baaa, or babb.
 *
 * Rule 0, therefore, matches a (rule 4), then any of the eight options from rule 1, then b (rule 5):
 * aaaabb, aaabab, abbabb, abbbab, aabaab, aabbbb, abaaab, or ababbb.
 *
 * The received messages (the bottom part of your puzzle input) need to be checked against the rules so you can
 * determine which are valid and which are corrupted. Including the rules and the messages together, this might
 * look like:
 *
 * 0: 4 1 5
 * 1: 2 3 | 3 2
 * 2: 4 4 | 5 5
 * 3: 4 5 | 5 4
 * 4: "a"
 * 5: "b"
 *
 * ababbb
 * bababa
 * abbbab
 * aaabbb
 * aaaabbb
 * Your goal is to determine the number of messages that completely match rule 0. In the above example, ababbb and
 * abbbab match, but bababa, aaabbb, and aaaabbb do not, producing the answer 2. The whole message must match all of
 * rule 0; there can't be extra unmatched characters in the message. (For example, aaaabbb might appear to match rule 0
 * above, but it has an extra unmatched b on the end.)
 *
 * How many messages completely match rule 0?
 *
 * Your puzzle answer was 272.
 *
 * --- Part Two ---
 * As you look over the list of messages, you realize your matching rules aren't quite right. To fix them, completely
 * replace rules 8: 42 and 11: 42 31 with the following:
 *
 * 8: 42 | 42 8
 * 11: 42 31 | 42 11 31
 * This small change has a big impact: now, the rules do contain loops, and the list of messages they could
 * hypothetically match is infinite. You'll need to determine how these changes affect which messages are valid.
 *
 * Fortunately, many of the rules are unaffected by this change; it might help to start by looking at which rules always
 * match the same set of values and how those rules (especially rules 42 and 31) are used by the new versions of rules 8
 * and 11.
 *
 * (Remember, you only need to handle the rules you have; building a solution that could handle any hypothetical
 * combination of rules would be significantly more difficult.)
 *
 * For example:
 *
 * 42: 9 14 | 10 1
 * 9: 14 27 | 1 26
 * 10: 23 14 | 28 1
 * 1: "a"
 * 11: 42 31
 * 5: 1 14 | 15 1
 * 19: 14 1 | 14 14
 * 12: 24 14 | 19 1
 * 16: 15 1 | 14 14
 * 31: 14 17 | 1 13
 * 6: 14 14 | 1 14
 * 2: 1 24 | 14 4
 * 0: 8 11
 * 13: 14 3 | 1 12
 * 15: 1 | 14
 * 17: 14 2 | 1 7
 * 23: 25 1 | 22 14
 * 28: 16 1
 * 4: 1 1
 * 20: 14 14 | 1 15
 * 3: 5 14 | 16 1
 * 27: 1 6 | 14 18
 * 14: "b"
 * 21: 14 1 | 1 14
 * 25: 1 1 | 1 14
 * 22: 14 14
 * 8: 42
 * 26: 14 22 | 1 20
 * 18: 15 15
 * 7: 14 5 | 1 21
 * 24: 14 1
 *
 * abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa
 * bbabbbbaabaabba
 * babbbbaabbbbbabbbbbbaabaaabaaa
 * aaabbbbbbaaaabaababaabababbabaaabbababababaaa
 * bbbbbbbaaaabbbbaaabbabaaa
 * bbbababbbbaaaaaaaabbababaaababaabab
 * ababaaaaaabaaab
 * ababaaaaabbbaba
 * baabbaaaabbaaaababbaababb
 * abbbbabbbbaaaababbbbbbaaaababb
 * aaaaabbaabaaaaababaa
 * aaaabbaaaabbaaa
 * aaaabbaabbaaaaaaabbbabbbaaabbaabaaa
 * babaaabbbaaabaababbaabababaaab
 * aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba
 * Without updating rules 8 and 11, these rules only match three messages: bbabbbbaabaabba, ababaaaaaabaaab,
 * and ababaaaaabbbaba.
 *
 * However, after updating rules 8 and 11, a total of 12 messages match:
 *
 * bbabbbbaabaabba
 * babbbbaabbbbbabbbbbbaabaaabaaa
 * aaabbbbbbaaaabaababaabababbabaaabbababababaaa
 * bbbbbbbaaaabbbbaaabbabaaa
 * bbbababbbbaaaaaaaabbababaaababaabab
 * ababaaaaaabaaab
 * ababaaaaabbbaba
 * baabbaaaabbaaaababbaababb
 * abbbbabbbbaaaababbbbbbaaaababb
 * aaaaabbaabaaaaababaa
 * aaaabbaabbaaaaaaabbbabbbaaabbaabaaa
 * aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba
 * After updating rules 8 and 11, how many messages completely match rule 0?
 */
public class Day19 {

    /**
     * By converting the rules into one unholy regex this will then parse each message to see if it matches the regex
     * @param grammarData List of rules and messages to parse
     * @return The number of messages that fit the rules
     */
    public long solvePartOne(List<String> grammarData) {
        Map<Integer, String> stringsToConvertToRegex = new HashMap<>();
        int rowNum = 0;
        while(!grammarData.get(rowNum).equals("")) {
            String[] parts = grammarData.get(rowNum).split(": ");
            int ruleNumber = Integer.parseInt(parts[0]);
            String ruleString = parts[1];
            if(ruleString.charAt(0) == '"') {
                ruleString = ""+ruleString.charAt(1);
            }else{
               String[] rsParts = ruleString.split(" ");
               String newRs = "";
               boolean foundOr = false;
               for(String newP : rsParts) {
                   if(!newP.equals("|")) {
                       newRs += "{" + newP + "}";
                   }else{
                       newRs += "|";
                       foundOr = true;
                   }
               }
               ruleString = newRs;
               if(foundOr) {
                   ruleString = "(" + ruleString + ")";
               }
            }

            stringsToConvertToRegex.put(ruleNumber, ruleString);

            rowNum++;
        }
        rowNum++;

        List<String> inputs = new ArrayList<>();
        while(rowNum < grammarData.size()) {
            inputs.add(grammarData.get(rowNum));
            rowNum++;
        }

        boolean performedReplacement = true;
        while(performedReplacement && stringsToConvertToRegex.size() > 1) {
            performedReplacement = false;
            for(Map.Entry<Integer, String> e : stringsToConvertToRegex.entrySet()) {
                boolean allAsAndBs = true;
                for(int i = 0; i < e.getValue().length(); i++) {
                    char c = e.getValue().charAt(i);
                    if(c != 'a' && c != 'b' && c != '(' && c != ')' && c != '|') {
                        allAsAndBs = false;
                    }
                }

                if(allAsAndBs) {
                    performedReplacement = true;
                    Map<Integer, String> newMap = new HashMap<>();

                    for (Map.Entry<Integer, String> eReplacement : stringsToConvertToRegex.entrySet()) {
                        if(!e.getKey().equals(eReplacement.getKey())) {
                            String stringToReplace = eReplacement.getValue().replace("{" + e.getKey() + "}", e.getValue());
                            newMap.put(eReplacement.getKey(), stringToReplace);
                        }
                    }
                    stringsToConvertToRegex = newMap;
                    break;
                }
            }


        }

        if(stringsToConvertToRegex.size() == 1) {
            String regex = "^"+ stringsToConvertToRegex.get(0) + "$";

            int matching = 0;
            for(String s : inputs) {
                if(s.matches(regex)) {
                    matching++;
                }
            }

            return matching;
        }

        return -1;

    }

    /**
     * By converting the rules into one unholy regex this will then parse each message to see if it matches the regex
     *
     * Because we have something that cannot be represented by a regex, we cheat! We know how long the message is at most
     * and therefore we can expand the rules that cannot be represented in a single regex, into many regexes which
     * will then fit the pattern.
     *
     * This makes the regex incredibly verbose but actually it still solves the problem pretty fast. This is a good
     * example of where if you can limit the problem by known constraints you can simplify it and solve it with a known
     * solution (aka the above solution).
     *
     * @param grammarData List of rules and messages to parse
     * @return The number of messages that fit the rules
     */
    public long solvePartTwo(List<String> grammarData) {
        Map<Integer, String> stringsToConvertToRegex = new HashMap<>();
        int rowNum = 0;
        while(!grammarData.get(rowNum).equals("")) {
            String[] parts = grammarData.get(rowNum).split(": ");
            int ruleNumber = Integer.parseInt(parts[0]);
            String ruleString = parts[1];
            if(ruleString.charAt(0) == '"') {
                ruleString = ""+ruleString.charAt(1);
            }else{
                String[] rsParts = ruleString.split(" ");
                String newRs = "";
                boolean foundOr = false;
                for(String newP : rsParts) {
                    if(!newP.equals("|")) {
                        newRs += "{" + newP + "}";
                    }else{
                        newRs += "|";
                        foundOr = true;
                    }
                }
                ruleString = newRs;
                if(foundOr) {
                    ruleString = "(" + ruleString + ")";
                }
            }

            stringsToConvertToRegex.put(ruleNumber, ruleString);

            rowNum++;
        }
        rowNum++;

        //Here we manually swap 8 for a regex which represents the new rule
        stringsToConvertToRegex.put(8, "({42})+");

        // Here we swap the problematic rule that cannot be represented with a regex. We actually swap it with something
        // that effectively becomes becuase we know the maximum length that it could become:
        // 42 31 | 42 42 31 31 | 42 42 42 31 31 31 |  42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31
        // This then is simply solved by the regex method above!
        // The chosen max length has been decided by reviewing the input manually but could be determined automatically
        // The solution does suggest you don't need to handle every possible input (which makes it significantly easier)
        String cheatingString = "";
        int cheatCopy = 1;
        while(cheatCopy < 5) {
            int i =0;
            while(i < cheatCopy) {
                cheatingString += "{42}";
                i++;
            }
            i = 0;
            while(i < cheatCopy) {
                cheatingString += "{31}";
                i++;
            }
            cheatingString += "|";
            cheatCopy++;
        }

        stringsToConvertToRegex.put(11, "("+ cheatingString.substring(0, cheatingString.length() -1) + ")");



        List<String> inputs = new ArrayList<>();
        while(rowNum < grammarData.size()) {
            inputs.add(grammarData.get(rowNum));
            rowNum++;
        }

        boolean performedReplacement = true;
        while(performedReplacement && stringsToConvertToRegex.size() > 1) {
            performedReplacement = false;
            for(Map.Entry<Integer, String> e : stringsToConvertToRegex.entrySet()) {
                boolean allAsAndBs = true;
                for(int i = 0; i < e.getValue().length(); i++) {
                    char c = e.getValue().charAt(i);
                    if(c != 'a' && c != 'b' && c != '(' && c != ')' && c != '|' && c != '+' && c != '*') {
                        allAsAndBs = false;
                    }
                }

                if(allAsAndBs) {
                    performedReplacement = true;
                    Map<Integer, String> newMap = new HashMap<>();

                    for (Map.Entry<Integer, String> eReplacement : stringsToConvertToRegex.entrySet()) {
                        if(!e.getKey().equals(eReplacement.getKey())) {
                            String stringToReplace = eReplacement.getValue().replace("{" + e.getKey() + "}", e.getValue());
                            newMap.put(eReplacement.getKey(), stringToReplace);
                        }
                    }
                    stringsToConvertToRegex = newMap;
                    break;
                }
            }


        }

        if(stringsToConvertToRegex.size() == 1) {
            String regex = "^"+ stringsToConvertToRegex.get(0) + "$";

            int matching = 0;
            for(String s : inputs) {
                if(s.matches(regex)) {
                    matching++;
                }
            }

            return matching;
        }

        return -1;

    }

    public static void main(String[] args) {
        List<String> grammarData = ProblemLoader.loadProblemIntoStringArray(2020, 19);
        Day19 d = new Day19();
        long p1 = d.solvePartOne(grammarData);
        System.out.println("The number of matches with the ruleset for part one is " + p1);
        long p2 = d.solvePartTwo(grammarData);
        System.out.println("The number of matches for the modified ruleset for part two is " + p2);

    }

}
