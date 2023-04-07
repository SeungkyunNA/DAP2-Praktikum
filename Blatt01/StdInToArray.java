import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class StdInToArray {

    public static void main(String[] args) {

        if (args.length == 2) {
            System.out.println("Enter at least one Number");
            return;
        }
        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            try {
                ls.add(Integer.parseInt(sc.next()));
            } catch (NumberFormatException e) {
                System.err.println("Es gibt ein Zahl, der nicht GanzenZahl ist.");
            }
            
        }
        sc.close();
        int[] result = new int[ls.size()];
        
        for (int i = 0 ; i < ls.size() ; i ++) {
            result[i] = ls.get(i);
        }

        System.out.println(Arrays.toString(result));
        
    }
}