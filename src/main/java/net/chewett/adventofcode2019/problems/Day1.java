package net.chewett.adventofcode2019.problems;

import net.chewett.adventofcode2019.spaceship.Spaceship;
import net.chewett.adventofcode2019.spaceship.SpaceshipModule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day1 {

    public static void main(String[] argv) {
        try {
            File file = new File("day_1_input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            Spaceship s = new Spaceship();

            while ((st = br.readLine()) != null) {
                int newModuleMass = Integer.parseInt(st);
                s.addModule(new SpaceshipModule(newModuleMass));
            }

            System.out.println("Total fuel just counting module weight: " + s.getFuelForModules());
            System.out.println("Total fueld needed counting fuel and module weight: " + s.getFuelForModulesCountingFuelMass());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
