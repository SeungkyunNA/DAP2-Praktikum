import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class KurzaufgabeB1A1 {
    public static void main(String[] args) {
        
        // Check if the correct command is entered.
        if (args.length != 1) {
            System.out.println("missing command (e.g java Select \"3\")");
            return;
        }
        int target = -1;
        try {
            target = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("invaild command (e.g java Select \"3\")");
        }
        if (target < 1) {
            System.err.println("invaild command (e.g java Select \"3\")");
            return;
        }


        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        // With a while-loop, checking if an integer was entered.
        while (sc.hasNextLine()) {
            ls.add(Integer.parseInt(sc.nextLine())); 
        }
        sc.close();

        // Check if the correct boundaries are entered
        if (target > ls.size()) {
            System.out.println("The list must contain at least k=" + target 
            + " numbers, but only " + ls.size() + " were provided.");
            return;
        }

        // Creating and copying an array as many as the number entered
        int[] result = new int[ls.size()];
        
        for (int i = 0 ; i < ls.size() ; i ++) {
            result[i] = ls.get(i);
        }

        Arrays.sort(result);

        System.out.println("The " + args[0] + "-smallst value is " + result[target-1]);
    }
}
