package algorithmPackage;

import resultsPackage.Results;
import systemPackage.CPU;
import systemPackage.Process;
import systemPackage.ProcessSender;

import java.util.ArrayList;
import java.util.Queue;

public interface Algorithm {

    Results executeAlgorithm(ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, int maxTime);
    boolean tryToExecuteProcess(Process currentProcess, ArrayList<CPU> cpus, Queue<Process> queue);
}
