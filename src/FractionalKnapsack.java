import java.math.BigDecimal;
import java.util.*;

public class FractionalKnapsack {

    public static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here

        double[][] unitValues = new double[values.length][2];
        for(int i = 0; i < values.length; i++) {
            unitValues[i][0] = (values[i] * 1.00)/(weights[i] * 1.00);
            unitValues[i][1] = weights[i];
        }
        Arrays.sort(unitValues, (Comparator<double[]>) (o1, o2) -> Double.compare(o2[0],o1[0]));

        int unitIndex = 0;
        while (capacity > 0 && unitIndex < unitValues.length) {
            if (capacity >= unitValues[unitIndex][1]) {
                value += unitValues[unitIndex][0] * unitValues[unitIndex][1];
                capacity -=  unitValues[unitIndex][1];
            } else {
               value += unitValues[unitIndex][0] * capacity;
               capacity = 0;
            }
           unitIndex++;
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
