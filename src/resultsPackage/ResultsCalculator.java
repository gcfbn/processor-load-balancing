package resultsPackage;

import statsPackage.RootMeanSquare;
import statsPackage.StandardDeviation;

public class ResultsCalculator {

    public static Results calculate(double[] loads, int questions, int migrations){

        double averageLoad = RootMeanSquare.calculate(loads);
        double standardDeviation = StandardDeviation.calculate(loads, averageLoad);

        return new Results(averageLoad, standardDeviation, questions, migrations);
    }
}
