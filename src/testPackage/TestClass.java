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

        System.out.println("Strategia 1:");
        System.out.println("Średnie obciążenie: " + results.getAverageLoad());
        System.out.println("Odchylenie standardowe: " + results.getStandardDeviation());
        System.out.println("Migracje: " + results.getMigrations());
        System.out.println("Zapytania: " + results.getQuestions());

        System.out.println();

        algorithm = new StrategyTwo();
        for (CPU cpu : cpus){
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

        algorithm = new StrategyThree();
        for (CPU cpu : cpus){
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
