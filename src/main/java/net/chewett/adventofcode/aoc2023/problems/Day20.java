package net.chewett.adventofcode.aoc2023.problems;


import net.chewett.adventofcode.aoc2023.*;
import net.chewett.adventofcode.helpers.ProblemLoader;
import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.*;

/**
 * --- Day 20: Pulse Propagation ---
 * With your help, the Elves manage to find the right parts and fix all of the machines. Now, they just need to send
 * the command to boot up the machines and get the sand flowing again.
 *
 * The machines are far apart and wired together with long cables. The cables don't connect to the machines directly,
 * but rather to communication modules attached to the machines that perform various initialization tasks and also
 * act as communication relays.
 *
 * Modules communicate using pulses. Each pulse is either a high pulse or a low pulse. When a module sends a pulse,
 * it sends that type of pulse to each module in its list of destination modules.
 *
 * There are several different types of modules:
 *
 * Flip-flop modules (prefix %) are either on or off; they are initially off. If a flip-flop module receives a high
 * pulse, it is ignored and nothing happens. However, if a flip-flop module receives a low pulse, it flips between on
 * and off. If it was off, it turns on and sends a high pulse. If it was on, it turns off and sends a low pulse.
 *
 * Conjunction modules (prefix &) remember the type of the most recent pulse received from each of their connected
 * input modules; they initially default to remembering a low pulse for each input. When a pulse is received, the
 * conjunction module first updates its memory for that input. Then, if it remembers high pulses for all inputs, it
 * sends a low pulse; otherwise, it sends a high pulse.
 *
 * There is a single broadcast module (named broadcaster). When it receives a pulse, it sends the same pulse to all
 * of its destination modules.
 *
 * Here at Desert Machine Headquarters, there is a module with a single button on it called, aptly, the button module.
 * When you push the button, a single low pulse is sent directly to the broadcaster module.
 *
 * After pushing the button, you must wait until all pulses have been delivered and fully handled before pushing it
 * again. Never push the button if modules are still processing pulses.
 *
 * Pulses are always processed in the order they are sent. So, if a pulse is sent to modules a, b, and c, and then
 * module a processes its pulse and sends more pulses, the pulses sent to modules b and c would have to be handled
 * first.
 *
 * The module configuration (your puzzle input) lists each module. The name of the module is preceded by a symbol
 * identifying its type, if any. The name is then followed by an arrow and a list of its destination modules. For
 * example:
 *
 * broadcaster -> a, b, c
 * %a -> b
 * %b -> c
 * %c -> inv
 * &inv -> a
 * In this module configuration, the broadcaster has three destination modules named a, b, and c. Each of these
 * modules is a flip-flop module (as indicated by the % prefix). a outputs to b which outputs to c which outputs
 * to another module named inv. inv is a conjunction module (as indicated by the & prefix) which, because it has
 * only one input, acts like an inverter (it sends the opposite of the pulse type it receives); it outputs to a.
 *
 * By pushing the button once, the following pulses are sent:
 *
 * button -low-> broadcaster
 * broadcaster -low-> a
 * broadcaster -low-> b
 * broadcaster -low-> c
 * a -high-> b
 * b -high-> c
 * c -high-> inv
 * inv -low-> a
 * a -low-> b
 * b -low-> c
 * c -low-> inv
 * inv -high-> a
 * After this sequence, the flip-flop modules all end up off, so pushing the button again repeats the same sequence.
 *
 * Here's a more interesting example:
 *
 * broadcaster -> a
 * %a -> inv, con
 * &inv -> b
 * %b -> con
 * &con -> output
 * This module configuration includes the broadcaster, two flip-flops (named a and b), a single-input conjunction
 * module (inv), a multi-input conjunction module (con), and an untyped module named output (for testing purposes).
 * The multi-input conjunction module con watches the two flip-flop modules and, if they're both on, sends a low
 * pulse to the output module.
 *
 * Here's what happens if you push the button once:
 *
 * button -low-> broadcaster
 * broadcaster -low-> a
 * a -high-> inv
 * a -high-> con
 * inv -low-> b
 * con -high-> output
 * b -high-> con
 * con -low-> output
 * Both flip-flops turn on and a low pulse is sent to output! However, now that both flip-flops are on and con
 * remembers a high pulse from each of its two inputs, pushing the button a second time does something different:
 *
 * button -low-> broadcaster
 * broadcaster -low-> a
 * a -low-> inv
 * a -low-> con
 * inv -high-> b
 * con -high-> output
 * Flip-flop a turns off! Now, con remembers a low pulse from module a, and so it sends only a high pulse to output.
 *
 * Push the button a third time:
 *
 * button -low-> broadcaster
 * broadcaster -low-> a
 * a -high-> inv
 * a -high-> con
 * inv -low-> b
 * con -low-> output
 * b -low-> con
 * con -high-> output
 * This time, flip-flop a turns on, then flip-flop b turns off. However, before b can turn off, the pulse sent to
 * con is handled first, so it briefly remembers all high pulses for its inputs and sends a low pulse to output.
 * After that, flip-flop b turns off, which causes con to update its state and send a high pulse to output.
 *
 * Finally, with a on and b off, push the button a fourth time:
 *
 * button -low-> broadcaster
 * broadcaster -low-> a
 * a -low-> inv
 * a -low-> con
 * inv -high-> b
 * con -high-> output
 * This completes the cycle: a turns off, causing con to remember only low pulses and restoring all modules to their
 * original states.
 *
 * To get the cables warmed up, the Elves have pushed the button 1000 times. How many pulses got sent as a result
 * (including the pulses sent by the button itself)?
 *
 * In the first example, the same thing happens every time the button is pushed: 8 low pulses and 4 high pulses are
 * sent. So, after pushing the button 1000 times, 8000 low pulses and 4000 high pulses are sent. Multiplying these
 * together gives 32000000.
 *
 * In the second example, after pushing the button 1000 times, 4250 low pulses and 2750 high pulses are sent.
 * Multiplying these together gives 11687500.
 *
 * Consult your module configuration; determine the number of low pulses and high pulses that would be sent after
 * pushing the button 1000 times, waiting for all pulses to be fully handled after each push of the button. What do
 * you get if you multiply the total number of low pulses sent by the total number of high pulses sent?
 *
 * --- Part Two ---
 * The final machine responsible for moving the sand down to Island Island has a module attached named rx.
 * The machine turns on when a single low pulse is sent to rx.
 *
 * Reset all modules to their default states. Waiting for all pulses to be fully handled after each button press,
 * what is the fewest number of button presses required to deliver a single low pulse to the module named rx?
 */
public class Day20 {

    /**
     * Given the input this returns a mapping of the connector names to connector object
     * @param input Puzzle input
     * @return Mapping of connector names to objects
     */
    public Map<String, Day20Connector> parseInput(List<String> input) {
        Map<String, Day20Connector> connectors = new HashMap<>();
        for(String line : input) {
            String[] parts = line.split(" -> ");
            String[] outputs = parts[1].split(", ");
            String name = parts[0];

            if(name.equals("broadcaster")) {
                connectors.put(name, new Day20Broadcaster(name, outputs));
            }else{
                char type = name.charAt(0);
                name = name.substring(1);

                if(type == '%') {
                    connectors.put(name, new Day20FlipFlop(name, outputs));
                }else{
                    connectors.put(name, new Day20ConjunctionModule(name, outputs));
                }
            }
        }

        //Set all the inputs for conjunction modules
        for(Map.Entry<String, Day20Connector> conns : connectors.entrySet()) {
            Day20Connector thisConn = conns.getValue();
            for(String outputString : thisConn.getOutputs()) {
                Day20Connector output = connectors.get(outputString);
                if(output instanceof Day20ConjunctionModule) {
                    ((Day20ConjunctionModule) output).setAsInput(conns.getKey());
                }
            }
        }

        return connectors;
    }

    /**
     * Work out the number of low pulses that would be sent after pressing the button 1000 times
     * @param input List of connectors
     * @return Number of times that low pulses are sent if the button is pressed 1000 times
     */
    public long solvePartOne(List<String> input) {
        Map<String, Day20Connector> connectors = this.parseInput(input);
        Day20Connector start = connectors.get("broadcaster");
        if(start == null) {
            throw new RuntimeException("Start not found");
        }

        long low = 0;
        long high = 0;
        //Simple brute force
        for(int i =0; i < 1000; i++) {
            //Keep track of a queue of pulses sent and handle them one by one
            Queue<String[]> pulsesToSend = new LinkedList<>();
            pulsesToSend.add(new String[]{"button", "broadcaster", "low"});

            while (!pulsesToSend.isEmpty()) {
                String[] pulse = pulsesToSend.poll();
                String sender = pulse[0];
                String reciever = pulse[1];
                String val = pulse[2];

                //We use the "DoNothingConnector" to simplify the logic a little but it does nothing!
                Day20Connector rec = connectors.getOrDefault(reciever, new Day20DoNothingConnector("nop"));
                if (val.equals("high")) {
                    high++;
                    pulsesToSend.addAll(rec.pulseHigh(connectors, sender));
                } else {
                    low++;
                    pulsesToSend.addAll(rec.pulseLow(connectors, sender));
                }
            }

        }

        return high * low;
    }

    /**
     * This is a bit of a painful one as it requires input instrospection to work out the trick!
     *
     * The trick in this case is that to get a "low" to rx you need to have the preceeding conjunction module
     * having all inputs high. It will take a very long time until each one is high normally so we need to find
     * the second trick. Essentially the first time that all the inputs are high are the LCM of the period of each
     * of them.
     *
     * @param input List of connectors
     * @return The number of button presses after which rx receives a low pulse
     */
    public long solvePartTwo(List<String> input) {

        Map<String, Day20Connector> connectors = this.parseInput(input);
        Day20Connector start = connectors.get("broadcaster");
        if(start == null) {
            throw new RuntimeException("Start not found");
        }

        //find what goes to RX out final value
        String toRx = null;
        for(Map.Entry<String, Day20Connector> conns : connectors.entrySet()) {
            String[] outs = conns.getValue().getOutputs();
            for(String out : outs) {
                if(out.equals("rx")) {
                    toRx = conns.getKey();
                }
            }
        }

        Day20Connector toRxModule = connectors.get(toRx);
        if(!(toRxModule instanceof Day20ConjunctionModule)) {
            throw new RuntimeException("Module is unexpectedly not a conjuration");
        }
        Day20ConjunctionModule trickyPairing = (Day20ConjunctionModule)toRxModule;

        //Keep track of the number of times it takes to get to the module
        Map<String, Long> timeToRx = new HashMap<>();
        for(String str : trickyPairing.getInputs()) {
            timeToRx.put(str, -1L);
        }

        //Keep pressing the buttons until we reach a value for each of the four preceeding modules
        long iteration = 0;
        boolean allInputsHaveValue = false;
        while(!allInputsHaveValue) {
            iteration++;
            Queue<String[]> pulsesToSend = new LinkedList<>();
            pulsesToSend.add(new String[]{"button", "broadcaster", "low"});

            while (!pulsesToSend.isEmpty()) {
                String[] pulse = pulsesToSend.poll();
                String sender = pulse[0];
                String reciever = pulse[1];
                String val = pulse[2];

                Day20Connector rec = connectors.getOrDefault(reciever, new Day20DoNothingConnector("nop"));
                if (val.equals("high")) {
                    pulsesToSend.addAll(rec.pulseHigh(connectors, sender));
                } else {
                    pulsesToSend.addAll(rec.pulseLow(connectors, sender));
                }

                //After every pulse is handled we check to see if it has gone high and log it
                for(Map.Entry<String, Boolean> e : trickyPairing.getInputMapping().entrySet()) {
                    if(e.getValue() && timeToRx.get(e.getKey()) == -1) {
                        timeToRx.put(e.getKey(), iteration);
                    }
                }
            }

            //Stop once all the inputs have gone high and we have logged the number of iterations
            allInputsHaveValue = true;
            for(Map.Entry<String, Long> b : timeToRx.entrySet()) {
                if(b.getValue() == -1) {
                    allInputsHaveValue = false;
                }
            }
        }

        //Now just work out the LCM and we are done!
        long curLcm = 1;
        for(Map.Entry<String, Long> b : timeToRx.entrySet()) {
            curLcm = ArithmeticUtils.lcm(curLcm, b.getValue());
        }

        return curLcm;
    }

    public static void main(String[] args) {
        List<String> input = ProblemLoader.loadProblemIntoStringArray(2023, 20);

        Day20 d = new Day20();
        long partOne = d.solvePartOne(input);
        System.out.println("After 1000 cycles the multiplication of low and high pulses is " + partOne);

        long partTwo = d.solvePartTwo(input);
        System.out.println("The first cycle that rx is sent a low pulse is " + partTwo);
    }
}


