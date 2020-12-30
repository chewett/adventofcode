package net.chewett.adventofcode.aoc2020;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Could be a passport, could be north pole identification, could be a piece of paper!
 */
public class IdentificationPaper {

    private String birthYear;
    private boolean birthYearSet = false;
    private String issueYear;
    private boolean issueYearSet = false;
    private String expiryYear;
    private boolean expiryYearSet = false;
    private String height;
    private boolean heightSet = false;
    private String hairColour;
    private boolean hairColourSet = false;
    private String eyeColour;
    private boolean eyeColourSet = false;
    private String passportId;
    private boolean passportIdSet = false;
    //These aren't used but kept here for completeness
    private String countryId;
    private boolean countryIdSet = false;

    /*

        requiredFields.add("hcl"); // (Hair Color)
        requiredFields.add("ecl"); // (Eye Color)
        requiredFields.add("pid"); // (Passport ID)
        //Not required after we "modify" the system
        // requiredFields.add("cid"); // (Country ID)
     */

    public IdentificationPaper(Map<String, String> data) {
        for(Map.Entry<String, String> entry : data.entrySet()) {
            if(entry.getKey().equals("byr")) {
                this.birthYearSet = true;
                this.birthYear = entry.getValue();
            }else if(entry.getKey().equals("iyr")) {
                this.issueYearSet = true;
                this.issueYear = entry.getValue();
            }else if(entry.getKey().equals("eyr")) {
                this.expiryYearSet = true;
                this.expiryYear = entry.getValue();
            }else if(entry.getKey().equals("hgt")) {
                this.heightSet = true;
                this.height = entry.getValue();
            }else if(entry.getKey().equals("hcl")) {
                this.hairColourSet = true;
                this.hairColour = entry.getValue();
            }else if(entry.getKey().equals("ecl")) {
                this.eyeColourSet = true;
                this.eyeColour = entry.getValue();
            }else if(entry.getKey().equals("pid")) {
                this.passportIdSet = true;
                this.passportId = entry.getValue();
            }else if(entry.getKey().equals("cid")) {
                this.countryIdSet = true;
                this.countryId = entry.getValue();
            }
        }
    }

    /**
     * Returns the set of eye colours that are valid on passport documents
     * @return Set holding all valid eye colour strings
     */
    private Set<String> getValidEyeColours() {
        Set<String> validEyeColours = new HashSet<>();
        validEyeColours.add("amb");
        validEyeColours.add("blu");
        validEyeColours.add("brn");
        validEyeColours.add("gry");
        validEyeColours.add("grn");
        validEyeColours.add("hzl");
        validEyeColours.add("oth");

        return validEyeColours;
    }

    /**
     * Checks to see if the identification paper has all the required information to act as a passport
     * @return Returns true if the paper has all the needed information or false if not
     */
    public boolean passportHaveAllRequiredFields() {
        return this.birthYearSet && this.issueYearSet && this.expiryYearSet &&
                this.heightSet && this.hairColourSet && this.eyeColourSet && this.passportIdSet;
    }

    /**
     * Performs various checks to make sure that the passports are valid as expected.
     * @return False if the passport is not valid and true if it matches all input checks
     */
    public boolean isPassportValid() {
        boolean allFieldsSet = this.passportHaveAllRequiredFields();
        if(!allFieldsSet) {
            return allFieldsSet;
        }

        //Birth year is only valid between 1920 and 2020 inclusive
        int birthYear = Integer.parseInt(this.birthYear);
        if(birthYear < 1920 || birthYear > 2002) {
            return false;
        }

        //Issue date is only valid between 2010 and 2020 inclusive
        int issueYear = Integer.parseInt(this.issueYear);
        if(issueYear < 2010 || issueYear > 2020) {
            return false;
        }

        //Expiration date is valid between 2019 and 2030 inclusive (I wonder why a passport expiring in 2019 be valid in 2020?)
        int expirationDate = Integer.parseInt(this.expiryYear);
        if(expirationDate < 2019 || expirationDate > 2030) {
            return false;
        }

        //Hacky regex to validate the height
        Pattern heightPattern = Pattern.compile("^(1([5678][0-9]|9[0-3])cm|(59|6[0-9]|7[0-6])in)$");
        Matcher heightMatch = heightPattern.matcher(this.height);
        if (!heightMatch.find()) {
            return false;
        }

        //Validate hex code using regex
        Pattern hairColourPattern = Pattern.compile("^#[0-9a-f]{6}$");
        Matcher hairColourMatch = hairColourPattern.matcher(this.hairColour);
        if (!hairColourMatch.find()) {
            return false;
        }

        //Validate passport ID using a regex
        Pattern passportIdPattern = Pattern.compile("^[0-9]{9}$");
        Matcher passportIdMatch = passportIdPattern.matcher(this.passportId);
        if (!passportIdMatch.find()) {
            return false;
        }

        //Eye colours only accept a given set
        Set<String> validEyeColours = this.getValidEyeColours();
        if(!validEyeColours.contains(this.eyeColour)) {
            return false;
        }

        //If all validation passed then lets say it worked
        return true;
    }

}
