package net.chewett.adventofcode.aoc2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reaction {

    private String reactionName;
    private ReactionChemicalValue produces;
    private List<ReactionChemicalValue> requirements = new ArrayList<>();

    public Reaction(String s) {
        this.reactionName = s;
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

    public List<ReactionChemicalValue> getRequirements() {
        return requirements;
    }

    public boolean canReactionBeRun(Map<ReactionChemical, Long> chemicalPool) {
        return this.canReactionBeRun(chemicalPool, 1);
    }

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

    public ReactionChemical findMissingChemical(Map<ReactionChemical, Long> chemicalPool) {
        return this.findMissingChemical(chemicalPool, 1);
    }

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

    public void runReaction(Map<ReactionChemical, Long> chemicalPool) {
        this.runReaction(chemicalPool, 1);
    }

    public void runReaction(Map<ReactionChemical, Long> chemicalPool, int numberOfTimes) {
        System.out.println(this.reactionName);

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
