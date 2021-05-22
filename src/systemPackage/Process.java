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
}
