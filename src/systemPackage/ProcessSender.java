package systemPackage;

public class ProcessSender {

    private final int frequency;
    private final int load;
    private final int duration;
    private final CPU firstChoiceCpu;

    public ProcessSender(int frequency, int load, int duration, CPU firstChoiceCpu) {
        this.frequency = frequency;
        this.load = load;
        this.duration = duration;
        this.firstChoiceCpu = firstChoiceCpu;
    }

    public Process sendProcess() {
        return new Process(load, duration, firstChoiceCpu);
    }

    public int getFrequency() {
        return frequency;
    }

    public int getLoad() {
        return load;
    }

    public int getDuration() {
        return duration;
    }

    public CPU getFirstChoiceCpu() {
        return firstChoiceCpu;
    }
}
