package net.chewett.adventofcode.aoc2021;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet class used for 2021 Day 16 to hold details of BITS transmission
 *
 * Processing packets is a recursive process which may create a whole tree of packets
 */
public class Packet {

    /** Original String of the packet */
    private String packetString;

    /** Version of the packet */
    private int version;
    /** Packet type */
    private int type;

    /** Holds the rest of the string that needs to be processed */
    private String restOfString;

    /** Holds the literal value of the packet (if it has one) */
    private long literalValOfPacket;
    /** Holds any child packets (if it has some) */
    private List<Packet> childPackets = new ArrayList<>();

    public Packet(String packetString) {
        this.packetString = packetString;

        //Version and type are simple bits of the packet string
        this.version = Integer.parseInt(this.packetString.substring(0, 3), 2);
        this.type = Integer.parseInt(this.packetString.substring(3, 6), 2);

        //We work out the type, each type does different things
        if(this.type == 4) { //Type 4 is a literal value, This is the "base case" where no recursion happens
            int currentIndex = 6;
            boolean moreToRead = true;
            //We read all the part of my literal
            StringBuilder literalValue = new StringBuilder();
            while(moreToRead) {
                char firstChar = this.packetString.charAt(currentIndex);
                if(firstChar == '0') {
                    moreToRead = false;
                }
                literalValue.append(this.packetString.substring(currentIndex + 1, currentIndex + 5));
                currentIndex += 5;
            }

            //We then convert this to a value and store it
            this.literalValOfPacket = Long.parseLong(literalValue.toString(), 2);
            //We then save the "rest" of the string for processing somewhere else (higher in the recursive chain)
            this.restOfString = this.packetString.substring(currentIndex);
        }else{
            //This is the recursive step!
            int lengthTypeId = this.packetString.charAt(6) == '0' ? 0 : 1;
            if(lengthTypeId == 0) {

                int totalLengthOfSubPackets = Integer.parseInt(this.packetString.substring(7, 22), 2);
                String subPacketsString = this.packetString.substring(22, 22 + totalLengthOfSubPackets);
                //Keep parsing the sub-packets
                while(subPacketsString.length() > 0) {
                    Packet childPacket = new Packet(subPacketsString);
                    //Once parsed, grab the "rest" of the string
                    subPacketsString = childPacket.getRestOfString();
                    this.childPackets.add(childPacket);
                }
                //Once finished save the rest o the string
                this.restOfString = this.packetString.substring(22 + totalLengthOfSubPackets);
            }else{
                int numberOfPackets = Integer.parseInt(this.packetString.substring(7, 18), 2);
                this.restOfString = this.packetString.substring(18);
                while(this.childPackets.size() < numberOfPackets) {
                    Packet childPacket = new Packet(this.restOfString);
                    this.restOfString = childPacket.getRestOfString();
                    this.childPackets.add(childPacket);
                }
            }
        }

    }

    /**
     * Gets the version
     * @return Returns the packet version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Gets the type of the packet
     * @return Returns the packet type as an int
     */
    public int getType() {
        return type;
    }

    /**
     * Returns the rest of the string needed for processing
     * @return Rest of the string not consumed by this packet
     */
    public String getRestOfString() {
        return restOfString;
    }

    /**
     * For packets with a literal value, this returns the literal value
     * @return Literal value of the packet
     */
    public long getLiteralValOfPacket() {
        return literalValOfPacket;
    }

    /**
     * Sum's together the versions of this packet and all children packets. This is a recursive step
     * @return Sum total of all versions in the packet hierarchy
     */
    public int getSumOfAllVersions() {
        int versionTotal = this.version;
        for(Packet p : this.childPackets) {
            versionTotal += p.getSumOfAllVersions();
        }
        return versionTotal;
    }

    /**
     * Gets the value of the packet type, how this is calculated depends on the type of the packet and is a recursive
     * step
     * @return Value of the packet type
     */
    public long getValueOfPacket() {

        if(this.type == 0) { // SUM packet adds all their children together
            long val = 0;
            for(Packet p : this.childPackets) {
                val += p.getValueOfPacket();
            }
            return val;

        }else if(this.type == 1) { // product packets multiple all their children together
            long val = 1;
            for(Packet p : this.childPackets) {
                val *= p.getValueOfPacket();
            }
            return val;

        }else if(this.type == 2) { // Minimum packets find the smallest value of all their child packets
            long minVal = Long.MAX_VALUE;
            for(Packet p : this.childPackets) {
                minVal = Long.min(minVal, p.getValueOfPacket());
            }
            return minVal;

        }else if(this.type == 3) { // Maximum packets find the largest value of all their child packets
            long maxVal = Long.MIN_VALUE;
            for(Packet p : this.childPackets) {
                maxVal = Long.max(maxVal, p.getValueOfPacket());
            }
            return maxVal;

        }else if(this.type == 4) { // Type 4 is just a literal
            return this.literalValOfPacket;

        }else if(this.type == 5) { // Type 5 is greater than
            if(this.childPackets.get(0).getValueOfPacket() > this.childPackets.get(1).getValueOfPacket()) {
                return 1L;
            }else{
                return 0L;
            }

        }else if(this.type == 6) { // Type 6 is less than
            if(this.childPackets.get(0).getValueOfPacket() < this.childPackets.get(1).getValueOfPacket()) {
                return 1L;
            }else{
                return 0L;
            }

        }else if(this.type == 7) { // Type 7 is equal to
            if(this.childPackets.get(0).getValueOfPacket() == this.childPackets.get(1).getValueOfPacket()) {
                return 1L;
            }else{
                return 0L;
            }
        }

        throw new RuntimeException("ERROR BAD PACKET TYPE");
    }
}
