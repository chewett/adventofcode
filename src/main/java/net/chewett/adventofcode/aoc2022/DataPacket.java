package net.chewett.adventofcode.aoc2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Datapacket class to hold information about data packets
 * Either it will hold a value (an integer) or a list of child packets
 */
public class DataPacket {

    private Integer value = null;
    private List<DataPacket> children = null;

    public DataPacket(String input) {

        //Base case where it's not a list
        if(!input.contains("[")) {
            value = Integer.parseInt(input);
        }else{
            children = new ArrayList<>();

            int nestingLevel = 0;
            //Go from 1 -> length-1 to strip out the brackets

            StringBuilder currentParts = new StringBuilder();
            for(int i = 1; i < input.length() -1; i++) {
                if(input.charAt(i) == ',' && nestingLevel == 0) {
                    //Create the children in this instance
                    children.add(new DataPacket(currentParts.toString()));
                    currentParts.setLength(0);
                }else if(input.charAt(i) == '[') {
                    currentParts.append(input.charAt(i));
                    nestingLevel++;
                }else if(input.charAt(i) == ']') {
                    currentParts.append(input.charAt(i));
                    nestingLevel--;
                }else{
                    currentParts.append(input.charAt(i));
                }
            }

            //create the final child if there is any more data
            if(currentParts.length() > 0) {
                children.add(new DataPacket(currentParts.toString()));
            }
        }
    }

    /**
     * Gets the value of the packet, this may return null
     * @return Value of the packet (may be null)
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Gets the children of the packet, this may be null
     * @return Children of the packet (may be null)
     */
    public List<DataPacket> getChildren() {
        return children;
    }

    /**
     * This is somewhat of a custom comparator to compare whether this packet should be placed before the one passed
     * in, identified here as the "right" one (as in right hand side)
     * @param right Right hand side packet to review
     * @return Return 1 if the ordering is correct, -1 if the ordering is incorrect, and 0 if they are the same
     */
    public int isOrderedVsRight(DataPacket right) {
        //Handle the "base" comparison of two numbers
        if(this.value != null && right.getValue() != null) {
            if(Objects.equals(this.value, right.getValue())) {
                return 0;
            }else{
                if(this.value < right.getValue()) {
                    return 1;
                }else{
                    return -1;
                }
            }

        //handle the somewhat more complex case of comparing the children
        }else if(this.children != null && right.getChildren() != null) {
            //Compare each one until you find a difference
            for(int packetIndex = 0; packetIndex < this.children.size(); packetIndex++) {
                //If you run out of right hand side items before the left, its ordered wrongly!
                if(right.getChildren().size() <= packetIndex) {
                    return -1;
                }
                int comparisonOk = this.children.get(packetIndex).isOrderedVsRight(right.getChildren().get(packetIndex));
                if(comparisonOk != 0) {
                    return comparisonOk;
                }

            }

            if(right.getChildren().size() > children.size()) {
                return 1;
            }

            //If we have looped over all of them and they were all the same... return null
            return 0;

            //This item is a value but the other is a list, convert this to a list and compare
        }else if(this.value != null) {
            DataPacket realValueToCompare = new DataPacket("[" + this.value + "]");
            return realValueToCompare.isOrderedVsRight(right);

            //The "right" item is a value but this is a list, convert right to a list and compare
        }else {
            DataPacket realRightToCompare = new DataPacket("[" + right.getValue() + "]");
            return this.isOrderedVsRight(realRightToCompare);
        }
    }

    /**
     * A bit of a rubbish way as it will add a trailing , but oh well!
     * @return A string roughly representing this
     */
    @Override
    public String toString() {
        if(this.value != null) {
            return ""+ value;
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(DataPacket p : children) {
                sb.append(p.toString()).append(",");
            }
            sb.append("]");
            return sb.toString();

        }
    }
}
