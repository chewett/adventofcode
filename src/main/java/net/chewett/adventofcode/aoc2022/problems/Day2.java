package net.chewett.adventofcode.aoc2022.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 2: Rock Paper Scissors ---
 * The Elves begin to set up camp on the beach. To decide whose tent gets to be closest to the snack storage, a giant
 * Rock Paper Scissors tournament is already in progress.
 *
 * Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round, the players each
 * simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then, a winner for that round is selected:
 * Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose the same shape, the
 * round instead ends in a draw.
 *
 * Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) that they
 * say will be sure to help you win. "The first column is what your opponent is going to play: A for Rock, B for Paper,
 * and C for Scissors. The second column--" Suddenly, the Elf is called away to help with someone's tent.
 *
 * The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
 * Winning every time would be suspicious, so the responses must have been carefully chosen.
 *
 * The winner of the whole tournament is the player with the highest score. Your total score is the sum of your scores
 * for each round. The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3
 * for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
 *
 * Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the score you would get
 * if you were to follow the strategy guide.
 *
 * For example, suppose you were given the following strategy guide:
 *
 * A Y
 * B X
 * C Z
 * This strategy guide predicts and recommends the following:
 *
 * In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win for you
 * with a score of 8 (2 because you chose Paper + 6 because you won).
 * In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss for
 * you with a score of 1 (1 + 0).
 * The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
 * In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).
 *
 * What would your total score be if everything goes exactly according to your strategy guide?
 *
 * --- Part Two ---
 * The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the second column says how the round
 * needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win.
 * Good luck!"
 *
 * The total score is still calculated in the same way, but now you need to figure out what shape to choose so the
 * round ends as indicated. The example above now goes like this:
 *
 * In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y), so you also
 * choose Rock. This gives you a score of 1 + 3 = 4.
 * In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a score
 * of 1 + 0 = 1.
 * In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
 * Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.
 *
 * Following the Elf's instructions for the second column, what would your total score be if everything goes exactly
 * according to your strategy guide?
 */
public class Day2 {

    //Memoization storing the mapping of games to scores
    Map<String, Integer> scoreMap = new HashMap<>();
    //Keep track of the score of each character
    Map<Character, Integer> characterScoreMap = new HashMap<>();
    //Convert from the inputs to the 'R', 'P', and 'S' values
    Map<Character, Character> inputToRPSMap = new HashMap<>();

    public Day2() {
        //Store the score of playing Rock, Paper, and Scissors
        characterScoreMap.put('R', 1);
        characterScoreMap.put('P', 2);
        characterScoreMap.put('S', 3);

        //Store a map converting from the input to 'R', 'P', and 'S'
        inputToRPSMap.put('A', 'R');
        inputToRPSMap.put('B', 'P');
        inputToRPSMap.put('C', 'S');
        inputToRPSMap.put('X', 'R');
        inputToRPSMap.put('Y', 'P');
        inputToRPSMap.put('Z', 'S');
    }

    /**
     * Convert the List of Strings which represent the moves into a char[][] so I can more easily process it
     * and also convert the values to 'R', 'P', 'S'
     * @param moves List of moves to play
     * @return Array of chars representing the moves in an easier to use method
     */
    private char[][] convertMovesToCharChar(List<String> moves) {
        char[][] movesConverted = new char[moves.size()][2];
        int index = 0;
        for(String move : moves) {
            movesConverted[index] = new char[] { inputToRPSMap.get(move.charAt(0)), inputToRPSMap.get(move.charAt(2)) };
            index++;
        }

        return movesConverted;
    }

    /**
     * Given two characters representing the moves this will return the score for the round
     * @param opponentMove The opponents move that was played (either 'R', 'P', or 'S')
     * @param myMove My move that was played (either 'R', 'P', or 'S')
     * @return The score of the move
     */
    private int scoreMove(char opponentMove, char myMove) {
        //Here we create a mapping value and check the precomputed score maps to see if we have already calculated it
        //If we don't then we don't bother to calculate it again and just return it
        String mapIndex = ""+ opponentMove + myMove;
        if(scoreMap.containsKey(mapIndex)) {
            return scoreMap.get(mapIndex);
        }

        //Score for playing the specific type
        int score = this.characterScoreMap.get(myMove);

        //Handle draw
        if(opponentMove == myMove) {
            score += 3;
        }else{
            //Handle winning!
            if(myMove == 'R' && opponentMove == 'S' ||
                myMove == 'P' && opponentMove == 'R' ||
                myMove == 'S' && opponentMove == 'P') {
                score += 6;
            }

            //If we don't meet either conditions, we lost and don't get more score
        }

        //Save this score for quicker runs in the future (memoization)
        scoreMap.put(mapIndex, score);

        return score;
    }

    /**
     * Attempt to play the game using the cheatsheet provided
     * @param moves List of cheatsheet moves to play
     * @return The total score at the end of the games
     */
    public long solvePartOne(List<String> moves) {
        //Convert the moves
        char[][] convertedMoves = this.convertMovesToCharChar(moves);

        //Then score the games!
        int totalScore = 0;
        for(char[] move : convertedMoves) {
            int thisScore = this.scoreMove(move[0], move[1]);
            totalScore += thisScore;
        }
        return totalScore;
    }


    /**
     * Given the cheatsheet, work out what move needs to be played to get the designed outcome and work out the final score
     * @param moves Cheatsheet of outcomes that need to be met
     * @return The totla score at the end of the games
     */
    public long solvePartTwo(List<String> moves) {
        int totalScore = 0;

        //Loop over the moves and work out what moves need to be made, just a hardcoded list really based on
        //their move and the outcome
        for(String move : moves) {
            String[] actualMove = move.split(" ");
            if(actualMove[1].equals("X")) {
                //Lose
                if(actualMove[0].equals("A")) {
                    actualMove[1] = "Z";
                }else if(actualMove[0].equals("B")) {
                    actualMove[1] = "X";
                }else{
                    actualMove[1] = "Y";
                }

            }else if(actualMove[1].equals("Y")) {
                //Draw
                if(actualMove[0].equals("A")) {
                    actualMove[1] = "X";
                }else if(actualMove[0].equals("B")) {
                    actualMove[1] = "Y";
                }else{
                    actualMove[1] = "Z";
                }

            }else{
                //Win
                if(actualMove[0].equals("A")) {
                    actualMove[1] = "Y";
                }else if(actualMove[0].equals("B")) {
                    actualMove[1] = "Z";
                }else{
                    actualMove[1] = "X";
                }

            }

            int thisScore = this.scoreMove(inputToRPSMap.get(actualMove[0].charAt(0)), inputToRPSMap.get(actualMove[1].charAt(0)));
            totalScore += thisScore;
        }
        return totalScore;

    }

    public static void main(String[] args) {
        List<String> moves = ProblemLoader.loadProblemIntoStringArray(2022, 2);

        Day2 d = new Day2();
        long partOne = d.solvePartOne(moves);
        System.out.println("The score after playing with the cheatsheet as I think I need to use it " + partOne);

        long partTwo = d.solvePartTwo(moves);
        System.out.println("The score after playing with the cheatsheet as I was told to use it " + partTwo);


    }

}
