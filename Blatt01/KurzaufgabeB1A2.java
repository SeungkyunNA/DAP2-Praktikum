import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class KurzaufgabeB1A2 {
    public static void main(String[] args) {
       

        Scanner sc = new Scanner(System.in);
        List<Integer> ls = new ArrayList<Integer>();

        while (sc.hasNextLine()) {
            ls.add(Integer.parseInt(sc.nextLine()));
        }
        sc.close();

        // Creating and copying an array as many as the number entered
        int[] result = new int[ls.size()];
        
        for (int i = 0 ; i < ls.size() ; i ++) {
            result[i] = ls.get(i);
        }
        System.out.println("Es gibt " + printPermutations(result, 0) + " Permutationen der Eingabe");
    }

    public static int printPermutations(int[] arr , int d) {
        
        

        return 0;
    }
}