package statsPackage;

public class StandardDeviation {

    public static double calculate(double[] values, double average) {

        if (values.length < 1) throw new IllegalArgumentException();

        double result = 0;

        for (Double d : values) {
            result += (d - average) * (d - average);
        }

        result /= values.length;

        return Math.sqrt(result);
    }
}
