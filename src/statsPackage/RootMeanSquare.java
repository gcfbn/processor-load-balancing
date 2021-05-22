package statsPackage;

public class RootMeanSquare {

    public static double calculate(double[] values){

        if (values.length < 1) throw new IllegalArgumentException();

        double result = 0;

        for (Double d : values){
            result += d*d;
        }

        result /= values.length;

        return Math.sqrt(result);
    }
}
