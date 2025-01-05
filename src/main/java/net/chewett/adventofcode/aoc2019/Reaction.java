package net.chewett.adventofcode.aoc2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data holder holding the details of reactions that occur between reaction elements for 2019 Day 14
 */
public class Reaction {

    //Track what it produces and what it requires
    private ReactionChemicalValue produces;
    private List<ReactionChemicalValue> requirements = new ArrayList<>();

    public Reaction(String s) {
        String[] reactionParts = s.split("=>");
        String requirements = reactionParts[0];
        String produces = reactionParts[1];

        this.produces = new ReactionChemicalValue(produces);
        for(String requirementParts : requirements.split(",")) {
            this.requirements.add(new ReactionChemicalValue(requirementParts));
        }
    }

    public ReactionChemicalValue getProduces() {
        return produces;
    }

    /**
     * Works out if there is enough reaction chemicals in the pool to run this once
     * @param chemicalPool Pool of chemicals
     * @return True if we can run this operation
     */
    public boolean canReactionBeRun(Map<ReactionChemical, Long> chemicalPool) {
        return this.canReactionBeRun(chemicalPool, 1);
    }

    /**
     * Works out if there is enough reaction chemicals in the pool to run this once
     * @param chemicalPool Pool of chemicals
     * @param timesToRun The number of times to run the reaction
     * @return True if we can run this operation
     */
    public boolean canReactionBeRun(Map<ReactionChemical, Long> chemicalPool, int timesToRun) {
        for(ReactionChemicalValue rcv : this.requirements) {
            if(chemicalPool.containsKey(rcv.getReactionChemical())) {
                if(chemicalPool.get(rcv.getReactionChemical()) < (rcv.getValue() * timesToRun)) {
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * Works out the first missing chemical that means we cannot run the reaction
     * @param chemicalPool Pool of chemicals
     * @return The missing chemical causing this reaction to be unfulfilled
     */
    public ReactionChemical findMissingChemical(Map<ReactionChemical, Long> chemicalPool) {
        return this.findMissingChemical(chemicalPool, 1);
    }

    /**
     * Works out the first missing chemical that means we cannot run the reaction
     * @param chemicalPool Pool of chemicals
     * @param timesToRun The number of times to run the reaction
     * @return The missing chemical causing this reaction to be unfulfilled
     */
    public ReactionChemical findMissingChemical(Map<ReactionChemical, Long> chemicalPool, int timesToRun) {
        for(ReactionChemicalValue rcv : this.requirements) {
            if(!chemicalPool.containsKey(rcv.getReactionChemical())) {
                return rcv.getReactionChemical();
            }
            if(chemicalPool.get(rcv.getReactionChemical()) < (rcv.getValue() * timesToRun)) {
                return rcv.getReactionChemical();
            }
        }
        return null;
    }

    /**
     * Runs the reaction by generating the output chemical and consuming the input chemicals
     * @param chemicalPool Pool of chemicals
     */
    public void runReaction(Map<ReactionChemical, Long> chemicalPool) {
        this.runReaction(chemicalPool, 1);
    }

    /**
     * Runs the reaction by generating the output chemical and consuming the input chemicals
     * @param chemicalPool Pool of chemicals
     * @param numberOfTimes The number of times to run the reaction
     */
    public void runReaction(Map<ReactionChemical, Long> chemicalPool, int numberOfTimes) {
        for(ReactionChemicalValue rcv : this.requirements) {
            chemicalPool.put(
                rcv.getReactionChemical(),
                chemicalPool.get(rcv.getReactionChemical()) - (rcv.getValue() * numberOfTimes)
            );
        }

        long currentLevel = 0;
        if(chemicalPool.containsKey(this.getProduces().getReactionChemical())) {
            currentLevel = chemicalPool.get(this.getProduces().getReactionChemical());
        }

        chemicalPool.put(
            this.getProduces().getReactionChemical(),
            currentLevel + (this.produces.getValue() * numberOfTimes)
        );
    }
}
