import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.Instant;
import java.time.Duration;

class Quicksort {


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

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }

        Instant start = Instant.now();
        qsort(result);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("One Pivot Time: " + time);

        if (result.length < 20) {
            System.out.println(Arrays.toString(result));
        }
        assert(isSorted(result));

        double med = result[0] + result[result.length-1];

        System.out.println("Min : " + result[result.length-1] + ", Med : " + med/2 + ", Max : " + result[0]);
        
    }


    public static int partition(int[] data , int l , int r) {

        int pivot = data[l];
        int i = l;
        int j = l;
        int k = r;

        while(j <= k) {
            if (data[j] > pivot) {
                int save = data[i];
                data[i] = data[j];
                data[j] = save;
                i++;
                j++;

            } else if (data[j] < pivot) {
                int save = data[j];
                data[j] = data[k];
                data[k] = save;
                k--;

            } else {
                j++;
                while (j <= k && data[k] == pivot) {
                    k--;
                }
            }
        }
        return k;
    }

    public static void qsort(int[] data , int l , int r) {

        if (l < r) {
            int a = partition(data , l , r);
        
            qsort(data , l , a);
            qsort(data , a+1 , r);
        }

    }

    public static void qsort(int[] data) {

        qsort(data , 0 , data.length -1);
        
    }

    public static boolean isSorted(int[] data) {
        for (int i = 1 ; i < data.length ; i++) {
            if (data[i-1] < data[i]) {
                return false;
            }
        }   
        return true;
    }



}