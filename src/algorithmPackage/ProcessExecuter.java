package algorithmPackage;

import systemPackage.CPU;
import systemPackage.Process;

import java.util.ArrayList;

public class ProcessExecuter {

    public static void execute(ArrayList<CPU> cpus) {

        for (CPU cpu : cpus) {
            int i = 0;
            while (i < cpu.getProcesses().size()) {

                Process currentProcess = cpu.getProcesses().get(i);

                currentProcess.setTimeLeft(currentProcess.getTimeLeft() - 1);

                if (currentProcess.getTimeLeft() == 0)
                    cpu.getProcesses().remove(currentProcess);

                else i++;
            }
        }
    }
}
