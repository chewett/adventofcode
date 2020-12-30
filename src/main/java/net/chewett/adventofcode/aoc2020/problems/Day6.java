package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 6: Custom Customs ---
 * As your flight approaches the regional airport where you'll switch to a much larger plane, customs declaration forms
 * are distributed to the passengers.
 *
 * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions for
 * which anyone in your group answers "yes". Since your group is just you, this doesn't take very long.
 *
 * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help. For
 * each of the people in their group, you write down the questions for which they answer "yes", one per line. For
 * example:
 *
 * abcx
 * abcy
 * abcz
 * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z. (Duplicate answers to the
 * same question don't count extra; each question counts at most once.)
 *
 * Another group asks for your help, then another, and eventually you've collected answers from every group on the plane
 * (your puzzle input). Each group's answers are separated by a blank line, and within each group, each person's answers
 * are on a single line. For example:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * The first group contains one person who answered "yes" to 3 questions: a, b, and c.
 * The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.
 * The fourth group contains four people; combined, they answered "yes" to only 1 question, a.
 * The last group contains one person who answered "yes" to only 1 question, b.
 * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
 *
 * For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
 *
 * --- Part Two ---
 * As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
 *
 * You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which
 * everyone answered "yes"!
 *
 * Using the same example as above:
 *
 * abc
 *
 * a
 * b
 * c
 *
 * ab
 * ac
 *
 * a
 * a
 * a
 * a
 *
 * b
 * This list represents answers from five groups:
 *
 * In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
 * In the second group, there is no question to which everyone answered "yes".
 * In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c,
 * they don't count.
 * In the fourth group, everyone answered yes to only 1 question, a.
 * In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
 * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
 *
 * For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
 */
public class Day6 {

    /**
     * Count the questions which every group had at least one person who said yes to
     * @param lines Group question answer lines
     * @return The number of questions which each group had at least one answer for yes for
     */
    public int solvePartOne(List<String> lines) {
        int totalQuestionsAnsweredYesByAnyoneInGroup = 0;
        //Set to hold the questions that the current group have replied to yes to at least once
        Set<Character> questions = new HashSet<>();
        for(String s : lines) {
            if(s.equals("")) {
                totalQuestionsAnsweredYesByAnyoneInGroup += questions.size();
                questions = new HashSet<>();
            }else{
                for(int i = 0; i < s.length(); i++) {
                    questions.add(s.charAt(i));
                }
            }
        }
        //Handle the final group
        totalQuestionsAnsweredYesByAnyoneInGroup += questions.size();

        return totalQuestionsAnsweredYesByAnyoneInGroup;
    }

    /**
     * Group each set of people together and work out which questions they all answered yes to. Then sum those questions
     * and return the value of that.
     * @param lines Group question answer lines
     * @return Number of questions that the entire group answered yes to
     */
    public int solvePartTwo(List<String> lines) {
        int totalQuestionsAnsweredYesByEveryoneInGroup = 0;
        //Keep track of list of sets with each questions answered by each person
        List<Set<Character>> allQuestionAnswersInGroup = new ArrayList<>();
        for(String s : lines) {
            if(s.equals("")) {
                //Once the group has finished, we work out the set of questionw everyone answered yes to
                Set<Character> allQuestionsAnsweredYesByAll = allQuestionAnswersInGroup.get(0);
                for(int i = 1; i < allQuestionAnswersInGroup.size(); i++) {
                    allQuestionsAnsweredYesByAll.retainAll(allQuestionAnswersInGroup.get(i));
                }
                totalQuestionsAnsweredYesByEveryoneInGroup += allQuestionsAnsweredYesByAll.size();
                allQuestionAnswersInGroup = new ArrayList<>();
            }else{
                Set<Character> newAnswers = new HashSet<>();
                for(int i = 0; i < s.length(); i++) {
                    newAnswers.add(s.charAt(i));
                }
                allQuestionAnswersInGroup.add(newAnswers);
            }
        }

        //Handle the final group
        Set<Character> allQuestionsAnsweredYesByAll = allQuestionAnswersInGroup.get(0);
        for(int i = 1; i < allQuestionAnswersInGroup.size(); i++) {
            allQuestionsAnsweredYesByAll.retainAll(allQuestionAnswersInGroup.get(i));
        }
        totalQuestionsAnsweredYesByEveryoneInGroup += allQuestionsAnsweredYesByAll.size();

        return totalQuestionsAnsweredYesByEveryoneInGroup;
    }

    public static void main(String[] args) {
        Day6 d = new Day6();

        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 6);

        int p1 = d.solvePartOne(lines);
        System.out.println("The number of questions that someone answered yes to: " + p1);
        int p2 = d.solvePartTwo(lines);
        System.out.println("The number of questions that everyone in the group answered yes to: " + p2);
    }

}
