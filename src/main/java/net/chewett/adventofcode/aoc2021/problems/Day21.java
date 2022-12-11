package net.chewett.adventofcode.aoc2021.problems;

import net.chewett.adventofcode.datastructures.Pair;
import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 21: Dirac Dice ---
 * There's not much to do as you slowly descend to the bottom of the ocean. The submarine computer challenges you to a
 * nice game of Dirac Dice.
 *
 * This game consists of a single die, two pawns, and a game board with a circular track containing ten spaces marked 1
 * through 10 clockwise. Each player's starting space is chosen randomly (your puzzle input). Player 1 goes first.
 *
 * Players take turns moving. On each player's turn, the player rolls the die three times and adds up the results.
 * Then, the player moves their pawn that many times forward around the track (that is, moving clockwise on spaces in
 * order of increasing value, wrapping back around to 1 after 10). So, if a player is on space 7 and they roll
 * 2, 2, and 1, they would move forward 5 times, to spaces 8, 9, 10, 1, and finally stopping on 2.
 *
 * After each player moves, they increase their score by the value of the space their pawn stopped on. Players' scores
 * start at 0. So, if the first player starts on space 7 and rolls a total of 5, they would stop on space 2 and add 2
 * to their score (for a total score of 2). The game immediately ends as a win for any player whose score reaches at
 * least 1000.
 *
 * Since the first game is a practice game, the submarine opens a compartment labeled deterministic dice and a 100-sided
 * die falls out. This die always rolls 1 first, then 2, then 3, and so on up to 100, after which it starts over at 1
 * again. Play using this die.
 *
 * For example, given these starting positions:
 *
 * Player 1 starting position: 4
 * Player 2 starting position: 8
 * This is how the game would go:
 *
 * Player 1 rolls 1+2+3 and moves to space 10 for a total score of 10.
 * Player 2 rolls 4+5+6 and moves to space 3 for a total score of 3.
 * Player 1 rolls 7+8+9 and moves to space 4 for a total score of 14.
 * Player 2 rolls 10+11+12 and moves to space 6 for a total score of 9.
 * Player 1 rolls 13+14+15 and moves to space 6 for a total score of 20.
 * Player 2 rolls 16+17+18 and moves to space 7 for a total score of 16.
 * Player 1 rolls 19+20+21 and moves to space 6 for a total score of 26.
 * Player 2 rolls 22+23+24 and moves to space 6 for a total score of 22.
 * ...after many turns...
 *
 * Player 2 rolls 82+83+84 and moves to space 6 for a total score of 742.
 * Player 1 rolls 85+86+87 and moves to space 4 for a total score of 990.
 * Player 2 rolls 88+89+90 and moves to space 3 for a total score of 745.
 * Player 1 rolls 91+92+93 and moves to space 10 for a final score, 1000.
 * Since player 1 has at least 1000 points, player 1 wins and the game ends. At this point, the losing player had 745
 * points and the die had been rolled a total of 993 times; 745 * 993 = 739785.
 *
 * Play a practice game using the deterministic 100-sided die. The moment either player wins, what do you get if you
 * multiply the score of the losing player by the number of times the die was rolled during the game?
 *
 * --- Part Two ---
 * Now that you're warmed up, it's time to play the real game.
 *
 * A second compartment opens, this time labeled Dirac dice. Out of it falls a single three-sided die.
 *
 * As you experiment with the die, you feel a little strange. An informational brochure in the compartment explains
 * that this is a quantum die: when you roll it, the universe splits into multiple copies, one copy for each possible
 * outcome of the die. In this case, rolling the die always splits the universe into three copies: one where the
 * outcome of the roll was 1, one where it was 2, and one where it was 3.
 *
 * The game is played the same as before, although to prevent things from getting too far out of hand, the game now
 * ends when either player's score reaches at least 21.
 *
 * Using the same starting positions as in the example above, player 1 wins in 444356092776315 universes, while player
 * 2 merely wins in 341960390180808 universes.
 *
 * Using your given starting positions, determine every possible outcome. Find the player that wins in more universes;
 * in how many universes does that player win?
 */
public class Day21 {

    /**
     * Play a game of Dirac Dice and multiply the score of the losing player with the number of times the die was rolled
     * @param startP1 Starting Position of player 1
     * @param startP2 Starting Position of player 2
     * @return The losing players score multiplied by the number of times the die was rolled
     */
    public long solvePartOne(int startP1, int startP2) {
        int p1Score = 0;
        int p2Score = 0;

        int currentDiceNo = 1;
        int p1Pos = startP1;
        int p2Pos = startP2;
        int timesThrown = 0;
        while(p1Score <= 1000 || p2Score <= 1000) {

            timesThrown++;
            int p1DiceThrow = currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }
            timesThrown++;
            p1DiceThrow += currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }
            timesThrown++;
            p1DiceThrow += currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }

            //Move the position back in a 1-10 range
            p1Pos += p1DiceThrow;
            while(p1Pos > 10) {
                p1Pos -= 10;
            }

            p1Score += p1Pos;

            if(p1Score >= 1000) {
                break;
            }

            timesThrown++;
            int p2DiceThrow = currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }
            timesThrown++;
            p2DiceThrow += currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }
            timesThrown++;
            p2DiceThrow += currentDiceNo;
            currentDiceNo++;
            //Rollover the die at 101
            if(currentDiceNo == 101) {
                currentDiceNo = 1;
            }

            p2Pos += p2DiceThrow;
            //Move the position back in a 1-10 range
            while(p2Pos > 10) {
                p2Pos -= 10;
            }

            p2Score += p2Pos;

            if(p2Score >= 1000) {
                break;
            }

        }

        int loserScore = p1Score;
        if(p1Score >= 1000) {
            loserScore = p2Score;
        }

        return loserScore * timesThrown;
    }

    /** Memoization for the positions so we can keep track of how we can handle this! */
    private Map<Integer, Map<Integer, Map<Integer, Map<Integer, Pair<Long>>>>> memoization = new HashMap<>();

    /**
     * Given a P1 and P1 position and score return a Pair representing the number of times player 1 and player 2 will win
     * @param p1Position Player 1 position
     * @param p1Score Player 1 score
     * @param p2Position Player 2 position
     * @param p2Score Player 2 score
     * @return A pair representing the number of times player 1 and player 2 have won
     */
    private Pair<Long> seeWhoWins(int p1Position, int p1Score, int p2Position, int p2Score) {
        if(p1Score >= 21) {
            //Base case 1: p1 wins as the score is higher or equal to 21
            return new Pair<Long>(1L, 0L);
        }else if(p2Score >= 21) {
            //Base case 2: p2 wins as the score is higher or equal to 21
            return new Pair<Long>(0L, 1L);
        }else{
            if(memoization.containsKey(p1Position) &&
                memoization.get(p1Position).containsKey(p1Score) &&
                memoization.get(p1Position).get(p1Score).containsKey(p2Position) &&
                memoization.get(p1Position).get(p1Score).get(p2Position).containsKey(p2Score)
            ) {
                //Base case 3: We already have this value stored
                return memoization.get(p1Position).get(p1Score).get(p2Position).get(p2Score);
            }else{
                long p1Wins = 0;
                long p2Wins = 0;
                //Run over all 27 ways of creating a score, we could optimise this by actually doing the maths
                //of how much each one comes up, TODO make a class for this in the future.
                for(int x = 1; x < 4; x++) {
                    for(int y = 1; y < 4; y++) {
                        for(int z = 1; z < 4; z++) {
                            int newPosition = p1Position + x + z + y;
                            while(newPosition > 10) {
                                newPosition -= 10;
                            }
                            int newScore = p1Score + newPosition;

                            //The next turn is p2, so call this with p2 in the first place, this is the recursive step
                            Pair<Long> wins = this.seeWhoWins(p2Position, p2Score, newPosition, newScore);
                            //Since P2 is the first place, the stats will be returned with P2 being the "first" so a adds to p2
                            p2Wins += wins.a;
                            //And b adds to p1
                            p1Wins += wins.b;
                        }
                    }
                }
                Pair<Long> newWinsLosses = new Pair<Long>(p1Wins, p2Wins);

                //Now store this for the future!
                if(!memoization.containsKey(p1Position)) {
                    memoization.put(p1Position, new HashMap<>());
                }
                if(!memoization.get(p1Position).containsKey(p1Score)) {
                    memoization.get(p1Position).put(p1Score, new HashMap<>());
                }
                if(!memoization.get(p1Position).get(p1Score).containsKey(p2Position)) {
                    memoization.get(p1Position).get(p1Score).put(p2Position, new HashMap<>());
                }

                memoization.get(p1Position).get(p1Score).get(p2Position).put(p2Score, newWinsLosses);

                return newWinsLosses;
            }
        }
    }

    /**
     * This time we play the same game, except with the quantum die that splits the universes every time its rolled
     * @param p1Start Starting Position of player 1
     * @param p2Start Starting Position of player 2
     * @return The number of times, the player that wins the most, wins in all universes
     */
    public long solvePartTwo(int p1Start, int p2Start) {
        Pair<Long> wins = this.seeWhoWins(p1Start, 0, p2Start, 0);
        return Math.max(wins.a, wins.b);
    }

    public static void main(String[] args) {
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2021, 21);
        int playerOnePosition = Integer.parseInt(lines.get(0).split(": ")[1]);
        int playerTwoPosition = Integer.parseInt(lines.get(1).split(": ")[1]);

        Day21 d = new Day21();
        System.out.println("The losing players score multiplied by the number of times the die was rolled is "
                + d.solvePartOne(playerOnePosition, playerTwoPosition));
        System.out.println("The number of times, the player that wins the most, wins in all universes is"
                + d.solvePartTwo(playerOnePosition, playerTwoPosition));
    }

}
