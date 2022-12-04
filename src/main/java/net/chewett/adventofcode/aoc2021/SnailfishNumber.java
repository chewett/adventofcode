package net.chewett.adventofcode.aoc2021;

/**
 * Snailfish Number representation
 */
public class SnailfishNumber {

    private SnailfishVal leftNumber = null;
    private SnailfishVal rightNumber = null;
    private SnailfishNumber leftSnail = null;
    private SnailfishNumber rightSnail = null;
    private int level = 0;
    private SnailfishNumber parent = null;

    /**
     * Constructor to create the Snailfish at the top level
     * @param str Snailfish string to parse
     */
    public SnailfishNumber(String str) {
        this(str, null);
    }

    /**
     * Constructor to create the Snailfish at any level (where parent might be null or a higher level)
     * @param str String representing the Snailfish
     * @param parent Parent of the Snailfish
     */
    public SnailfishNumber(String str, SnailfishNumber parent) {
        this.parent = parent;
        //If there is a parent, the level must be one lower than the parent
        if(this.parent != null) {
            this.level = this.parent.getLevel() + 1;
        }
        String actualNumberParts = str.substring(1, str.length() - 1);

        int nestedLevel = 0;
        int commaPosition = 0;
        int charIndex = 0;
        //Loop over to find the split of the number where there is no nesting
        while(commaPosition == 0) {
            char charAtPos = actualNumberParts.charAt(charIndex);
            //This is the root split where there is no nesting and we have found a comma, this is where we split the number
            if(charAtPos == ',' && nestedLevel == 0) {
                commaPosition = charIndex;

            //nesting point, so the next comma won't be the middle
            }else if(charAtPos == '[') {
                nestedLevel++;

            //un-nesting point, we might be getting closer to the comma!
            }else if(charAtPos == ']') {
                nestedLevel--;
            }

            charIndex++;
        }

        //Split the Snailfish number into parts
        String leftHandSide = actualNumberParts.substring(0, commaPosition);
        //The right hand side is all of the rest (which may include more snailfish numbers)
        String rightHandSide = actualNumberParts.substring(commaPosition+1);

        //Recursive steps here both times to handle the Snail-fishy-ness, SnailfishVal is our base case
        if(leftHandSide.contains("[")) {
            this.leftSnail = new SnailfishNumber(leftHandSide, this);
        }else{
            this.leftNumber = new SnailfishVal(Integer.parseInt(leftHandSide));
        }

        //The same recursive step as above just for the right side this time
        if(rightHandSide.contains("[")) {
            this.rightSnail = new SnailfishNumber(rightHandSide, this);
        }else{
            this.rightNumber = new SnailfishVal(Integer.parseInt(rightHandSide));
        }
    }

    /**
     * Sets up the datastructure to handle the left/right values
     * @param currentLeftValue Pass in the current left value so that it can be set across the datastructure
     * @return Returns the snailfish number on the left most of this equation
     */
    public SnailfishVal traverseListFindingLeftValues(SnailfishVal currentLeftValue) {
        if(this.leftNumber != null) {
            this.leftNumber.setLeftVal(currentLeftValue);
            if(currentLeftValue != null) {
                currentLeftValue.setRightVal(this.leftNumber);
            }
            currentLeftValue = this.leftNumber;
        }else{
            currentLeftValue = this.leftSnail.traverseListFindingLeftValues(currentLeftValue);
        }

        if(this.rightNumber != null) {
            this.rightNumber.setLeftVal(currentLeftValue);
            if(currentLeftValue != null) {
                currentLeftValue.setRightVal(this.rightNumber);
            }
            currentLeftValue = this.rightNumber;
        }else{
            currentLeftValue = this.rightSnail.traverseListFindingLeftValues(currentLeftValue);
        }
        return currentLeftValue;
    }

    public SnailfishVal getLeftNumber() {
        return leftNumber;
    }

    public SnailfishVal getRightNumber() {
        return rightNumber;
    }

    public SnailfishNumber getParent() {
        return parent;
    }

    public SnailfishNumber getLeftSnail() {
        return leftSnail;
    }

    public SnailfishNumber getRightSnail() {
        return rightSnail;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        String leftSide, rightSide;
        if(this.leftNumber != null) {
            leftSide = String.valueOf(this.leftNumber.getVal());
        }else{
            leftSide = this.leftSnail.toString();
        }
        if(this.rightNumber != null) {
            rightSide = String.valueOf(this.rightNumber.getVal());
        }else{
            rightSide = this.rightSnail.toString();
        }

        return "[" + leftSide + "," + rightSide + "]";
    }

    /**
     * Perform the explosion checking code, if its found then we start exploding it and handling that
     * @return Boolean representing whether an explosion was found or not
     */
    public boolean checkForExplosions() {
        boolean foundExplosion = false;
        if(this.leftSnail != null) {
            //Any time the left snail has a level greater than 3 we need to explode it
            if(this.leftSnail.getLevel() > 3) {
                foundExplosion = true;
                SnailfishVal newLeft = new SnailfishVal(0);
                SnailfishVal leftExploding = this.leftSnail.getLeftNumber();
                SnailfishVal rightExploding = this.leftSnail.getRightNumber();
                newLeft.setLeftVal(leftExploding.getLeftVal());
                if (leftExploding.getLeftVal() != null) {
                    leftExploding.getLeftVal().setRightVal(newLeft);
                    leftExploding.getLeftVal().setVal(leftExploding.getVal() + leftExploding.getLeftVal().getVal());
                }
                newLeft.setRightVal(rightExploding.getRightVal());
                if (rightExploding.getRightVal() != null) {
                    rightExploding.getRightVal().setLeftVal(newLeft);
                    rightExploding.getRightVal().setVal(rightExploding.getVal() + rightExploding.getRightVal().getVal());
                }

                this.leftSnail = null;
                this.leftNumber = newLeft;
            }else{
                foundExplosion = this.leftSnail.checkForExplosions();
            }
        }
        //Similar explosion method for the right side, we don't explode twice so we can review the entire string
        //So we will explode again later if the right side needs to explode and the left has just exploded
        if(this.rightSnail != null && !foundExplosion) {
            if(this.rightSnail.getLevel() > 3) {

                foundExplosion = true;

                SnailfishVal newRight = new SnailfishVal(0);
                SnailfishVal leftExploding = this.rightSnail.getLeftNumber();
                SnailfishVal rightExploding = this.rightSnail.getRightNumber();
                newRight.setLeftVal(leftExploding.getLeftVal());
                if (leftExploding.getLeftVal() != null) {
                    leftExploding.getLeftVal().setRightVal(newRight);
                    leftExploding.getLeftVal().setVal(leftExploding.getVal() + leftExploding.getLeftVal().getVal());
                }
                newRight.setRightVal(rightExploding.getRightVal());
                if (rightExploding.getRightVal() != null) {
                    rightExploding.getRightVal().setLeftVal(newRight);
                    rightExploding.getRightVal().setVal(rightExploding.getVal() + rightExploding.getRightVal().getVal());
                }

                this.rightSnail = null;
                this.rightNumber = newRight;
            }else{
                foundExplosion = foundExplosion || this.rightSnail.checkForExplosions();
            }
        }

        return foundExplosion;
    }

    /**
     * Performs the split checking code and returns true if we found a split
     * @return True if we found and performed a split
     */
    public boolean checkForSplits() {
        boolean foundSplit = false;

        if(this.leftNumber != null) {
            if(this.leftNumber.getVal() > 9) {
                foundSplit = true;
                double halfLeftFloat = this.leftNumber.getVal() / 2.0;
                SnailfishNumber newNumber = new SnailfishNumber("[" + (int)Math.floor(halfLeftFloat) + "," + (int)Math.ceil(halfLeftFloat) + "]", this);
                if(this.leftNumber.getLeftVal() != null) {
                    newNumber.getLeftNumber().setLeftVal(this.leftNumber.getLeftVal());
                    this.leftNumber.getLeftVal().setRightVal(newNumber.getLeftNumber());
                }

                newNumber.getLeftNumber().setRightVal(newNumber.getRightNumber());
                newNumber.getRightNumber().setLeftVal(newNumber.getLeftNumber());

                if(this.leftNumber.getRightVal() != null) {
                    newNumber.getRightNumber().setRightVal(this.leftNumber.getRightVal());
                    this.leftNumber.getRightVal().setLeftVal(newNumber.getRightNumber());
                }

                this.leftNumber = null;
                this.leftSnail = newNumber;
            }
        }else{
            foundSplit = this.leftSnail.checkForSplits();
        }

        if(foundSplit) {
            //We have handled a single split so return true and we will try again to preserve split ordering
            return true;
        }

        if(this.rightNumber != null) {
            if(this.rightNumber.getVal() > 9) {
                foundSplit = true;
                double halfRightFloat = this.rightNumber.getVal() / 2.0;
                SnailfishNumber newNumber = new SnailfishNumber("[" + (int)Math.floor(halfRightFloat) + "," + (int)Math.ceil(halfRightFloat) + "]", this);
                if(this.rightNumber.getLeftVal() != null) {
                    newNumber.getLeftNumber().setLeftVal(this.rightNumber.getLeftVal());
                    this.rightNumber.getLeftVal().setRightVal(newNumber.getLeftNumber());
                }

                newNumber.getLeftNumber().setRightVal(newNumber.getRightNumber());
                newNumber.getRightNumber().setLeftVal(newNumber.getLeftNumber());

                if(this.rightNumber.getRightVal() != null) {
                    newNumber.getRightNumber().setRightVal(this.rightNumber.getRightVal());
                    this.rightNumber.getRightVal().setLeftVal(newNumber.getRightNumber());
                }

                this.rightNumber = null;
                this.rightSnail = newNumber;
            }
        }else{
            foundSplit = this.rightSnail.checkForSplits();
        }

        return foundSplit;
    }

    /**
     * Adding two snailfish numbers just puts them together in brackets so we cheat somewhat and just create a new
     * string and then create a new Snailfish number
     * @param newNumber Second number to add to this one
     * @return
     */
    public SnailfishNumber add(SnailfishNumber newNumber) {
        return new SnailfishNumber("[" + this.toString() + "," + newNumber.toString() + "]");
    }

    /**
     * The magnitude is obtained by multiplying the left value by three and the right by two
     * @return Magnitude of the Snailfish Number
     */
    public int getMagnitude() {
        int leftVal;
        int rightVal;
        if(this.leftNumber != null) {
            leftVal = this.leftNumber.getVal();
        }else{
            leftVal = this.leftSnail.getMagnitude();
        }
        if(this.rightNumber != null) {
            rightVal = this.rightNumber.getVal();
        }else{
            rightVal = this.rightSnail.getMagnitude();
        }

        return (leftVal * 3) + (rightVal * 2);
    }

}
