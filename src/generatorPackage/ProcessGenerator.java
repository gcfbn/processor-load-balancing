package generatorPackage;

import systemPackage.CPU;
import systemPackage.ProcessSender;

import java.util.ArrayList;
import java.util.Random;

public class ProcessGenerator {

    private final int minFrequency;
    private final int maxFrequency;
    private final int maxLoad;
    private final int minDuration;
    private final int maxDuration;
    private final int numberOfDifferentProcesses;

    public ProcessGenerator(int minFrequency, int maxFrequency, int maxLoad, int minDuration, int maxDuration, int numberOfDifferentProcesses) {
        this.minFrequency = minFrequency;
        this.maxFrequency = maxFrequency;
        this.maxLoad = maxLoad;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.numberOfDifferentProcesses = numberOfDifferentProcesses;
    }

    public ArrayList<ProcessSender> generateData(ArrayList<CPU> cpus) {

        Random randomGenerator = new Random();
        ArrayList<ProcessSender> data = new ArrayList<>();

        for (int i = 0; i < numberOfDifferentProcesses; i++) {

            int frequency = randomGenerator.nextInt(maxFrequency - minFrequency) + minFrequency;
            int load = randomGenerator.nextInt(maxLoad) + 1;
            int duration = randomGenerator.nextInt(maxDuration - minDuration) + 1;
            CPU chosenCPU = cpus.get(randomGenerator.nextInt(cpus.size()));

            data.add(new ProcessSender(frequency, load, duration, chosenCPU));
        }

        return data;
    }
}
