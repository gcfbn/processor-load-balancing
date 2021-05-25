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

public class StrategyTwo implements Algorithm {

    private final static int MAX_LOAD_MIGRATION = 40;
    private int questions = 0;
    private int migrations = 0;

    private final Random randomGenerator = new Random();

    @Override
    public Results executeAlgorithm(ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, int maxTime) {

        Queue<Process> queue = new LinkedList<>();

        for (int currentTime = 1; currentTime <= maxTime; currentTime++) {

            ProcessExecuter.execute(cpus);

            for (ProcessSender sender : processes) {

                if (sender.getFrequency() % currentTime == 0) {
                    Process currentProcess = sender.sendProcess();

                    tryToExecuteProcess(currentProcess, cpus, queue);
                }
            }

            boolean tryToExecuteProcessFromQueue = true;
            while (tryToExecuteProcessFromQueue) {
                if (queue.isEmpty()) tryToExecuteProcessFromQueue = false;
                else {
                    Process currentProcess = queue.poll();
                    tryToExecuteProcessFromQueue = tryToExecuteProcess(currentProcess, cpus, queue);
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


        if (currentProcess.getCurrentCPU().getLoad() < MAX_LOAD_MIGRATION) {
            CPU chosenCpu = currentProcess.getCurrentCPU();

            chosenCpu.getProcesses().add(currentProcess);
            chosenCpu.setLoad(chosenCpu.getLoad() + currentProcess.getLoad());

            return true;
        }

        boolean[] attempedCpus = new boolean[cpus.size()];
        int attemptsDone = 0;
        boolean foundCPU = false;

        while (attemptsDone < cpus.size() && !foundCPU) {
            int randomIndex;

            do {
                randomIndex = randomGenerator.nextInt(cpus.size());
            } while (attempedCpus[randomIndex]);

            CPU randomCPU = cpus.get(randomIndex);
            attempedCpus[randomIndex] = true;

            attemptsDone++;
            questions++;

            if (randomCPU.getLoad() < MAX_LOAD_MIGRATION) {
                foundCPU = true;
                randomCPU.getProcesses().add(currentProcess);
                randomCPU.setLoad(randomCPU.getLoad() + currentProcess.getLoad());
                currentProcess.setCurrentCPU(randomCPU);

                migrations++;
            }
        }

        if (!foundCPU){
            queue.add(currentProcess);
            return false;
        }

        return true;
    }
}
