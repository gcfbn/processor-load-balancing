package resultsPackage;

public class Results {

    private final double averageLoad;
    private final double standardDeviation;
    private final int questions;
    private final int migrations;

    public Results(double averageLoad, double standardDeviation, int questions, int migrations) {
        this.averageLoad = averageLoad;
        this.standardDeviation = standardDeviation;
        this.questions = questions;
        this.migrations = migrations;
    }

    public double getAverageLoad() {
        return averageLoad;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public int getQuestions() {
        return questions;
    }

    public int getMigrations() {
        return migrations;
    }
}
