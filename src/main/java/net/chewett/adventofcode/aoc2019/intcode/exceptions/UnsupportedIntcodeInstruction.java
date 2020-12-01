package net.chewett.adventofcode.aoc2019.intcode.exceptions;

/**
 * Simple exception to be thrown when there is an unsupported instruction that is attempted to be ran
 */
public class UnsupportedIntcodeInstruction extends RuntimeException {

    public UnsupportedIntcodeInstruction(long instructionId) {
        super("Cannot find instruction ID: " + instructionId);
    }

}
