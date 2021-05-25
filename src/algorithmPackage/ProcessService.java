package algorithmPackage;

import systemPackage.CPU;
import systemPackage.Process;
import systemPackage.ProcessSender;

import java.util.ArrayList;
import java.util.Queue;

public class ProcessService {

    public static void service(Algorithm implementation, ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, Queue<Process> queue, int currentTime) {
        ProcessExecuter.execute(cpus);

        for (ProcessSender sender : processes) {

            if (sender.getFrequency() % currentTime == 0) {
                Process currentProcess = sender.sendProcess();

                implementation.tryToExecuteProcess(currentProcess, cpus, queue);
            }
        }

        boolean tryToExecuteProcessFromQueue = true;
        while (tryToExecuteProcessFromQueue) {
            if (queue.isEmpty()) tryToExecuteProcessFromQueue = false;
            else {
                Process currentProcess = queue.poll();
                tryToExecuteProcessFromQueue = implementation.tryToExecuteProcess(currentProcess, cpus, queue);
            }
        }
    }
}
