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

    private final int parameterP;
    private final int parameterR;
    private final int maxTries;

    private int questions = 0;
    private int migrations = 0;

    Random randomGenerator = new Random();

    private final StrategyTwo strategyTwo;

    public StrategyThree(int parameterP, int parameterR, int maxTries) {
        this.parameterP = parameterP;
        this.parameterR = parameterR;
        this.maxTries = maxTries;
        this.strategyTwo = new StrategyTwo(parameterP);
    }

    public Results executeAlgorithm(ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, int maxTime) {

        Queue<Process> queue = new LinkedList<>();

        for (int currentTime = 1; currentTime <= maxTime; currentTime++) {

            ProcessService.service(this, cpus, processes, queue, currentTime);

            // all this shit goes here
            for (CPU cpu : cpus) {

                if (cpu.getLoad() < parameterR) {
                    int tries = 0;
                    boolean foundCPU = false;
                    while (tries < maxTries && !foundCPU) {
                        int randomIndex = randomGenerator.nextInt(cpus.size());
                        CPU randomCPU = cpus.get(randomIndex);

                        tries++;
                        questions++;

                        if (randomCPU.getLoad() > parameterP) {
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
