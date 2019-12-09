package net.chewett.adventofcode2019.intcode;

public class ParameterMode {

    public static String getModename(int mode) {
        if(mode == 0) {
            return "Loc";
        }else if(mode == 1) {
            return "Val";
        }else if(mode == 2) {
            return "Rel";
        }else{
            throw new RuntimeException("UnsupportedMode - " + mode);
        }
    }
}
