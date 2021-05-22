import systemPackage.CPU;
import systemPackage.ProcessSender;

import java.util.ArrayList;
import java.util.Random;

public class ProcessGenerator {

    private final static int MIN_FREQUENCY = 5;
    private final static int MAX_FREQUENCY = 10;
    private final static int MAX_LOAD = 5;
    private final static int MIN_DURATION = 1;
    private final static int MAX_DURATION = 4;
    private final static int NUMBER_OF_DIFFERENT_PROCESSES = 40;

    public static ArrayList<ProcessSender> generateData(ArrayList<CPU> cpus) {

        Random randomGenerator = new Random();
        ArrayList<ProcessSender> data = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_DIFFERENT_PROCESSES; i++) {

            int frequency = randomGenerator.nextInt(MAX_FREQUENCY - MIN_FREQUENCY) + MIN_FREQUENCY;
            int load = randomGenerator.nextInt(MAX_LOAD) + 1;
            int duration = randomGenerator.nextInt(MAX_DURATION - MIN_DURATION) + 1;
            CPU chosenCPU = cpus.get(randomGenerator.nextInt(cpus.size()));

            data.add(new ProcessSender(frequency, load, duration, chosenCPU));
        }

        return data;
    }
}
