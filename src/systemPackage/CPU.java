package systemPackage;

import java.util.ArrayList;

public class CPU {

    private final ArrayList<Process> processes = new ArrayList<>();
    private int load = 0;
    private final double[] loadsInTime;

    public CPU(int simulationLength) {
        loadsInTime = new double[simulationLength];
    }
}
