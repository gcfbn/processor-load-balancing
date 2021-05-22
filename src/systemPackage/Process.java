package systemPackage;

public class Process {

    private final int load;
    private CPU currentCPU;
    private int timeLeft;

    public Process(int load, int duration, CPU currentCPU) {
        this.load = load;
        this.currentCPU = currentCPU;
        this.timeLeft = duration;
    }

    public int getLoad() {
        return load;
    }

    public CPU getCurrentCPU() {
        return currentCPU;
    }

    public void setCurrentCPU(CPU currentCPU) {
        this.currentCPU = currentCPU;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
