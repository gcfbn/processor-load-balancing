package systemPackage;

import statsPackage.RootMeanSquare;

import java.util.ArrayList;

public class CPU {

    private final ArrayList<Process> processes = new ArrayList<>();
    private int load = 0;
    private final double[] loadsInTime;

    public CPU(int simulationLength) {
        loadsInTime = new double[simulationLength];
    }

    public void clearLoad() {
        load = 0;
    }

    public void clearProcesses() {
        processes.clear();
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public double[] getLoadsInTime() {
        return loadsInTime;
    }

    public double getAverageLoad() {
        return RootMeanSquare.calculate(loadsInTime);
    }
}
