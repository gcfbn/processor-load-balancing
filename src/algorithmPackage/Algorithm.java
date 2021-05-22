package algorithmPackage;

import resultsPackage.Results;
import systemPackage.CPU;
import systemPackage.ProcessSender;

import java.util.ArrayList;

public interface Algorithm {

    Results executeAlgorithm(ArrayList<CPU> cpus, ArrayList<ProcessSender> processes, int maxTime);
}
