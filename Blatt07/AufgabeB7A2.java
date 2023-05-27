import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AufgabeB7A2 {
    public static void main(String[] args) {
        if(args.length == 0) {
            System.err.println("No Argument");
            return;
        }
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument must be a number");
            return;
        }
        if (k < 0) {
            System.err.println("Argument must be a Positiv number");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int[] weight;
        int[] value;
        try {
            value = getInput(sc);
        } catch (NumberFormatException e) {
            System.err.println("Input must be a Positiv number");
            return;
        }
        try {
            weight = getInput(sc);
        } catch (NumberFormatException e) {
            System.err.println("Input must be a Positiv number");
            return;
        }

        if(weight.length != value.length) {
            System.err.println("The number of values does not match the number of weights.");
            return;
        }

        if(sc.hasNext()) {
            System.err.println("Input did not end after second list.");
            return;
        }

        int[][] result = knapsack(value, weight, k);
        // for (int[] y : result) {
        //     System.out.println(Arrays.toString(y));
        // }
        System.out.println(result[value.length][k]);

    }
    public static int[] getInput(Scanner scanner) throws NumberFormatException{

        List<Integer> temp = new ArrayList<Integer>();
        while(scanner.hasNext()) {
            String s = scanner.nextLine();
            if (s == "") {
                break;
            }

            temp.add(Integer.parseInt(s));
        }
        int[] result = new int[temp.size()];
        for (int i = 0 ; i < result.length ; i++) {
            result[i] = temp.get(i);
        }
        return result;

    }

    public static int[][] knapsack(int[] values, int[] weights, int capacity){
        
        int [][] result = new int[values.length+1][capacity+1];

        for (int c = 1 ; c <= capacity ; c ++) {
            for (int i = 1 ; i <= values.length ; i ++) {
                
                if (weights[i-1] <= c) {
                    result[i][c] = Math.max(values[i-1] + result[i-1][c-weights[i-1]],result[i-1][c]);
                } else {
                    result[i][c] = result[i-1][c];
                }
            }
        }
        return result;
    }
}
