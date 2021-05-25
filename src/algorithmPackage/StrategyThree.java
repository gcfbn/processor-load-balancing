package algorithmPackage;

import resultsPackage.Results;
import resultsPackage.ResultsCalculator;
import systemPackage.CPU;
import systemPackage.Process;
import systemPackage.ProcessSender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class StrategyThree implements Algorithm {

    private final static int MAX_LOAD_MIGRATION = 40;
    private final static int MAX_LOAD_DIVIDING_JOB = 15;
    private final static int DIVIDE_JOB_TRIES = 5;

    private int questions = 0;
    private int migrations = 0;

    Random randomGenerator = new Random();

    private final StrategyTwo strategyTwo = new StrategyTwo();

    public Results executeAlgorithm(ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, int maxTime) {

        Queue<Process> queue = new LinkedList<>();

        for (int currentTime = 1; currentTime <= maxTime; currentTime++) {

            ProcessService.service(this, cpus, processes, queue, currentTime);

            // all this shit goes here
            for (CPU cpu : cpus) {

                if (cpu.getLoad() < MAX_LOAD_DIVIDING_JOB) {
                    int tries = 0;
                    boolean foundCPU = false;
                    while (tries < DIVIDE_JOB_TRIES && !foundCPU) {
                        int randomIndex = randomGenerator.nextInt(cpus.size());
                        CPU randomCPU = cpus.get(randomIndex);

                        tries++;
                        questions++;

                        if (randomCPU.getLoad() > MAX_LOAD_MIGRATION) {
                            foundCPU = true;
                            int halfOfLoad = randomCPU.getLoad() / 2;
                            int movedLoad = 0;

                            while (movedLoad < halfOfLoad) {
                                Process movedProcess = randomCPU.getProcesses().get(0);
                                movedProcess.setCurrentCPU(cpu);

                                randomCPU.getProcesses().remove(movedProcess);
                                randomCPU.setLoad(randomCPU.getLoad() - movedProcess.getLoad());

                                cpu.getProcesses().add(movedProcess);
                                cpu.setLoad(cpu.getLoad() + movedProcess.getLoad());

                                movedLoad += movedProcess.getLoad();

                                migrations++;
                            }
                        }
                    }
                }
            }

            for (CPU cpu : cpus) {
                cpu.getLoadsInTime()[currentTime - 1] = cpu.getLoad();
            }
        }

        double[] loads = new double[cpus.size()];

        for (int i = 0; i < cpus.size(); i++) {
            loads[i] = cpus.get(i).getAverageLoad();
        }

        return ResultsCalculator.calculate(loads, questions, migrations);
    }

    public boolean tryToExecuteProcess(Process currentProcess, ArrayList<CPU> cpus, Queue<Process> queue) {

        return strategyTwo.tryToExecuteProcess(currentProcess, cpus, queue);
    }
}
