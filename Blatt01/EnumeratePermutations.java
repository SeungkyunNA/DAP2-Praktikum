import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class EnumeratePermutations {
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
        System.out.println(ls.size() + "! = " + fac(ls.size()));
    }

    public static int printPermutations(int[] arr , int d) {

        if (d == 0) {

            int count = 0;

            for (int i = 0 ; i < arr.length ; i ++) {
                int save = arr[0];
                for (int j = 1 ; j < arr.length ; j++) {
                    arr[j - 1] = arr[j];
                }
                arr[arr.length-1] = save;
                count += printPermutations(arr, 1);
            }

            return count;
        }

        if (d == arr.length -1) {
            System.out.println(Arrays.toString(arr));
            return 1;
        } else {
            int count = 0;
            for (int i = d ; i < arr.length ; i ++) {
                int save = arr[d];
                for (int j = d + 1 ; j < arr.length ; j++) {
                    arr[j - 1] = arr[j];
                }
                arr[arr.length-1] = save;
                count += printPermutations(arr, d + 1);
            }
            return count;
        }

    }

    public static int fac(int a) {
        if (a == 0) {
            return 1;
        }
        return a * fac(a-1);
        
    }
}