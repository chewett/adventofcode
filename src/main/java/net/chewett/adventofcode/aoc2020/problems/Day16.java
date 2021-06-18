package net.chewett.adventofcode.aoc2020.problems;

import net.chewett.adventofcode.helpers.ProblemLoader;

import java.util.*;

/**
 * --- Day 16: Ticket Translation ---
 * As you're walking to yet another connecting flight, you realize that one of the legs of your re-routed trip coming up
 * is on a high-speed train. However, the train ticket you were given is in a language you don't understand. You should
 * probably figure out what it says before you get to the train station after the next flight.
 *
 * Unfortunately, you can't actually read the words on the ticket. You can, however, read the numbers, and so you figure
 * out the fields these tickets must have and the valid ranges for values in those fields.
 *
 * You collect the rules for ticket fields, the numbers on your ticket, and the numbers on other nearby tickets for the
 * same train service (via the airport security cameras) together into a single document you can reference (your puzzle
 * input).
 *
 * The rules for ticket fields specify a list of fields that exist somewhere on the ticket and the valid ranges of
 * values for each field. For example, a rule like class: 1-3 or 5-7 means that one of the fields in every ticket is
 * named class and can be any value in the ranges 1-3 or 5-7 (inclusive, such that 3 and 5 are both valid in this field,
 * but 4 is not).
 *
 * Each ticket is represented by a single line of comma-separated values. The values are the numbers on the ticket in
 * the order they appear; every ticket has the same format. For example, consider this ticket:
 *
 * .--------------------------------------------------------.
 * | ????: 101    ?????: 102   ??????????: 103     ???: 104 |
 * |                                                        |
 * | ??: 301  ??: 302             ???????: 303      ??????? |
 * | ??: 401  ??: 402           ???? ????: 403    ????????? |
 * '--------------------------------------------------------'
 * Here, ? represents text in a language you don't understand. This ticket might be represented as
 * 101,102,103,104,301,302,303,401,402,403; of course, the actual train tickets you're looking at are much more
 * complicated. In any case, you've extracted just the numbers in such a way that the first number is always the same
 * specific field, the second number is always a different specific field, and so on - you just don't know what each
 * position actually means!
 *
 * Start by determining which tickets are completely invalid; these are tickets that contain values which aren't valid
 * for any field. Ignore your ticket for now.
 *
 * For example, suppose you have the following notes:
 *
 * class: 1-3 or 5-7
 * row: 6-11 or 33-44
 * seat: 13-40 or 45-50
 *
 * your ticket:
 * 7,1,14
 *
 * nearby tickets:
 * 7,3,47
 * 40,4,50
 * 55,2,20
 * 38,6,12
 * It doesn't matter which position corresponds to which field; you can identify invalid nearby tickets by considering
 * only whether tickets contain values that are not valid for any field. In this example, the values on the first nearby
 * ticket are all valid for at least one field. This is not true of the other three nearby tickets: the values 4, 55,
 * and 12 are are not valid for any field. Adding together all of the invalid values produces your ticket scanning error
 * rate: 4 + 55 + 12 = 71.
 *
 * Consider the validity of the nearby tickets you scanned. What is your ticket scanning error rate?
 *
 * --- Part Two ---
 * Now that you've identified which tickets contain invalid values, discard those tickets entirely. Use the remaining
 * valid tickets to determine which field is which.
 *
 * Using the valid ranges for each field, determine what order the fields appear on the tickets. The order is consistent
 * between all tickets: if seat is the third field, it is the third field on every ticket, including your ticket.
 *
 * For example, suppose you have the following notes:
 *
 * class: 0-1 or 4-19
 * row: 0-5 or 8-19
 * seat: 0-13 or 16-19
 *
 * your ticket:
 * 11,12,13
 *
 * nearby tickets:
 * 3,9,18
 * 15,1,5
 * 5,14,9
 * Based on the nearby tickets in the above example, the first position must be row, the second position must be class,
 * and the third position must be seat; you can conclude that in your ticket, class is 12, row is 11, and seat is 13.
 *
 * Once you work out which field is which, look for the six fields on your ticket that start with the word departure.
 * What do you get if you multiply those six values together?
 */
public class Day16 {

    /**
     * Parse out the field information from the initial input
     * @param lines The lines of the initial input
     * @return A map representing the field name, and the various numbers repreesnting what is valid
     */
    private Map<String, int[]> getFieldInformation(List<String> lines) {
        int lineNo = 0;
        Map<String, int[]> fieldInfo = new HashMap<>();
        while(!lines.get(lineNo).equals("")) {
            String[] sections = lines.get(lineNo).split(": ");
            String fieldName = sections[0];
            String[] validParts = sections[1].split(" or ");

            int[] validNumbers = new int[4];
            validNumbers[0] = Integer.parseInt(validParts[0].split("-")[0]);
            validNumbers[1] = Integer.parseInt(validParts[0].split("-")[1]);
            validNumbers[2] = Integer.parseInt(validParts[1].split("-")[0]);
            validNumbers[3] = Integer.parseInt(validParts[1].split("-")[1]);

            fieldInfo.put(fieldName, validNumbers);

            lineNo++;
        }
        return fieldInfo;
    }

    /**
     * Parse out my ticket numbers from the input strings
     * @param lines The lines of the initial input
     * @return The list of numbers on my ticket that I don't know how to correlate
     */
    private List<Integer> parseMyTicketNumbers(List<String> lines) {
        int lineNo = 0;
        //Get past the rules
        while(!lines.get(lineNo).equals("")) {
            lineNo++;
        }

        //Move onto my ticket line and start processing it
        lineNo += 2;

        List<Integer> myTicket = new ArrayList<>();
        String[] myTicketString = lines.get(lineNo).split(",");
        for(String ticketPart : myTicketString) {
            myTicket.add(Integer.parseInt(ticketPart));
        }
        return myTicket;
    }

    private List<List<Integer>> parseOtherTickets(List<String> lines) {
        int lineNo = 0;
        //Get past the rules
        while(!lines.get(lineNo).equals("")) {
            lineNo++;
        }
        lineNo += 2;
        //Get past my ticket
        while(!lines.get(lineNo).equals("")) {
            lineNo++;
        }

        //Move onto the other tickets and start processing them
        lineNo += 2;
        List<List<Integer>> allTickets = new ArrayList<>();
        while(lineNo < lines.size()) {
            String[] ticketParts = lines.get(lineNo).split(",");
            List<Integer> newTicket = new ArrayList<>();
            for(String newTicketPart : ticketParts) {
                newTicket.add(Integer.parseInt(newTicketPart));
            }
            allTickets.add(newTicket);

            lineNo++;
        }
        return allTickets;
    }

    /**
     * Given the ticket rules, my ticket, and other ticket information, work out the error rate for scanning the tickets
     *
     * This is done by identifying tickets which have values that are not valid for any of the fields
     *
     * @param lines The given rules, my ticket, and other peoples tickets
     * @return The error rate for scanning tickets (adding all the invalid numbers together)
     */
    public long solvePartOne(List<String> lines) {
        Map<String, int[]> validFields = this.getFieldInformation(lines);
        List<List<Integer>> allTickets = this.parseOtherTickets(lines);

        long invalidFields = 0;
        for(List<Integer> ticketToCheck : allTickets) {
            for(Integer ticketFieldVal : ticketToCheck) {
                boolean foundValidField = false;
                //Loop over every field until we find one that works with this value, if none are found, mark it invalid
                for(Map.Entry<String, int[]> field : validFields.entrySet()) {
                    int[] validData = field.getValue();
                    if(ticketFieldVal <= validData[1] && ticketFieldVal >= validData[0]) {
                        foundValidField = true;
                        break;
                    }

                    if(ticketFieldVal <= validData[3] && ticketFieldVal >= validData[2]) {
                        foundValidField = true;
                        break;
                    }
                }

                //No valid fields were found so we add this to the invalid total to return later
                if(!foundValidField) {
                    invalidFields += ticketFieldVal;
                }
            }
        }

        return invalidFields;
    }

    /**
     * Now we have the harder job of trying to track down which field belongs to which position. Here we have a set
     * of rules, my tickets numbers, and the numbers on all the other tickets.
     *
     * This will iteratively check each field and try and match where every ticket is valid
     * @param lines The given rules, my ticket, and other peoples tickets
     * @return All departure fields on my ticket multiplied together
     */
    public long solvePartTwo(List<String> lines) {
        Map<String, int[]> fieldRules = this.getFieldInformation(lines);
        List<Integer> myTicket = this.parseMyTicketNumbers(lines);
        List<List<Integer>> allTickets = this.parseOtherTickets(lines);

        //First remove all the known invalid tickets so we are just left with the valid ones
        List<List<Integer>> validTicketsOnly = new ArrayList<>();
        for(List<Integer> ticketToCheck : allTickets) {
            boolean foundInvalidFieldInTicket = false;

            for(Integer ticketFieldVal : ticketToCheck) {
                boolean foundValidField = false;
                for(Map.Entry<String, int[]> field : fieldRules.entrySet()) {
                    int[] validData = field.getValue();
                    if(ticketFieldVal <= validData[1] && ticketFieldVal >= validData[0]) {
                        foundValidField = true;
                        break;
                    }

                    if(ticketFieldVal <= validData[3] && ticketFieldVal >= validData[2]) {
                        foundValidField = true;
                        break;
                    }
                }

                if(!foundValidField) {
                    foundInvalidFieldInTicket = true;
                    break;
                }
            }

            if(!foundInvalidFieldInTicket) {
                validTicketsOnly.add(ticketToCheck);
            }
        }

        //Store a mapping of Field index to possible valid field names for this given index
        List<Set<String>> fieldsPossiblyValid = new ArrayList<>();
        //Now we are going to loop over valid ticket and try and work out which fields are valid for which position
        for(List<Integer> validTicket : validTicketsOnly) {
            //Loop over every field ID in the given ticket and try and see what rules are valid
            for(int fieldIndex = 0; fieldIndex < validTicket.size(); fieldIndex++) {
                //Keep track of what fields are valid
                Set<String> validFieldsForThisIndex = new HashSet<>();
                int ticketFieldVal = validTicket.get(fieldIndex);

                //Loop over every field and see if its valid for this field index
                for(Map.Entry<String, int[]> field : fieldRules.entrySet()) {
                    boolean fieldIsValidForPosition = false;
                    int[] validData = field.getValue();
                    if(ticketFieldVal <= validData[1] && ticketFieldVal >= validData[0]) {
                        fieldIsValidForPosition = true;
                    }

                    if(ticketFieldVal <= validData[3] && ticketFieldVal >= validData[2]) {
                        fieldIsValidForPosition = true;
                    }

                    //If the field is valid for this index, then we add this field name to the list of valid fields
                    if(fieldIsValidForPosition) {
                        validFieldsForThisIndex.add(field.getKey());
                    }
                }

                //Simple init check, the first time around this won't have all the fields so we just add in
                if(fieldIndex >= fieldsPossiblyValid.size()) {
                    fieldsPossiblyValid.add(fieldIndex, validFieldsForThisIndex);
                }else{
                    //Once we have some known good fields, we keep filtering in down using retainALl to ensure that only
                    //field indexes which match every ticket field are valid
                    fieldsPossiblyValid.get(fieldIndex).retainAll(validFieldsForThisIndex);
                }
            }
        }

        //Right, now we know which index supports which of the field names, we can slowly remove them one by one
        //This works on the logic that one field index will only support a single field
        //Then we can remove that field from everything, and hopefully find a new field index that also now only supports
        //a single field.
        //We keepo doing this iteratively until the field that every field index supports is known
        boolean validFieldsChanged = true;
        List<Integer> fieldsProcessed = new ArrayList<>();
        while(validFieldsChanged) {
            validFieldsChanged = false;

            //Go over every field index we haven't yet processed
            for(int validSetIndex = 0; validSetIndex < fieldsPossiblyValid.size(); validSetIndex++) {
                if(!fieldsProcessed.contains(validSetIndex)) {
                    Set<String> fieldsValidSet = fieldsPossiblyValid.get(validSetIndex);
                    //If we find this only supports a single field then we have found it
                    if(fieldsValidSet.size() == 1) {
                        String fieldFound = "";
                        //Super hack to get the string out of the Set
                        for(String st : fieldsValidSet) {
                            fieldFound = st;
                        }

                        //Then remove this found field from all of the other field indexes
                        for(int validSetRemovalIndex = 0; validSetRemovalIndex < fieldsPossiblyValid.size(); validSetRemovalIndex++) {
                            if(validSetRemovalIndex != validSetIndex) {
                                Set<String> setToRemove = fieldsPossiblyValid.get(validSetRemovalIndex);
                                setToRemove.remove(fieldFound);
                            }
                        }

                        //Process the fact that we have found an valid index and then mark the loop variable so we try once more
                        fieldsProcessed.add(validSetIndex);
                        validFieldsChanged = true;
                    }
                }
            }
        }

        //Now we know the mappins we can do the maths to multiply the "departure" fields on my ticket together
        List<Integer> fieldNumbersToMultiply = new ArrayList<>();
        for(int fieldsToCheck = 0; fieldsToCheck < fieldsPossiblyValid.size(); fieldsToCheck++) {
            Set<String> s = fieldsPossiblyValid.get(fieldsToCheck);
            String st = "";
            for(String str : s) {
                st = str;
            }

            if(st.contains("departure")) {
                fieldNumbersToMultiply.add(fieldsToCheck);
            }
        }

        long total = 1;
        for(int i : fieldNumbersToMultiply) {
            total *= myTicket.get(i);
        }

        return total;
    }

    public static void main(String[] args) {
        Day16 d = new Day16();
        List<String> lines = ProblemLoader.loadProblemIntoStringArray(2020, 16);
        long p1 = d.solvePartOne(lines);
        System.out.println("The error rate for scanning tickets is " + p1);
        long p2 = d.solvePartTwo(lines);
        System.out.println("The value after multiplying all 'departure' fields on my ticket " + p2);
    }

}
