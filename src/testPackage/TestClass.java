package testPackage;

import algorithmPackage.Algorithm;
import algorithmPackage.StrategyOne;
import algorithmPackage.StrategyThree;
import algorithmPackage.StrategyTwo;
import generatorPackage.ProcessGenerator;
import resultsPackage.Results;
import systemPackage.CPU;
import systemPackage.ProcessSender;

import java.util.ArrayList;

public class TestClass {

    private static final int NUMBER_OF_CPUS = 50;
    private static final int MAX_TIME = 10000;

    private static final int MIN_FREQUENCY = 3;
    private static final int MAX_FREQUENCY = 10;
    private static final int MAX_SINGLE_LOAD = 20;
    private static final int MIN_DURATION = 10;
    private static final int MAX_DURATION = 50;
    private static final int NUMBER_OF_PROCESSES = 4500;

    private static final int PARAMETER_P = 40;
    private static final int PARAMETER_Z = 3;
    private static final int PARAMETER_R = 15;
    private static final int STRAT_THREE_TRIES = 5;

    public static void main(String[] args) {

        ArrayList<CPU> cpus = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_CPUS; i++) {
            cpus.add(new CPU(MAX_TIME));
        }

        var processGenerator =
                new ProcessGenerator(MIN_FREQUENCY, MAX_FREQUENCY, MAX_SINGLE_LOAD, MIN_DURATION, MAX_DURATION, NUMBER_OF_PROCESSES);

        ArrayList<ProcessSender> senders = processGenerator.generateData(cpus);

        Algorithm algorithm;
        Results results;

        algorithm = new StrategyOne(PARAMETER_P, PARAMETER_Z);
        results = algorithm.executeAlgorithm(cpus, senders, MAX_TIME);

        System.out.println("Strategia 1:");
        System.out.println("Średnie obciążenie: " + results.getAverageLoad());
        System.out.println("Odchylenie standardowe: " + results.getStandardDeviation());
        System.out.println("Migracje: " + results.getMigrations());
        System.out.println("Zapytania: " + results.getQuestions());

        System.out.println();

        algorithm = new StrategyTwo(PARAMETER_P);
        for (CPU cpu : cpus) {
            cpu.clearLoad();
            cpu.clearProcesses();
        }

        results = algorithm.executeAlgorithm(cpus, senders, MAX_TIME);

        System.out.println("Strategia 2:");
        System.out.println("Średnie obciążenie: " + results.getAverageLoad());
        System.out.println("Odchylenie standardowe: " + results.getStandardDeviation());
        System.out.println("Migracje: " + results.getMigrations());
        System.out.println("Zapytania: " + results.getQuestions());

        System.out.println();

        algorithm = new StrategyThree(PARAMETER_P, PARAMETER_R, STRAT_THREE_TRIES);
        for (CPU cpu : cpus) {
            cpu.clearLoad();
            cpu.clearProcesses();
        }

        results = algorithm.executeAlgorithm(cpus, senders, MAX_TIME);

        System.out.println("Strategia 3:");
        System.out.println("Średnie obciążenie: " + results.getAverageLoad());
        System.out.println("Odchylenie standardowe: " + results.getStandardDeviation());
        System.out.println("Migracje: " + results.getMigrations());
        System.out.println("Zapytania: " + results.getQuestions());
    }
}
