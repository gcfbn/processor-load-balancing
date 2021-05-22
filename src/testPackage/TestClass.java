package testPackage;

import algorithmPackage.Algorithm;
import algorithmPackage.StrategyOne;
import generatorPackage.ProcessGenerator;
import resultsPackage.Results;
import systemPackage.CPU;
import systemPackage.ProcessSender;

import java.util.ArrayList;

public class TestClass {

    private static final int NUMBER_OF_CPUS = 50;
    private static final int MAX_TIME = 10000;

    public static void main(String[] args) {

        ArrayList<CPU> cpus = new ArrayList<>();

        for (int i=0; i<NUMBER_OF_CPUS; i++){
            cpus.add(new CPU(MAX_TIME));
        }

        ArrayList<ProcessSender> senders = ProcessGenerator.generateData(cpus);

        Algorithm algorithm;
        Results results;

        algorithm = new StrategyOne();
        results = algorithm.executeAlgorithm(cpus, senders, MAX_TIME);

        System.out.println("Average load: " + results.getAverageLoad());
        System.out.println("Standard deviation: " + results.getStandardDeviation());
        System.out.println("Migrations: " + results.getMigrations());
        System.out.println("Questions: " + results.getQuestions());
    }

}
